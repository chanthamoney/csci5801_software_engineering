Project 1 Task 3
Team 14

Please see the index page of the Javadocs documentation in the documentation folder.

Compilation

To get started open the Terminal in the src directory. If it is the first time this program has been run on your machine, run the command:
> \>\> javac main/\*.java votingsystems/\*.java mariahgui/\*.java auditor/\*.java

Basic Run
To run the compiled application use the command:
> \>\> java main/MariahEP

Run with File
You have the option of running the application with a pre-defined file:
> \>\> java main/MariahEP <election file>

Run without GUI
You have the option of running the application without the use of the GUI. When entering the election file you can either drag the file to the terminal or enter the absolute path. 
Simply set the --no-gui flag on the command line:
> \>\> java main/MariahEP --no-gui <election file>

System Testers,
To compile tests use the following:
> \>\> javac -cp <JUNIT Jar> main/\*.java votingsystems/\*.java auditor/\*.java mariahgui/\*.java test/main/\*.java test/votingsystems/\*.java test/auditor/\*.java

NOTE: Tests are dependent on the structure of how directories are positioned. Many tests for IRV, OPLV, and the main driver (MariahEP) require files that are in testing/OPLV or testing/IRV.
