# Bash-Terminal

## Overview 
1. Utilized Java programming language and Linux command line knowledge to develop a Bash Terminal
2. Implemented various executable Linux commands such as pwd, ls, find, mv, cd, mkdir, touch and exit
3. Designed a ternary tree data structure to ensure project speed and flexibility

## How to Run
1. Make sure you have Haskell installed on your system.
2. Clone the repository and navigate to the project's directory.
3. Compile the Java source code into bytecode, use the javac command followed by the name of the Java file.
4. Once the compilation is successful, use the java command followed by the name of the main class to execute the Java program.

## Functions
### resetCursor()
Sets the cursor to the root of the directory tree.
### changeDirectory(String name)
Changes the cursor to the specified directory with the given name. 
#### Parameters:
1. name: The name of the directory to change to.
### moveMe(String src, String dest, String[] srcArr, String[] destArr)
Moves a file or directory from one location to another. 
#### Parameters:
1. src: The name of the file or directory to be moved.
2. dest: The destination directory to move the file or directory to.
3. srcArr: An array containing the path components of the source directory.
4. destArr: An array containing the path components of the destination directory.
### moveToParent()
Moves the cursor to the parent directory of the current directory.
### findMe(String name)
Finds the path of the specified directory. 
#### Parameters:
1. name: The name of the directory to find.
2. Returns: The path of the directory.
### presentWorkingDirectory()
Gets the path of the current directory that the cursor is on.
1. Returns: The path of the current directory.
### listDirectory()
Lists the directories and files in the current directory.
1. Returns: A space-separated string containing the names of directories and files in the current directory.
### makeDirectory(String name)
Creates a new directory in the current directory. 
#### Parameters:
1. name: The name of the directory to create.
### makeFile(String name)
Creates a new file in the current directory. 
#### Parameters:
1. name: The name of the file to create.
### printDirectoryTree()
Prints the directory tree starting from the current directory.
