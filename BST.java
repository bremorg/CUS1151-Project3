import java.util.LinkedList;

//import lab.Node;
//import lab.Tree;

/**
 * @author
 *
 */
class BST{
    
    Node root;

    private class Node{
    	
    	String keyword;
    	Record record;
        int size;
        Node l;
        Node r;

        private Node(String k){
        	// TODO Instantialize a new Node with keyword k.
        	this.keyword = k;
        }

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
        	//TODO Adds the Record r to the linked list of records. 
        	// You do not have to check if the record is already in the list.
        	//HINT: Add the Record r to the front of your linked list.
        }
    }

    public BST(){
        this.root = null;
    }
    
    public BST(Node node) 
    {
    	this.root = node;
    }
    
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
	public void newNode(Node node) 
	{
		newNode(this, node);
	}
    
	private Node find(String keyword) 
	{
		return find(this.root, keyword);
	}
	
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
	
    public void insert(String keyword, FileData fd) {
        Record recordToAdd = new Record(fd.id, fd.title, fd.author, null);
        //TODO Write a recursive insertion that adds recordToAdd 
        Node nodearg = find(keyword);
        if(null == nodearg) 
        {
        	nodearg = new Node(keyword);
        	this.newNode( nodearg );
        }
        
        nodearg.update(recordToAdd);
        
        // to the list of records for the node associated with keyword.
        // If there is no node, this code should add the node.
    }
	
    public boolean contains(String keyword){
    	return ( null == this.find(keyword) );
    	//TODO Write a recursive function which returns true 
    	// if a particular keyword exists in the BST
    }

	public Record get_records(String keyword){
		Node n = this.find(keyword);
		if( n == null) 
		{
			return null;
		}
		return n.record;
        //TODO Returns the first record for a particular keyword. 
    	// This record will link to other records
    	// If the keyword is not in the BST, it should return null.
    }

    public void delete(String keyword){
    	//TODO Write a recursive function which removes the Node with keyword 
    	// from the binary search tree.
    	// You may not use lazy deletion and if the keyword is not in the BST, 
    	// the function should do nothing.
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
