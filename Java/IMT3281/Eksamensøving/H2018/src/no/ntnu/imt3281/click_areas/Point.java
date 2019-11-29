package no.ntnu.imt3281.click_areas;import java.util.Locale;

/**
 * See : https://stackoverflow.com/a/8721483
 * @author Dean Povey
 *
 */

public class Point {
	private final double x;
	private final double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return String.format("%.1f : %.1f", x, y);
	}
}
