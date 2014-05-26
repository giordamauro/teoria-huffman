package huffman.impl;

import huffman.model.HuffmanComposite;
import huffman.model.HuffmanNode;
import huffman.model.HuffmanSymbol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HuffmanUtil {

	private HuffmanUtil() {

	}

	public static List<Integer> getBinaryCodeForSymbol(HuffmanComposite treeRootNode, HuffmanSymbol<?> symbol) {
		List<Integer> code = new ArrayList<Integer>();
		setBinaryCode(treeRootNode, symbol, code);

		List<Integer> unmutableList = Collections.unmodifiableList(code);

		return unmutableList;
	}

	private static void setBinaryCode(HuffmanComposite treeNode, HuffmanSymbol<?> symbol, List<Integer> currentSequence) {

		HuffmanNode leftNode = treeNode.getLeftNode();
		HuffmanNode rightNode = treeNode.getRightNode();

		if (symbol.equals(leftNode)) {
			currentSequence.add(1);
		} else if (symbol.equals(rightNode)) {
			currentSequence.add(0);
		} else {
			if (symbol.getProbability() > leftNode.getProbability()) {
				throw new IllegalStateException(String.format("Symbol '%s' isn't found", symbol.getValue()));
			}

			if (symbol.getProbability() > rightNode.getProbability()) {

				if (!leftNode.isComposite()) {
					throw new IllegalStateException(String.format("Symbol '%s' isn't found", symbol.getValue()));
				} else {
					currentSequence.add(1);
					setBinaryCode((HuffmanComposite) leftNode, symbol, currentSequence);
				}
			} else {
				if (!rightNode.isComposite()) {
					throw new IllegalStateException(String.format("Symbol '%s' isn't found", symbol.getValue()));
				} else {
					currentSequence.add(0);
					setBinaryCode((HuffmanComposite) rightNode, symbol, currentSequence);
				}
			}
		}
	}
}
