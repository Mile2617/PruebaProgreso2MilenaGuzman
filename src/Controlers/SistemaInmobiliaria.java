package Controlers;

import Models.Casa;
import Models.Departamento;
import Models.Inmueble;
import Models.Terreno;

import java.util.ArrayList;
import java.util.List;

public class SistemaInmobiliaria {
    List<Inmueble>inmuebles= new ArrayList<>();
    double ventasTotal=0;



    // Registrar Inmueble
    public void crearInmueble (String id, double precio, double area, String estado, String tipo, double metrosconstruccion, int habitaciones, int pisos){
        Casa c = new Casa(id,precio, area, estado, tipo, metrosconstruccion, habitaciones, pisos);
        inmuebles.add(c);
    }

    public void crearInmueble (String id, double precio, double area, String estado, String tipo, int habitaciones, double alicuota, String amenidades){
        Departamento d = new Departamento(id,precio, area, estado, tipo, habitaciones, alicuota, amenidades );
        inmuebles.add(d);
    }

    public void crearInmueble (String id, double precio, double area, String estado, String tipo, String ubicacion, String cerramiento, String serviciosbasicos){
        Terreno t = new Terreno(id,precio, area, estado, tipo, ubicacion, cerramiento, serviciosbasicos );
        inmuebles.add(t);
    }

    // Buscar Productos por tipo
    public void leerInmueble (String tipo){
        for (Inmueble i : inmuebles){
            if (i.getTipo().equalsIgnoreCase(tipo)){
                System.out.println("Id: "+ i.getId());
                System.out.println("precio: "+ i.getPrecio());
                System.out.println("area: "+ i.getArea());
                System.out.println("estado: "+ i.getEstado());
                System.out.println("tipo: "+ i.getTipo());

                if (i instanceof Casa){
                    Casa c= (Casa) i;
                    System.out.println("Metros de Construccion: "+ c.getMetrosconstruccion());
                    System.out.println("Habitaciones: "+ c.getHabitaciones());
                    System.out.println("Pisos: "+ c.getPisos());

                }
                else if (i instanceof Departamento) {
                    Departamento d = (Departamento) i;
                    System.out.println("Habitaciones: "+ d.getHabitaciones());
                    System.out.println("Alicuota: "+ d.getAlicuota());
                    System.out.println("Amenidades: "+ d.getAmenidades());

                }

                else if (i instanceof Terreno) {
                    Terreno t = (Terreno) i;
                    System.out.println("Ubicacion: "+ t.getUbicacion());
                    System.out.println("Cerramiento: "+ t.getCerramiento());
                    System.out.println("Servicios Basicos: "+ t.getServiciosbasicos());
                }
                return;
            }
        }
        System.out.println("Inmueble no encontrado");
    }

    // Listar
    public void listarInmueble(){
        int i = 1;
        System.out.printf("n\t\tTipo\t\tEstado\t\tArea\t\tPrecio\n");





    }


}




