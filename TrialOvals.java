package OrbitalNMK;

import org.opensourcephysics.frames.PlotFrame;

public class TrialOvals {
	public static void main(String[] args) {
		PlotFrame d = new PlotFrame("X", "Y", "Orbital Simulation");
		d.setVisible(true);
		d.setDefaultCloseOperation(3);
		//d.setPreferredMinMax(-2E11, 2E11, -1E11, 1E11);
		 double Xmin = -2.2E10;
		 double Xmax = 1.5E11;
		 double Ymin = -5.7E10;
		 double Ymax = 5.7E10;
		 double Xcenter = 2.4E11;
		 double Ycenter = 1.1E11;
		 double SemiMajor = 8.6E10;
		 double SemiMinor = 5.7E10;

		// double Xmin = -2E10;
		// double Xmax = 1.5E11;
		// double Ymin = -5E10;
		// double Ymax = 5E10;
		// double Xcenter = 8.5E10;
		// double Ycenter = 0;
		// double SemiMajor = 6.5E10;
		// double SemiMinor = 5E10;

//		double Xmin = -2E10;
//		double Xmax = 15E10;
//		double Ymin = -4E10;
//		double Ymax = 6E10;
//		double Xcenter = 8.5E10;
//		double Ycenter = 1E10;
//		double SemiMajor = 6.5E10;
//		double SemiMinor = 5E10;

		// double Xmin = -4;
		// double Xmax = 6;
		// double Ymin = -1;
		// double Ymax = 3;
		// double Xcenter = 1;
		// double Ycenter = 1;
		// double SemiMajor = 5;
		// double SemiMinor = 2;
		double XmH = 0;
		double a2 = 0;
		double b2 = 0;
		

		for (double i = Xmin; i < Xmax; i = i + 1E8) {
			System.out.println(i);
			XmH = Math.pow((i - Xcenter), 2);
			a2 = Math.pow(SemiMajor, 2);
			System.out.println((1 - (XmH / a2)) + Ycenter);
			// XmH/a2 + (y-k)^2/b2 = 1
			// y = +- sqrt (1-(XmH/a2)) + k
			d.append(0, i, (Math.sqrt(1 - (XmH / a2)))*SemiMinor + Ycenter);
			d.append(0, i, -Math.sqrt(1 - (XmH / a2))*SemiMinor + Ycenter);
		}
	}
}
