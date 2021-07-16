package problemSet_1;

public class BSTOperation implements IBSTOperation{
	
	public BinaryTreeNode<Integer> createBST(){
		BinaryTreeNode<Integer> node7 = new BinaryTreeNode<Integer>(87, null, null);
		BinaryTreeNode<Integer> node6 = new BinaryTreeNode<Integer>(66, null, null);
		BinaryTreeNode<Integer> node5 = new BinaryTreeNode<Integer>(37, null, null);
		BinaryTreeNode<Integer> node4 = new BinaryTreeNode<Integer>(12, null, null);
		BinaryTreeNode<Integer> node3 = new BinaryTreeNode<Integer>(75, node6, node7);
		BinaryTreeNode<Integer> node2 = new BinaryTreeNode<Integer>(25, node4, node5);
		BinaryTreeNode<Integer> node1 = new BinaryTreeNode<Integer>(50, node2, node3);
		return node1;
	}

	@Override
	public void searchElementBST(BinaryTreeNode<Integer> node, int data){
		if(node == null){
			System.out.println("Data not available ");
			return;
		}
			
		if(node.getData() == data)
			System.out.println("Found data : " + node.getData());
		if(node.getData() > data)
			searchElementBST(node.getLeftNode(), data);
		else if(node.getData() < data)
			searchElementBST(node.getRightNode(), data);
	}
	
	//LCA
	@Override
	public BinaryTreeNode LowestCommonAncestor(BinaryTreeNode<Integer> node, int value1, int value2){
		if(node == null){
			System.out.println("LCA doesn't exist for given values : " + value1 +" and : " + value2);
			return null;
		}
		
		if(node.getData() > value1 && node.getData() > value2)
			return LowestCommonAncestor(node.getLeftNode(), value1, value2);
		if(node.getData() < value1 && node.getData() < value2)
			return LowestCommonAncestor(node.getRightNode(), value1, value2);
		
		return node;
	}

	@Override
	public boolean isBinaryTreeBST(BinaryTreeNode<Integer> node){
		boolean isLeftSubTreeBST = (node.getLeftNode() == null) || (
				node.getLeftNode() != null && node.getLeftNode().getData() < node.getData() && 
				isBinaryTreeBST(node.getLeftNode()));
		if(!isLeftSubTreeBST)
			return false;
		
		return (node.getRightNode() == null) || (
				node.getRightNode() != null && node.getRightNode().getData() > node.getData() &&
				isBinaryTreeBST(node.getRightNode()));
	}

	@Override
	public boolean isBinaryTreeBST2(BinaryTreeNode<Integer> node){
		return isBinaryTreeBST2(node.getLeftNode(), node.getData(), 0) && 
				isBinaryTreeBST2(node.getRightNode(), 9999999, node.getData());
	}
	
	private boolean isBinaryTreeBST2(BinaryTreeNode<Integer> node, int maxLimit, int minLimit){
		return ((node.getLeftNode() == null) || (
		node.getLeftNode() != null && node.getLeftNode().getData() < node.getData() && 
				node.getLeftNode().getData() < maxLimit &&
		isBinaryTreeBST2(node.getLeftNode(), Math.min(maxLimit, node.getLeftNode().getData()), minLimit))) 
		&& 
		((node.getRightNode() == null) || (
		node.getRightNode() != null && node.getRightNode().getData() > node.getData() &&
				node.getRightNode().getData() > minLimit &&
		isBinaryTreeBST2(node.getRightNode(), maxLimit, Math.max(minLimit, node.getRightNode().getData()))));
	}

	// Time Complexity - O(n)
	// Space Complexity - O(1)
	@Override
	public BinaryTreeNode<Integer> insertAnElement(BinaryTreeNode<Integer> node, int dataToInsert) {
		if (node == null)
			return new BinaryTreeNode<Integer>(dataToInsert, null, null);
		if (dataToInsert < node.data) {
			if (node.leftNode == null)
				node.leftNode = new BinaryTreeNode<Integer>(dataToInsert, null, null);
			else insertAnElement(node.leftNode, dataToInsert);
			return node;
		}
		if (dataToInsert > node.data) {
			if (node.rightNode == null)
				node.rightNode = new BinaryTreeNode<Integer>(dataToInsert, null, null);
			else insertAnElement(node.rightNode, dataToInsert);
			return node;
		}
		throw new RuntimeException("dataToInsert is already available");
	}

	// Time complexity - O(n)
	// Space complexity - O(1)
	@Override
	public BinaryTreeNode<Integer> deletingAnElement(BinaryTreeNode<Integer> node, int dataToDelete) {
		if (node == null) return node;
		if (node.data != dataToDelete) {
			node.leftNode = deletingAnElement(node.leftNode, dataToDelete);
			node.rightNode = deletingAnElement(node.rightNode, dataToDelete);
			return node;
		}
		if (node.leftNode == null && node.rightNode == null)
			return null;
		if (node.leftNode != null && node.rightNode != null) {
			BinaryTreeNode<Integer> maxNodeOnLeft = node.leftNode;
			while (maxNodeOnLeft.rightNode != null)
				maxNodeOnLeft = maxNodeOnLeft.rightNode;
			node.data = maxNodeOnLeft.data;    // Swap with node.data with node having maximum value on left sub-tree.
			// As maxNodeOnLeft data is contained by node after swapping, we will delete the maxNodeOnLeft data hereon
			node.leftNode = deletingAnElement(maxNodeOnLeft, maxNodeOnLeft.data);
			return node;
		}

		return node.leftNode != null ? node.leftNode : node.rightNode;
	}

	// Time Complexity - O(n)
	// Space complexity - O(1)
	@Override
	public BinaryTreeNode<Integer> findKthSmallestElementInBST(BinaryTreeNode<Integer> root, int k) {
		return (BinaryTreeNode<Integer>)findKthSmallest(root, k, 0)[1];
	}

	// Object[] array hold count at index 0 and node at index 1
	private Object[] findKthSmallest(BinaryTreeNode<Integer> node, int k, int count) {
		if (node == null)
			return new Object[]{count, node};

		// Check left sub-tree
		Object[] leftResults = findKthSmallest(node.leftNode, k, count);
		count = (int) leftResults[0];
		if (count == new Integer(k))
			return leftResults;

		// Check the current Node
		if (++count == k)
			return new Object[] {count, node};

		// Check the right sub-tree
		return findKthSmallest(node.rightNode, k, count);
	}


	// Time Complexity - O(n)
	// Space Complexity - O(1)
	@Override
	public void BST_To_DLL(BinaryTreeNode<Integer> root) {
		// result index 0 holds front node in DLL and index 1 holds rear node in DLL
		BinaryTreeNode<Integer>[] result = convert(root);
		System.out.println("Minimum data : " + result[0].data);
		System.out.println("Maximum data : " + result[1].data);
	}

	private BinaryTreeNode<Integer>[] convert(BinaryTreeNode<Integer> node) {
		BinaryTreeNode<Integer>[] leftSubTreeResult = null;
		BinaryTreeNode<Integer>[] rightSubTreeResult = null;
		if (node.leftNode != null)
			leftSubTreeResult = convert(node.leftNode);
		if (node.rightNode != null)
			rightSubTreeResult = convert(node.rightNode);

		if (leftSubTreeResult != null && leftSubTreeResult[1] != null) {
			leftSubTreeResult[1].rightNode = node;
			node.leftNode = leftSubTreeResult[1];
		} else node.leftNode = null;

		if (rightSubTreeResult != null && rightSubTreeResult[0] != null) {
			rightSubTreeResult[0].leftNode = node;
			node.rightNode = rightSubTreeResult[0];
		} else node.rightNode = null;

		BinaryTreeNode<Integer> frontEnd = (leftSubTreeResult == null || leftSubTreeResult[0] == null) ? node : leftSubTreeResult[0];
		BinaryTreeNode<Integer> backEnd = (rightSubTreeResult == null || rightSubTreeResult[1] == null) ? node : rightSubTreeResult[1];
		return new BinaryTreeNode[] {frontEnd, backEnd};
	}


	// Time complexity -- O(3n) -- O(n)
	// Space Complexity -- O(n)  -- for array
	@Override
	public BinaryTreeNode<Integer> sortedDLL_To_BST(BinaryTreeNode<Integer> root) {
		int length = 0;
		BinaryTreeNode<Integer> node = root;
		while (node != null) {
			length++;
			node = node.rightNode;
		}
		node = root;
		BinaryTreeNode<Integer>[] arr = new BinaryTreeNode[length];
		for (int i = 0; i < length; i++) {
			arr[i] = node;
			node = node.rightNode;
		}

		return arrayToBST(arr);
	}


	// Time Complexity -- O(n)
	// Space Complexity -- O(1)
	@Override
	public BinaryTreeNode<Integer> arrayToBST(BinaryTreeNode<Integer>[] arr) {
		return arrayToBST_Convert(arr, 0, arr.length-1);
	}

	private BinaryTreeNode<Integer> arrayToBST_Convert(BinaryTreeNode<Integer>[] arr, int startIndex, int endIndex){
		if (endIndex < startIndex) return null;

		int midIndex = startIndex + (endIndex - startIndex)/2;
		if ((endIndex - startIndex) % 2 != 0) midIndex++;

		return new BinaryTreeNode<Integer>(arr[midIndex].data, arrayToBST_Convert(arr, startIndex, midIndex -1),
				arrayToBST_Convert(arr, midIndex +1, endIndex));
	}

	// Time Complexity -- O(n)
	// Space Complexity -- O(1)
	@Override
	public BinaryTreeNode<Integer> arrayToBST2(int[] arr, int startIndex, int endIndex){
		if (endIndex < startIndex)
			return null;

		int midIndex = startIndex + (endIndex - startIndex)/2;
		if ((endIndex - startIndex) % 2 != 0) midIndex++;
		return new BinaryTreeNode<Integer>(arr[midIndex],
				arrayToBST2(arr, startIndex, midIndex -1), arrayToBST2(arr, midIndex +1, endIndex));
	}

	// Time Complexity -- O(2n) ~~ O(n) ~~ One n for calculating length, One n for processing the logic
	// Space Complexity -- O(1) ~~ no temporary space required. Here, we are creating new Tree which will take space equals to LinkedList.
	@Override
	public BinaryTreeNode<Integer> sortedSLL_To_BST(BinaryTreeNode<Integer> root) {
		int length = 0;
		BinaryTreeNode<Integer> node = root;
		while (node != null) {
			length++;
			// node.rightNode -- is same as node.getNext() for LinkedList
			node = node.rightNode;
		}
		return SLL_To_BST(root, 0, length-1);
	}

	private BinaryTreeNode<Integer> SLL_To_BST(BinaryTreeNode<Integer> node, int startIndex, int endIndex) {
		// This is just act as a counter which tells how many sub-tree will be created under the root node
		if (endIndex < startIndex) return null;
		int midIndex = startIndex + (endIndex - startIndex)/2;
		if ((endIndex - startIndex) % 2 != 0) midIndex++;
		// We are creating new node with given data. First left subTree will be created and then root node.
		// At each step of creation, node is pushed to next node and reference of node would be changed at each step. So, we are traversing through Linked List.
		BinaryTreeNode<Integer> rootNode =  new BinaryTreeNode<>(node.data,
				SLL_To_BST(node, startIndex, midIndex-1), null);
		// node.rightNode -- is same as node.getNext() for LinkedList
		node = node.rightNode;
		// Right node is created after left and root node
		rootNode.rightNode = SLL_To_BST(node, midIndex +1, endIndex);
		return rootNode;
	}

	// Time complexity -- O(n)
	// Space complexity -- O(1)
	@Override
	public void printAllElementsInBSTBetweenK1andK2(BinaryTreeNode<Integer> node, int k1, int k2) {
		if (node == null) return;
		if (node.data == k1 || node.data == k2) {
			System.out.print(node.data + " ");
			return;
		}
		if (node.data >= k1 && node.data <= k2) {
			printAllElementsInBSTBetweenK1andK2(node.leftNode, k1, k2);
			System.out.print(node.data + " ");
			printAllElementsInBSTBetweenK1andK2(node.rightNode, k1, k2);
			return;
		}
		if (node.data <= k1) {
			printAllElementsInBSTBetweenK1andK2(node.rightNode, k1, k2);
			return;
		}
		if (node.data >= k2) {
			printAllElementsInBSTBetweenK1andK2(node.leftNode, k1, k2);
			return;
		}
	}


	public static void main(String[] args) {
		BSTOperation obj = new BSTOperation();
		BinaryTreeNode<Integer> root = obj.createBST();
		obj.printAllElementsInBSTBetweenK1andK2(root, 37, 87);
		//BinaryTreeNode<Integer> node = obj.arrayToBST2(new int[] {1, 2, 3, 4, 5, 6, 7}, 0, 6);
		System.out.println();
		//System.out.println(obj.findKthSmallestElementInBST(root, 4).data);
	}

}
