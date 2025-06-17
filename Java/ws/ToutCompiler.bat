@echo off

javac --module-path ../lib/javafx --add-modules javafx.controls,javafx.graphics,javafx.fxml -sourcepath ../src -d ../class ../src/controller/*.java
javac -d ../class -sourcepath ../src ../src/model/dao/*.java
javac -d ../class -sourcepath ../src ../src/model/graph/*.java
REM javac -d ../class -sourcepath ../src ../src/graph/algorithm/*.java
REM javac -d ../class -sourcepath ../src ../src/graph/graphmodel/*.java
javac -d ../class -sourcepath ../src ../src/model/persistence/*.java
javac -d ../class -sourcepath ../src ../src/model/service/*.java
javac --module-path ../lib/javafx --add-modules javafx.controls,javafx.graphics,javafx.fxml -sourcepath ../src -d ../class ../src/view/App.java

java --module-path ../lib/javafx --add-modules javafx.controls,javafx.graphics,javafx.fxml -cp ../fxml;../class view.App

pause