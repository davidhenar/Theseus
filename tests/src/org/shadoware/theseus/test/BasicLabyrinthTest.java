/**
 * Theseus. Labyrinth builder library.
 * 
 * @copyright (C) 2014 David Henar Palacin.
 * @license GNU General Public License version 3 or later, see license.txt
 */

package org.shadoware.theseus.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.shadoware.theseus.Connector;
import org.shadoware.theseus.Node;
import org.shadoware.theseus.Position;
import org.shadoware.theseus.UnconnectedConnector;
import org.shadoware.theseus.basic.BasicLabyrinth;
import org.shadoware.theseus.basic.BasicNode;

public class BasicLabyrinthTest {
	
	private BasicLabyrinth labyrinth;
	private Node[] nodes;
	
	@Before
	public void init() {
		labyrinth = new BasicLabyrinth();
		labyrinth.generate(7);
		nodes = labyrinth.nodes();
	}

	@Test
	public void size() {
		assertTrue(nodes.length == 7);
	}
	
	@Test
	public void fisrtNode() {
		BasicNode firstNode = (BasicNode) nodes[0];
		assertTrue(firstNode.position().equals(new Position(0, 0)));
	}
	
	@Test
	public void notUnconnectedConnectors() {
		for (Node node : nodes) {
			assertFalse(hasUnconnectedConnectors(node));
		}
	}
	
	@Test
	public void atLeastOneNodeConectionForEveryNode() {
		for (Node node : nodes) {
			assertTrue(hasOneNodeConnected(node));
		}
	}
	
	private boolean hasUnconnectedConnectors(Node node) {
		for (Connector connector : node.connectors()) {
			if (connector instanceof UnconnectedConnector) {
				return true;
			}
		}
		return false;
	}
	
	private boolean hasOneNodeConnected(Node node) {
		for (Connector connector : node.connectors()) {
			if (connector instanceof BasicNode) {
				return true;
			}
		}
		return false;
	}
}
