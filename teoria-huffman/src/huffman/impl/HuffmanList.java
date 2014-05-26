package huffman.impl;

import huffman.model.HuffmanNode;
import huffman.model.HuffmanSymbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanList {

	private final List<HuffmanNode> nodeList = new ArrayList<HuffmanNode>();

	public <T> HuffmanList(List<HuffmanSymbol<T>> symbolList) {

		for (HuffmanSymbol<?> symbol : symbolList) {
			nodeList.add(symbol);
		}

		Collections.sort(nodeList, Collections.reverseOrder());
	}

	public void insertInOrder(HuffmanNode node) {
		nodeList.add(node);
		Collections.sort(nodeList, Collections.reverseOrder());
	}

	public HuffmanNode popLast() {
		if (nodeList.isEmpty()) {
			throw new IllegalStateException("Cannot pop last - list is empty!");
		}
		HuffmanNode node = nodeList.get(nodeList.size() - 1);
		nodeList.remove(nodeList.size() - 1);

		return node;
	}

	public int size() {
		return nodeList.size();
	}
}
