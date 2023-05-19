package es.andresfusteralonso.gimnasio_fct;

public class Producto {
    private int idPO;
    private String nombrePO;
    private String tipoPO;
    private String marca;
    private String sala;
    private String precio;
    private String modelo;

    public int getIdPO() {
        return idPO;
    }

    public void setIdPO(int idPO) {
        this.idPO = idPO;
    }

    public String getNombrePO() {
        return nombrePO;
    }

    public void setNombrePO(String nombrePO) {
        this.nombrePO = nombrePO;
    }

    public String getTipoPO() {
        return tipoPO;
    }

    public void setTipoPO(String tipoPO) {
        this.tipoPO = tipoPO;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
