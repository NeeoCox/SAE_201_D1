@echo off

REM Exécution de l'application
java --module-path ../lib/mysql/mysql-connector-j-9.3.0.jar;../lib/javafx --add-modules javafx.controls,javafx.graphics,javafx.fxml -cp ../fxml;../class view.App

pause