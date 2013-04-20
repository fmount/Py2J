#!/bin/sh
#Script per compilare tutte le classi Java prodotte dal traduttore..
echo "Start.."
work_dir=~/workspace/TraduttorePy2J/test/
echo "Cleaning directory"
rm -rf ~/workspace/TraduttorePy2J/test/classes
rm ~/workspace/TraduttorePy2J/test/*.java
echo "Executing Translator"
java -jar ~/workspace/TraduttorePy2J/Translator.jar
echo "Working into: " ${work_dir}
echo "Copying support classes"
cp ~/workspace/TraduttorePy2J/src/models/*.java ~/workspace/TraduttorePy2J/test/
cp ~/workspace/TraduttorePy2J/src/symbol/*.java ~/workspace/TraduttorePy2J/test/
echo "#Compiling sources"
echo "Sending command: javac ~/workspace/TraduttorePy2J/test/*.java"
javac ~/workspace/TraduttorePy2J/test/*.java
echo "#Moving sources"
mkdir ~/workspace/TraduttorePy2J/test/classes 
mv ~/workspace/TraduttorePy2J/test/*.class ~/workspace/TraduttorePy2J/test/classes
echo "NOW YOU CAN EXEC YOUR APP! :D"
