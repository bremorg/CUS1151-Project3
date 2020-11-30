
class BST{
    
    Node root;

    private class Node{
    	
    	String keyword;
    	Record record;
        int size;
        Node l;
        Node r;
	/**
 	* Instantialize a new Node with keyword k.
	* @author Brendan Morgenstern
 	* @param k The Node's keyword
 	*/
        private Node(String k){
        	this.keyword = k;
        }
	/**
 	* Adds the Record r to the linked list of records.
	* @author Brendan Morgenstern
 	* @param r The record to add
 	*/
        private void update(Record r){
        	
        	if(null == this.record) 
        	{
        		this.record = r;
        		return;
        	}
        	Record curr = record;
        	while( null != curr.next ) 
        	{
        		curr = curr.next;
        	}
        	curr.next = r;
        	this.size++;
        }
    }

    public BST(){
        this.root = null;
    }
    
    public BST(Node node) 
    {
    	this.root = node;
    }
    
   	/**
    	* Adds a given node to a given BST
    	* @author Brendan Morgenstern
    	* @param bst The BST to add to
    	* @param n The Node to add     
	*/
	private static void newNode(BST bst, Node n) 
	{
		if (null == bst.root) 
		{
			bst.root = n;
			return;
		}
		if ( (bst.root.keyword.compareTo(n.keyword)) < 0 ) 
		{
			BST t = new BST(bst.root.l);
			newNode(t, n);
			bst.root.l = t.root;
			return;
		}
		if( (bst.root.keyword.compareTo(n.keyword)) > 0 )
		{
			BST t = new BST(bst.root.r);
			newNode(t, n);
			bst.root.r = t.root;
			return;
		}
		
	}
   	/**
    	* Adds a given node to the current BST
    	* @author Brendan Morgenstern
    	* @param node The Node to add     
	*/
	public void newNode(Node node) 
	{
		newNode(this, node);
	}
       	/**
    	* Finds the node in the current BST with the given keyword
    	* @author Brendan Morgenstern
    	* @param keyword The keyword to search for
	* @return Returns the node if a match is found, null if not.
	*/
	private Node find(String keyword) 
	{
		return find(this.root, keyword);
	}
       	/**
    	* Finds the node in the current BST with the given keyword
    	* @author Brendan Morgenstern
	* @param root The root of the tree to search
    	* @param keyword The keyword to search for
	* @return Returns the node if a match is found, null if not.
	*/
	private Node find(Node root, String keyword) 
	{
		if(null == root) 
		{
			return null;
		}
		
		int comp = root.keyword.compareToIgnoreCase(keyword);
		
		if ( 0 == comp )
		{
			return root;
		} 
		else if ( 0 > comp ) 
		{
			return find(root.l, keyword);
		}
		else
		{
			return find(root.r, keyword);
		}
	}
	
	/**
    	* Adds a new Record based off given FileData to the given Node
    	* @author Brendan Morgenstern
	* @param fd The FileData to create a new Record off of
    	* @param keyword The keyword to associate the record with
	*/
    	public void insert(String keyword, FileData fd) 
	{
        	Record recordToAdd = new Record(fd.id, fd.title, fd.author, null);
        	Node nodearg = find(keyword);
        	if(null == nodearg) 
        	{
        		nodearg = new Node(keyword);
        		this.newNode( nodearg );
        	}
        
        	nodearg.update(recordToAdd);
	}
       	/**
    	* Checks if there is a node in the current BST with the given keyword
    	* @author Brendan Morgenstern
    	* @param keyword The keyword to search for
	* @return Returns true node if a match is found, false if not.
	*/	
	public boolean contains(String keyword){
		return ( null == this.find(keyword) );
	}
       	/**
    	* Finds the first record for a particular keyword. 
    	* @author Brendan Morgenstern
    	* @param keyword The keyword to search for
	* @return Returns the record if a match is found, null if not.
	*/
	public Record get_records(String keyword)
	{
		Node n = this.find(keyword);
		if( n == null) 
		{
			return null;
		}
		return n.record;
	}

    public void delete(String keyword){
    	//TODO Write a recursive function which removes the Node with keyword 
    	// from the binary search tree.
    	// You may not use lazy deletion and if the keyword is not in the BST, 
    	// the function should do nothing.
    	
    	Node previous = null;
    	Node current = this.root;
    	
    	while (current != null && !(current.keyword.equals(keyword))) {
    		 previous = current;
    		 int order = keyword.compareTo(current.keyword);
    		 
    		 if (order < 0) {
                 current = current.r;
             }
             if (order > 0) {
                 current = current.l;
             }
    	}
    	
    	if(current == null) {
    		System.out.println(keyword + " was not found in the BST.");
    		return;
    	}
    	
        if (current.r == null && current.l == null) {
            if (current != this.root) {
                if (previous.r == current) {
                    previous.r = null;
                } 
                else {
                    previous.l = null;
                }
            }
            else {
                this.root = null;
            }
        }
        
        else if (current.r != null && current.l != null) {
            Node replacement = findReplacement(current.r);
            String replacementKeyword = replacement.keyword;
            current.record = replacement.record;
            this.delete(replacementKeyword);
            current.keyword = replacementKeyword;
        }
        
        else {
            Node child;
            
            if (current.l == null) {
            	child = current.r;
            }
            else {
            	child = current.l;
            }
            
            if (current != this.root)
            {
                if (current == previous.r) {
                    previous.r = child;
                } else {
                    previous.l = child;
                }
            }
            else {
                this.root = child;
            }
        }
    }
    
    public static Node findReplacement (Node c){
        while (c.l != null) {
            c = c.l;
        }
        return c;
    }

    public void print(){
        print(root);
    }

    private void print(Node t){
        if (t != null){
            print(t.l);
            System.out.println(t.keyword);
            Record r = t.record;
            while(r != null){
            	System.out.println("\t" + r.title);
                r = r.next;
            }
            print(t.r);
        } 
    }
}
