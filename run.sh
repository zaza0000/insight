#!/bin/bash
if [[ ! -d "class" ]]; then
    mkdir class
fi
echo "Compiling Consumer_Complaints_JZ project.."
javac -d class ./src/*.java
echo "run project.."
java -classpath ./class Main
