
@echo off

:: Clean Project
call clean.bat

:: Compile Project (Targets Sent to "build" Directory)
javac -d build src\*.java src\simulation\*.java src\ui\*.java src\utility\*.java

:: Print Update
echo Project compiled.
echo Running project.

:: Run Project (Binaries Read from "build" Directory)
java -cp build Main