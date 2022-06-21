package cz.muni.fi.pb162.project.geometry;

/**
 * Getter and setter of colorful objects
 *
 * @author Michal Soltis
 */
public interface Colored {
    /**
     * Returns color of object
     *
     * @return color constant from enumeration of colors
     */
    Color getColor();

    /**
     * Sets object's color to given value
     *
     * @param color new color from enumeration of colors
     */
    void setColor(Color color);
}
