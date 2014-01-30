/**
 * Theseus. Labyrinth builder library.
 * 
 * @copyright (C) 2014 David Henar Palacin.
 * @license GNU General Public License version 3 or later, see license.txt
 */

package org.shadoware.theseus.basic;

import java.util.ArrayList;
import java.util.List;

import org.shadoware.theseus.Connector;
import org.shadoware.theseus.Labyrinth;
import org.shadoware.theseus.Node;
import org.shadoware.theseus.Position;
import org.shadoware.theseus.UnconnectedConnector;


public class BasicLabyrinth implements Labyrinth {
	
	private List<BasicNode> build = new ArrayList<BasicNode>();

	
	public void generate(int size) {
		buildNodes(size);
		closeUnconnectedConnectors();
	}

	public Node[] nodes() {
		return build.toArray(new Node[build.size()]);
	}
	
	
	private void buildNodes(int size) {
		while (build.size() < size) {
			try {
				BasicNode freeNode = getRandomFreeLabyrinthNode();
				BasicNode nextNode = new BasicNode();
				connectNodes(freeNode, nextNode);
				build.add(nextNode);
			}
			catch (EmptyLabyrinthException e) {
				build.add(new BasicNode());
			} 
			catch (ConnectionException e) {
			}
		}	
	}
	
	private BasicNode getRandomFreeLabyrinthNode() throws EmptyLabyrinthException {
		int randomPosition = getRandomValue(build.size());
		try {
			return build.get(randomPosition);
		}
		catch (Exception e) {
			throw new EmptyLabyrinthException();
		}
	}
	
	private void connectNodes(BasicNode freeNode, BasicNode nextNode) throws ConnectionException {
		try {
			int freeNodeConnector = getRandomFreeConnector(freeNode);
			Position nextPosition = freeNode.getConnectorPosition(freeNodeConnector);
			if (isPositionFreeToConnect(nextPosition)) {
				interconnectNodes(freeNode, nextNode, freeNodeConnector); 
			}
			else {
				closeInvalidConnector(freeNode, freeNodeConnector);
				throw new ConnectionException();
			}
		} catch (ClosedNodeException e) {
			throw new ConnectionException();
		}
	}
	
	private int getRandomFreeConnector(BasicNode node) throws ClosedNodeException {
		if (isOpened(node)) {
			Connector[] connectors = node.connectors();
			int randomConnector;
			do {
				randomConnector = getRandomValue(connectors.length);
			}
			while ( !(connectors[randomConnector] instanceof UnconnectedConnector) );
			return randomConnector;
		}
		else {
			throw new ClosedNodeException();
		}
	}
	
	private boolean isOpened(BasicNode node) {
		for (Connector connector : node.connectors()) {
			if (connector instanceof UnconnectedConnector) {
				return true;
			}
		}
		return false;
	}
	
	private int getRandomValue(int limit) {
		return (int) (Math.random() * limit);
	}
	
	private void interconnectNodes(BasicNode baseNode, BasicNode newNode, int baseConnector) {
		int newConnector = BasicNode.inverseConnector(baseConnector);
		newNode.connectNodeAt(baseNode, newConnector);
		baseNode.connectNodeAt(newNode, baseConnector);
	}
	
	private void closeInvalidConnector(BasicNode node, int connector) {
		node.close(connector);
	}
	
	private boolean isPositionFreeToConnect(Position position) {
		for (BasicNode node : build) {
			if (node.position().equals(position)) {
				return false;
			}
		}
		return true;
	}
	
	private void closeUnconnectedConnectors() {
		for (BasicNode node : build) {
			node.closeAllUnconnected();
		}
	}
}
