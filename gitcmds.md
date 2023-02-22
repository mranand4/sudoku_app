git pull // pulls the latest content of this branch from remote
git pull origin <branch-name> // pulls content of some other branch from remote
git add <exact-file-name-with-path> // adds a file to git to track
git add . // adds all the files
git add "app/_" // adds all the files starting with app/
git restore <exact-file-name-with-path> // removes file from tracking
git restore . // removes all files from tracking
git restore "app/_" // you get the idea right ?
git restore --staging <exact-file-name-with-path> // restores this file from staging area, same can be done with other patterns.
git commit -m "..." // commits the files in the staging area with the given message.
git commit --ammend // edit the last commit message
git push // pushes the changes to remote
git checkout <branch-name> // checks out to branch with specified name.
git checkout -b <branch-name> // creates branch and checks out to it.
git checkout <commit-hash> // checks out to commit with particular hash

NOTE : PRESS TAB to autocomplete git commands and names.
