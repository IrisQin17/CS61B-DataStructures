public class NBody{
	private static String starfield="images/starfield.jpg";


	public static double readRadius(String path){
		In in = new In(path);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String path){
		In in = new In(path);
		int number = in.readInt();
		in.readDouble();
		Body[] planets = new Body[number];
		for(int i = 0; i < number; i++){
			planets[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),
								in.readDouble(),in.readDouble(),in.readString());
		}			
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] planets = readBodies(filename);

		/**background music*/
		StdAudio.play("audio/2001.mid");

 		/**drawing picture*/
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.enableDoubleBuffering();
		for (double i = 0; i<=T; i+=dt){
			double[] xForces=new double[planets.length];
			double[] yForces=new double[planets.length];
			for (int j = 0; j<planets.length; j++){
				xForces[j]=planets[j].calcNetForceExertedByX(planets);
				yForces[j]=planets[j].calcNetForceExertedByY(planets);
			}
			for (int k = 0; k<planets.length; k++){
				planets[k].update(dt, xForces[k], yForces[k]);
			}

			StdDraw.picture(0,0, starfield);
			for (Body p:planets){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}

		/**printing the universe*/
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}	
    }
}