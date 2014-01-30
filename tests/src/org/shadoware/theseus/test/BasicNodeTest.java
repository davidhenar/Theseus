package org.shadoware.theseus.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.shadoware.theseus.ClosedConnector;
import org.shadoware.theseus.Connector;
import org.shadoware.theseus.Position;
import org.shadoware.theseus.UnconnectedConnector;
import org.shadoware.theseus.basic.BasicNode;

public class BasicNodeTest {
	
	private BasicNode node;
	
	@Before
	public void init() {
		node = new BasicNode();
	}
	
	@Test
	public void initialsMustBeUnconnected() {
		for (Connector connector : node.connectors()) {
			assertTrue(connector instanceof UnconnectedConnector);
		}
	}
	
	@Test
	public void connect() {
		BasicNode firstNode = new BasicNode();
		assertTrue(firstNode.position().equals(new Position(0, 0)));	
		
		node.connectNodeAt(firstNode, BasicNode.UP_CONNECTOR);
		assertTrue(node.position().equals(new Position(0, -1)));
		assertTrue(node.connectors()[BasicNode.UP_CONNECTOR] == firstNode);
		
		node.connectNodeAt(firstNode, BasicNode.DOWN_CONNECTOR);
		assertTrue(node.position().equals(new Position(0, 1)));
		assertTrue(node.connectors()[BasicNode.DOWN_CONNECTOR] == firstNode);
		
		node.connectNodeAt(firstNode, BasicNode.RIGHT_CONNECTOR);
		assertTrue(node.position().equals(new Position(-1, 0)));
		assertTrue(node.connectors()[BasicNode.RIGHT_CONNECTOR] == firstNode);
		
		node.connectNodeAt(firstNode, BasicNode.LEFT_CONNECTOR);
		assertTrue(node.position().equals(new Position(1, 0)));
		assertTrue(node.connectors()[BasicNode.LEFT_CONNECTOR] == firstNode);
	}
	
	@Test
	public void coloseAllUnconnectedConnectors() {
		node.closeAllUnconnected();
		for (Connector connector : node.connectors()) {
			assertTrue(connector instanceof ClosedConnector);
		}
	}
}
