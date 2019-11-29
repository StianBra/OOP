package no.ntnu.imt3281.click_areas;

import java.util.Arrays;

/**
 * See : https://stackoverflow.com/a/8721483
 * @author Dean Povey
 *
 */
public class Polygon {
	private final Point[] points; // Points making up the boundary
	
	public Polygon(Point[] points) {
		this.points = Arrays.copyOf(points, points.length);
	}
	
	/**
     * Return true if the given point is contained inside the boundary.
     * See: http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
     * @param test The point to check
     * @return true if the point is inside the boundary, false otherwise
     *
     */
    public boolean contains(Point test) {
      int i;
      int j;
      boolean result = false;
      for (i = 0, j = points.length - 1; i < points.length; j = i++) {
        if ((points[i].getY() > test.getY()) != (points[j].getY() > test.getY()) &&
            (test.getX() < (points[j].getX() - points[i].getX()) * (test.getY() - points[i].getY()) / (points[j].getY()-points[i].getY()) + points[i].getX())) {
          result = !result;
         }
      }
      return result;
    }
}