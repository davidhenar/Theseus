package org.shadoware.theseus;

public interface Labyrinth {
	
	void generate(int size);
	Node[] nodes();
}