package edu.ucam.cliente;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

import edu.ucam.compartida.IGestionUniversidad;
import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Titulacion;

public class Cliente {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            IGestionUniversidad gestion = (IGestionUniversidad) Naming.lookup("//localhost/Universidad");
            System.out.println("Conectado al servidor correctamente.");

            int opcion = -1;
            
            do {
                mostrarMenuCompleto();
                
                try {
                    String entrada = sc.nextLine();
                    opcion = Integer.parseInt(entrada);

                    System.out.println("------------------------------------------------");
                    
                    switch (opcion) {
                        case 1: crearTitulacion(gestion); break;
                        case 2: listarTitulaciones(gestion); break;
                        case 3: borrarTitulacion(gestion); break;
                        case 4: crearAsignatura(gestion); break;
                        case 5: listarAsignaturas(gestion); break;
                        case 6: asociarAsignaturaATitulo(gestion); break;
                        case 7: crearMatricula(gestion); break;
                        case 8: consultarMatricula(gestion); break;
                        case 0: 
                            System.out.println("Saliendo de la aplicacion..."); 
                            break;
                        default: 
                            System.out.println("Opcion no valida (1-8).");
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Error: Por favor, introduce un numero.");
                } catch (Exception e) {
                    System.out.println("Error en la operacion: " + e.getMessage());
                    e.printStackTrace();
                }

                
            } while (opcion != 0);

        } catch (Exception e) {
            System.err.println("Error critico: No se pudo conectar con el servidor.");
            System.err.println("Asegurate de que Servidor.java se esta ejecutando.");
        } finally {
            sc.close();
        }
    }

    private static void mostrarMenuCompleto() {
    
        System.out.println("*****GESTION UNIVERSITARIA (RMI)*****");
        System.out.println("  1. Anadir nueva Titulacion");
        System.out.println("  2. Listar todas las Titulaciones");
        System.out.println("  3. Eliminar una Titulacion");
        System.out.println("  4. Anadir nueva Asignatura");
        System.out.println("  5. Listar todas las Asignaturas");
        System.out.println("  6. Asociar Asignatura a Titulacion");
        System.out.println("  7. Realizar Matricula");
        System.out.println("  8. Consultar Matricula por ID");
        System.out.println("  0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static void crearTitulacion(IGestionUniversidad gestion) throws Exception {
        System.out.println(">> NUEVA TITULACION");
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Nombre: "); String nombre = sc.nextLine();

        if (id.isEmpty() || nombre.isEmpty()) {
            System.out.println("Los campos no pueden estar vacios.");
            return;
        }

        Titulacion t = new Titulacion();
        t.setId(id);
        t.setNombre(nombre);
        
        gestion.addTitulacion(t);
        System.out.println("Titulacion guardada correctamente.");
    }

    private static void listarTitulaciones(IGestionUniversidad gestion) throws Exception {
        System.out.println(">> LISTADO DE TITULACIONES");
        List<Titulacion> lista = gestion.listTitulaciones();
        
        if (lista.isEmpty()) {
            System.out.println("No hay titulaciones registradas.");
        } else {
            for (Titulacion t : lista) {
                System.out.println(" - [" + t.getId() + "] " + t.getNombre());
            }
        }
    }

    private static void borrarTitulacion(IGestionUniversidad gestion) throws Exception {
        System.out.println(">> BORRAR TITULACION");
        System.out.print("Introduce el ID de la titulacion a borrar: ");
        String id = sc.nextLine();
        
        boolean eliminado = gestion.removeTitulacion(id);
        if (eliminado) {
            System.out.println("Titulacion eliminada.");
        } else {
            System.out.println("No se encontro una titulacion con ese ID.");
        }
    }

    private static void crearAsignatura(IGestionUniversidad gestion) throws Exception {
        System.out.println(">> NUEVA ASIGNATURA");
        System.out.print("ID: "); String id = sc.nextLine();
        System.out.print("Nombre: "); String nombre = sc.nextLine();

        Asignatura a = new Asignatura();
        a.setId(id);
        a.setNombre(nombre);

        gestion.addAsignatura(a);
        System.out.println("Asignatura creada.");
    }

    private static void listarAsignaturas(IGestionUniversidad gestion) throws Exception {
        System.out.println(">> LISTADO DE ASIGNATURAS");
        List<Asignatura> lista = gestion.listAsignaturas();
        
        if (lista.isEmpty()) {
            System.out.println("No hay asignaturas en el sistema global.");
        } else {
            for (Asignatura a : lista) {
                System.out.println(" - [" + a.getId() + "] " + a.getNombre());
            }
        }
    }

    private static void asociarAsignaturaATitulo(IGestionUniversidad gestion) throws Exception {
        System.out.println(">> ASOCIAR ASIGNATURA A TITULACION");
        System.out.print("ID de la Asignatura (ya existente): ");
        String idAsig = sc.nextLine();
        System.out.print("ID de la Titulacion (ya existente): ");
        String idTit = sc.nextLine();
        
        if(idAsig.isEmpty() || idTit.isEmpty()){
             System.out.println("IDs invalidos.");
             return;
        }

        gestion.addAsignaturaATitulo(idAsig, idTit);
        System.out.println("Solicitud enviada (si los IDs existen, se ha asociado).");
    }

    private static void crearMatricula(IGestionUniversidad gestion) throws Exception {
        System.out.println(">> NUEVA MATRICULA");
        System.out.print("ID Matricula: "); String id = sc.nextLine();
        
        Matricula m = new Matricula();
        m.setId(id);
        
        gestion.addMatricula(m);
        System.out.println("Matricula registrada.");
    }

    private static void consultarMatricula(IGestionUniversidad gestion) throws Exception {
        System.out.println(">> CONSULTAR MATRICULA");
        System.out.print("Introduce el ID de la matricula: ");
        String id = sc.nextLine();
        
        Matricula m = gestion.getMatricula(id);
        if (m != null) {
            System.out.println("Encontrada: ID " + m.getId());
        } else {
            System.out.println("No existe ninguna matricula con ese ID.");
        }
    }
}