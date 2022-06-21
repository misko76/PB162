package cz.muni.fi.pb162.project.geometry;

/**
 * This class represents regular octagon in two-dimensional space.
 *
 * @author Michal Soltis
 */
public class RegularOctagon extends GeneralRegularPolygon {
    /**
     * Calls parent's constructor with given values
     *
     * @param center middle of polygon object
     * @param radius distance from center to perimeter
     */
    public RegularOctagon(Vertex2D center, double radius) {
        super(center, 8, radius);
    }
}
