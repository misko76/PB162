package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exception.EmptyDrawableException;
import cz.muni.fi.pb162.project.exception.MissingVerticesException;
import cz.muni.fi.pb162.project.exception.TransparentColorException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class simulates paper on which colored polygons can be drawn.
 *
 * @author Michal Soltis
 */
public class Paper implements Drawable, PolygonFactory {
    private final Collection<ColoredPolygon> allDrawnPolygons;
    private Color color = Color.BLACK;

    /**
     * Initializes collection of drawn polygons with empty set
     */
    public Paper() {
        this.allDrawnPolygons = new HashSet<>();
    }

    /**
     * Copies collection of drawn polygons from given drawable object
     *
     * @param drawable interface representing objects which can be drawn onto
     */
    public Paper(Drawable drawable) {
        this.allDrawnPolygons = new HashSet<>(drawable.getAllDrawnPolygons());
    }

    @Override
    public void changeColor(Color color) {
        this.color = color;
    }

    @Override
    public void drawPolygon(Polygon polygon) throws TransparentColorException {
        if (color == Color.WHITE) {
            throw new TransparentColorException("Transparent white color");
        }
        ColoredPolygon newColoredPolygon = new ColoredPolygon(polygon, color);
        allDrawnPolygons.add(newColoredPolygon);
    }

    @Override
    public void erasePolygon(ColoredPolygon polygon) {
        allDrawnPolygons.remove(polygon);
    }

    @Override
    public void eraseAll() throws EmptyDrawableException {
        if (allDrawnPolygons.isEmpty()) {
            throw new EmptyDrawableException("Empty drawable");
        }
        allDrawnPolygons.clear();
    }

    @Override
    public Collection<ColoredPolygon> getAllDrawnPolygons() {
        return Collections.unmodifiableCollection(allDrawnPolygons);
    }

    @Override
    public int uniqueVerticesAmount() {
        Set<Vertex2D> uniqueVertices = new HashSet<>();
        for (ColoredPolygon coloredPolygon : allDrawnPolygons) {
            int num = coloredPolygon.getPolygon().getNumVertices();
            for (int i = 0; i < num; i++) {
                uniqueVertices.add(coloredPolygon.getPolygon().getVertex(i));
            }
        }
        return uniqueVertices.size();
    }

    @Override
    public Polygon tryToCreatePolygon(List<Vertex2D> vertices) throws MissingVerticesException {
        if (vertices == null) {
            throw new NullPointerException("Null pointer");
        }
        Polygon polygon;
        try {
            polygon = new CollectionPolygon(vertices);
        } catch (IllegalArgumentException e) {
            List<Vertex2D> withoutNull = vertices.stream().filter(Objects::nonNull).collect(Collectors.toList());
            polygon = new CollectionPolygon(withoutNull);
        }
        return polygon;
    }

    @Override
    public void tryToDrawPolygons(List<List<Vertex2D>> collectionPolygons) throws EmptyDrawableException {
        int drawnPolygons = 0;
        Exception lastCause = null;
        for (List<Vertex2D> collectionPolygon : collectionPolygons) {
            try {
                drawPolygon(tryToCreatePolygon(collectionPolygon));
                drawnPolygons++;
            } catch (TransparentColorException e) {
                changeColor(Color.BLACK);
                lastCause = e;
            } catch (MissingVerticesException | NullPointerException e) {
                lastCause = e;
            }
        }
        if (drawnPolygons == 0) {
            throw new EmptyDrawableException("Empty drawable", lastCause);
        }
    }

    Collection<Polygon> getPolygonsWithColor(Color color) {
        return allDrawnPolygons
                .stream()
                .filter(p -> p.getColor() == color)
                .map(ColoredPolygon::getPolygon)
                .collect(Collectors.toSet());
    }
}
