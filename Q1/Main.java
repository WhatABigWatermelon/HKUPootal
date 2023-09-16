package practice;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
	static String preOrderResult = "";
	static HashMap<Character, Integer> inOrderMap = null;
	static int nodeCount = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input the result of prOrder traversal: ");
		preOrderResult = scanner.nextLine();
		System.out.println("Please input the result of inOrder traversal: ");
		String inOrderResult = scanner.nextLine();
		scanner.close();

		nodeCount = preOrderResult.length();

		assert (!isEmpty(preOrderResult) && !isEmpty(inOrderResult)
				&& (preOrderResult.length() == inOrderResult.length()));

		BinaryTreeNode root = new BinaryTreeNode(preOrderResult.charAt(0));

		inOrderMap = generateMapFromInOrder(inOrderResult, nodeCount);

		findDirectSucc(root, 0, 0, nodeCount - 1);

		printPostOrder(root);

	}

	private static boolean isEmpty(String s) {
		return s != null && s != "";
	}

	private static HashMap<Character, Integer> generateMapFromInOrder(String inOrderResult, int length) {
		HashMap<Character, Integer> result = new HashMap<>();
		for (int i = 0; i < length; i++) {
			result.put(inOrderResult.charAt(i), i);
		}
		return result;
	}

	private static void findDirectSucc(BinaryTreeNode parent, int parentPreOrderPosition, int begin, int end) {

		if (begin >= end) {
			return;
		}

		int parentInOrderPosition = (int) inOrderMap.get(parent.value);
		int leftSubTreeSize = parentInOrderPosition - begin;
		int rightSubTreeSize = end - parentInOrderPosition;

		if (leftSubTreeSize > 0) {

			int leftChildPosition = parentPreOrderPosition + 1;

			assert (leftChildPosition < nodeCount); // check invalid input

			BinaryTreeNode leftChild = new BinaryTreeNode(preOrderResult.charAt(leftChildPosition));
			parent.left = leftChild;
			findDirectSucc(leftChild, leftChildPosition, begin, parentInOrderPosition - 1);
		}
		if (rightSubTreeSize > 0) {

			int rightChildPosition = parentPreOrderPosition + leftSubTreeSize + 1;

			assert (rightChildPosition < nodeCount); // check invalid input

			BinaryTreeNode rightChild = new BinaryTreeNode(preOrderResult.charAt(rightChildPosition));
			parent.right = rightChild;
			System.out.println("WYL: " + rightChild.value + " " + rightChildPosition);
			findDirectSucc(rightChild, rightChildPosition, parentInOrderPosition + 1, end);
		}
	}

	private static void printPostOrder(BinaryTreeNode root) {
		if (root == null) {
			return;
		}
		printPostOrder(root.left);
		printPostOrder(root.right);
		System.out.print(root.value);
	}
}
