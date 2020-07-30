package OrbitalNMK;

import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.frames.DisplayFrame;
import java.awt.Color;
import java.awt.TextField;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.*;

public class KeplersFirstLaw extends AbstractSimulation {
	// create a display frame named d
	PlotFrame d = new PlotFrame("X", "Y", "Orbital Simulation");

	// time
	double timeStep = 10000;

	// EARTH
	double massEarth = (5.972 * Math.pow(10, 10));
	double vxEarth = 0;
	double vyEarth = 30000;
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
	int counter = 0;
	boolean oval = false;
	double XmH = 0;
	double a2 = 0;
	double b2 = 0;
	String xPlus = "-";
	String yPlus = "-";
	double newXCenter = 0;
	double newYCenter = 0;
	TextField equation = new TextField();

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
			// F = (SS.get(i).getM() * Math.pow((distance(SS.get(i).getVx(),
			// SS.get(i).getVy(), 0, 0)), 2)
			// / (distance(SS.get(0).getXposition(), SS.get(0).getYposition(),
			// SS.get(1).getXposition(),
			// SS.get(1).getYposition())));

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
				// add points and then find the greatest
			}
			if (i == 1) {
				EarthOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
				EllipseX.add(SS.get(i).getXposition());
				EllipseY.add(SS.get(i).getYposition());
			}
		}
		if (oval == false) {
			if (SS.get(1).getXposition() > SS.get(0).getXposition() && SS.get(1).getYposition() < 0) {
				Collections.sort(EllipseX);
				Collections.sort(EllipseY);
				Xmin = EllipseX.get(0).doubleValue();
				Xmax = EllipseX.get(EllipseX.size() - 1).doubleValue();
				Ymin = EllipseY.get(0).doubleValue();
				Ymax = EllipseY.get(EllipseY.size() - 1).doubleValue();
				Xcenter = Xmax - (.5 * (Xmax - Xmin));
				Ycenter = Ymax - (.5 * (Ymax - Ymin));
				SemiMajor = (.5 * (Xmax - Xmin));
				SemiMinor = (.5 * (Ymax - Ymin));
				// center (h,k)
				// (x-h)^2/a^2 + (y-k)^2/b^2 = 1
				// (y-k)^2/b^2 = 1-(x-h)^2/a^2
				// (y-k)^2 = b^2(1-((x-h)^2/a^2))
				// y = +- sqrt[ b^2(1-((x-h)^2/a^2))] + k
				for (double i = Xmin; i < Xmax; i = i + 1E8) {
					XmH = Math.pow((i - Xcenter), 2);
					a2 = Math.pow(SemiMajor, 2);
					// XmH/a2 + (y-k)^2/b2 = 1
					// y = +- sqrt (1-(XmH/a2)) + k
					d.setMarkerColor(0, Color.cyan, Color.cyan);
					d.append(0, i, ((Math.sqrt(1 - (XmH / a2))) * SemiMinor) + Ycenter);
					d.append(0, i, ((-Math.sqrt(1 - (XmH / a2))) * SemiMinor) + Ycenter);
				}
				counter++;
			}
			if (counter > 10) {
				newXCenter = Xcenter;
				newYCenter = Ycenter;
				if (Xcenter < 0) {
					xPlus = "+";
					newXCenter = -newXCenter;
				}
				if (Ycenter < 0) {
					yPlus = "+";
					newYCenter = -newYCenter;
				}
				DecimalFormat df = new DecimalFormat("##0.###E0");
				equation.setText("The Equation of the Ellipse is: " + "\n" + "(x" + xPlus + df.format(newXCenter)
						+ ")^2/" + df.format(SemiMajor) + "^2 + (y" + yPlus + df.format(newYCenter) + ")^2/"
						+ df.format(SemiMinor) + "^2 = 1");
				equation.setForeground(Color.MAGENTA);
				equation.setLocation(0, 0);
				equation.setSize(800, 50);
				d.add(equation);
				oval = true;
			}
		}
		equation.setBounds(0, d.getHeight() - 100, d.getWidth(), 50);
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
		SimulationControl.createApp(new KeplersFirstLaw());
	}

	public double distance(double xp1, double yp1, double xp2, double yp2) {
		return Math.sqrt(Math.pow((xp1 - xp2), 2) + Math.pow((yp1 - yp2), 2));
	}

	public double Fg(double m1, double m2, double r) {
		return (G * m1 * m2) / Math.pow(r, 2);
	}

}
