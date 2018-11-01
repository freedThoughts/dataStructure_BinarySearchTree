package problemSet_1;

public interface IBSTOperation {
	
	problemSet_1.BinaryTreeNode<Integer> createBST();
	void searchElementBST(BinaryTreeNode<Integer> node, int data);
	BinaryTreeNode LowestCommonAncestor(BinaryTreeNode<Integer> node, int value1, int value2);
	boolean isBinaryTreeBST(BinaryTreeNode<Integer> node);
	boolean isBinaryTreeBST2(BinaryTreeNode<Integer> node);
	BinaryTreeNode<Integer> insertAnElement(BinaryTreeNode<Integer> node, int dataToInsert);
	BinaryTreeNode<Integer> deletingAnElement(BinaryTreeNode<Integer> node, int dataToDelete);
	BinaryTreeNode<Integer> findKthSmallestElementInBST(BinaryTreeNode<Integer> root, int k);
	void BST_To_DLL(BinaryTreeNode<Integer> root);
	BinaryTreeNode<Integer> arrayToBST(BinaryTreeNode<Integer>[] arr);
	BinaryTreeNode<Integer> arrayToBST2(int[] arr, int startIndex, int endIndex);
	BinaryTreeNode<Integer> sortedDLL_To_BST(BinaryTreeNode<Integer> root);
	BinaryTreeNode<Integer> sortedSLL_To_BST(BinaryTreeNode<Integer> root);
}
