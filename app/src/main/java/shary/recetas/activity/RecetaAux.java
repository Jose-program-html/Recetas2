package shary.recetas.activity;

/**
 * Created by Shary on 25/07/2015.
 */
public class RecetaAux {
    Integer idPhoto;
    String name;
    String category;

    public RecetaAux(Integer idPhoto, String name, String category) {
        this.idPhoto = idPhoto;
        this.name = name;
        this.category = category;
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

    public Integer getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Integer idPhoto) {
        this.idPhoto = idPhoto;
    }
}
