/**
 * A replication of a terminal with linux commands. The directories and files are stored using a tree.
 * This class contains the methods in order to create a tree of directories.
 */
public class DirectoryTree {
	private DirectoryNode root;
	private DirectoryNode cursor;
	
	public DirectoryTree()  {
		
	}
	/**
	 * @param root creates a tree with the reference root and sets the cursor equal to root
	 */
	public DirectoryTree(DirectoryNode root)  {
		this.root = root;
		this.cursor = root;
	}
	
	/**
	 * sets the cursor equal to the root
	 */
	public void resetCursor() {
		this.cursor = root;
	}
	
	/**
	 * @param name the new directory that the user wants the cursor to reference
	 * @throws NotADirectoryException thrown when user tries to reference a directory that is actually a file
	 * @throws DirectoryNotFoundException thrown when user enters a directory that does not exist
	 */
	public void changeDirectory(String name) throws NotADirectoryException, DirectoryNotFoundException {
		DirectoryNode pointer = this.cursor;
		boolean running = true;
		while (running) {
			//System.out.println(pointer.getName());
			DirectoryNode check = pointer;
				if (pointer.getLeft() != null) {
					pointer = pointer.getLeft();
					if (pointer.getName().equals(name)) {
						if (!pointer.getIsFile()) {
							this.cursor = pointer;
							//System.out.println(this.cursor.getName());
							return;
						}
						else {
							throw new NotADirectoryException();
						}
					}
					pointer = this.cursor;
				}
				if (pointer.getMiddle() != null) {
					pointer = pointer.getMiddle(); 
					if (pointer.getName().equals(name)) {
						if (!pointer.getIsFile()) {
							this.cursor = pointer;
							//System.out.println(this.cursor.getName());
							return;
						}
						else {
							throw new NotADirectoryException();
						}
					}
					pointer = this.cursor;
					}
				if (pointer.getRight() != null) {
					pointer = pointer.getRight(); 
					if (pointer.getName().equals(name)) {
						if (!pointer.getIsFile()) {
							this.cursor = pointer;
							//System.out.println(this.cursor.getName());
							return;
						}
						else {
							throw new NotADirectoryException();
						}
					}
					pointer = this.cursor;
					}
				if (check.equals(pointer)) {
					running = false;
				
			}
		}
		throw new DirectoryNotFoundException();
	}
	
	/**
	 * @param src of which file to move
	 * @param dest of where to move
	 * @throws Exception when could not move
	 */
	public void moveMe(String src, String dest, String[] srcArr, String[] destArr) throws Exception {
		DirectoryNode remove = this.root;
		boolean running = true;
		while (running) {
			if (remove.getLeft() != null && remove.getLeft().getName().equals(src)) {
				remove = remove.getLeft();
				running = false;
			}
			else if (remove.getMiddle() != null && remove.getMiddle().getName().equals(src)) {
				remove = remove.getMiddle();
				running = false;
			}
			else if (remove.getRight() != null && remove.getRight().getName().equals(src)) {
				remove = remove.getRight();
				running = false;
			}
			else {
				if (remove.getLeft() != null) {
					remove = remove.getLeft();
				}
				else if (remove.getMiddle() != null) {
					remove = remove.getMiddle();
				}	
				else if (remove.getRight() != null) {
					remove = remove.getRight();
				}
				else if (remove.getLeft() == null && remove.getMiddle() == null && remove.getRight() == null) {
					running = false;
				}
			}
		}
		running = true;
		DirectoryNode add = this.root;
		while (running) {
			if (add.getLeft() != null && add.getLeft().getName().equals(src)) {
				add = add.getLeft();
				running = false;
			}
			else if (add.getMiddle() != null && add.getMiddle().getName().equals(src)) {
				add = add.getMiddle();
				running = false;
			}
			else if (add.getRight() != null && add.getRight().getName().equals(src)) {
				add = add.getRight();
				running = false;
			}
			else {
				if (add.getLeft() != null) {
					add = add.getLeft();
				}
				else if (add.getMiddle() != null) {
					add = add.getMiddle();
				}	
				else if (add.getRight() != null) {
					add = add.getRight();
				}
				else if (add.getLeft() == null && add.getMiddle() == null && add.getRight() == null) {
					running = false;
				}
			}
		}
		this.resetCursor();
		DirectoryNode dup = remove;
		//System.out.println(remove.getName());
		int index = srcArr.length;
		for (int i = 1; i < index; i++) {
			this.changeDirectory(srcArr[i]);
		}
		//System.out.println("1");
		running = true;
		this.moveToParent();
		while (running) {
			if (cursor.getLeft() != null && cursor.getLeft().getName().equals(dup.getName())) {
				cursor.removeChild("left");
				running = false;
			}
			if (cursor.getMiddle() != null && cursor.getMiddle().getName().equals(dup.getName())) {
				cursor.removeChild("middle");
				running = false;
			}
			if (cursor.getRight() != null && cursor.getRight().getName().equals(dup.getName())) {
				cursor.removeChild("right");
				running = false;
			}
			if (cursor.getLeft() == null && cursor.getMiddle() == null && cursor.getRight() == null) {
				running = false;
			}
		}
		this.resetCursor();
		int index2 = destArr.length;
		for (int i = 1; i < index2; i++) {
			this.changeDirectory(destArr[i]);
		}
		if (add.getIsFile() == false) {
			this.makeDirectory(dup.getName());
			this.changeDirectory(dup.getName());
			
			if (dup.getLeft() != null) {
				this.cursor.addChild(dup.getLeft());
			}
			if (dup.getMiddle() != null) {
				this.cursor.addChild(dup.getMiddle());
			}
			if (dup.getRight() != null) {
				this.cursor.addChild(dup.getRight());
			}
		}
		else {
			this.makeFile(add.getName());
		}
		this.resetCursor();
 	}
	
	/**
	 * this method moves the cursor to the parent that the cursor is currently on
	 */
	public void moveToParent() {
		DirectoryNode node = this.root;
		boolean running = true;
		while (running) {
			DirectoryNode check = node;
			if (node.getLeft() != null) {
				if (node.getLeft().equals(this.cursor)) {
					this.cursor = node;
					running = false;
				}
				else {
					node = node.getLeft();
				}
			}
			else if (node.getMiddle() != null) {
			if (node.getMiddle().equals(this.cursor)) {
				this.cursor = node;
				running = false;
			}
			else {
				node = node.getMiddle();
			}
			}	
			else if (node.getRight() != null) {
			if (node.getRight().equals(this.cursor)) {
				this.cursor = node;
				running = false;
			}
			else {
				node = node.getRight();
			}
		}
		if (node.equals(check)) {
			running = false;
		}
		}
	}
	
	
	
	/**
	 * @param name to find the directory that user wants to find
	 * @return the node of the directory
	 * @throws Exception thrown when user enters a directory that does not exist
	 */
	public String findMe(String name) throws Exception{
		DirectoryNode start = this.root;
		boolean run = true;
		while (run) {
		DirectoryNode check = start;
		if (start.getLeft() != null) {
			if (start.getLeft().getName().equals(name)) {
				start = start.getLeft();
			}
			else {
				start = start.getLeft();
			}
		}
		if (start.getMiddle() != null) {
			if (start.getMiddle().getName().equals(name)) {
				start = start.getMiddle();
			}
			else {
				start = start.getLeft();
			}
		}
		if (start.getRight() != null) {
			if (start.getRight().getName().equals(name)) {
				start = start.getRight();
			}
			else {
				start = start.getLeft();
			}
		}
		if (start.equals(check)) {
			run = false;
		}
		}
		String printStr = "";
		boolean running = true;
		DirectoryNode node = start;
		DirectoryNode pointer = this.root;
		printStr += "/" +  pointer.getName();
		if (node.equals(this.root)) return printStr;
		while (running) { 
			DirectoryNode check = pointer;
			if (pointer.getLeft() != null &&  pointer.getMiddle() != null && pointer.getRight() != null) {
				if (pointer.getLeft().equals(node) || pointer.getRight().equals(node) || pointer.getMiddle().equals(node)) {
					printStr += "/" +  node.getName();
					return printStr;
				}
			}
			if (pointer.getLeft() != null) {
				pointer = pointer.getLeft(); 
				printStr += "/" +  pointer.getName();
				
				}
			else if (pointer.getMiddle() != null) {
				pointer = pointer.getMiddle(); 
				printStr += "/" +  pointer.getName();
				}
			else if (pointer.getRight() != null) {
				pointer = pointer.getRight(); 
				printStr += "/" +  pointer.getName();
				}
			if (check.equals(pointer)) {
				running = false;
			}
		}
		
		return printStr;
		
	}
	
	/** 
	 * @return a String of the current directory that the user is on
	 */
	public String presentWorkingDirectory() {
		String printStr = "";
		boolean running = true;
		DirectoryNode pointer = this.root;
		printStr += "/" +  pointer.getName();
		if (this.cursor.equals(root)) return printStr;
		while (running) { 
			DirectoryNode check = pointer;
			if (pointer.getLeft() != null &&  pointer.getMiddle() != null && pointer.getRight() != null) {
				if (pointer.getLeft().equals(this.cursor) || pointer.getRight().equals(this.cursor) || pointer.getMiddle().equals(this.cursor)) {
					printStr += "/" +  this.cursor.getName();
					return printStr;
				}
			}
			if (pointer.getLeft() != null) {
				pointer = pointer.getLeft(); 
				printStr += "/" +  pointer.getName();
				
				}
			else if (pointer.getMiddle() != null) {
				pointer = pointer.getMiddle(); 
				printStr += "/" +  pointer.getName();
				}
			else if (pointer.getRight() != null) {
				pointer = pointer.getRight(); 
				printStr += "/" +  pointer.getName();
				}
			if (check.equals(pointer)) {
				running = false;
			}
		}
		return printStr;
	}
	
	/**
	 * @return a list of all the directories added
	 */
	public String listDirectory() {
		String printStr = "";
		DirectoryNode pointer = this.cursor;
		//printStr += " " + this.cursor.getName();
			if (pointer.getLeft() != null) {
				printStr += " " +  pointer.getLeft().getName();
				}
			if (pointer.getMiddle() != null) {
				printStr += " " +  pointer.getMiddle().getName();
				}
			if (pointer.getRight() != null) {
				printStr += " " +  pointer.getRight().getName();
				}
		
		return printStr;
	}
	
	/**
	 * @param node that marks which node I am at
	 * @param level saves the amount of spaces
	 */
	public void helperPrint(DirectoryNode node, int level) {
		int counter  =  level;
		for (int i = 0; i < level; i++) {
			System.out.print("\t");
		}
		
		if (node.getIsFile() == true) {
			System.out.print("- " + node.getName() + "\n");
		}
		else {
			System.out.print("|- " + node.getName() + "\n");
		}
		if (node.getLeft() != null) {
			//System.out.print("   ");
			//System.out.println("HELLOleft" +level);
			this.helperPrint(node.getLeft(), (++counter));
			counter = level;
		}
		if (node.getMiddle() != null) {
			//System.out.println("HELLOmiddle" +level);
			//System.out.print("   ");
			this.helperPrint(node.getMiddle(), (++counter));
			counter = level;
		}
		if (node.getRight() != null) {
			//System.out.println("HELLOright" +level);
			//System.out.print("   ");
			this.helperPrint(node.getRight(), (++counter));
			counter = level;
		}
		//System.out.println();
	}
	
	/**
	 * calls the helper method that prints the tree
	 */
	public void printDirectoryTree() {
		this.helperPrint(this.cursor, 0);
	}
	
	/**
	 * @param name of the directory being created
	 * @throws IllegalArgumentException when the directory has illegal characters 
	 * @throws FullDirectoryException when all the nodes of the parent are full
	 * @throws NotADirectoryException when user tries to add a directory to a file
	 */
	public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
		if (name.contains("/") || name.contains(" ")) {
			throw new IllegalArgumentException();
		}
		DirectoryNode node = new DirectoryNode(name);
		this.cursor.addChild(node);
	}
	
	/**
	 * @param name of the directory being created
	 * @throws IllegalArgumentException when the directory has illegal characters 
	 * @throws FullDirectoryException when all the nodes of the parent are full
	 * @throws NotADirectoryException when user tries to add a directory to a file
	 */
	public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
		if (name.contains("/") || name.contains(" ")) {
			throw new IllegalArgumentException();
		}
		DirectoryNode node = new DirectoryNode(name);
		node.setIsFile(true);
		this.cursor.addChild(node);
	
	}
	

}
