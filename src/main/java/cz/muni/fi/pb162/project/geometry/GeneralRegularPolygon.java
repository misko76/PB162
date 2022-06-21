package cz.muni.fi.pb162.project.geometry;

/**
 * This class represents general regular polygon in two-dimensional space
 *
 * @author Michal Soltis
 */
public class GeneralRegularPolygon implements RegularPolygon, Colored {
    private final Vertex2D center;
    private final int numEdges;
    private final double radius;
    private Color color = Color.BLACK;

    /**
     * Initializes GeneralRegularPolygon with given values
     *
     * @param center   middle of polygon object
     * @param numEdges number of edges of polygon object
     * @param radius   distance from center to perimeter
     */
    public GeneralRegularPolygon(Vertex2D center, int numEdges, double radius) {
        this.center = center;
        this.numEdges = numEdges;
        this.radius = radius;
    }

    @Override
    public Vertex2D getCenter() {
        return center;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public int getNumEdges() {
        return numEdges;
    }

    @Override
    public double getEdgeLength() {
        return 2 * radius * Math.sin(Math.PI / numEdges);
    }

    @Override
    public Vertex2D getVertex(int index) {
        double x = center.getX() - radius * (Math.cos(index * 2 * Math.PI / numEdges));
        double y = center.getY() - radius * (Math.sin(index * 2 * Math.PI / numEdges));
        return new Vertex2D(x, y);
    }

    @Override
    public String toString() {
        return numEdges + "-gon: center=" + center + ", radius=" + radius + ", color=" + color;
    }
}
