/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dotphysics;

import javax.swing.JFrame;

/**
 *
 * @author odin
 */
public class DotPhysics {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		JFrame jf = new JFrame("DotPhysics");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(500, 300);
		jf.add(new DrawingPanel());
		jf.setVisible(true);
	}
}
