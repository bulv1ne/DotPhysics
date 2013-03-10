/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dotphysics;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author odin
 */
public class Dot {
	
	private static int idcounter = 0;
	private int id = idcounter++;
	
	private boolean forcesDone = false;
	private boolean drawingDone = false;
	
	protected float x, y;
	protected float vx, vy;
	protected float ax, ay;
	protected float r;
	protected float mass;
	protected float force;
	
	private ArrayList<Dot> connectedDots = new ArrayList<Dot>();
	
	public Dot(float x, float y) {
		this.x = x;
		this.y = y;
		vx = 0f;
		vy = 0f;
		ax = 0f;
		ay = 0f;
		r = 50f;
		mass = 10f;
		force = 100f;
	}
	
	public void addDot(Dot dot) {
		for (Dot d : connectedDots) {
			if (d.id == dot.id)
				return;
		}
		connectedDots.add(dot);
		dot.addDot(this);
	}
	
	private void force(Dot d) {
		float dx, dy, A, F;
		dx = x - d.x;
		dy = y - d.y;
		
		A = (float) Math.sqrt(dx * dx + dy * dy);
		if (A == 0f) {
			A = 0.01f;
		}
		F = getForce(A);
		ax -= F / mass * dx / A;
		ay -= F / mass * dy / A;
		d.ax += F / d.mass * dx / A;
		d.ay += F / d.mass * dy / A;
	}
	
	protected float getForce(float A) {
		return force * (A - r);
	}
	
	public void doForces() {
		if (!forcesDone) {
			forcesDone = true;
			for (Dot d : connectedDots) {
				this.force(d);
				d.doForces();
			}
		}
	}
	
	public void finalizeForces(float t) {
		if (forcesDone) {
			forcesDone = false;
			for (Dot d : connectedDots) {
				d.finalizeForces(t);
			}
			vx += ax * t;
			vy += ay * t;
			vx *= 0.9f;
			vy *= 0.9f;
			/*if (vx > 100) vx = 100;
			if (vy > 100) vy = 100;
			if (vx < -100) vx = -100;
			if (vy < -100) vy = -100;*/
			x += vx * t;
			y += vy * t;
			//reset forces
			ax = 0;
			ay = 9.81f * 5;
			drawingDone = false;
		}
	}
	
	public final void drawDot(Graphics g) {
		if (!drawingDone) {
			drawingDone = true;
			int ix, iy, w, h;
			w = 10;
			h = 10;
			ix = (int) (x - w / 2);
			iy = (int) (y - h / 2);
			g.drawOval(ix, iy, w, h);
			for (Dot d : connectedDots) {
				g.drawLine((int)x, (int)y, (int)d.x, (int)d.y);
				d.drawDot(g);
			}
		}
	}

	@Override
	public String toString() {
		return "Dot{" + "x=" + x + ", y=" + y + ", vx=" + vx + ", vy=" + vy + ", ax=" + ax + ", ay=" + ay + ", r=" + r + ", mass=" + mass + ", force=" + force + '}';
	}

	public float getForce() {
		return force;
	}

	public void setForce(float force) {
		this.force = force;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
