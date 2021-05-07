@echo off
echo "location actuel du .bat"
echo %~dp0
echo "parametre : "
echo %1
cd %~dp0
java --module-path "..\..\..\..\.jdk\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media -jar scriber.jar %1
pause