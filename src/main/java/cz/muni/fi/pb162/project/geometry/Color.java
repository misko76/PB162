package cz.muni.fi.pb162.project.geometry;

/**
 * This enumeration type represents various common colors
 *
 * @author Michal Soltis
 */
public enum Color {
    WHITE, YELLOW, ORANGE, RED, BLUE, GREEN, BLACK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
