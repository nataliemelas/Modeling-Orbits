package OrbitalNMK;

import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.DisplayFrame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.*;

public class KeplersSecondLaw extends AbstractSimulation {
	// create a display frame named d
	PlotFrame d = new PlotFrame("X", "Y", "Orbital Simulation");

	// time
	double timeStep = 10000;
	// double interval = 170;

	// EARTH
	double massEarth = (5.972 * Math.pow(10, 10));
	double vxEarth = 0;
	double vyEarth = 15000;
	double vx0Earth = vxEarth;
	double vy0Earth = vyEarth;
	double axEarth = 0;
	double ayEarth = 0;
	double xpositionEarth = (150 * Math.pow(10, 9));
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
	ArrayList<Double> EllipseY = new ArrayList<Double>();
	ArrayList<Double> EllipseX = new ArrayList<Double>();
	double Xmax = 0;
	double Xmin = 0;
	double Ymax = 0;
	double Ymin = 0;
	double Xcenter = 0;
	double Ycenter = 0;
	double SemiMajor = 0;
	double SemiMinor = 0;
	int counter = 0; // 1367
	double xposition = xpositionEarth;
	double yposition = ypositionEarth;
	double S = 0;
	double a = 0;
	double b = 0;
	double c = 0;

	// for proving the law
	PlotFrame ATgraph = new PlotFrame("Number of Timesteps Past", "Area Covered in Timestep",
			"Area Covered vs. Time Graph for the Earth");
	double area = 0;

	@Override
	public void initialize() {
		d.setPreferredMinMax(-2E11, 2E11, -2E11, 2E11);
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

			// -F*(x1-x2)/r
			Fx = -F * (SS.get(0).getXposition() - SS.get(1).getXposition()) / (distance(SS.get(0).getXposition(),
					SS.get(0).getYposition(), SS.get(1).getXposition(), SS.get(1).getYposition()));

			// F*(y1-y2)/r
			Fy = F * (SS.get(0).getYposition() - SS.get(1).getYposition()) / (distance(SS.get(0).getXposition(),
					SS.get(0).getYposition(), SS.get(1).getXposition(), SS.get(1).getYposition()));

			// acceleration
			if (i == 0) {
				SS.get(i).setAx(-Fx / SS.get(i).getM());
				SS.get(i).setAy(-Fy / SS.get(i).getM());
			} else if (i == 1) {
				SS.get(i).setAx(-Fx / SS.get(i).getM());
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
			xposition = SS.get(1).getXposition();
			yposition = SS.get(1).getYposition();

			SS.get(i).setXposition((SS.get(i).getVx0() * timeStep) + SS.get(i).getXposition());
			SS.get(i).setYposition((SS.get(i).getVy0() * timeStep) + SS.get(i).getYposition());

			// set x,y
			SS.get(i).setXY(SS.get(i).getXposition(), SS.get(i).getYposition());
			if (i == 0) {
				SunOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
				// add points and then find the greatest
			}
			if (i == 1) {
				EarthOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
				EllipseX.add(SS.get(i).getXposition());
				EllipseY.add(SS.get(i).getYposition());
			}
		}

		// TRYING TO SHOW THAT THE SUM IS ALWAYS CONSTANT
		// if (counter == interval) {
		// }
		// UPDATE AREA --> HOW???

		// Heron's Formula
		// S=(A+B+C)/2
		// area = sqrt(s(s-a)(s-b)(s-c))
		// area = 0.5*base*height;

		// distance(double xp1, double yp1, double xp2, double yp2)
		a = distance(xposition, yposition, SS.get(1).getXposition(), SS.get(1).getYposition());
		b = distance(xposition, yposition, SS.get(0).getXposition(), SS.get(0).getYposition());
		c = distance(SS.get(1).getXposition(), SS.get(1).getYposition(), SS.get(0).getXposition(),
				SS.get(0).getYposition());
		S = (a + b + c) / 2;
		area = Math.sqrt(S * (S - a) * (S - b) * (S - c));
		ATgraph.append(1, timeStep * counter, area);
		// System.out.println(area);
		// add counter
		counter++;
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
		SimulationControl.createApp(new KeplersSecondLaw());
	}

	public double distance(double xp1, double yp1, double xp2, double yp2) {
		return Math.sqrt(Math.pow((xp1 - xp2), 2) + Math.pow((yp1 - yp2), 2));
	}

	public double Fg(double m1, double m2, double r) {
		return (G * m1 * m2) / Math.pow(r, 2);
	}

}
