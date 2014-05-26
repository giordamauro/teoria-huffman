package huffman.model;

public class HuffmanComposite implements HuffmanNode {

	private final HuffmanNode leftNode;

	private final HuffmanNode rightNode;

	public HuffmanComposite(HuffmanNode leftNode, HuffmanNode rightNode) {
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}

	public double getProbability() {
		return leftNode.getProbability() + rightNode.getProbability();
	}

	public HuffmanNode getLeftNode() {
		return leftNode;
	}

	public HuffmanNode getRightNode() {
		return rightNode;
	}

	@Override
	public int compareTo(HuffmanNode node) {
		int result = 0;

		if (node.getProbability() < this.getProbability()) {
			result = 1;
		}

		return result;
	}

	@Override
	public boolean isComposite() {
		return true;
	}

}
