package models;

import java.util.Objects;

public class Favorite {

    private int position;
    private String name;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Favorite(int position, String name){
        this.position = position;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return position == favorite.position && Objects.equals(name, favorite.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, name);
    }
}
