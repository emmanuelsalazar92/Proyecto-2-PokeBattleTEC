package battletech.com.battletech;

public class Movie {

    private String image;
    private String name;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}