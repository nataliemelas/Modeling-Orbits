package OrbitalNMK;

import org.opensourcephysics.controls.SimulationControl;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextField;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.opensourcephysics.controls.AbstractSimulation;
import org.opensourcephysics.controls.SimulationControl;
import org.opensourcephysics.display.*;
import org.opensourcephysics.frames.*;

public class KeplersThirdLaw extends AbstractSimulation {
	// create a display frame named d
	PlotFrame d = new PlotFrame("X", "Y", "Orbital Simulation");

	// time
	double timeStep = 10000;

	// EARTH
	double massEarth = (5.972 * Math.pow(10, 10));
	double vxEarth = 0;
	double vyEarth = 25000;
	double vx0Earth = vxEarth;
	double vy0Earth = vyEarth;
	double axEarth = 0;
	double ayEarth = 0;
	double xpositionEarth = (1.5 * Math.pow(10, 11));
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

	// for proving the relationship
	boolean ellipse = false;
	double XmH = 0;
	double a2 = 0;
	double b2 = 0;
	boolean time = false;
	int counter = 0;
	double period = 0;
	boolean NewEllipse = false;
	int xpcounter = 0;
	double precision = Math.pow(10, 9);
	JFrame data = new JFrame("Data");
	PlotFrame SMTgraph = new PlotFrame("SemiMajor Axis Cubed", "Time Squared",
			"SemiMajor Axis Cubed vs. Time Squared Graph for different Ellipses");
	Trail SMTline = new Trail();
	TextField introSlopes = new TextField("The Constant k such at t^2 = k*a^3 for this Ellipse is: ");
	TextField slopes = new TextField();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();
	String xPlus = "-";
	String yPlus = "-";
	double newXCenter = 0;
	double newYCenter = 0;
	int labelpy = 0;
	PlotFrame EnergyGraph = new PlotFrame("Number of Timesteps Passed", "Total Energy", "Is Energy Conserved?");
	PlotFrame VXGraph = new PlotFrame("Time", "Velocity", "V vx T Graph");
	double KE = 0;
	double PE = 0;
	double KEsun = 0;

	double KE1 = KE;
	double PE1 = PE;
	double KEsun1 = KEsun;
	int ecount = 0;

	@Override
	public void initialize() {
		slopes.setLocation(0, 0);
		slopes.setSize(500, 50);
		panel.setLocation(0, 0);
		panel.add(introSlopes);
		// EnergyGraph.setVisible(true);
		// VXGraph.setVisible(true);
		data.setVisible(true);
		SMTgraph.setVisible(true);
		SMTgraph.addDrawable(SMTline);
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

		if (NewEllipse == false) {
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

			// at this time
			counter++;

			// this gets the Ellipse
			if (ellipse == false) {
				Collections.sort(EllipseX);
				Xmin = EllipseX.get(0).doubleValue();
				if (SS.get(1).getXposition() > (Xmin + (xpositionEarth - Xmin) / 2) && SS.get(1).getYposition() < 0) {
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
						d.setMarkerColor(xpcounter, Color.cyan, Color.cyan);
						d.append(xpcounter, i, ((Math.sqrt(1 - (XmH / a2))) * SemiMinor) + Ycenter);
						d.append(xpcounter, i, ((-Math.sqrt(1 - (XmH / a2))) * SemiMinor) + Ycenter);
					}
					ellipse = true;
				}
			}

			// This gets the time it took to make the ellipse
			if (ellipse == true) {
				time = true;
			}
			if (time == true) {
				if (SS.get(1).getXposition() > (xpositionEarth - precision)
						&& (SS.get(1).getYposition() > -precision && SS.get(1).getYposition() < precision)) {
					period = counter * timeStep;
					SMTgraph.append(xpcounter, Math.pow(period, 2), Math.pow(SemiMajor, 3));
					SMTgraph.setMarkerColor(xpcounter, Color.DARK_GRAY, Color.DARK_GRAY);
					SMTline.addPoint(Math.pow(period, 2), Math.pow(SemiMajor, 3));
					SMTline.color = Color.orange;

					// printing the value out
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

					DecimalFormat df = new DecimalFormat("##0.##E0");
					System.out.println("The Equation of the Ellipse is: " + "\n" + "(x" + xPlus + df.format(newXCenter)
							+ ")^2/" + df.format(SemiMajor) + "^2 + (y" + yPlus + df.format(newYCenter) + ")^2/"
							+ df.format(SemiMinor) + "^2 = 1");
					System.out.println("The Constant k such at t^2 = k*a^3 for this Ellipse is: " + "\n"
							+ +(Math.pow(period, 2) / Math.pow(SemiMajor, 3)) + "");
					System.out.println("And 4pi^2/gm = " + ((4 * (Math.pow(Math.PI, 2))) / (G * SS.get(0).getM())));
					System.out.println(" ");

					// label.setText("The Equation of the Ellipse is: " + "\n" + "(x" + xPlus +
					// df.format(newXCenter)
					// + ")^2/" + df.format(SemiMajor) + "^2 + (y" + yPlus + df.format(newYCenter) +
					// ")^2/"
					// + df.format(SemiMinor) + "^2 = 1");
					// JLabel label1 = new JLabel();
					// label1.setText((Math.pow(period, 2) / Math.pow(SemiMajor, 3)) + "");
					// System.out.println(labelpy);
					// label.setLocation(100, 100 * labelpy);
					// labelpy++;
					// label1.setLocation(100, 100 * labelpy);
					// labelpy++;
					// data.add(label);
					// data.add(label1);
					// slopes.setText((Math.pow(period, 2) / Math.pow(SemiMajor, 3)) + "");
					// slopes.setForeground(Color.MAGENTA);
					// panel.setLocation(0, (xpcounter + 1) * 50);
					// panel.add(slopes);
					// //data.add(panel);
					// label.setText((Math.pow(period, 2) / Math.pow(SemiMajor, 3)) + "");
					// data.add(label);
					NewEllipse = true;
				}
			}

			KE1 = KE;
			PE1 = PE;
			KEsun1 = KEsun;

			KE = 0.5 * SS.get(1).getM() * Math.pow(distance(0, 0, SS.get(1).getVx(), SS.get(1).getVy()), 2);
			PE = -(G * SS.get(1).getM() * SS.get(0).getM()) / distance(SS.get(0).getXposition(),
					SS.get(0).getYposition(), SS.get(1).getXposition(), SS.get(1).getYposition());
			KEsun = 0.5 * SS.get(0).getM() * Math.pow(distance(0, 0, SS.get(0).getVx(), SS.get(0).getVy()), 2);
			if (ecount > 0) {
				EnergyGraph.append(0, ecount, ((KE + KEsun + PE - KE1 - KEsun1 - PE1)));
				EnergyGraph.append(1, ecount, KE + KEsun + PE);
			}
			ecount++;
			// at very top of orbit the energy is funky
			//
			// KE = 0.5 * SS.get(1).getM() * Math.pow(distance(0, 0, SS.get(1).getVx(),
			// SS.get(1).getVy()), 2);
			// PE = -(G * SS.get(1).getM() * SS.get(0).getM()) /
			// distance(SS.get(0).getXposition(),
			// SS.get(0).getYposition(), SS.get(1).getXposition(),
			// SS.get(1).getYposition());
			// KEsun = 0.5 * SS.get(0).getM() * Math.pow(distance(0, 0, SS.get(0).getVx(),
			// SS.get(0).getVy()), 2);
			//
			// EnergyGraph.append(xpcounter, counter, (KE + KEsun + PE));
			VXGraph.append(xpcounter, counter, distance(SS.get(1).getVx(), SS.get(1).getVy(), 0, 0));
			// System.out.println(distance(SS.get(1).getVx(), SS.get(1).getVy(), 0, 0));
			// // EnergyGraph.setMarkerColor(xpcounter, Color.green, Color.green);
			//
			// System.out.println("KE: " + KE + " PE: " + PE + " Total: " + (KE + PE));
			//
			// if (xpcounter == 4) {
			// System.out.println("KE: " + KE + " PE: " + PE + " Total: " + (KE + PE));
			// }
		}
		// starting new ellipse
		else if (NewEllipse == true) {
			if ((xpositionEarth - (Math.pow(10, 10) * xpcounter)) > 4.5E10) {
				xpcounter++;

				// SUN
				SS.get(0).setVx(vxSun);
				SS.get(0).setVy(-(massEarth * vyEarth) / massSun);
				SS.get(0).setVx0(vxSun);
				SS.get(0).setVy0(vySun);
				SS.get(0).setAx(axSun);
				SS.get(0).setAy(aySun);
				SS.get(0).setXposition(xpositionSun);
				SS.get(0).setYposition(ypositionSun);
				SunOrbit.clear();

				KE = 0;
				PE = 0;
				KEsun = 0;

				// slopes.setLocation(0, slopes.getHeight() * xpcounter);
				// slopes = new TextField();
				// slopes.setSize(500, 50);
				// panel = new JPanel();

				// EARTH
				SS.get(1).setVx(vxEarth);
				SS.get(1).setVy(vyEarth);
				SS.get(1).setVx0(vx0Earth);
				SS.get(1).setVy0(vy0Earth);
				SS.get(1).setAx(axEarth);
				SS.get(1).setAy(ayEarth);
				xpositionEarth = xpositionEarth - (Math.pow(10, 10) * xpcounter);
				SS.get(1).setXposition(xpositionEarth);
				SS.get(1).setYposition(ypositionEarth);
				EarthOrbit.clear();

				// ARRAYS
				EllipseY.clear();
				EllipseX.clear();

				// for proving the relationship
				ellipse = false;
				time = false;
				counter = 0;
				period = 0;
				NewEllipse = false;
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
		SimulationControl.createApp(new KeplersThirdLaw());
	}

	public double distance(double xp1, double yp1, double xp2, double yp2) {
		return Math.sqrt(Math.pow((xp1 - xp2), 2) + Math.pow((yp1 - yp2), 2));
	}

	public double Fg(double m1, double m2, double r) {
		return (G * m1 * m2) / Math.pow(r, 2);
	}

}
