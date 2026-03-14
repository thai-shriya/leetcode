#!/usr/bin/env python3
"""Fetch recent LeetCode submissions and create backdated git commits."""

import json
import time
import sys
import os
import subprocess
from urllib.request import Request, urlopen
from urllib.error import HTTPError
from datetime import datetime, timezone
import ssl

LOG_FILE = "leetcode_log.md"

def run_git(args, env=None):
    """Run a git command and return output."""
    result = subprocess.run(
        ["git"] + args,
        capture_output=True, text=True,
        env={**os.environ, **(env or {})}
    )
    if result.returncode != 0:
        print(f"  ❌ git {' '.join(args[:2])} failed: {result.stderr.strip()}")
        return None
    return result.stdout.strip()

def fetch_recent_submissions(session_cookie, csrf_token):
    """Fetch recent submissions from LeetCode."""
    submissions = []
    offset = 0
    limit = 20
    ctx = ssl.create_default_context()
    
    # Read existing log to avoid duplicate problem commits
    existing_titles = set()
    if os.path.exists(LOG_FILE):
        with open(LOG_FILE, "r") as f:
            for line in f:
                if "Solved: " in line:
                    # Parse out "- [2024-01-15] Solved: Two Sum (java)" -> "Two Sum"
                    parts = line.split("Solved: ")
                    if len(parts) > 1:
                        title_lang = parts[1].rsplit(" (", 1)
                        if len(title_lang) == 2:
                            existing_titles.add(title_lang[0].strip())

    print(f"📄 Found {len(existing_titles)} previously solved problems in {LOG_FILE}.")

    # Fetch up to ~100 latest submissions looking for new ones
    while offset < 100:
        url = f"https://leetcode.com/api/submissions/?offset={offset}&limit={limit}"
        req = Request(url)
        req.add_header("Cookie", f"LEETCODE_SESSION={session_cookie}; csrftoken={csrf_token}")
        req.add_header("X-CSRFToken", csrf_token)
        req.add_header("Referer", "https://leetcode.com")
        req.add_header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36")

        try:
            with urlopen(req, context=ctx) as resp:
                data = json.loads(resp.read().decode())
        except HTTPError as e:
            print(f"❌ HTTP Error fetching submissions: {e.code}")
            sys.exit(1)
        except Exception as e:
            print(f"❌ Connection Error: {e}")
            sys.exit(1)

        dumps = data.get("submissions_dump", [])
        if not dumps:
            break

        for sub in dumps:
            if sub["status_display"] == "Accepted":
                title = sub["title"]
                if title not in existing_titles:
                    submissions.append({
                        "title": title,
                        "timestamp": sub["timestamp"],
                        "lang": sub["lang"],
                        "date": datetime.fromtimestamp(sub["timestamp"], tz=timezone.utc).strftime("%Y-%m-%d"),
                    })
                    # Mark as seen so we don't add duplicates
                    existing_titles.add(title)

        has_next = data.get("has_next", False)
        if not has_next:
            break

        offset += limit
        time.sleep(2) # Rate limit protection

    return submissions

def main():
    session = os.environ.get("LEETCODE_SESSION")
    csrf = os.environ.get("LEETCODE_CSRF_TOKEN")
    
    if not session or not csrf:
        print("❌ Missing LEETCODE_SESSION or LEETCODE_CSRF_TOKEN environment variables.")
        sys.exit(1)
        
    print("📡 Fetching recent submissions...")
    subs = fetch_recent_submissions(session, csrf)
    
    if not subs:
        print("✅ No new accepted submissions found. Everything is up to date!")
        return
        
    # Process oldest first for correct chronological git history
    subs.sort(key=lambda x: x["timestamp"])
    print(f"\n🚀 Found {len(subs)} new unique submission(s) to commit.")
    
    for sub in subs:
        title = sub["title"]
        ts = sub["timestamp"]
        lang = sub["lang"]
        date_str = sub["date"]
        
        # ISO 8601 format for git dates
        dt = datetime.fromtimestamp(ts, tz=timezone.utc)
        git_date = dt.strftime("%Y-%m-%dT%H:%M:%S+00:00")
        
        with open(LOG_FILE, "a") as f:
            f.write(f"- [{date_str}] Solved: {title} ({lang})\n")
            
        run_git(["add", LOG_FILE])
        
        # Override git environment variables to backdate commits and assign authors
        env = {
            "GIT_AUTHOR_DATE": git_date,
            "GIT_COMMITTER_DATE": git_date,
            "GIT_AUTHOR_EMAIL": "thaishriya@gmail.com",
            "GIT_COMMITTER_EMAIL": "thaishriya@gmail.com",
            "GIT_AUTHOR_NAME": "thai-shriya",
            "GIT_COMMITTER_NAME": "thai-shriya"
        }
        
        commit_msg = f"Solved: {title}"
        result = run_git(["commit", "-m", commit_msg], env=env)
        
        if result is None:
            print(f"  ⚠️ Failed to commit: {title}")
        else:
            print(f"  ✅ Committed {date_str} — {title}")
            
    print("\n✅ New submissions successfully committed line by line.")
    print("Pushing to remote...")
    
    # We use GITHUB_TOKEN directly in workflow, push is simple
    push_result = run_git(["push"])
    if push_result is not None:
        print("🚀 Successfully pushed to remote!")
    else:
        print("❌ Push failed!")

if __name__ == "__main__":
    main()
