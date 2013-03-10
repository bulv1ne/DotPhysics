/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dotphysics;

/**
 *
 * @author odin
 */
public class StaticDot extends Dot {

	public StaticDot(float x, float y) {
		super(x, y);
	}

	@Override
	public void finalizeForces(float t) {
		ax = 0;
		ay = 0;
		super.finalizeForces(t);
	}
	
	
}
