import java.util.HashMap;
import java.util.Map;
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}


public class constructBinaryTreefromPreorderandInorderTraversal {
  public static void main(String[] args) {

    // Example usage:
    int[] preorder = {-1};
    int[] inorder = {-1};

    SolutionBinaryTree solution = new SolutionBinaryTree();
    TreeNode root = solution.buildTree(preorder, inorder);

    // Print the constructed tree (in-order traversal)
    System.out.println(root.val);
  }
}

class SolutionBinaryTree {
   // Map<Integer,Integer>pMap;
     Map<Integer,Integer>iMap=null;
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        //pMap=new HashMap<>();
        iMap=new HashMap<>();
        //  for(int i=0;i<preorder.length;i++){
        //     pMap.put(preorder[i],i);
        //  }
        for(int i=0;i<inorder.length;i++){
        iMap.put(inorder[i],i);
        }

         int size=preorder.length;
       return   buildTree( preorder,0,size-1,inorder,0,size-1);
        
    }

    public TreeNode buildTree(int[] preorder,int pLeft, int pRight, int[] inorder, int iLeft, int iRight){

        if(pLeft>pRight) return null;


        int rootVal=preorder[pLeft];

        int index=iMap.get(rootVal);

        int size=index-iLeft;

        TreeNode root=new TreeNode(rootVal);

        root.left=buildTree(preorder,pLeft+1, pLeft+size, inorder,iLeft,index-1);

        root.right=buildTree(preorder, pLeft+size+1, pRight, inorder,index+1,iRight);

        return root;

        



    }
}