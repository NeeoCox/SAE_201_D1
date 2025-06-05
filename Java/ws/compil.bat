@echo off

set FX="C:\Users\maelc\Desktop\Is_All\BUTnfo\Semestre_2\SAE\SAE2_01\SAE_201_D1\java\lib\javafx"

javac --module-path %FX% --add-modules javafx.controls,javafx.graphics,javafx.fxml -sourcepath ../src -d ../class ../src/view/App.java

java --module-path %FX% --add-modules javafx.controls,javafx.graphics,javafx.fxml -cp ../fxml;../class view.App

pause
