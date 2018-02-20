package org.usfirst.frc330.commands.drivecommands;

public class Waypoint {
	
	double x, y, heading;

	public Waypoint(double x, double y, double heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getHeading() {
		return heading;
	}

	@Override
	public String toString() {
		return x + ", " + y + ", " + heading;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setHeading(double heading) {
		this.heading = heading;
	}
	

}
