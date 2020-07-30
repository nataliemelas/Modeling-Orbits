package OrbitalNMK;

import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.DisplayFrame;
import java.awt.Color;
import java.util.ArrayList;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.*;

public class BasicCircleOrbit extends AbstractSimulation {
	// create a display frame named d
	DisplayFrame d = new DisplayFrame("X", "Y", "Orbital Simulation");

	// time
	double timeStep = 10000;

	// EARTH
	double massEarth = (5.972 * Math.pow(10, 28));
	double vxEarth = 0;
	double vyEarth = 30000;
	double vx0Earth = vxEarth;
	double vy0Earth = vyEarth;
	double axEarth = 0;
	double ayEarth = 0;
	double xpositionEarth = (149.6 * Math.pow(10, 9));
	double ypositionEarth = 0;
	Trail EarthOrbit = new Trail();

	// SUN
	double massSun = (1.989 * Math.pow(10, 30));
	double vxSun = 0;
	double vySun = -(massEarth * vyEarth) / massSun;
	double vx0Sun = vxSun;
	double vy0Sun = vySun;
	double axSun = 0;
	double aySun = 0;
	double xpositionSun = 0;
	double ypositionSun = 0;
	Trail SunOrbit = new Trail();

	// GRAVITY
	double G = (6.67408 * Math.pow(10, -11));
	double Fx = 0;
	double Fy = 0;
	double F = 0;

	ArrayList<Planets> SS = new ArrayList<Planets>();

	@Override
	public void initialize() {
		d.setPreferredMinMax(-2E11, 2E11, -1E11, 1E11);
		d.setVisible(true);
		d.addDrawable(EarthOrbit);
		d.addDrawable(SunOrbit);
		// sun
		Planets sun = new Planets(massSun, vxSun, vySun, vx0Sun, vy0Sun, axSun, aySun, xpositionSun, ypositionSun);
		sun.pixRadius = 5;
		sun.setXY(control.getDouble("xpositionSun"), control.getDouble("ypositionSun"));
		sun.color = Color.red;
		SS.add(sun);
		d.addDrawable(sun);
		// earth
		Planets earth = new Planets(massEarth, vxEarth, vyEarth, vx0Earth, vy0Earth, axEarth, ayEarth, xpositionEarth,
				ypositionEarth);
		earth.pixRadius = 5;
		earth.setXY(control.getDouble("xpositionEarth"), control.getDouble("ypositionEarth"));
		earth.color = Color.blue;
		SS.add(earth);
		d.addDrawable(earth);
	}

	// momentum will make it upwards
	// balance the momentum by giving the sun a negative velocity so that the system
	// has a net momentum of zero

	protected void doStep() {
		// speeding it up
		this.setDelayTime(1);
		// update everything else
		for (int i = 0; i < SS.size(); i++) {
			// // force of gravity
			F = Fg(SS.get(0).getM(), SS.get(1).getM(), distance(SS.get(0).getXposition(), SS.get(0).getYposition(),
					SS.get(1).getXposition(), SS.get(1).getYposition()));
			// F = (SS.get(i).getM() * Math.pow((distance(SS.get(i).getVx(),
			// SS.get(i).getVy(), 0, 0)), 2)
			// / (distance(SS.get(0).getXposition(), SS.get(0).getYposition(),
			// SS.get(1).getXposition(),
			// SS.get(1).getYposition())));

			// -F*(x1-x2)/r
			Fx = -F * (SS.get(0).getXposition() - SS.get(1).getXposition()) / (distance(SS.get(0).getXposition(),
					SS.get(0).getYposition(), SS.get(1).getXposition(), SS.get(1).getYposition()));

			// F*(y1-y2)/r
			Fy = -F * (SS.get(0).getYposition() - SS.get(1).getYposition()) / (distance(SS.get(0).getXposition(),
					SS.get(0).getYposition(), SS.get(1).getXposition(), SS.get(1).getYposition()));

			// acceleration
			if (i == 0) {
				SS.get(i).setAx(Fx / SS.get(i).getM());
				SS.get(i).setAy(Fy / SS.get(i).getM());
			} else if (i == 1) {
				SS.get(i).setAx(Fx / SS.get(i).getM());
				SS.get(i).setAy(Fy / SS.get(i).getM());
			}
			// velocity: v(t) = at+v0
			SS.get(i).setVx(SS.get(i).getAx() * timeStep + SS.get(i).getVx0());
			SS.get(i).setVy(SS.get(i).getAy() * timeStep + SS.get(i).getVy0());
			// old velocity
			SS.get(i).setVx0(SS.get(i).getVx());
			SS.get(i).setVy0(SS.get(i).getVy());
		}

		// update position
		for (int i = 0; i < SS.size(); i++) {
			// // 1/2at^2 + v0t +x0
			// SS.get(i).setXposition((0.5 * SS.get(i).getAx() * Math.pow(timeStep, 2)) +
			// (SS.get(i).getVx0() * timeStep)
			// + SS.get(i).getXposition());
			// SS.get(i).setYposition((0.5 * SS.get(i).getAy() * Math.pow(timeStep, 2)) +
			// (SS.get(i).getVy0() * timeStep)
			// + SS.get(i).getYposition());
			SS.get(i).setXposition((SS.get(i).getVx0() * timeStep) + SS.get(i).getXposition());
			SS.get(i).setYposition((SS.get(i).getVy0() * timeStep) + SS.get(i).getYposition());

			// set x,y
			SS.get(i).setXY(SS.get(i).getXposition(), SS.get(i).getYposition());
			if (i == 0) {
				SunOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			}
			if (i == 1) {
				EarthOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			}
		}
	}

	public void reset() {
		// SUN
		control.setValue("xpositionSun", xpositionSun);
		control.setValue("ypositionSun", ypositionSun);
		control.setValue("massSun", massSun);
		control.setValue("vxSun", vxSun);
		control.setValue("vySun", vySun);
		control.setValue("axSun", axSun);
		control.setValue("aySun", aySun);
		// EARTH
		control.setValue("xpositionEarth", xpositionEarth);
		control.setValue("ypositionEarth", ypositionEarth);
		control.setValue("massEarth", massEarth);
		control.setValue("vxEarth", vxEarth);
		control.setValue("vyEarth", vyEarth);
		control.setValue("axEarth", axEarth);
		control.setValue("ayEarth", ayEarth);
	}

	public static void main(String[] args) {
		SimulationControl.createApp(new BasicCircleOrbit());
	}

	public double distance(double xp1, double yp1, double xp2, double yp2) {
		return Math.sqrt(Math.pow((xp1 - xp2), 2) + Math.pow((yp1 - yp2), 2));
	}

	public double Fg(double m1, double m2, double r) {
		return (G * m1 * m2) / Math.pow(r, 2);
	}

}
