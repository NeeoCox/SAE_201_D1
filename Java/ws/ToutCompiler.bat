@echo off

REM Compilation des sources Java
javac --module-path ../lib/javafx --add-modules javafx.controls,javafx.graphics,javafx.fxml -sourcepath ../src -d ../class ../src/controller/*.java

javac -d ../class -sourcepath ../src ../src/model/dao/*.java

javac -d ../class -sourcepath ../src ../src/model/persistence/*.java

javac -d ../class -sourcepath ../src ../src/model/graph/algorithm/*.java
javac -d ../class -sourcepath ../src ../src/model/graph/algorithm/dag/*.java
javac -d ../class -sourcepath ../src ../src/model/graph/algorithm/exhaustive/*.java
javac -d ../class -sourcepath ../src ../src/model/graph/algorithm/greedy/*.java
javac -d ../class -sourcepath ../src ../src/model/graph/graphmodel/*.java


javac -d ../class -sourcepath ../src ../src/model/service/*.java

javac --module-path ../lib/javafx --add-modules javafx.controls,javafx.graphics,javafx.fxml -sourcepath ../src -d ../class ../src/view/App.java

REM Exécution de l'application
java --module-path ../lib/mysql/mysql-connector-j-9.3.0.jar;../lib/javafx --add-modules javafx.controls,javafx.graphics,javafx.fxml -cp ../fxml;../class view.App

pause