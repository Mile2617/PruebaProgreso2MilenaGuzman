package Models;

public class Casa extends Inmueble{
    private double metrosconstruccion;
    private int habitaciones;
    private int pisos;

    public Casa(String id, double precio, double area, String estado, String tipo, double metrosconstruccion, int habitaciones, int pisos) {
        super(id, precio, area, estado, tipo);
        this.metrosconstruccion = metrosconstruccion;
        this.habitaciones = habitaciones;
        this.pisos = pisos;
    }

    public double getMetrosconstruccion() {
        return metrosconstruccion;
    }

    public void setMetrosconstruccion(double metrosconstruccion) {
        this.metrosconstruccion = metrosconstruccion;
    }


    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getPisos() {
        return pisos;
    }

    public void setPisos(int pisos) {
        this.pisos = pisos;
    }
}
