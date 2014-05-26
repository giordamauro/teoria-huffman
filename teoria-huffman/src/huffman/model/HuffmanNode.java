package huffman.model;

public interface HuffmanNode extends Comparable<HuffmanNode> {

	double getProbability();

	boolean isComposite();

}
