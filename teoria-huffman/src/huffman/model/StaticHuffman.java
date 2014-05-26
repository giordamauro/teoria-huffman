package huffman.model;

import huffman.impl.HuffmanList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticHuffman<T> {

	private final HuffmanComposite rootNode;
	private final int totalValues;

	private final Map<T, HuffmanSymbol<T>> valueSymbolMap;

	public StaticHuffman(T[] values) {

		totalValues = values.length;
		valueSymbolMap = calculateProbabilities(values);

		List<HuffmanSymbol<T>> symbols = new ArrayList<HuffmanSymbol<T>>(valueSymbolMap.values());
		rootNode = getHuffmanTree(symbols);
	}

	public List<Integer> getCodeForValue(T value) {

		HuffmanSymbol<T> symbol = valueSymbolMap.get(value);
		if (symbol == null) {
			throw new IllegalStateException(String.format("There is not any symbol for value '%s'", value));
		}

		List<Integer> code = new ArrayList<Integer>();
		setBinaryCode(rootNode, symbol, code);

		List<Integer> unmutableList = Collections.unmodifiableList(code);

		return unmutableList;
	}

	private Map<T, HuffmanSymbol<T>> calculateProbabilities(T[] values) {

		Map<T, HuffmanSymbol<T>> probabilitiesMap = new HashMap<T, HuffmanSymbol<T>>();

		for (T value : values) {

			HuffmanSymbol<T> symbol = probabilitiesMap.get(value);

			if (symbol == null) {
				symbol = new HuffmanSymbol<T>(0, value);
				probabilitiesMap.put(value, symbol);
			}

			double probability = symbol.getProbability();
			symbol.setProbability(probability + (double) 1 / totalValues);
		}

		return probabilitiesMap;
	}

	private HuffmanComposite getHuffmanTree(List<HuffmanSymbol<T>> symbols) {

		HuffmanList list = new HuffmanList(symbols);

		while (list.size() > 1) {

			HuffmanNode smallestNode = list.popLast();
			HuffmanNode secondSmallerNode = list.popLast();

			HuffmanNode compositeNode = new HuffmanComposite(secondSmallerNode, smallestNode);

			list.insertInOrder(compositeNode);
		}

		HuffmanComposite treeRootNode = (HuffmanComposite) list.popLast();

		if (treeRootNode.getProbability() != 1D) {
			throw new IllegalStateException("Result tree probability sum does not complete 1.0");
		}

		return treeRootNode;
	}

	private void setBinaryCode(HuffmanComposite treeNode, HuffmanSymbol<?> symbol, List<Integer> currentSequence) {

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
