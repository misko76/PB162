package cz.muni.fi.pb162.project.geometry;

/**
 * This class represents a square in two-dimensional space
 *
 * @author Michal Soltis
 */
public class Square extends GeneralRegularPolygon {
    /**
     * Calls parent's constructor with given values
     *
     * @param center   middle of square object
     * @param diameter maximum distance between two points on a circle's perimeter
     */
    public Square(Vertex2D center, double diameter) {
        super(center, 4, diameter / 2);
    }

    /**
     * Calls parent's constructor with the parameters from given circular object
     *
     * @param obj circular object to create similar square
     */
    public Square(Circular obj) {
        super(obj.getCenter(), 4, obj.getRadius());
    }

    @Override
    public String toString() {
        return "Square: vertices=" +
                getVertex(0) + " " + getVertex(1) + " " +
                getVertex(2) + " " + getVertex(3);
    }
}
