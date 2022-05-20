package models;

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


}
