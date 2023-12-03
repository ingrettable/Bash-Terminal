/**
 * A replication of a terminal with linux commands. The directories and files are stored using a tree.
 * This class contains the exception DirectoryNotFoundException, which is thrown when the user searches for a directory that does not exist.
 */

@SuppressWarnings("serial")
public class DirectoryNotFoundException extends Exception {
	public DirectoryNotFoundException() {
		
	}
}
