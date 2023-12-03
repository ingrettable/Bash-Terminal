/**
 * A replication of a terminal with linux commands. The directories and files are stored using a tree.
 * This class contains the exception NotADirectoryException, which is thrown when a user tries to add a child to a file.
 */
@SuppressWarnings("serial")
public class NotADirectoryException extends Exception {
	
	public NotADirectoryException() {
		
	}

}
