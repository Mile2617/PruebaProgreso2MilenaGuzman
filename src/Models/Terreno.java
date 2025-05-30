package Models;

public class Terreno extends Inmueble{
    private String ubicacion;
    private String cerramiento;
    private String serviciosbasicos;

    public Terreno(String id, double precio, double area, String estado, String tipo, String ubicacion, String cerramiento, String serviciosbasicos) {
        super(id, precio, area, estado, tipo);
        this.ubicacion = ubicacion;
        this.cerramiento = cerramiento;
        this.serviciosbasicos = serviciosbasicos;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCerramiento() {
        return cerramiento;
    }

    public void setCerramiento(String cerramiento) {
        this.cerramiento = cerramiento;
    }

    public String getServiciosbasicos() {
        return serviciosbasicos;
    }

    public void setServiciosbasicos(String serviciosbasicos) {
        this.serviciosbasicos = serviciosbasicos;
    }
}

