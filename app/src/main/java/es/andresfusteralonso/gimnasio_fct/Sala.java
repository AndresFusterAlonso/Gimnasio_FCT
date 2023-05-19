package es.andresfusteralonso.gimnasio_fct;

public class Sala {
    private int idSA;
    private String nombreSA;
    private String dimension;
    private String aforo;
    private String descripcion;

    public int getIdSA() {
        return idSA;
    }

    public void setIdSA(int idSA) {
        this.idSA = idSA;
    }

    public String getNombreSA() {
        return nombreSA;
    }

    public void setNombreSA(String nombreSA) {
        this.nombreSA = nombreSA;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getAforo() {
        return aforo;
    }

    public void setAforo(String aforo) {
        this.aforo = aforo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
