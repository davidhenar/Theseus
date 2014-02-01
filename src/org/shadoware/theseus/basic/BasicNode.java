/**
 * Theseus. Labyrinth builder library.
 * 
 * @copyright (C) 2014 David Henar Palacin.
 * @license GNU General Public License version 3 or later, see license.txt
 */

package org.shadoware.theseus.basic;

import org.shadoware.theseus.ClosedConnector;
import org.shadoware.theseus.Connector;
import org.shadoware.theseus.Node;
import org.shadoware.theseus.Position;
import org.shadoware.theseus.UnconnectedConnector;


public class BasicNode implements Node {
	
	public final static int UP_CONNECTOR = 0;
	public final static int RIGHT_CONNECTOR = 1;
	public final static int DOWN_CONNECTOR = 2;
	public final static int LEFT_CONNECTOR = 3;
	
	private final static int NUM_CONNECTORS = 4;
	
	private final Connector[] connectors = new Connector[NUM_CONNECTORS];
	private Position position = new Position(0, 0);
	
	
	public BasicNode() {
		for (int connector=0; connector<connectors.length; connector++) {
			open(connector);
		}
	}
	
	public static int inverseConnector(int connector) {
		switch (connector) {
			case UP_CONNECTOR: return DOWN_CONNECTOR;
			case DOWN_CONNECTOR: return UP_CONNECTOR;
			case RIGHT_CONNECTOR: return LEFT_CONNECTOR;
			case LEFT_CONNECTOR: return RIGHT_CONNECTOR;
		}
		return 0;
	}
	
	public void connectNodeAt(BasicNode targetNode, int connector) {
		int targetConnector = inverseConnector(connector);
		connect(targetNode, connector);
		allocate(targetNode.getConnectorPosition(targetConnector));
	}
	
	private void connect(Connector connector, int position) {
		connectors[position] = connector;
	}
	
	private void allocate(Position position) {
		this.position = new Position(position.getX(), position.getY());
	}
	
	public void closeAllUnconnected() {
		for (int connector=0; connector<connectors.length; connector++) {
			if (connectors[connector] instanceof UnconnectedConnector) {
				close(connector);
			}
		} 
	}
	
	protected void open(int connector) {
		connectors[connector] = new UnconnectedConnector();
	}
	
	protected void close(int connector) {
		connectors[connector] = new ClosedConnector(); 
	}
	
	protected Position getConnectorPosition(int connector) {
		int x = position.getX();
		int y = position.getY();
		switch (connector) {
			case UP_CONNECTOR: y++; break;
			case DOWN_CONNECTOR: y--; break;
			case RIGHT_CONNECTOR: x++; break;
			case LEFT_CONNECTOR: x--; break;
		}
		return new Position(x, y);
	}
	
	public Connector[] connectors() {
		return connectors;
	}

	public Position position() {
		return position;
	}
}
