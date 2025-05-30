package Models;

public class Departamento extends Inmueble {
    private int habitaciones;
    private double alicuota;
    private String amenidades;

    public Departamento(String id, double precio, double area, String estado, String tipo, int habitaciones, double alicuota, String amenidades) {
        super(id, precio, area, estado, tipo);
        this.habitaciones = habitaciones;
        this.alicuota = alicuota;
        this.amenidades = amenidades;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }


    public double getAlicuota() {
        return alicuota;
    }

    public void setAlicuota(double alicuota) {
        this.alicuota = alicuota;
    }

    public String getAmenidades() {
        return amenidades;
    }

    public void setAmenidades(String amenidades) {
        this.amenidades = amenidades;
    }
}
