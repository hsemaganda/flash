package firstProgram;
import java.util.*;
public class Main
{
    static private int size= 0;
    static private int size2= 0;
    static TreeNode overallRoot;
    static TreeNode overallRoot2;
    static class TreeNode{
        TreeNode left, right;
        int val,height;
        public TreeNode(int data){
            this.val = data;
            height = 1;
        }
    }
    private static int max(int a, int b){
        if(a>b)
        return a;
        return b;
    }
    private static int height(TreeNode root){
        if(root == null)
        return 0;
        return max(height(root.left), height(root.right));
    }
    private static int getBalance(TreeNode root){
        if(root == null)
        return 0;
        return height(root.left) - height(root.right);
    }
    public static void add(int value){
        overallRoot = add(overallRoot,value);
        size++;
    }
    private static  TreeNode add(TreeNode root, int value){
        if(root== null)
        root = new TreeNode(value);
        else if(value < root.val)
        root.left = add(root.left,value);
        else
        root.right = add(root.right, value);
        root.height = max(height(root.left),height(root.right)) + 1;
        int balance = getBalance(root);
        if(balance > 1 && value < root.left.val)
        return rightRotate(root.left);
        if(balance < -1 && value > root.right.val)
        return leftRotate(root.right);
        if(balance > 1 && value > root.left.val){
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if(balance < -1 && value < root.right.val){
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }
    private static TreeNode rightRotate(TreeNode root){
        TreeNode temp = root.left;
        TreeNode x = temp.right;
        temp.right = root;
        root.left = x;
        root.height = max(height(root.right), height(root.left))+1;
        temp.height= max(height(root.left), height(root.right))+1;
        return temp;
    }
    private static TreeNode leftRotate(TreeNode root){
        TreeNode temp = root.right;
        TreeNode x = temp.left;
        temp.left = root;
        root.right = x;
        root.height = max(height(root.right), height(root.left))+1;
        temp.height= max(height(root.left), height(root.right))+1;
        return temp;
    }
    public static int [] toArray(TreeNode root){
        int [] nums = new int [size];
        fillArray(overallRoot,nums,0);
        return nums;
    }
    private static int fillArray(TreeNode root, int [] nums,int pos){
        if(root.left != null)
        pos = fillArray(root.left, nums, pos);
        nums[pos++] = root.val;
        if(root.right != null)
        pos = fillArray(root.right, nums, pos);
        return pos;
    }
    public void printInOrder(){
        printInOrder(overallRoot);
    }
    private void printInOrder(TreeNode root){
        if(root == null)
        return;
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }
    public static boolean contains (int value){
        return contains(overallRoot,value);
    }
    public static boolean contains2 (int value){
        return contains(overallRoot2,value);
    }
    private static boolean contains(TreeNode root, int value){
        if(root == null)
        return false;
        else if(value == root.val)
        return true;
        else if( value < root.val)
        return contains(root.left, value);
        else
        return contains(root.right, value);
    }
	public static void main(String[] args) {
	    Main tree = new Main();
	    Main tree2 = new Main();
	    for(int n = 4000; n>0; n--)
	    tree.add(n);
	    long start = System.currentTimeMillis();
	    
	    tree.contains(4000);
	    long stop = System.currentTimeMillis();
	    tree.printInOrder();
	    System.out.println();
	    System.out.println("Start time " + start);
	    System.out.println("Stop time " + stop);
	    System.out.println("time taken for AVL  in miliseconds is "  );
	    System.out.println( stop - start );
	    
	    for(int n = 4000; n>0; n--)
	    tree2.add2(n);
	    long start1 = System.currentTimeMillis();
	    System.out.println("Start1 time " + start1);
	    tree2.contains2(4000);
	    long stop2 =  System.currentTimeMillis();
	    System.out.println("Stop2 time " + stop2);
	    System.out.println("time taken for normal tree  in miliseconds is "  );
	   System.out.println(  (stop2-start1));
	    //tree.delete(67);
		//System.out.println(Arrays.toString(toArray(tree.overallRoot)));
		tree2.printInOrder();
		 
        
        
	}
	public static void add2(int value){
        overallRoot2 = add(overallRoot2,value);
        size2++;
    }
    private static  TreeNode add2(TreeNode root, int value){
        if(root== null)
        root = new TreeNode(value);
        else if(value < root.val)
        root.left = add(root.left,value);
        else
        root.right = add(root.right, value);
        
        return root;
    }
}



