package Views;
import Controlers.SistemaInmobiliaria;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static SistemaInmobiliaria sistema = new SistemaInmobiliaria();

    public static void main(String[] args) {
        int opcion;
        do{
            System.out.println("\nMenu Inmobiliaria");
            System.out.println("1. Registrar Inmueble");
            System.out.println("2. Buscar Inmueble");
            System.out.println("0. Salir");
            switch (opcion){
                case 1-> registrarInmueble();
                case 2-> leerInmueble();
                case 0 -> System.out.println("Saliendo");
                default -> System.out.println("Opcion Invalida");
            }
        }while (opcion != 0)
    }

    private static void registarInmueble(){




    }

    private static void buscarProducto(){
        System.out.println("Ingrese el tipo de producto:");




    }



}