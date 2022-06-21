package cz.muni.fi.pb162.project.geometry;

import java.util.Objects;

/**
 * This class represents a point in two-dimensional space
 *
 * @author Michal Soltis
 */
public class Vertex2D implements Comparable<Vertex2D> {
    private final double x;
    private final double y;

    /**
     * Initializes coordinates to corresponding values
     *
     * @param x coordinate
     * @param y coordinate
     */
    public Vertex2D(double x, double y) {
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
        return "[" + x + ", " + y + "]";
    }

    /**
     * Calculates middle value within range of current
     * vertex object and given vertex object
     *
     * @param vertex object to create middle
     * @return middle vertex of two vertices
     */
    public Vertex2D createMiddle(Vertex2D vertex) {
        double finalX = (this.x + vertex.x) / 2;
        double finalY = (this.y + vertex.y) / 2;
        return new Vertex2D(finalX, finalY);
    }

    /**
     * Calculates distance between current vertex object and given vertex object if it is not null,
     * otherwise methods returns -1.0 as an error indicator
     *
     * @param vertex object to calculate distance
     * @return distance between two vertex objects
     */
    public double distance(Vertex2D vertex) {
        if (vertex != null) {
            return Math.sqrt(Math.pow(vertex.x - x, 2) + Math.pow(vertex.y - y, 2));
        }
        return -1.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex2D vertex = (Vertex2D) o;
        return Double.compare(vertex.x, x) == 0 && Double.compare(vertex.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Vertex2D vertex) {
        if (Double.compare(x, vertex.getX()) == 0) {
            return Double.compare(y, vertex.y);
        }
        return Double.compare(x, vertex.x);
    }
}
