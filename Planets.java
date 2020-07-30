package OrbitalNMK;

import org.opensourcephysics.display.Circle;

public class Planets extends Circle {
	// extends circle to make the balls circles
	// initializes the attributes for a ball
	double m = 0; // mass
	double vx = 0; // x velocity
	double vy = 0; // y velocity
	double vx0 = 0; // x velocity
	double vy0 = 0; // y velocity
	double ax = 0;
	double ay = 0;
	double xposition = 0;
	double yposition = 0;

	public double getM() {
		return m;
	}

	public void setM(double m) {
		this.m = m;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getVx0() {
		return vx0;
	}

	public void setVx0(double vx0) {
		this.vx0 = vx0;
	}

	public double getVy0() {
		return vy0;
	}

	public void setVy0(double vy0) {
		this.vy0 = vy0;
	}

	public double getAx() {
		return ax;
	}

	public void setAx(double ax) {
		this.ax = ax;
	}

	public double getAy() {
		return ay;
	}

	public void setAy(double ay) {
		this.ay = ay;
	}

	public double getXposition() {
		return xposition;
	}

	public void setXposition(double xposition) {
		this.xposition = xposition;
	}

	public double getYposition() {
		return yposition;
	}

	public void setYposition(double yposition) {
		this.yposition = yposition;
	}

	/**
	 * Creates a planet with a unique set of attributes. Planets(mass, x position y
	 * position, x velocity, y velocity, old velocity, x acceleration, y
	 * acceleration, xposition, yposition).
	 *
	 */
	public Planets(double m, double vx, double vy, double vx0, double vy0, double ax, double ay, double xposition,
			double yposition) {
		this.m = m;
		this.vx = vx;
		this.vy = vy;
		this.vx0 = vx0;
		this.vy0 = vy0;
		this.ax = ax;
		this.ay = ay;
		this.xposition = xposition;
		this.yposition = yposition;

	}

}
