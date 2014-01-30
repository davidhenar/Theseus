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
		for (int i=0; i<connectors.length; i++) {
			connectors[i] = new UnconnectedConnector();
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
	
	public Connector[] connectors() {
		return connectors;
	}

	public Position position() {
		return position;
	}
	
	public void connectNodeAt(Node node, int connector) {
		connect(node, connector);
		allocate(node, connector);
	}
	
	public void closeAllUnconnected() {
		for (int i=0; i<connectors.length; i++) {
			if (connectors[i] instanceof UnconnectedConnector) {
				connectors[i] = new ClosedConnector();
			}
		} 
	}
	
	public void close(int connector) {
		connectors[connector] = new ClosedConnector(); 
	}
	
	public Position getConnectorPosition(int connector) {
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Connector connector : connectors) {
			sb.append(connector.toString());
			sb.append(", ");
		}
		sb.append("(");
		sb.append(position.toString());
		sb.append(")");
		return sb.toString();
	}
	
	private void connect(Connector connector, int position) {
		connectors[position] = connector;
	}
	
	private void allocate(Node node, int connector) {
		Position increment = getConnectingPositionIncrement(connector);
		int x = node.position().getX() + increment.getX();
		int y = node.position().getY() + increment.getY();
		position = new Position(x, y);
	}
	
	private Position getConnectingPositionIncrement(int connector) {
		int x = 0;
		int y = 0;
		switch (connector) {
			case UP_CONNECTOR: y--; break;
			case DOWN_CONNECTOR: y++; break;
			case RIGHT_CONNECTOR: x--; break;
			case LEFT_CONNECTOR: x++; break;
		}
		return new Position(x, y);
	}
}
