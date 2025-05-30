package Models;

public class Inmueble {

    private String id;
    private double precio;
    private double area;
    private String estado;
    private String tipo;

    public Inmueble(String id, double precio, double area, String estado, String tipo) {
        this.id = id;
        this.precio = precio;
        this.area = area;
        this.estado = estado;
        this.tipo = tipo;
    }

    public Inmueble() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
