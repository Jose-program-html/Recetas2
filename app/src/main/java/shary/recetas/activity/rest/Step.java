package shary.recetas.activity.rest;

/**
 * Created by Shary on 07/07/2015.
 */
public class Step {
    private Integer id;
    private Integer paso;
    private String paso_descripcion;
    private Integer idRecipe_step;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaso() {
        return paso;
    }

    public void setPaso(Integer paso) {
        this.paso = paso;
    }

    public String getPaso_descripcion() {
        return paso_descripcion;
    }

    public void setPaso_descripcion(String paso_descripcion) {
        this.paso_descripcion = paso_descripcion;
    }

    public Integer getIdRecipe_step() {
        return idRecipe_step;
    }

    public void setIdRecipe_step(Integer idRecipe_step) {
        this.idRecipe_step = idRecipe_step;
    }
}
