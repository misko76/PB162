package cz.muni.fi.pb162.project.geometry;

/**
 * This class represents a snowman consisting of multiple regular polygon objects
 *
 * @author Michal Soltis
 */
public class Snowman {
    public static final int NUMBER_OF_REGULAR_POLYGON_OBJECTS = 3;
    private static final double DEFAULT_REDUCTION_FACTOR = 0.8;
    private final double reductionFactor;
    private final RegularPolygon[] polygons;

    /**
     * Checks and initializes reduction factor and creates the whole snowman consisting of regular polygon objects
     *
     * @param firstPolygon    the lower sphere of snowman
     * @param reductionFactor the real number of the range (0..1>
     */
    public Snowman(RegularPolygon firstPolygon, double reductionFactor) {
        if (reductionFactor <= 0 || reductionFactor > 1) {
            this.reductionFactor = DEFAULT_REDUCTION_FACTOR;
        } else {
            this.reductionFactor = reductionFactor;
        }
        polygons = new RegularPolygon[NUMBER_OF_REGULAR_POLYGON_OBJECTS];
        polygons[0] = firstPolygon;
        for (int i = 1; i < NUMBER_OF_REGULAR_POLYGON_OBJECTS; i++) {
            RegularPolygon p = new GeneralRegularPolygon(
                    calculateNewCenter(polygons[i - 1]),
                    polygons[i - 1].getNumEdges(),
                    calculateNewRadius(polygons[i - 1]));
            polygons[i] = p;
        }
    }

    private Vertex2D calculateNewCenter(RegularPolygon oldPolygon) {
        Vertex2D oldCenter = oldPolygon.getCenter();
        double newY = oldCenter.getY() + oldPolygon.getRadius() + (oldPolygon.getRadius() * reductionFactor);
        return new Vertex2D(oldCenter.getX(), newY);
    }

    private double calculateNewRadius(RegularPolygon oldPolygon) {
        return oldPolygon.getRadius() * reductionFactor;
    }

    public RegularPolygon[] getBalls() {
        return polygons;
    }
}
