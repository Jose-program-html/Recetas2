package shary.recetas.activity.rest;

/**
 * Created by Shary on 06/07/2015.
 */
public class Ingredient {
    private Integer id;
    private String nombre;
    private String cantidad;
    private String clasificacion;
    private Integer idRecipeIngredient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Integer getIdRecipeIngredient() {
        return idRecipeIngredient;
    }

    public void setIdRecipeIngredient(Integer idRecipeIngredient) {
        this.idRecipeIngredient = idRecipeIngredient;
    }
}
