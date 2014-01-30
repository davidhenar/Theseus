package org.shadoware.theseus;

public interface Node extends Connector {
	
	Connector[] connectors();
	Position position();
}
