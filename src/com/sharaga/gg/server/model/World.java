package com.sharaga.gg.server.model;

import java.util.Arrays;

public class World {

    private String title;
    private String[][] matrix;

    public World() {
    }

    public World(String title, String[][] matrix) {
        this.title = title;
        this.matrix = matrix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(String[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        World world = (World) o;

        if (title != null ? !title.equals(world.title) : world.title != null) return false;
        return Arrays.deepEquals(matrix, world.matrix);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + Arrays.deepHashCode(matrix);
        return result;
    }

    @Override
    public String toString() {
        return "World{" +
                "title='" + title + '\'' +
                ", matrix=" + Arrays.toString(matrix) +
                '}';
    }

}
