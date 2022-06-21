package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.utils.SimpleMath;

import java.util.Arrays;

/**
 * This abstract class implements a few methods from the Polygon interface.
 *
 * @author Michal Soltis
 */
public abstract class SimplePolygon implements Polygon {

    /**
     * Throws IllegalArgumentException if the input array is invalid
     * Throws MissingVerticesException if the input array contains less than three vertices
     *
     * @param vertices array of vertices
     */
    public SimplePolygon(Vertex2D[] vertices) throws MissingVerticesException {
        if (vertices == null) {
            throw new IllegalArgumentException("Array of vertices is null");
        }
        if (Arrays.asList(vertices).contains(null)) {
            throw new IllegalArgumentException("Array of vertices contains null");
        }
        if (vertices.length < 3) {
            throw new MissingVerticesException("Array of vertices is too short");
        }
    }

    @Override
    public double getHeight() {
        return SimpleMath.maxY(this) - SimpleMath.minY(this);
    }

    @Override
    public double getWidth() {
        return SimpleMath.maxX(this) - SimpleMath.minX(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Polygon: vertices =");
        for (int i = 0; i < getNumVertices(); i++) {
            sb.append(" ");
            sb.append(getVertex(i));
        }
        return sb.toString();
    }
}
