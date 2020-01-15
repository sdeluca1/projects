package SPLT_A4;

public class SPLT_Playground {
  public static void main(String[] args){
    //genTest();
	//  rotateTest();
	//  insertTest();
	 // containsTest();
	 //insertAfterRemoveTest();
	 // removeTest();
	//  test();
  }
  
  public static void test() {
	  SPLT tree = new SPLT();
	  tree.insert("U");
	  tree.insert("E");
	  tree.insert("R");
	  tree.remove("E");
	  tree.insert("M");
	  tree.insert("W");
	  tree.remove("L");
	  tree.insert("F");
	  tree.contains("M");
	  tree.insert("L");
	  
	  
	  printLevelOrder(tree);
  }
  
  
  
  
  public static void removeTest() {
	  SPLT tree = new SPLT();
	  tree.insert("C");
	  tree.insert("D");
	  tree.insert("B");
	  tree.insert("F");
	  tree.insert("E");	 
	  tree.remove("C");
	  printLevelOrder(tree);
	
  }
  
  
  
  public static void insertAfterRemoveTest() {
	  SPLT tree = new SPLT();
	  tree.insert("B");
	  tree.insert("A");
	  tree.insert("D");
	  tree.insert("C");
	  tree.insert("E");
	  tree.remove("E");
	  printLevelOrder(tree);
	  tree.insert("F");
	  tree.insert("E");
	  
  }
  
  
  
  public static void insertTest() {
	  SPLT tree = new SPLT();
	  tree.insert("E");
	  tree.insert("C");
	  tree.insert("F");
 	  tree.insert("B");
 	  tree.insert("D");
 	 printLevelOrder(tree);
 	 

	  
  }
  
  
  public static void rotateTest() {
	  SPLT tree = new SPLT();
	  tree.insert("A");
	  tree.insert("B");
	 printLevelOrder(tree);
  }
  
  public static void containsTest() {
	  SPLT tree = new SPLT();
	  tree.insert("B");
	  tree.insert("A");
	  tree.insert("D");
	  tree.insert("C");
	  tree.insert("E");
	  tree.remove("D");
	  printLevelOrder(tree);
	 // System.out.println(tree.getRoot());
  }
  
  
  public static void genTest(){
    SPLT tree= new SPLT();
    tree.insert("hello");
    tree.insert("world");
    tree.insert("my");
    tree.insert("name");
    tree.insert("is");
    tree.insert("blank");
    tree.remove("hello");
    System.out.println("size is "+tree.size());
    
    printLevelOrder(tree);
  }

    static void printLevelOrder(SPLT tree){ 
    //will print current tree in Level-Order...Requires a correct height method
      int h=tree.getRoot().getHeight();
      for(int i=0;i<=h;i++){
        System.out.print("Level "+i+":");
        printGivenLevel(tree.getRoot(), i);
        System.out.println();
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
      if(root!=null){
      printInOrder(root.getLeft());
      System.out.print(root.getData()+" ");
      printInOrder(root.getRight());
      }
  }
  
}