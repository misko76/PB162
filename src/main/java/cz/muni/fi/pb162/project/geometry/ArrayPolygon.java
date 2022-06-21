package cz.muni.fi.pb162.project.geometry;

import java.util.Arrays;

/**
 * This class represents n-gon polygon stored in an array.
 *
 * @author Michal Soltis
 */
public class ArrayPolygon extends SimplePolygon {
    private final Vertex2D[] vertices;

    /**
     * Calls parent's constructor and initializes private attribute
     * with shallow copy of given array
     *
     * @param vertices array of vertices
     */
    public ArrayPolygon(Vertex2D[] vertices) {
        super(vertices);
        this.vertices = Arrays.copyOf(vertices, vertices.length);
    }

    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Illegal index");
        }
        return vertices[index % vertices.length];
    }

    @Override
    public int getNumVertices() {
        return vertices.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayPolygon that = (ArrayPolygon) o;
        return Arrays.equals(vertices, that.vertices);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vertices);
    }
}
