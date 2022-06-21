package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.utils.SimpleMath;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents n-gon polygon stored in a collection.
 *
 * @author Michal Soltis
 */
public class CollectionPolygon extends SimplePolygon {
    private final List<Vertex2D> vertices;

    /**
     * Calls parent's constructor and initializes private attribute
     * with shallow copy of given array converted to list
     *
     * @param vertices array of vertices
     */
    public CollectionPolygon(Vertex2D[] vertices) {
        super(vertices);
        this.vertices = new ArrayList<>(List.of(vertices));
    }

    /**
     * Calls parent's constructor and initializes private attribute
     * with shallow copy of given list
     *
     * @param vertices list of vertices
     */
    public CollectionPolygon(List<Vertex2D> vertices) {
        super(vertices.toArray(new Vertex2D[0]));
        this.vertices = new ArrayList<>(vertices);
    }

    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Illegal index");
        }
        return vertices.get(index % vertices.size());
    }

    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollectionPolygon that = (CollectionPolygon) o;
        return Objects.equals(vertices, that.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices);
    }

    /**
     * Finds minimum x-coordinate and creates new list
     * without containing vertices that do not have minimum x-coordinate
     *
     * @return new polygon without the leftmost vertices
     */
    CollectionPolygon withoutLeftmostVertices() {
        double minimum = SimpleMath.minX(this);
        List<Vertex2D> result = new ArrayList<>();
        for (Vertex2D vertex : vertices) {
            if (vertex.getX() != minimum) {
                result.add(vertex);
            }
        }
        if (result.size() == 0) {
            return null;
        }
        return new CollectionPolygon(result);
    }
}
