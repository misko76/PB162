package cz.muni.fi.pb162.project.geometry;

/**
 * This class represents a circle in two-dimensional space
 *
 * @author Michal Soltis
 */
public class Circle extends GeneralRegularPolygon implements Measurable, Circular {
    /**
     * Calls parent's constructor with given values
     *
     * @param center middle of circle object
     * @param radius distance from center to perimeter
     */
    public Circle(Vertex2D center, double radius) {
        super(center, Integer.MAX_VALUE, radius);
        super.setColor(Color.RED);
    }

    /**
     * Parameter-free constructor that calls parametric constructor with default values
     */
    public Circle() {
        this(new Vertex2D(0, 0), 1);
    }

    @Override
    public String toString() {
        return "Circle: center=" + super.getCenter() + ", radius=" + super.getRadius();
    }

    @Override
    public double getEdgeLength() {
        return 0;
    }
}
