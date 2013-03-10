/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dotphysics;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JPanel;

/**
 *
 * @author odin
 */
public class DrawingPanel extends JPanel implements ActionListener {

    private static final int COLUMNS = 10;
    
	Date now = new Date();
	
	Dot root;
	
	public DrawingPanel() {
		root = new StaticDot(20,20);
		Dot root2 = new StaticDot(20,70);
		Dot root3 = new StaticDot(20,120);
		root.addDot(root2);
		root2.addDot(root3);
		Dot lastUpper = root, lastLower = root2, lastLowerLower = root3;
		Dot d1, d2, d3;
		int i;
        
		for (i = 0; i < (COLUMNS - 2); i++) {
			d1 = new Dot(20 + 50 * (i+1), 20);
			d2 = new Dot(20 + 50 * (i+1), 70);
			d3 = new Dot(20 + 50 * (i+1), 120);
			lastUpper.addDot(d1);
			lastUpper.addDot(d2);
			lastLower.addDot(d2);
			lastLower.addDot(d1);
			lastLower.addDot(d3);
			lastLowerLower.addDot(d2);
			lastLowerLower.addDot(d3);
			d1.addDot(d2);
			d2.addDot(d3);
			lastUpper = d1;
			lastLower = d2;
			lastLowerLower = d3;
			if (i == 2) {
				d1.setMass(50);
				d2.setMass(50);
				d3.setMass(50);
			}
		}
		d1 = new StaticDot(20 + 50 * (i+1), 20);
		d2 = new StaticDot(20 + 50 * (i+1), 70);
		d3 = new StaticDot(20 + 50 * (i+1), 120);
		lastUpper.addDot(d1);
		lastUpper.addDot(d2);
		lastLower.addDot(d2);
		lastLower.addDot(d1);
		lastLower.addDot(d3);
		lastLowerLower.addDot(d2);
		lastLowerLower.addDot(d3);
		d1.addDot(d2);
		d2.addDot(d3);
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
        
        float t;
        
        // BUG
		//Date dt = new Date();
        //t = (float) (dt.getTime() - now.getTime()) / 1000.0f;
        
        t = 0.01f;
        System.out.println(t);
		root.doForces();
		root.finalizeForces(t);
		root.drawDot(g);
        // BUG
		//now = dt;
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
	}
	
}
