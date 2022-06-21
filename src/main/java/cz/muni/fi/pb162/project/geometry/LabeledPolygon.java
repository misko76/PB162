package cz.muni.fi.pb162.project.geometry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * This class, representing polygon, stores vertices under their names
 *
 * @author Michal Soltis
 */
public final class LabeledPolygon extends SimplePolygon implements Polygon, Labelable, Sortable, PolygonWritable {
    private final SortedMap<String, Vertex2D> vertices;

    private LabeledPolygon(SortedMap<String, Vertex2D> vertices) {
        super(vertices.values().toArray(new Vertex2D[0]));
        this.vertices = vertices;
    }

    @Override
    public Vertex2D getVertex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        }
        Vertex2D[] array = vertices.values().toArray(new Vertex2D[0]);
        return array[index % vertices.size()];
    }

    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    @Override
    public Vertex2D getVertex(String label) {
        Vertex2D vertex = vertices.get(label);
        if (vertex == null) {
            throw new IllegalArgumentException("Such vertex does not exist");
        }
        return vertex;
    }

    @Override
    public Collection<String> getLabels() {
        return Collections.unmodifiableCollection(vertices.keySet());
    }

    @Override
    public Collection<String> getLabels(Vertex2D vertex) {
        Collection<String> result = new HashSet<>();
        for (Map.Entry<String, Vertex2D> entry : vertices.entrySet()) {
            if (entry.getValue().compareTo(vertex) == 0) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    @Override
    public Collection<Vertex2D> getSortedVertices() {
        return new TreeSet<>(vertices.values());
    }

    @Override
    public Collection<Vertex2D> getSortedVertices(Comparator<Vertex2D> comparator) {
        Set<Vertex2D> result = new TreeSet<>(comparator);
        result.addAll(vertices.values());
        return result;
    }

    /**
     * @return a set of vertices that are stored multiple times under different names in the polygon
     */
    public Collection<Vertex2D> duplicateVertices() {
        Collection<Vertex2D> result = new HashSet<>();
        Collection<Vertex2D> seen = new HashSet<>();
        for (Map.Entry<String, Vertex2D> entry : vertices.entrySet()) {
            if (seen.contains(entry.getValue())) {
                result.add(entry.getValue());
            } else {
                seen.add(entry.getValue());
            }
        }
        return result;
    }

    @Override
    public void write(OutputStream os) {
        PrintWriter writer = new PrintWriter(os);
        writer.write(formatVertices());
        writer.flush();
    }

    @Override
    public void write(File file) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(formatVertices());
        }
    }

    private String formatVertices() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Vertex2D> entry : vertices.entrySet()) {
            builder.append(entry.getValue().getX()).
                    append(" ").
                    append(entry.getValue().getY()).
                    append(" ").
                    append(entry.getKey()).
                    append(System.lineSeparator());
        }
        return builder.toString();
    }

    /**
     * Writes a map in JSON format to the output stream
     *
     * @param os output stream
     */
    public void writeJson(OutputStream os) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(this.vertices);
        PrintWriter writer = new PrintWriter(os);
        writer.write(jsonOutput);
        writer.flush();
    }

    /**
     * This class takes care of creating the labeled polygon
     *
     * @author Michal Soltis
     */
    public static class Builder implements Buildable<LabeledPolygon>, PolygonReadable {
        private final SortedMap<String, Vertex2D> labels = new TreeMap<>();

        /**
         * Saves the vertex under the given name
         *
         * @param label name of the vertex
         * @param vert  new vertex
         * @return current builder object
         */
        public Builder addVertex(String label, Vertex2D vert) {
            if (label == null) {
                throw new IllegalArgumentException("Argument label cannot be null");
            }
            if (vert == null) {
                throw new IllegalArgumentException("Argument vertex cannot be null");
            }
            labels.put(label, vert);
            return this;
        }

        @Override
        public LabeledPolygon build() {
            return new LabeledPolygon(labels);
        }

        @Override
        public PolygonReadable read(InputStream is) throws IOException {
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader buffer = new BufferedReader(reader);
            readVertices(buffer.lines().collect(Collectors.toList()));
            return this;
        }

        @Override
        public PolygonReadable read(File file) throws IOException {
            readVertices(Files.readAllLines(file.toPath()));
            return this;
        }

        private void readVertices(List<String> allLines) throws IOException {
            for (String line : allLines) {
                List<String> values = List.of(line.split(" "));
                if (values.size() < 3) {
                    throw new IOException("Invalid file format");
                }
                String label = String.join(" ", values.subList(2, values.size()));
                try {
                    double x = Double.parseDouble(values.get(0));
                    double y = Double.parseDouble(values.get(1));
                    addVertex(label, new Vertex2D(x, y));
                } catch (NumberFormatException e) {
                    throw new IOException("Invalid file format", e);
                }
            }
        }
    }
}
