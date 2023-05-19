package es.andresfusteralonso.gimnasio_fct;

public class Actividad {
    private int idAC;
    private String nombreAC;
    private String tipoAC;
    private String descripcionAC;

    public int getIdAC() {
        return idAC;
    }

    public void setIdAC(int idAC) {
        this.idAC = idAC;
    }

    public String getNombreAC() {
        return nombreAC;
    }

    public void setNombreAC(String nombre) {
        this.nombreAC = nombre;
    }

    public String getTipoAC() {
        return tipoAC;
    }

    public void setTipoAC(String tipo) {
        this.tipoAC = tipo;
    }

    public String getDescripcionAC() {
        return descripcionAC;
    }

    public void setDescripcionAC(String descripcion) {
        this.descripcionAC = descripcion;
    }
}
