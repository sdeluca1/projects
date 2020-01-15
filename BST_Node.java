package SPLT_A4;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node par;
	boolean justMade;
	boolean contains;
	
	BST_Node(String data){
		this.data=data;
		this.justMade = true;
		this.contains = true;
	}
	
	BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ 
	    this.data=data;
	    this.left=left;
	    this.right=right;
	    this.par=par;
	    this.justMade=true;
	    this.contains = true;
	  }
 // --- used for testing  ----------------------------------------------
	public String getData(){
		return data;
	}
	public BST_Node getLeft(){
		return left;
	}
	public BST_Node getRight(){
		return right;
	}
 // --- end used for testing -------------------------------------------

	public BST_Node containsNode(String s){ //it was me
		if(data.equals(s)) {
			splay(this); //splay
			contains = true;
			return findRoot();
		}
		if(data.compareTo(s)>0){//s lexiconically less than data
			if(left==null) {
				splay(this); //splay
				contains = false;
				return findRoot();
			}
			return left.containsNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null) {
				splay(this); //splay
				contains = false;
				return findRoot();
			}
			return right.containsNode(s);
		}
		return this;//shouldn't hit
	}
	public BST_Node insertNode(String s){
		if(data.compareTo(s)>0){
			if(left==null){
				left=new BST_Node(s);
				left.par = this;
				splay(left); //splay
				justMade = true;
				return findRoot();
			}
			return left.insertNode(s);
		}
		if(data.compareTo(s)<0){
			if(right==null){
				right=new BST_Node(s);
				right.par = this;
				splay(right); //splay
				justMade = true;
				return findRoot();
			}
			return right.insertNode(s);
		}
		splay(this); //splay the duplicate to the root
		justMade = false;
		return this;//ie we have a duplicate
	}
	public BST_Node removeNode(String s){ //DIO
			BST_Node newRoot = new BST_Node("");
			if (left != null) {
			newRoot = left.findMax();
			newRoot.par = null;
			if (this.right != null) {
			newRoot.right = this.right;
			this.right.par = newRoot;
			} else {
				newRoot.right = null;
			}
			return newRoot;
			} else {
			 newRoot = right.findMin();
			 newRoot.par = null;
			 if (this.left != null) {
				 newRoot.left = this.left;
				 this.left.par = newRoot;
			 } else {
				 newRoot.left = null;
			 }
			 return newRoot;
			}
			//root of right subtree is now the right child of the root of the left subtree		
			
			
		/*if(data==null)return false;
		if(data.equals(s)){
			if(left!=null){
				data=left.findMax().data;
				left.removeNode(data);
				if(left.data==null)left=null;
			}
			else if(right!=null){
				data=right.findMin().data;
				right.removeNode(data);
				if(right.data==null)right=null;
			}
			else data=null;
			return true;
		}
		else if(data.compareTo(s)>0){
			if(left==null)return false;
			if(!left.removeNode(s))return false;
			if(left.data==null)left=null;
			return true;
		}
		else if(data.compareTo(s)<0){
			if(right==null)return false;
			if(!right.removeNode(s))return false;
			if(right.data==null)right=null;
			return true;
		}
		return false;
		*/
	}
	
	public BST_Node findMin(){
		if(left!=null)return left.findMin();
		splay(this); //splay
		return this;
	}
	public BST_Node findMax(){
		if(right!=null)return right.findMax();
		splay(this); //splay
		return this;
	}
	public int getHeight(){
		int l=0;
		int r=0;
		if(left!=null)l+=left.getHeight()+1;
		if(right!=null)r+=right.getHeight()+1;
		return Integer.max(l, r);
	}
	
	
    private void splay(BST_Node toSplay) { 
    	if (toSplay.par == null) { //node is at root
    		return;
    	}
    	//if node to splay is on the right subtree and is one level down from the root
		if(toSplay.getData().compareTo(findRootData(toSplay)) > 0 &&
		   toSplay.par.par == null) {
			toSplay.rotateLeft();
		} else //if node to splay is on left subtree and is one level down from root
		if (toSplay.getData().compareTo(findRootData(toSplay)) < 0 &&
			toSplay.par.par == null) {
			toSplay.rotateRight();
		} else //if node to splay is on right subtree and is > 1 level down from root
		if(toSplay.getData().compareTo(findRootData(toSplay)) > 0 &&
			toSplay.par.par != null) {
				if(toSplay.getData().compareTo(toSplay.par.getData()) > 0 && 
				   toSplay.par.getData().compareTo(toSplay.par.par.getData()) > 0) {
				//if node to splay is right child of parent, and parent is right child of Grandparent
					toSplay.zigZigLeft();
				} else if (toSplay.getData().compareTo(toSplay.par.getData()) > 0 &&
						toSplay.par.getData().compareTo(toSplay.par.par.getData()) < 0) {
					//if node to splay is right child of parent, and parent is left child of grandparent
					toSplay.zigZagRight();
				} else if (toSplay.getData().compareTo(toSplay.par.getData()) < 0 &&
						toSplay.par.getData().compareTo(toSplay.par.par.getData()) > 0) {
					//if node to splay is left child of parent, and parent is right child of grandparent
					toSplay.zigZagLeft();
				}
				else if (toSplay.getData().compareTo(toSplay.par.getData()) < 0 &&
						toSplay.par.getData().compareTo(toSplay.par.par.getData()) < 0) {
				//if node to splay is left child of parent, and parent is left child of grandparent
					toSplay.zigZigRight();
				}
			} else //if node to splay is on left subtree and is > 1 level down from root
		if(toSplay.getData().compareTo(findRootData(toSplay)) < 0 &&
			toSplay.par.par != null) {
			if(toSplay.getData().compareTo(toSplay.par.getData()) > 0 && 
					   toSplay.par.getData().compareTo(toSplay.par.par.getData()) > 0) {
					//if node to splay is right child of parent, and parent is right child of Grandparent
						toSplay.zigZigLeft();
					} else if (toSplay.getData().compareTo(toSplay.par.getData()) > 0 &&
							toSplay.par.getData().compareTo(toSplay.par.par.getData()) < 0) {
						//if node to splay is right child of parent, and parent is left child of grandparent
						toSplay.zigZagRight();
					} else if (toSplay.getData().compareTo(toSplay.par.getData()) < 0 &&
							toSplay.par.getData().compareTo(toSplay.par.par.getData()) > 0) {
						//if node to splay is left child of parent, and parent is right child of grandparent
						toSplay.zigZagLeft();
					}
					else if (toSplay.getData().compareTo(toSplay.par.getData()) < 0 &&
							toSplay.par.getData().compareTo(toSplay.par.par.getData()) < 0) {
					//if node to splay is left child of parent, and parent is left child of grandparent
						toSplay.zigZigRight();
					}
		}
		
		if (toSplay.par != null) {
			splay(toSplay);
		} else {
			return;
		}
    }
	
    private void rotateLeft() {
    	BST_Node tempPar = new BST_Node("");
    	BST_Node tempLeft = new BST_Node("");
    	BST_Node tempGrand = new BST_Node("");
    	
    	tempPar = par;
    	tempLeft = left;  	
    	if (this.par.par != null) {
    		tempGrand = this.par.par;
    	}
    	
    	if (this.par.par != null) {//grandparent
    		if (tempGrand.left == tempPar) { //if splaying nodes are on the left subtree
    		if (this.left != null) {//grandparent and left child
    			this.left.par = tempPar;
    			tempPar.right = this.left;
    			this.left = tempPar;
    			tempPar.par = this;
    			this.par = tempGrand;
    			tempGrand.left = this;//
    		} else {//grandparent, no left child
    			this.left = tempPar;
    			tempPar.par = this;
    			this.par = tempGrand;
    			tempGrand.left = this;
    			tempPar.right = null;
    			}
    		} else {//if tempGrand.right == tempPar, if splaying nodes are on right subtree
    			if (this.left != null) {//right subtree, left child
    				this.left.par = tempPar;
    				tempPar.right = this.left;
    				this.left = tempPar;
    				tempPar.par = this;
    				this.par = tempGrand;
    				tempGrand.right = this;
    			} else {//right subtree, no left child
    				this.left = tempPar;
    				tempPar.par = this;
    				this.par = tempGrand;
    				tempGrand.right = this;
    				tempPar.right = null;
    			}
    			
    		}
    	} else { //no grandparent
    		if (this.left != null) {//no grandparent, left child
    	    	this.left.par = tempPar; 
    	    	tempPar.right = this.left;
    	    	this.left = tempPar;
    	      	tempPar.par = this;
    	      	this.par = null;	
    	    	} else { //no grandparent no left child
    	    	this.left = tempPar;
    	    	tempPar.par = this;
    	    	this.par = null;
    	    	tempPar.right = null;
    	    	}
    	}
	    
    }
    
    private void rotateRight() {
    	BST_Node tempPar = new BST_Node("");
    	BST_Node tempRight = new BST_Node("");
    	BST_Node tempGrand = new BST_Node("");
    	
    	tempPar = par;
    	tempRight = right;
    	if (this.par.par != null) {
    		tempGrand = this.par.par;
    	}
    	
    	if (this.par.par == null) {//if there is not a grandparent
    	if (this.right != null) { //if there is a right child, no  grandparent
    		this.right.par = tempPar;
    		tempPar.left = this.right;
    		this.right = tempPar;
    		tempPar.par = this;
    		this.par = null;
    	} else { //if there is not a right child, no grandparent
    		this.right = tempPar;
    		tempPar.par = this;
    		this.par = null;
    		tempPar.left = null;
    		}
    	}	else {//grandparent
    		if (tempGrand.left == tempPar) {
    		if (this.right != null) {//right child, grandparent
    			this.right.par = tempPar;
    			tempPar.left = this.right;
    			this.right = tempPar;
    			tempPar.par = this;
    			this.par = tempGrand;
    			tempGrand.left = this;
    		} else {//no right child, grandparent
    			this.right = tempPar;
    			tempPar.par = this;
    			this.par = tempGrand;
    			tempGrand.left = this;
    			tempPar.left = null;
    		}
    		} else {
    			if (tempGrand.right == tempPar) {
    				if (this.right != null) {//right child, grandparent
    	    			this.right.par = tempPar;
    	    			tempPar.left = this.right;
    	    			this.right = tempPar;
    	    			tempPar.par = this;
    	    			this.par = tempGrand;
    	    			tempGrand.right = this;
    	    		} else {//no right child, grandparent
    	    			this.right = tempPar;
    	    			tempPar.par = this;
    	    			this.par = tempGrand;
    	    			tempGrand.right = this;
    	    			tempPar.left = null;
    	    		}
    			}
    		}
    	}
    }
    
    private void zigZigLeft() {
    	this.par.rotateLeft();
    	this.rotateLeft();
    }
    
    private void zigZigRight() {
    	this.par.rotateRight();
    	this.rotateRight();
    	
    }
    
    private void zigZagLeft() {
    	BST_Node tempPar = new BST_Node("");
    	BST_Node tempLeft = new BST_Node("");
    	BST_Node tempRight = new BST_Node("");
    	BST_Node tempGrand = new BST_Node("");
    	BST_Node tempGreatGrand = new BST_Node("");
    	
    	tempPar = par;
    	tempLeft = left;
    	tempRight = right;
    	tempGrand = this.par.par;
    	if (this.par.par.par != null) {
    		tempGreatGrand = this.par.par.par;
    	}  	
    	if (this.par.par.par != null) {//great grandparent
    		if (tempGreatGrand.right == tempGrand) {
    			if (this.left != null) {//left
    				if (this.right != null) {//left, right, great grandparent
    				tempLeft.par = tempGrand;
    				tempGrand.right = tempLeft;
    				tempRight.par = tempPar;
    				tempPar.left = tempRight;
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;
    				this.par = tempGreatGrand;
    				tempGreatGrand.right = this;
    			} else {//left, no right, great grandparent
    				tempLeft.par = tempGrand;
    				tempGrand.right = tempLeft;
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;
    				this.right.left = null;
    				this.par = tempGreatGrand;
    				tempGreatGrand.right = this;
    			}
    		} else {//no left
    			if (this.right != null) {//no left, right, great grandparent
    				tempRight.par = tempPar;
    				tempPar.left = tempRight;
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;
    				this.left.right = null;
    				this.par = tempGreatGrand;
    				tempGreatGrand.right = this;
    			} else {//no left, no right, great grandparent
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;		
    				this.right.left = null;
    				this.left.right = null;
    				this.par = tempGreatGrand;
    				tempGreatGrand.right = this;
    			}
    		}
    		} else {//tempGreatGrand.left == tempGrand, nodes to splay are on left subtree
    			if (this.left != null) {//left
    				if (this.right != null) {//left, right, great grandparent
    				tempLeft.par = tempGrand;
    				tempGrand.right = tempLeft;
    				tempRight.par = tempPar;
    				tempPar.left = tempRight;
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;
    				this.par = tempGreatGrand;
    				tempGreatGrand.left = this;
    			} else {//left, no right, great grandparent
    				tempLeft.par = tempGrand;
    				tempGrand.right = tempLeft;
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;
    				this.right.left = null;
    				this.par = tempGreatGrand;
    				tempGreatGrand.left = this;
    			}
    		} else {//no left
    			if (this.right != null) {//no left, right, great grandparent
    				tempRight.par = tempPar;
    				tempPar.left = tempRight;
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;
    				this.left.right = null;
    				this.par = tempGreatGrand;
    				tempGreatGrand.left = this;
    			} else {//no left, no right, great grandparent
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;		
    				this.right.left = null;
    				this.left.right = null;
    				this.par = tempGreatGrand;
    				tempGreatGrand.left = this;
    			}
    		}
    		}
    	} else {//no great grandparent
    		if (this.left != null) {//left
    			if (this.right != null) {//left, right, no great grandparent
    				tempLeft.par = tempGrand;
    				tempGrand.right = tempLeft;
    				tempRight.par = tempPar;
    				tempPar.left = tempRight;
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;
    				this.par = null;
    			} else {//left, no right, no great grandparent
    				tempLeft.par = tempGrand;
    				tempGrand.right = tempLeft;
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;
    				tempPar.left = null;
    				this.par = null;
    			}
    		} else {//no left
    			if (this.right != null) {//no left, right, no great grandparent
    				tempRight.par = tempPar;
    				tempPar.left = tempRight;
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;
    				tempGrand.right = null;
    				this.par = null;			
    			} else {//no left, no right, no great grandparent
    				this.left = tempGrand;
    				tempGrand.par = this;
    				this.right = tempPar;
    				tempPar.par = this;
    				this.left.right = null;
    				this.right.left = null;
    				this.par = null;				
    			}
    		}
    	}
    	
    }
    
    private void zigZagRight() {
    	BST_Node tempPar = new BST_Node("");
    	BST_Node tempLeft = new BST_Node("");
    	BST_Node tempRight = new BST_Node("");
    	BST_Node tempGrand = new BST_Node("");
    	BST_Node tempGreatGrand = new BST_Node("");
    	
    	tempPar = par;
    	tempLeft = left;
    	tempRight = right;
    	tempGrand = this.par.par;
    	
    	if (this.par.par.par != null) {
    		tempGreatGrand = this.par.par.par;
    	}
    	
    	if (this.par.par.par != null) {	//if there is a great grandparent:
    		if (tempGreatGrand.right == tempGrand) { //if splaying nodes are on right subtree
    			if (this.left != null) {//if there is a left child:
    				if (this.right != null) {//left, right, great grandparent:
    					tempLeft.par = tempPar;
    					tempPar.right = tempLeft;
    					tempRight.par = tempGrand;
    					tempGrand.left = tempRight;
    					this.left = tempPar;
    					tempPar.par = this;
    					this.right = tempGrand;
    					tempGrand.par = this;		
    					this.par = tempGreatGrand;
    					tempGreatGrand.right = this;
    		} else {//left, no right, great grandparent:
    			tempLeft.par = tempPar;
    			tempPar.right = tempLeft;
    			this.left = tempPar;
    			tempPar.par = this;
    			this.right = tempGrand;
    			tempGrand.par = this;
    			this.par = tempGreatGrand;
    			tempGreatGrand.right = this;
    			tempGrand.left = null;			
    			}
    		} else { //no left
    			if (this.right != null) { //no left, right, great grandparent:
    				tempRight.par = tempGrand;
    				tempGrand.left = tempRight;
    				this.left = tempPar;
    				tempPar.par = this;
    				this.right = tempGrand;
    				tempGrand.par = this;
    				this.par = tempGreatGrand;
    				tempGreatGrand.right = this;
    				tempPar.right = null;
    			} else { //no left, no right, great grandparent:
    				this.left = tempPar;
    				tempPar.par = this;
    				this.right = tempGrand;
    				tempGrand.par = this;
    				this.par = tempGreatGrand;
    				tempGreatGrand.right = this;
    				tempPar.right = null;
    				tempGrand.left = null;
    				
    			}		
    		}
    		} else { //if tempGreatGrand.left == tempGrand, nodes to splay are on left subtree
    			if (this.left != null) {//if there is a left child:
    				if (this.right != null) {//left, right, great grandparent:
    					tempLeft.par = tempPar;
    					tempPar.right = tempLeft;
    					tempRight.par = tempGrand;
    					tempGrand.left = tempRight;
    					this.left = tempPar;
    					tempPar.par = this;
    					this.right = tempGrand;
    					tempGrand.par = this;		
    					this.par = tempGreatGrand;
    					tempGreatGrand.left = this;
    		} else {//left, no right, great grandparent:
    			tempLeft.par = tempPar;
    			tempPar.right = tempLeft;
    			this.left = tempPar;
    			tempPar.par = this;
    			this.right = tempGrand;
    			tempGrand.par = this;
    			this.par = tempGreatGrand;
    			tempGreatGrand.left = this;
    			tempGrand.left = null;			
    			}
    		} else { //no left
    			if (this.right != null) { //no left, right, great grandparent:
    				tempRight.par = tempGrand;
    				tempGrand.left = tempRight;
    				this.left = tempPar;
    				tempPar.par = this;
    				this.right = tempGrand;
    				tempGrand.par = this;
    				this.par = tempGreatGrand;
    				tempGreatGrand.left = this;
    				tempPar.right = null;
    			} else { //no left, no right, great grandparent:
    				this.left = tempPar;
    				tempPar.par = this;
    				this.right = tempGrand;
    				tempGrand.par = this;
    				this.par = tempGreatGrand;
    				tempGreatGrand.left = this;
    				tempPar.right = null;
    				tempGrand.left = null;
    				
    			}		
    		}
    		}
    	} else { //if no great grandparent:
    		if (this.left != null) { //left child
    			if (this.right != null) { //left child, right child, no great grandparent
    				tempLeft.par = tempPar;
					tempPar.right = tempLeft;
					tempRight.par = tempGrand;
					tempGrand.left = tempRight;
					this.left = tempPar;
					tempPar.par = this;
					this.right = tempGrand;
					tempGrand.par = this;
					this.par = null;
    			} else {//left child, no right child, no great grandparent
    				this.left.par = tempPar;
    				tempPar.right = this.left;
    				this.left = tempPar;
    				tempPar.par = this;
    				this.right = tempGrand;
    				tempGrand.par = this;
    				tempGrand.left = null;
    				this.par = null;
    			}
    		} else {//no left child
    			if (this.right != null) { //no left child, right child, no great grandparent
    				tempRight.par = tempGrand;
    				tempGrand.left = tempRight;
    				this.left = tempPar;
    				tempPar.par = this;
    				this.right = tempGrand;
    				tempGrand.par = this;
    				tempPar.right = null;
    				this.par = null;
    			} else {//no left child, no right child, no grandparent
    				this.left = tempPar; //left child is what the parent was
            		tempPar.par = this; //parent of parent equals this
            		this.left.right = null;
            		this.right = tempGrand;//right child is what the grandparent was
            		tempGrand.par = this;//parent of grand parent equals this
            		this.right.left = null;
            		this.par = null;
    			}
    		}
    	}
    }
	
    private String findRootData(BST_Node current) { //find root data to compare to
    	String rootData = "";
    	if(current.par == null) {
    		rootData = current.getData();
    		return rootData;
    	} else {
    		rootData = findRootData(current.par);
    		return rootData;
    	}
    	
    }
    
    private BST_Node findRoot() {
    	if (this.par == null) {
    		return this;
    	} else {
    		return this.par.findRoot();
    	}
    }
	public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")+",Right: "+((this.right!=null)?right.data:"null");
	}
	
}
