package huffman.model;

public class HuffmanSymbol<T> implements HuffmanNode {

	private double probability;

	private final T value;

	public HuffmanSymbol(double probability, T value) {
		this.probability = probability;
		this.value = value;
	}

	public double getProbability() {
		return probability;
	}

	void setProbability(double probability) {
		this.probability = probability;
	}

	public T getValue() {
		return value;
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
		return false;
	}

}
