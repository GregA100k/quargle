rem  read in a test file and write out a submission file

echo %1
echo %2
lein run train %1 %2
