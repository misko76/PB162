package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Polygon;

import java.util.Arrays;

/**
 * This class provides simple mathematical methods working with polygons.
 *
 * @author Michal Soltis
 */
public class SimpleMath {
    private static double[] allSortedValues(Polygon polygon, boolean getX) {
        double[] result = new double[polygon.getNumVertices()];
        for (int i = 0; i < polygon.getNumVertices(); i++) {
            result[i] = getX ? polygon.getVertex(i).getX() : polygon.getVertex(i).getY();
        }
        Arrays.sort(result);
        return result;
    }

    /**
     * Method returns minimum x-coordinate of given polygon object
     *
     * @param polygon object which coordinates will be compared
     * @return minimum x-coordinate
     */
    public static double minX(Polygon polygon) {
        return allSortedValues(polygon, true)[0];
    }

    /**
     * Method returns minimum y-coordinate of given polygon object
     *
     * @param polygon object which coordinates will be compared
     * @return minimum y-coordinate
     */
    public static double minY(Polygon polygon) {
        return allSortedValues(polygon, false)[0];
    }

    /**
     * Method returns maximum x-coordinate of given polygon object
     *
     * @param polygon object which coordinates will be compared
     * @return maximum x-coordinate
     */
    public static double maxX(Polygon polygon) {
        return allSortedValues(polygon, true)[polygon.getNumVertices() - 1];
    }

    /**
     * Method returns maximum y-coordinate of given polygon object
     *
     * @param polygon object which coordinates will be compared
     * @return maximum y-coordinate
     */
    public static double maxY(Polygon polygon) {
        return allSortedValues(polygon, false)[polygon.getNumVertices() - 1];
    }
}
