package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Measurable;
import cz.muni.fi.pb162.project.geometry.Triangle;


/**
 * This class allows you to "measure" objects and print information about their height and width
 *
 * @author Michal Soltis
 */
public class Gauger {
    /**
     * static method, that prints information about measurable object
     *
     * @param obj which width and height will be printed
     */
    public static void printMeasurement(Measurable obj) {
        System.out.println("Width: " + obj.getWidth());
        System.out.println("Height: " + obj.getHeight());
    }

    /**
     * overloaded static method, that prints information about triangle
     *
     * @param triangle object which width and height will be printed
     */
    public static void printMeasurement(Triangle triangle) {
        System.out.println(triangle.toString());
        printMeasurement((Measurable) triangle);
    }
}
