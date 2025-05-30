package Views;

import Controlers.SistemaInmobiliaria;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static SistemaInmobiliaria sistema = new SistemaInmobiliaria();

    public static void main(String[] args) {
        int opcion = -1; // Initialize the variable
        do {
            System.out.println("\nMenu Inmobiliaria");
            System.out.println("1. Registrar Inmueble");
            System.out.println("2. Buscar Inmueble");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (opcion) {
                case 1 -> registrarInmueble();
                case 2 -> buscarProducto();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0); // Add missing semicolon
    }

    private static void registrarInmueble() {
        System.out.println("Ingrese el tipo de inmueble (Casa, Departamento, Terreno): ");
        String tipo = scanner.nextLine();

        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Área: ");
        double area = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer
        System.out.print("Estado (Disponible/Vendido): ");
        String estado = scanner.nextLine();

        switch (tipo.toLowerCase()) {
            case "casa" -> {
                System.out.print("Metros de construcción: ");
                double metrosConstruccion = scanner.nextDouble();
                System.out.print("Habitaciones: ");
                int habitaciones = scanner.nextInt();
                System.out.print("Pisos: ");
                int pisos = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                sistema.crearInmueble(id, precio, area, estado, tipo, metrosConstruccion, habitaciones, pisos);
            }
            case "departamento" -> {
                System.out.print("Habitaciones: ");
                int habitaciones = scanner.nextInt();
                System.out.print("Alicuota: ");
                double alicuota = scanner.nextDouble();
                scanner.nextLine(); // Clear the buffer
                System.out.print("Amenidades: ");
                String amenidades = scanner.nextLine();
                sistema.crearInmueble(id, precio, area, estado, tipo, habitaciones, alicuota, amenidades);
            }
            case "terreno" -> {
                System.out.print("Ubicación: ");
                String ubicacion = scanner.nextLine();
                System.out.print("Cerramiento: ");
                String cerramiento = scanner.nextLine();
                System.out.print("Servicios básicos: ");
                String serviciosBasicos = scanner.nextLine();
                sistema.crearInmueble(id, precio, area, estado, tipo, ubicacion, cerramiento, serviciosBasicos);
            }
            default -> System.out.println("Tipo de inmueble no válido.");
        }
    }

    private static void buscarProducto() {
        System.out.print("Ingrese el tipo de inmueble a buscar (Casa, Departamento, Terreno): ");
        String tipo = scanner.nextLine();
        sistema.leerInmueble(tipo);
    }
}