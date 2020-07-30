package OrbitalExtensionNMK;

import org.opensourcephysics.controls.SimulationControl;
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

public class MarsLanding extends AbstractSimulation {
	// Mercury - 58 million km
	// Venus - 108 million km
	// Earth - 150 million km
	// Mars - 228 million km -- abt 300 days to get there
	// Jupiter - 778 million km
	// Saturn - 1,429 million km
	// Uranus - 2,875 million km
	// Neptune - 4,504 million km
	// Pluto - 5,900 million km

	// solar eclipse

	// create a display frame named d
	PlotFrame d = new PlotFrame("X", "Y", "Orbital Simulation");

	// time
	double timeStep = 10000;

	// Sun
	double massSun = 2E30;
	double vxSun = 0;
	double vySun = 0;
	double vx0Sun = vxSun;
	double vy0Sun = vySun;
	double axSun = 0;
	double aySun = 0;
	double xpositionSun = 0;
	double ypositionSun = 0;
	Trail SunOrbit = new Trail();

	// Mercury
	double massMercury = 0.3285E24;
	double vxMercury = 0;
	double vyMercury = 47400;
	double vx0Mercury = vxMercury;
	double vy0Mercury = vyMercury;
	double axMercury = 0;
	double ayMercury = 0;
	double xpositionMercury = 5.791E10;
	double ypositionMercury = 0;
	Trail MercuryOrbit = new Trail();

	// Venus
	double massVenus = 4.8675E24;
	double vxVenus = 0;
	double vyVenus = 35000;
	double vx0Venus = vxVenus;
	double vy0Venus = vyVenus;
	double axVenus = 0;
	double ayVenus = 0;
	double xpositionVenus = 1.0821E11;
	double ypositionVenus = 0;
	Trail VenusOrbit = new Trail();

	// Earth
	double massEarth = 6E24;
	double vxEarth = 0;
	double vyEarth = 29822;
	double vx0Earth = vxEarth;
	double vy0Earth = vyEarth;
	double axEarth = 0;
	double ayEarth = 0;
	double xpositionEarth = 1.5E11;
	double ypositionEarth = 0;
	Trail EarthOrbit = new Trail();

	// Moon
	double massMoon = 7E22;
	double vxMoon = 0;
	double vyMoon = 30822; // 30822
	double vx0Moon = vxMoon;
	double vy0Moon = vyMoon;
	double axMoon = 0;
	double ayMoon = 0;
	// 1.51
	double xpositionMoon = 1.504E11;
	double ypositionMoon = 0;
	Trail MoonOrbit = new Trail();

	// Rocket
	double massRocket = 7E7;
	double vxRocket = 0;
	// double vyRocket = 34200;
	// double vyRocket = 33680;
	double vyRocket = 31000;
	double vx0Rocket = vxRocket;
	double vy0Rocket = vyRocket;
	double axRocket = 0;
	double ayRocket = 0;
	double xpositionRocket = 1.506E11;
	double ypositionRocket = 0;
	Trail RocketOrbit = new Trail();

	// Realistic ocket
	double massRRocket = 1E7;
	double vxRRocket = 0;
	// double vyRocket = 34200;
	// double vyRocket = 33680;
	double vyRRocket = 30950;
	double vx0RRocket = vxRRocket;
	double vy0RRocket = vyRRocket;
	double axRRocket = 0;
	double ayRRocket = 0;
	double xpositionRRocket = 1.5015E11;
	double ypositionRRocket = 0;
	Trail RRocketOrbit = new Trail();

	// Mars
	double massMars = 6.4171E23;
	// double vxMars = 0;
	// double vyMars = 24070;
	double vxMars = -24070;
	double vyMars = 0;
	double vx0Mars = vxMars;
	double vy0Mars = vyMars;
	double axMars = 0;
	double ayMars = 0;
	// double xpositionMars = 2.28E11;
	// double ypositionMars = 0;
	double xpositionMars = 0;
	double ypositionMars = 2.28E11;
	Trail MarsOrbit = new Trail();

	// Jupiter
	double massJupiter = 1.898E27;
	double vxJupiter = 0;
	double vyJupiter = 13100;
	double vx0Jupiter = vxJupiter;
	double vy0Jupiter = vyJupiter;
	double axJupiter = 0;
	double ayJupiter = 0;
	double xpositionJupiter = 7.8E11;
	double ypositionJupiter = 0;
	Trail JupiterOrbit = new Trail();

	// Jupiter Moon
	double massJMoon = 4.8E22;
	double vxJMoon = 0;
	double vyJMoon = 14000;
	double vx0JMoon = vxJMoon;
	double vy0JMoon = vyJMoon;
	double axJMoon = 0;
	double ayJMoon = 0;
	double xpositionJMoon = 7.695E11;
	double ypositionJMoon = 0;
	Trail JMoonOrbit = new Trail();

	// Jupiter Moon 2
	double massJMoon2 = 9E22;
	double vxJMoon2 = 0;
	double vyJMoon2 = 15000;
	double vx0JMoon2 = vxJMoon2;
	double vy0JMoon2 = vyJMoon2;
	double axJMoon2 = 0;
	double ayJMoon2 = 0;
	double xpositionJMoon2 = 7.92E11;
	double ypositionJMoon2 = 0;
	Trail JMoon2Orbit = new Trail();

	// Jupiter Moon 3
	// double massJMoon3 = 1E22;
	// double vxJMoon3 = 0;
	// double vyJMoon3 = 10000;
	// double vx0JMoon3 = vxJMoon3;
	// double vy0JMoon3 = vyJMoon3;
	// double axJMoon3 = 0;
	// double ayJMoon3 = 0;
	// double xpositionJMoon3 = 7.85E11;
	// double ypositionJMoon3 = 0;
	// Trail JMoon3Orbit = new Trail();
	double massJMoon3 = 1.48E23;
	double vxJMoon3 = 0;
	double vyJMoon3 = 10880 + 13100;
	double vx0JMoon3 = vxJMoon3;
	double vy0JMoon3 = vyJMoon3;
	double axJMoon3 = 0;
	double ayJMoon3 = 0;
	double xpositionJMoon3 = 7.8107E11;
	double ypositionJMoon3 = 0;
	Trail JMoon3Orbit = new Trail();

	// Europa (jupiter moon)
	// orbital period 85 hours
	double massEuropa = 4.8E22;
	double vxEuropa = 0;
	double vyEuropa = 14380 + 13100;
	double vx0Europa = vxEuropa;
	double vy0Europa = vyEuropa;
	double axEuropa = 0;
	double ayEuropa = 0;
	double xpositionEuropa = 7.8067E11;
	double ypositionEuropa = 0;
	Trail EuropaOrbit = new Trail();

	// // Jupiter Moon 3
	// double massJMoon4 = 1E22;
	// double vxJMoon4 = 0;
	// double vyJMoon4 = 10000;
	// double vx0JMoon4 = vxJMoon;
	// double vy0JMoon4 = vyJMoon;
	// double axJMoon3 = 0;
	// double ayJMoon3 = 0;
	// double xpositionJMoon3 = 7.9E11;
	// double ypositionJMoon3 = 0;
	// Trail JMoon3Orbit = new Trail();

	// Saturn
	double massSaturn = 5.683E26;
	double vxSaturn = 0;
	double vySaturn = 9600;
	double vx0Saturn = vxSaturn;
	double vy0Saturn = vySaturn;
	double axSaturn = 0;
	double aySaturn = 0;
	double xpositionSaturn = 1.433E12;
	double ypositionSaturn = 0;
	Trail SaturnOrbit = new Trail();

	// Uranus
	double massUranus = 8.681E25;
	double vxUranus = 0;
	double vyUranus = 6800;
	double vx0Uranus = vxUranus;
	double vy0Uranus = vyUranus;
	double axUranus = 0;
	double ayUranus = 0;
	// 2.871 trillion m
	double xpositionUranus = 2.871E12;
	double ypositionUranus = 0;
	Trail UranusOrbit = new Trail();

	// Neptune
	double massNeptune = 1.024E26;
	double vxNeptune = 0;
	double vyNeptune = 5400;
	double vx0Neptune = vxNeptune;
	double vy0Neptune = vyNeptune;
	double axNeptune = 0;
	double ayNeptune = 0;
	// 4.495 trillion m
	double xpositionNeptune = 4.495E12;
	double ypositionNeptune = 0;
	Trail NeptuneOrbit = new Trail();

	// Astroid
	double massAstroid = 1.024E10;
	// slingshot status
	// double vxAstroid = -5000;
	// double vyAstroid = -7500;
	double vxAstroid = -48000;
	double vyAstroid = -46000;
	double vx0Astroid = vxAstroid;
	double vy0Astroid = vyAstroid;
	double axAstroid = 0;
	double ayAstroid = 0;
	// 4.495 trillion m
	double xpositionAstroid = 2.495E12;
	double ypositionAstroid = 2.495E12;
	Trail AstroidOrbit = new Trail();

	// Gravity
	double G = (6.67408 * Math.pow(10, -11));
	double Fx = 0;
	double Fy = 0;
	double F = 0;

	ArrayList<Planets> SS = new ArrayList<Planets>();
	ArrayList<Double> EllipseY = new ArrayList<Double>();
	ArrayList<Double> EllipseX = new ArrayList<Double>();
	boolean SendIt = false;
	boolean Land = false;
	boolean FullSend = false;

	@Override
	public void initialize() {
		// d.setPreferredMinMax(-4.55E12, 4.55E12, -4.55E12, 4.55E12);
		// earth sun moon d.setPreferredMinMax(-1.7E11, 1.7E11, -1.7E11, 1.7E11);
		d.setPreferredMinMax(-2.32E11, 2.32E11, -2.32E11, 2.32E11);
		// jmoons d.setPreferredMinMax(5E11, 9E11, -1E11, 9E11);
		d.setVisible(true);
		d.addDrawable(EarthOrbit);
		d.addDrawable(SunOrbit);
		d.addDrawable(VenusOrbit);
		d.addDrawable(MarsOrbit);
		d.addDrawable(MercuryOrbit);
		d.addDrawable(MoonOrbit);
		d.addDrawable(JupiterOrbit);
		d.addDrawable(SaturnOrbit);
		d.addDrawable(UranusOrbit);
		d.addDrawable(NeptuneOrbit);
		d.addDrawable(AstroidOrbit);
		d.addDrawable(RocketOrbit);
		d.addDrawable(JMoonOrbit);
		d.addDrawable(JMoon2Orbit);
		d.addDrawable(JMoon3Orbit);
		d.addDrawable(EuropaOrbit);
		d.addDrawable(RRocketOrbit);

		// sun
		Planets sun = new Planets(massSun, vxSun, vySun, vx0Sun, vy0Sun, axSun, aySun, xpositionSun, ypositionSun);
		sun.pixRadius = 5;
		sun.setXY(control.getDouble("xpositionSun"), control.getDouble("ypositionSun"));
		sun.color = Color.yellow;
		SS.add(sun);
		d.addDrawable(sun);

		// mercury
		Planets mercury = new Planets(massMercury, vxMercury, vyMercury, vx0Mercury, vy0Mercury, axMercury, ayMercury,
				xpositionMercury, ypositionMercury);
		mercury.pixRadius = 5;
		mercury.setXY(control.getDouble("xpositionMercury"), control.getDouble("ypositionMercury"));
		mercury.color = Color.magenta;
		SS.add(mercury);
		d.addDrawable(mercury);

		// venus
		Planets venus = new Planets(massVenus, vxVenus, vyVenus, vx0Venus, vy0Venus, axVenus, ayVenus, xpositionVenus,
				ypositionVenus);
		venus.pixRadius = 5;
		venus.setXY(control.getDouble("xpositionVenus"), control.getDouble("ypositionVenus"));
		venus.color = Color.red;
		SS.add(venus);
		d.addDrawable(venus);

		// earth
		Planets earth = new Planets(massEarth, vxEarth, vyEarth, vx0Earth, vy0Earth, axEarth, ayEarth, xpositionEarth,
				ypositionEarth);
		earth.pixRadius = 5;
		earth.setXY(control.getDouble("xpositionEarth"), control.getDouble("ypositionEarth"));
		earth.color = Color.blue;
		SS.add(earth);
		d.addDrawable(earth);

		// moon
		Planets moon = new Planets(massMoon, vxMoon, vyMoon, vx0Moon, vy0Moon, axMoon, ayMoon, xpositionMoon,
				ypositionMoon);
		moon.pixRadius = 3;
		moon.setXY(control.getDouble("xpositionMoon"), control.getDouble("ypositionMoon"));
		moon.color = Color.cyan;
		SS.add(moon);
		d.addDrawable(moon);

		// mars
		Planets mars = new Planets(massMars, vxMars, vyMars, vx0Mars, vy0Mars, axMars, ayMars, xpositionMars,
				ypositionMars);
		mars.pixRadius = 5;
		mars.setXY(control.getDouble("xpositionMars"), control.getDouble("ypositionMars"));
		mars.color = Color.green;
		SS.add(mars);
		d.addDrawable(mars);

		// Jupiter
		Planets jupiter = new Planets(massJupiter, vxJupiter, vyJupiter, vx0Jupiter, vy0Jupiter, axJupiter, ayJupiter,
				xpositionJupiter, ypositionJupiter);
		jupiter.pixRadius = 6;
		jupiter.setXY(control.getDouble("xpositionJupiter"), control.getDouble("ypositionJupiter"));
		jupiter.color = Color.DARK_GRAY;
		SS.add(jupiter);
		d.addDrawable(jupiter);

		// Saturn
		Planets saturn = new Planets(massSaturn, vxSaturn, vySaturn, vx0Saturn, vy0Saturn, axSaturn, aySaturn,
				xpositionSaturn, ypositionSaturn);
		saturn.pixRadius = 6;
		saturn.setXY(control.getDouble("xpositionSaturn"), control.getDouble("ypositionSaturn"));
		saturn.color = Color.green;
		SS.add(saturn);
		d.addDrawable(saturn);

		// Uranus
		Planets uranus = new Planets(massUranus, vxUranus, vyUranus, vx0Uranus, vy0Uranus, axUranus, ayUranus,
				xpositionUranus, ypositionUranus);
		uranus.pixRadius = 6;
		uranus.setXY(control.getDouble("xpositionUranus"), control.getDouble("ypositionUranus"));
		uranus.color = Color.lightGray;
		SS.add(uranus);
		d.addDrawable(uranus);

		// Neptune
		Planets neptune = new Planets(massNeptune, vxNeptune, vyNeptune, vx0Neptune, vy0Neptune, axNeptune, ayNeptune,
				xpositionNeptune, ypositionNeptune);
		neptune.pixRadius = 6;
		neptune.setXY(control.getDouble("xpositionSaturn"), control.getDouble("ypositionSaturn"));
		neptune.color = Color.orange;
		SS.add(neptune);
		d.addDrawable(neptune);

		// Astroid
		Planets astroid = new Planets(massAstroid, vxAstroid, vyAstroid, vx0Astroid, vy0Astroid, axAstroid, ayAstroid,
				xpositionAstroid, ypositionAstroid);
		astroid.pixRadius = 6;
		astroid.setXY(control.getDouble("xpositionSaturn"), control.getDouble("ypositionSaturn"));
		astroid.color = Color.black;
		SS.add(astroid);
		d.addDrawable(astroid);

		// Rocket
		Planets rocket = new Planets(massRocket, vxRocket, vyRocket, vx0Rocket, vy0Rocket, axRocket, ayRocket,
				xpositionRocket, ypositionRocket);
		rocket.pixRadius = 2;
		rocket.setXY(control.getDouble("xpositionRocket"), control.getDouble("ypositionRocket"));
		rocket.color = Color.black;
		SS.add(rocket);
		d.addDrawable(rocket);

		// Jupiter Moon
		Planets jmoon = new Planets(massJMoon, vxJMoon, vyJMoon, vx0JMoon, vy0JMoon, axJMoon, ayJMoon, xpositionJMoon,
				ypositionJMoon);
		jmoon.pixRadius = 4;
		jmoon.setXY(control.getDouble("xpositionJMoon"), control.getDouble("ypositionJMoon"));
		jmoon.color = Color.blue;
		SS.add(jmoon);
		d.addDrawable(jmoon);

		// Jupiter Moon 2
		Planets jmoon2 = new Planets(massJMoon2, vxJMoon2, vyJMoon2, vx0JMoon2, vy0JMoon2, axJMoon2, ayJMoon2,
				xpositionJMoon2, ypositionJMoon2);
		jmoon2.pixRadius = 4;
		jmoon2.setXY(control.getDouble("xpositionJMoon2"), control.getDouble("ypositionJMoon2"));
		jmoon2.color = Color.GREEN;
		SS.add(jmoon2);
		d.addDrawable(jmoon2);

		// Jupiter Moon 3
		Planets jmoon3 = new Planets(massJMoon3, vxJMoon3, vyJMoon3, vx0JMoon3, vy0JMoon3, axJMoon3, ayJMoon3,
				xpositionJMoon3, ypositionJMoon3);
		jmoon3.pixRadius = 4;
		jmoon3.setXY(control.getDouble("xpositionJMoon3"), control.getDouble("ypositionJMoon3"));
		jmoon3.color = Color.magenta;
		SS.add(jmoon3);
		d.addDrawable(jmoon3);

		// Europa
		Planets europa = new Planets(massEuropa, vxEuropa, vyEuropa, vx0Europa, vy0Europa, axEuropa, ayEuropa,
				xpositionEuropa, ypositionEuropa);
		europa.pixRadius = 4;
		europa.setXY(control.getDouble("xpositionEuropa"), control.getDouble("ypositionEuropa"));
		europa.color = Color.orange;
		SS.add(europa);
		d.addDrawable(europa);

		// Realistic Rocket
		Planets rrocket = new Planets(massRRocket, vxRRocket, vyRRocket, vx0RRocket, vy0RRocket, axRRocket, ayRRocket,
				xpositionRRocket, ypositionRRocket);
		rrocket.pixRadius = 4;
		rrocket.setXY(control.getDouble("xpositionRRocket"), control.getDouble("ypositionRRocket"));
		rrocket.color = Color.black;
		SS.add(rrocket);
		d.addDrawable(rrocket);

	}

	// momentum will make it upwards
	// balance the momentum by giving the sun a negative velocity so that the system
	// has a net momentum of zero

	// they all have a force on them
	// have to take a planet and calculate the forces on it from all of the planets
	// then have to repeat this for all of the planets
	// and then update all of the accelerations/velocities/positions (trails)

	protected void doStep() {
		// speeding it up
		this.setDelayTime(1);
		// update everything else
		for (int j = 0; j < SS.size(); j++) {
			for (int i = 0; i < SS.size(); i++) {
				// if (!(j == 2 && i == 0)) {
				if (j != i) {
					// // force of gravity
					F = Fg(SS.get(j).getM(), SS.get(i).getM(), distance(SS.get(j).getXposition(),
							SS.get(j).getYposition(), SS.get(i).getXposition(), SS.get(i).getYposition()));

					// -F*(x1-x2)/r
					Fx = -F * (SS.get(j).getXposition() - SS.get(i).getXposition())
							/ (distance(SS.get(j).getXposition(), SS.get(j).getYposition(), SS.get(i).getXposition(),
									SS.get(i).getYposition()));

					// -F*(y1-y2)/r
					Fy = -F * (SS.get(j).getYposition() - SS.get(i).getYposition())
							/ (distance(SS.get(j).getXposition(), SS.get(j).getYposition(), SS.get(i).getXposition(),
									SS.get(i).getYposition()));
					// if (j == 2 && i == 0) {
					// System.out.println("Fx Sun: " + Fx);
					// System.out.println("Fy Sun: " + Fy);
					// } else if (j == 2 && i == 1) {
					// System.out.println("Fx Earth: " + Fx);
					// System.out.println("Fy Earth: " + Fy);
					// }

					// acceleration
					SS.get(j).setAx(Fx / SS.get(j).getM());
					SS.get(j).setAy(Fy / SS.get(j).getM());

					// new velocity: v = at + v0
					SS.get(j).setVx(SS.get(j).getAx() * timeStep + SS.get(j).getVx0());
					SS.get(j).setVy(SS.get(j).getAy() * timeStep + SS.get(j).getVy0());

					// old velocity
					SS.get(j).setVx0(SS.get(j).getVx());
					SS.get(j).setVy0(SS.get(j).getVy());
				}
				// }
			}
		}

		// update position
		for (int i = 0; i < SS.size(); i++) {
			SS.get(i).setXposition((SS.get(i).getVx0() * timeStep) + SS.get(i).getXposition());
			SS.get(i).setYposition((SS.get(i).getVy0() * timeStep) + SS.get(i).getYposition());

			if (SendIt == false) {
				if (SS.get(11).getXposition() < 0 && SS.get(11).getYposition() < 1E11) {
					SS.get(11).setVx0(SS.get(11).getVx0() - 2.5E4);
					SS.get(11).setVy0(SS.get(11).getVy0() - 1.35E4);
					SendIt = true;
					// SS.get(4).setVx0(SS.get(6).getVx0() + 7E15);
				}
			}
			// if (Land == false) {
			// if (SS.get(11).getXposition() < -2.235E11) {
			// SS.get(11).setVx0(SS.get(11).getVx0() + 3.31E4);
			// SS.get(11).setVy0(SS.get(11).getVy0() + 1.81E4);
			// Land = true;
			// // SS.get(4).setVx0(SS.get(6).getVx0() + 7E15);
			// }
			// }
			if (FullSend == false) {
				if (SS.get(16).getXposition() < 0 && SS.get(16).getYposition() < .5E11) {
					// SS.get(16).setVx0(SS.get(16).getVx0() - 2.5E4);
					// SS.get(16).setVy0(SS.get(16).getVy0() - 1.349999E3);
					SS.get(16).setVy0(SS.get(16).getVy0() - 1.350027E3);
					FullSend = true;
					// SS.get(4).setVx0(SS.get(6).getVx0() + 7E15);
				}
			}

			// if (i == 6 && SS.get(6).getXposition() < 5E10 && SS.get(6).getXposition() >
			// -5E10
			// && SS.get(6).getYposition() < 0) {
			// if (SendIt == false) {
			// SendIt = true;
			// FullOrb++;
			// if (FullOrb == 2) {
			// System.out.println("HIIIIII");
			// SS.get(6).setVy0(SS.get(6).getVy0() - 7E15);
			// SS.get(6).setVx0(SS.get(6).getVx0() + 7E15);
			// }
			// }
			// }
			// if (i == 6 && SendIt == true && SS.get(6).getYposition() > 0 && FullOrb == 1)
			// {
			// SendIt = true;
			// }

			// set x,y
			SS.get(i).setXY(SS.get(i).getXposition(), SS.get(i).getYposition());

			// updating the trails
			if (i == 0) {
				SunOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 1) {
				MercuryOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 2) {
				VenusOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 3) {
				EarthOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 4) {
				MoonOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 5) {
				MarsOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 6) {
				JupiterOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 7) {
				SaturnOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 8) {
				UranusOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 9) {
				NeptuneOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 10) {
				AstroidOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 11) {
				RocketOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 12) {
				JMoonOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 13) {
				JMoon2Orbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 14) {
				JMoon3Orbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 15) {
				EuropaOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			} else if (i == 16) {
				RRocketOrbit.addPoint(SS.get(i).getXposition(), SS.get(i).getYposition());
			}
		}

	}

	public void reset() {
		// Sun
		control.setValue("xpositionSun", xpositionSun);
		control.setValue("ypositionSun", ypositionSun);
		control.setValue("massSun", massSun);
		control.setValue("vxSun", vxSun);
		control.setValue("vySun", vySun);
		control.setValue("axSun", axSun);
		control.setValue("aySun", aySun);
		// Earth
		control.setValue("xpositionEarth", xpositionEarth);
		control.setValue("ypositionEarth", ypositionEarth);
		control.setValue("massEarth", massEarth);
		control.setValue("vxEarth", vxEarth);
		control.setValue("vyEarth", vyEarth);
		control.setValue("axEarth", axEarth);
		control.setValue("ayEarth", ayEarth);
		// Moon
		control.setValue("xpositionMoon", xpositionMoon);
		control.setValue("ypositionMoon", ypositionMoon);
		control.setValue("massMoon", massMoon);
		control.setValue("vxMoon", vxMoon);
		control.setValue("vyMoon", vyMoon);
		control.setValue("axMoon", axMoon);
		control.setValue("ayMoon", ayMoon);
		// Venus
		control.setValue("xpositionVenus", xpositionVenus);
		control.setValue("ypositionVenus", ypositionVenus);
		control.setValue("massVenus", massVenus);
		control.setValue("vxVenus", vxVenus);
		control.setValue("vyVenus", vyVenus);
		control.setValue("axVenus", axVenus);
		control.setValue("ayVenus", ayVenus);
		// Mars
		control.setValue("xpositionMars", xpositionMars);
		control.setValue("ypositionMars", ypositionMars);
		control.setValue("massMars", massMars);
		control.setValue("vxMars", vxMars);
		control.setValue("vyMars", vyMars);
		control.setValue("axMars", axMars);
		control.setValue("ayMars", ayMars);
		// Mercury
		control.setValue("xpositionMercury", xpositionMercury);
		control.setValue("ypositionMercury", ypositionMercury);
		control.setValue("massMercury", massMercury);
		control.setValue("vxMercury", vxMercury);
		control.setValue("vyMercury", vyMercury);
		control.setValue("axMercury", axMercury);
		control.setValue("ayMercury", ayMercury);
		// Jupiter
		control.setValue("xpositionJupiter", xpositionJupiter);
		control.setValue("ypositionJupiter", ypositionJupiter);
		control.setValue("massJupiter", massJupiter);
		control.setValue("vxJupiter", vxJupiter);
		control.setValue("vyJupiter", vyJupiter);
		control.setValue("axJupiter", axJupiter);
		control.setValue("ayJupiter", ayJupiter);
		// Saturn
		control.setValue("xpositionSaturn", xpositionSaturn);
		control.setValue("ypositionSaturn", ypositionSaturn);
		control.setValue("massSaturn", massSaturn);
		control.setValue("vxSaturn", vxSaturn);
		control.setValue("vySaturn", vySaturn);
		control.setValue("axSaturn", axSaturn);
		control.setValue("aySaturn", aySaturn);
		// Uranus
		control.setValue("xpositionUranus", xpositionUranus);
		control.setValue("ypositionUranus", ypositionUranus);
		control.setValue("massUranus", massUranus);
		control.setValue("vxUranus", vxUranus);
		control.setValue("vyUranus", vyUranus);
		control.setValue("axUranus", axUranus);
		control.setValue("ayUranus", ayUranus);
		// Neptune
		control.setValue("xpositionNeptune", xpositionNeptune);
		control.setValue("ypositionNeptune", ypositionNeptune);
		control.setValue("massNeptune", massNeptune);
		control.setValue("vxNeptune", vxNeptune);
		control.setValue("vyNeptune", vyNeptune);
		control.setValue("axNeptune", axNeptune);
		control.setValue("ayNeptune", ayNeptune);
		// Astroid
		control.setValue("xpositionAstroid", xpositionAstroid);
		control.setValue("ypositionAstroid", ypositionAstroid);
		control.setValue("massAstroid", massAstroid);
		control.setValue("vxAstroid", vxAstroid);
		control.setValue("vyAstroid", vyAstroid);
		control.setValue("axAstroid", axAstroid);
		control.setValue("ayAstroid", ayAstroid);
		// Rocket
		control.setValue("xpositionRocket", xpositionRocket);
		control.setValue("ypositionRocket", ypositionRocket);
		control.setValue("massRocket", massRocket);
		control.setValue("vxRocket", vxRocket);
		control.setValue("vyRocket", vyRocket);
		control.setValue("axRocket", axRocket);
		control.setValue("ayRocket", ayRocket);
		// Realistic Rocket
		control.setValue("xpositionRRocket", xpositionRRocket);
		control.setValue("ypositionRRocket", ypositionRRocket);
		control.setValue("massRRocket", massRRocket);
		control.setValue("vxRRocket", vxRRocket);
		control.setValue("vyRRocket", vyRRocket);
		control.setValue("axRRocket", axRRocket);
		control.setValue("ayRRocket", ayRRocket);
		// Jupiter Moon
		control.setValue("xpositionJMoon", xpositionJMoon);
		control.setValue("ypositionJMoon", ypositionJMoon);
		control.setValue("massJMoon", massJMoon);
		control.setValue("vxJMoon", vxJMoon);
		control.setValue("vyJMoon", vyJMoon);
		control.setValue("axJMoon", axJMoon);
		control.setValue("ayJMoon", ayJMoon);
		// Jupiter Moon 2
		control.setValue("xpositionJMoon2", xpositionJMoon2);
		control.setValue("ypositionJMoon2", ypositionJMoon2);
		control.setValue("massJMoon2", massJMoon2);
		control.setValue("vxJMoon2", vxJMoon2);
		control.setValue("vyJMoon2", vyJMoon2);
		control.setValue("axJMoon2", axJMoon2);
		control.setValue("ayJMoon2", ayJMoon2);
		// Jupiter Moon 3
		control.setValue("xpositionJMoon3", xpositionJMoon3);
		control.setValue("ypositionJMoon3", ypositionJMoon3);
		control.setValue("massJMoon3", massJMoon3);
		control.setValue("vxJMoon3", vxJMoon3);
		control.setValue("vyJMoon3", vyJMoon3);
		control.setValue("axJMoon3", axJMoon3);
		control.setValue("ayJMoon3", ayJMoon3);
		// Europa
		control.setValue("xpositionEuropa", xpositionEuropa);
		control.setValue("ypositionEuropa", ypositionEuropa);
		control.setValue("massEuropa", massEuropa);
		control.setValue("vxEuropa", vxEuropa);
		control.setValue("vyEuropa", vyEuropa);
		control.setValue("axEuropa", axEuropa);
		control.setValue("ayEuropa", ayEuropa);
	}

	public static void main(String[] args) {
		SimulationControl.createApp(new MarsLanding());
	}

	public double distance(double xp1, double yp1, double xp2, double yp2) {
		return Math.sqrt(((xp1 - xp2) * (xp1 - xp2)) + ((yp1 - yp2) * (yp1 - yp2)));
	}

	public double Fg(double m1, double m2, double r) {
		return (G * m1 * m2) / (r * r);
	}

}
