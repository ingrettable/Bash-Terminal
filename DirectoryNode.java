/**
 * A replication of a terminal with linux commands. The directories and files are stored using a tree.
 * This class contains the methods in order to create a directory node.
 */

public class DirectoryNode {
	private String name;
	private DirectoryNode left;
	private DirectoryNode right;
	private DirectoryNode middle;
	private boolean isFile;
	
	/**
	 * creates a directory node
	 */
	public DirectoryNode() {
		
	}
	
	/**
	 * @param name of the directory node being created with this constructor
	 */
	public DirectoryNode(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name of the directory being created 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name sets the node name to the parameter
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the left node
	 */
	public DirectoryNode getLeft() {
		return left;
	}
	
	/**
	 * @return the right node
	 */
	public DirectoryNode getRight() {
		return right;
	}
	
	/**
	 * @return the middle node
	 */
	public DirectoryNode getMiddle() {
		return middle;
	}
	
	/**
	 * @return if node is a file
	 */
	public boolean getIsFile() {
		return isFile;
	}
	
	/**
	 * @param isFile to set whether or not node is a file
	 */
	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}

	/**
	 * @param newChild to be added to current node
	 * @throws FullDirectoryException when the current node already has three kids
	 * @throws NotADirectoryException when user tries to add node to a file
	 */
	public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException {
		if (this.isFile == true) {
			throw new NotADirectoryException();
		}
		if (left == null) {
			left = newChild;
			return;
		}
		if (middle == null) {
			middle = newChild;
			return;
		}
		if (right == null) {
			right = newChild;
			return;
		}
		else {
			throw new FullDirectoryException();
		}
	}
	
	public void removeChild(String where)  {
		if (where.equals("left")) {
			left = null;
			return;
		}
		if (where.equals("middle")) {
			middle = null;
			return;
		}
		if (where.equals("right")) {
			right = null;
			return;
		}
	}
}
