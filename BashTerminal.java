/**
 * A replication of a terminal with linux commands. The directories and files are stored using a tree.
 * This class contains the main method which manages I/O of the bash terminal.
 */

import java.util.Scanner;

public class BashTerminal {
	static String errorPrint;
	/**
	 * @param args
	 * @throws IllegalArgumentException when files and directories contain illegal characters
	 * @throws FullDirectoryException when all the leaves of the directory are full (three leaves)
	 * @throws NotADirectoryException thrown when one tries to add leaves to a file
	 * @throws DirectoryNotFoundException thrown when one tries to search for a directory that does not exist
	 */
	public static void main(String[] args) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException, DirectoryNotFoundException{
		DirectoryNode root = new DirectoryNode("root");
		DirectoryTree tree = new DirectoryTree(root);
		System.out.println("Starting bash terminal.");
		Scanner input = new Scanner(System.in);
		boolean running = true;
		
		while (running) {
			try {
			System.out.print("[ghalollari@host]: $");
			String answer = input.nextLine();
			String[] answerArr = answer.trim().split(" ");
			if (answerArr[0].contains("pwd")) {
				System.out.println(tree.presentWorkingDirectory());
			}
			if (answerArr[0].contains("ls") && answerArr.length == 1) {
				System.out.println(tree.listDirectory());
			}
			if (answerArr[0].contains("ls") && answerArr.length > 1) {
				tree.printDirectoryTree();
			}
			if (answerArr[0].contains("find") && answerArr.length > 1) {
				String print = tree.findMe(answerArr[1]);
				System.out.println(print);
			}
			if (answerArr[0].contains("mv") && answerArr.length > 2) {
				tree.resetCursor();
				String array[] = answerArr[1].trim().split("/");
				String array2[] = answerArr[2].trim().split("/");
				int index = array.length;
				int index2 = array2.length;
				//System.out.println(array[index - 1]);
				//System.out.println(array2[index2-1]);
				tree.moveMe(array[index - 1], array2[index2-1], array, array2);
			}
			if (answerArr[0].contains("cd") && answerArr[1].contains("/") && !answerArr[1].equals("/")) {
				tree.resetCursor();
				String array[] = answerArr[1].trim().split("/");
				errorPrint = answerArr[1];
				int index = array.length;
				for (int i = 1; i < index; i++) {
					//System.out.println(array[i]);
					tree.changeDirectory(array[i]);
				}
			}
			if (answerArr[0].contains("cd") && answerArr[1].equals("..")) {
				tree.moveToParent();
			}
			if (answerArr[0].contains("cd") && !answerArr[1].equals("/") && !answerArr[1].equals("..") && !answerArr[1].contains("/")) {
				errorPrint = answerArr[1];
				tree.changeDirectory(answerArr[1]);
			}
			if (answerArr[0].contains("cd") && answerArr[1].equals("/")) {
				tree.resetCursor();
			}
			if (answerArr[0].contains("mkdir")) {
				tree.makeDirectory(answerArr[1]);
			}
			if (answerArr[0].contains("touch")) {
				tree.makeFile(answerArr[1]);
			}
			if (answerArr[0].contains("exit")) {
				System.out.println("Program terminating normally");
				running = false;
			}
			}  catch (FullDirectoryException e) {
				System.out.println("ERROR: Present directory is full.");
			} catch (NotADirectoryException e) {
				System.out.println("EERROR: Cannot change directory into a file.");
			} catch (DirectoryNotFoundException e) {
				System.out.println("ERROR: No such directory named '" + errorPrint + "'.");
			} catch (Exception e) {
				System.out.println("ERROR: Illegal entry, try again.");
			}
			
		}
		
		input.close();
		
	}


}
