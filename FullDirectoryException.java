/**
 * A replication of a terminal with linux commands. The directories and files are stored using a tree.
 * This class contains the exception FullDirectoryException, which is thrown when a user tries to add more nodes to a directory with three children.
 */
@SuppressWarnings("serial")
public class FullDirectoryException extends Exception {
	
	public FullDirectoryException() {
		
	}

}
