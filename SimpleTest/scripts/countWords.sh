#!/bin/bash
# Usage: ./scripts/countWords simple.txt

FILE_DIR="$(pwd)/src/test/resources/sampleFiles/$1"

echo $FILE_DIR

ant run -Darg0=$FILE_DIR -Darg1="$(pwd)/src/test/resources"
cp -r build/output/ ../webapp/app/views/
CURR_DIR=$(pwd)
cd ../webapp
gulp serve:dist
cd $CURR_DIR
