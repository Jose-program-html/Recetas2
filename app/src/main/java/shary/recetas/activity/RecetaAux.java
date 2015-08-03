package shary.recetas.activity;

/**
 * Created by Shary on 25/07/2015.
 */
public class RecetaAux {
    String photo;
    String name;
    String category;

    public RecetaAux(String photo, String name, String category) {
        this.photo = photo;
        this.name = name;
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
