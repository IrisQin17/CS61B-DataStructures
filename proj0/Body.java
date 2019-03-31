public class Body{
	public double xxPos,yyPos,xxVel,yyVel,mass;
	public String imgFileName;
	private static final double G=6.67e-11;

	/**constructor*/
	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	/**calculates the distance between two planet*/
	public double calcDistance(Body b){
		double r = Math.sqrt(Math.pow((this.xxPos-b.xxPos),2) 
					+ Math.pow((this.yyPos - b.yyPos),2));
		return r;
	}

	/**calculates the force exerted on this body by the given body.*/
	public double calcForceExertedBy(Body b){
		double r = this.calcDistance(b);
		double f = G * this.mass * b.mass / Math.pow(r,2);
		return f;
	}

	public double calcForceExertedByX(Body b){
		double f = this.calcForceExertedBy(b);
		double r = this.calcDistance(b);
		double fx = f * (b.xxPos-this.xxPos) / r;
		return fx;
	}

	public double calcForceExertedByY(Body b){
		double f = this.calcForceExertedBy(b);
		double r = this.calcDistance(b);
		double fy = f * (b.yyPos - this.yyPos) / r;
		return fy;
	}

	public double calcNetForceExertedByX(Body[] bs){
		double netfx = 0;
		for(Body b:bs){
			if (this.equals(b))
				continue;
			netfx+=this.calcForceExertedByX(b);
		}
		return netfx;
	}

	public double calcNetForceExertedByY(Body[] bs){
		double netfy = 0;
		for(Body b:bs){
			if (this.equals(b))
				continue;
			netfy+=this.calcForceExertedByY(b);
		}
		return netfy;
	}

	public void update(double dt, double fx, double fy){
		double ax = fx / this.mass;
		double ay = fy / this.mass;
		this.xxVel = this.xxVel + dt * ax;
		this.yyVel = this.yyVel + dt * ay;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos, "images/" + this.imgFileName);
	}

}