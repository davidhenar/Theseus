/**
 * Theseus. Labyrinth builder library.
 * 
 * @copyright (C) 2014 David Henar Palacin.
 * @license GNU General Public License version 3 or later, see license.txt
 */

package org.shadoware.theseus;

public interface Node extends Connector {
	
	Connector[] connectors();
	Position position();
}
