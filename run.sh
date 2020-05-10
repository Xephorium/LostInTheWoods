#!/bin/bash

# Clean Project
./clean.sh

# Compile Project (Targets Sent to "build" Directory)
javac -d build src/*.java src/simulation/*.java src/ui/*.java src/ui/utility/*.java src/ui/res/*.java

# Print Update
echo Project compiled.
echo Running project.

# Run Project (Binaries Read from "build" Directory)
java -cp build Main
