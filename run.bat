
@echo off

:: Clean Project
call clean.bat

:: Compile Project (Targets Sent to "build" Directory)
javac -d build src\Main.java src\LostWoods.java src\Explorer.java

:: Print Update
echo Project compiled.
echo Running project.

:: Run Project (Binaries Read from "build" Directory)
java -cp build Main