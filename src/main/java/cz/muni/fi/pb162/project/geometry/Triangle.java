package cz.muni.fi.pb162.project.geometry;

/**
 * This class represents a triangle in two-dimensional space.
 *
 * @author Michal Soltis
 */
public class Triangle extends ArrayPolygon {
    private final Triangle[] subTriangles;
    private static final double TOLERATED_ABSOLUT_DEVIATION = 0.001;

    /**
     * Initializes Triangle with given vertex objects
     * and set its subTriangles to null value
     *
     * @param v1 vertex A in classic triangle example
     * @param v2 vertex B in classic triangle example
     * @param v3 vertex C in classic triangle example
     */
    public Triangle(Vertex2D v1, Vertex2D v2, Vertex2D v3) {
        super(new Vertex2D[]{v1, v2, v3});
        this.subTriangles = new Triangle[3];
    }

    /**
     * Calls previous constructor with given vertex objects and divides triangle into Sierpinski triangle
     *
     * @param v1    vertex A in classic triangle example
     * @param v2    vertex B in classic triangle example
     * @param v3    vertex C in classic triangle example
     * @param depth of division triangle into sub-triangles
     */
    public Triangle(Vertex2D v1, Vertex2D v2, Vertex2D v3, int depth) {
        this(v1, v2, v3);
        divide(depth);
    }

    @Override
    public String toString() {
        return "Triangle: vertices=" + getVertex(0) + " " + getVertex(1) + " " + getVertex(2);
    }

    /**
     * Divides triangle into three equal sub-triangles
     *
     * @return true if triangle was successfully divided into sub-triangles, otherwise false
     */
    public boolean divide() {
        if (!isDivided()) {
            Vertex2D downMiddleValue = getVertex(0).createMiddle(getVertex(1));
            Vertex2D leftMiddleValue = getVertex(0).createMiddle(getVertex(2));
            Vertex2D rightMiddleValue = getVertex(1).createMiddle(getVertex(2));

            Triangle leftTriangle = new Triangle(getVertex(0), downMiddleValue, leftMiddleValue);
            Triangle rightTriangle = new Triangle(downMiddleValue, getVertex(1), rightMiddleValue);
            Triangle upperTriangle = new Triangle(leftMiddleValue, rightMiddleValue, getVertex(2));

            subTriangles[0] = leftTriangle;
            subTriangles[1] = rightTriangle;
            subTriangles[2] = upperTriangle;
            return true;
        }
        return false;
    }

    /**
     * Recursively divides triangle into Sierpinski triangle
     *
     * @param depth of division triangle into sub-triangles
     */
    public void divide(int depth) {
        if (depth > 0) {
            divide();
            for (Triangle subTriangle : subTriangles) {
                subTriangle.divide(depth - 1);
            }
        }
    }

    /**
     * Checks if triangle was already divided
     *
     * @return true if triangle was already divided into sub-triangles, otherwise false
     */
    public boolean isDivided() {
        return subTriangles[0] != null;
    }

    /**
     * Returns triangle object at given index
     *
     * @param index position in array of sub-triangles
     * @return triangle object at given index (sub-triangle)
     */
    public Triangle getSubTriangle(int index) {
        if (index < 0 || index > 2) {
            return null;
        }
        return subTriangles[index];
    }

    /**
     * Checks if triangle is equilateral
     *
     * @return true if in triangle all three sides have the same length
     */
    public boolean isEquilateral() {
        double d1 = getVertex(0).distance(getVertex(1));
        double d2 = getVertex(0).distance(getVertex(2));
        double d3 = getVertex(1).distance(getVertex(2));
        return Math.abs(d1 - d2) < TOLERATED_ABSOLUT_DEVIATION &&
                Math.abs(d1 - d3) < TOLERATED_ABSOLUT_DEVIATION &&
                Math.abs(d2 - d3) < TOLERATED_ABSOLUT_DEVIATION;
    }
}
