# object_serialisation_7ld
The given program performs the following actions:
• Outputs an on-screen menu containing five commands: Create, Calculate, View, About and Exit and
enter the command number from the keyboard.
• If the user selects the Create command, the program creates a file Students.dat (in the current folder)
and writes five objects of class Student to the given file.
• If the user selects the View command, the program displays the contents of the Students.dat file on the screen.
• If the user selects the Exit command, the program ends its work.
• If the user selects the Calculate or About command, the program calls the Main method of the class
calculate or the about method.
In the calculate method, the student number must be entered from the keyboard (assuming that there are students
numbered from one) and modifies the grades of the student given in the Students.dat file. New marks are entered from the keyboard. If a negative student number was entered or the number exceeds number of students in the file, then the program outputs the statement "no such student".
• The about method outputs information about the program developer.
