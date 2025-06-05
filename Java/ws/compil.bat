<<<<<<< HEAD
@echo off

set FX="C:\Users\maelc\Desktop\Is_All\BUTnfo\Semestre_2\SAE\SAE2_01\SAE_201_D1\java\javafx-sdk-21.0.7\lib"
=======

javac -d ../class -sourcepath ../src ../src/metier/persistence/*.java ../src/metier/service/*.java
>>>>>>> 2ed2943cdf851ffc3cfbaad7f0687a5319cf2818

javac --module-path %FX% --add-modules javafx.controls,javafx.graphics,javafx.fxml -sourcepath ../src -d ../class ../src/view/App.java

java --module-path %FX% --add-modules javafx.controls,javafx.graphics,javafx.fxml -cp ../fxml;../class view.App

pause
