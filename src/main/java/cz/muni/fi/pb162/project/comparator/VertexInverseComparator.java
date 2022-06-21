package cz.muni.fi.pb162.project.comparator;

import cz.muni.fi.pb162.project.geometry.Vertex2D;

import java.util.Comparator;

/**
 * The comparator will sort the vertices by descending
 *
 * @author Michal Soltis
 */
public class VertexInverseComparator implements Comparator<Vertex2D> {
    @Override
    public int compare(Vertex2D v1, Vertex2D v2) {
        return v1.compareTo(v2) * -1;
    }
}
