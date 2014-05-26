package main;

import huffman.model.StaticHuffman;

public class Main {

	public static void main(String[] args) {

		String[] values = { "a", "a", "a", "a", "a", "a", "b", "b", "b", "c", "d" };

		// TODO: semi-estatic Huffman permite ir agregando nuevos simbolos y
		// re-calculando las probabilidades y toda la estructura.

		// siempre sumar los dos menores y crear un nodo nuevo.
		// Miro el nodo menor: si es simbolo armo el nodo, sino paso a laburar
		// adentro del nodo.

		StaticHuffman<String> huffman = new StaticHuffman<String>(values);

		System.out.println(huffman.getCodeForValue("d"));
	}
}
