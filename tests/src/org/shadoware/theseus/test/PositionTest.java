/**
 * Theseus. Labyrinth builder library.
 * 
 * @copyright (C) 2014 - David Henar Palacin.
 * @license GNU General Public License version 3 or later, see license.txt
 */

package org.shadoware.theseus.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.shadoware.theseus.Position;

public class PositionTest {
	
	private Position position;
	
	@Before
	public void init() {
		position = new Position(-3, 10);
	}

	@Test
	public void position() {
		assertTrue(position.getX() == -3);
		assertTrue(position.getY() == 10);
	}
	
	@Test
	public void equals() {
		Position positionEquals = new Position(-3, 10);
		Position positionNotEquals = new Position(0, -1);
		
		assertTrue(position.equals(positionEquals));
		assertTrue(!position.equals(positionNotEquals));
	}
	
	@Test
	public void string() {
		assertTrue(position.toString().equals("-3,10"));
	}
}
