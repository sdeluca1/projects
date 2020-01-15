package BST_A2;

public class BST_Playground {

	 public static void main(String[] args) {
		 test1();
		 //test2();
		 //test3();
	 }
	
	 public static void test1() {
		 BST aBST = new BST();
		 //BST_Node root = new BST_Node("hi")
		 //aBST.insert(root);
		 aBST.insert("B");
		 aBST.insert("A");
		 aBST.insert("D");
		 aBST.insert("C");
		 aBST.insert("E");
		 aBST.remove("D");
		 printLevelOrder(aBST);
	 }
	 
	 public static void test2() {
		 BST aBST = new BST();
		 aBST.insert("D");
		 aBST.insert("B");
		 aBST.remove("B");
		 aBST.insert("B");
		 aBST.insert("E");
		 printLevelOrder(aBST);
		 
	 }
	 
	 public static void test3() {
		 BST aBST = new BST();
		 aBST.insert("0");
		 aBST.insert("C");
		 aBST.insert("A");
		 aBST.insert("B");
		 aBST.insert("E");
		 aBST.insert("D");
		 aBST.remove("C");
	 }
	
	 static void printLevelOrder(BST tree){ 
		  //will print current tree in Level-Order...
		    int h=tree.getRoot().getHeight(tree.getRoot());
		    for(int i=0;i<=h;i++){
		      printGivenLevel(tree.getRoot(), i);
		    }
		    
		  }
		  static void printGivenLevel(BST_Node root,int level){
		    if(root==null)return;
		    if(level==0)System.out.print(root.data+" ");
		    else if(level>0){
		      printGivenLevel(root.left,level-1);
		      printGivenLevel(root.right,level-1);
		    }
		  }
		  static void printInOrder(BST_Node root){
		  //will print current tree In-Order
		  if(root!=null){
		    printInOrder(root.getLeft());
		    System.out.print(root.getData() + " ");
		    printInOrder(root.getRight());
		  }
		  }
	 
	//public static void printInOrder(BST_Node root) {
		
		
		
		/*BST_Node root = bst.getRoot();
		String data = new String("");
		System.out.println(root.getData());
		if (root.getLeft() != null) {
			data = root.getLeft().getData();
			System.out.println(data);
		}
	*/		
			
		
	
}

