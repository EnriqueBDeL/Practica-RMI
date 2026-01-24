package edu.ucam.cliente;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

import edu.ucam.compartida.IGestionUniversidad;
import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Titulacion;

public class Cliente {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        try {
            IGestionUniversidad gestion = (IGestionUniversidad) Naming.lookup("rmi://localhost:1010/ServidorUniversidad");
            System.out.println("Conectado al servidor correctamente.");

            int opcion = -1;

            do {
                System.out.println("\n***** GESTION UNIVERSITARIA (RMI) *****");
                System.out.println("  1. Añadir Titulación");
                System.out.println("  2. Modificar Titulación");
                System.out.println("  3. Listar Titulaciones");
                System.out.println("  4. Borrar Titulación");
                System.out.println("  5. Contar Total de Titulaciones");
                System.out.println("  6. Añadir Asignatura");
                System.out.println("  7. Modificar Asignatura");
                System.out.println("  8. Listar Asignaturas");
                System.out.println("  9. Borrar Asignatura");
                System.out.println(" 10. Asociar Asignatura a Título");
                System.out.println(" 11. Desvincular Asignatura de Título");
                System.out.println(" 12. Ver Asignaturas de un Título");
                System.out.println(" 13. Añadir Matrícula");
                System.out.println(" 14. Modificar Matrícula");
                System.out.println(" 15. Consultar Matrícula");
                System.out.println(" 16. Borrar Matrícula");
                System.out.println("  0. Salir");
                System.out.print("Seleccione una opción: ");
                
                try {
                    String entrada = sc.nextLine();
                    opcion = Integer.parseInt(entrada);

                    System.out.println("------------------------------------------------");
                    
                    switch (opcion) {
                        case 1: 
                            System.out.println(">> NUEVA TITULACIÓN");
                            System.out.print("ID: "); String idT = sc.nextLine();
                            System.out.print("Nombre: "); String nomT = sc.nextLine();
                            Titulacion t = new Titulacion();
                            t.setId(idT); t.setNombre(nomT);
                            gestion.addTitulacion(t);
                            System.out.println("Guardado.");
                            break;

                        case 2:
                            System.out.println(">> MODIFICAR TITULACIÓN");
                            System.out.print("ID del Título a modificar: "); String idTM = sc.nextLine();
                            Titulacion tExiste = gestion.getTitulacion(idTM);
                            if(tExiste != null){
                                System.out.println("Nombre actual: " + tExiste.getNombre());
                                System.out.print("Nuevo Nombre: "); String nuevoNom = sc.nextLine();
                                Titulacion tMod = new Titulacion();
                                tMod.setId(idTM); tMod.setNombre(nuevoNom);
                                gestion.updateTitulacion(tMod);
                                System.out.println("Actualizado.");
                            } else {
                                System.out.println("No existe ese título.");
                            }
                            break;

                        case 3: 
                            System.out.println(">> LISTADO TITULACIONES");
                            List<Titulacion> lT = gestion.listTitulaciones();
                            if(lT.isEmpty()) System.out.println("Lista vacía.");
                            for(Titulacion item : lT) System.out.println("- ["+item.getId()+"] " + item.getNombre());
                            break;

                        case 4: 
                            System.out.println(">> BORRAR TITULACIÓN");
                            System.out.print("ID: "); String idTB = sc.nextLine();
                            if(gestion.removeTitulacion(idTB)) System.out.println("Eliminado.");
                            else System.out.println("No encontrado.");
                            break;

                        case 5:
                            System.out.println(">> TOTAL TITULACIONES");
                            System.out.println("Hay " + gestion.countTitulaciones() + " títulos registrados.");
                            break;

                        case 6: 
                            System.out.println(">> NUEVA ASIGNATURA");
                            System.out.print("ID: "); String idA = sc.nextLine();
                            System.out.print("Nombre: "); String nomA = sc.nextLine();
                            Asignatura a = new Asignatura();
                            a.setId(idA); a.setNombre(nomA);
                            gestion.addAsignatura(a);
                            System.out.println("Guardada.");
                            break;

                        case 7:
                            System.out.println(">> MODIFICAR ASIGNATURA");
                            System.out.print("ID Asignatura: "); String idAM = sc.nextLine();
                            Asignatura aExiste = gestion.getAsignatura(idAM);
                            if(aExiste != null){
                                System.out.println("Nombre actual: " + aExiste.getNombre());
                                System.out.print("Nuevo Nombre: "); String nNomA = sc.nextLine();
                                Asignatura aMod = new Asignatura();
                                aMod.setId(idAM); aMod.setNombre(nNomA);
                                gestion.updateAsignatura(aMod);
                                System.out.println("Actualizada.");
                            } else {
                                System.out.println("No existe esa asignatura.");
                            }
                            break;

                        case 8: 
                            System.out.println(">> LISTADO ASIGNATURAS");
                            List<Asignatura> lA = gestion.listAsignaturas();
                            if(lA.isEmpty()) System.out.println("Lista vacía.");
                            for(Asignatura item : lA) System.out.println("- ["+item.getId()+"] " + item.getNombre());
                            break;

                        case 9:
                            System.out.println(">> BORRAR ASIGNATURA");
                            System.out.print("ID: "); String idAB = sc.nextLine();
                            if(gestion.removeAsignatura(idAB)) System.out.println("Eliminada.");
                            else System.out.println("No encontrada.");
                            break;

                        case 10: 
                            System.out.println(">> ASOCIAR ASIGNATURA -> TÍTULO");
                            System.out.print("ID Asignatura: "); String idAsig = sc.nextLine();
                            System.out.print("ID Título: "); String idTit = sc.nextLine();
                            gestion.addAsignaturaATitulo(idAsig, idTit);
                            System.out.println("Operación enviada.");
                            break;

                        case 11:
                            System.out.println(">> DESVINCULAR ASIGNATURA DE TÍTULO");
                            System.out.print("ID Asignatura: "); String idAsigD = sc.nextLine();
                            System.out.print("ID Título: "); String idTitD = sc.nextLine();
                            gestion.removeAsignaturaDeTitulo(idAsigD, idTitD);
                            System.out.println("Operación enviada.");
                            break;

                        case 12:
                            System.out.println(">> VER ASIGNATURAS DE UN TÍTULO");
                            System.out.print("ID Título: "); String idTV = sc.nextLine();
                            List<Asignatura> lRel = gestion.listAsignaturasDeTitulo(idTV);
                            if(lRel.isEmpty()) System.out.println("No tiene asignaturas o el título no existe.");
                            for(Asignatura as : lRel) System.out.println(" -> " + as.getNombre());
                            break;

                        case 13: 
                            System.out.println(">> NUEVA MATRÍCULA");
                            System.out.print("ID Matrícula: "); String idM = sc.nextLine();
                            Matricula m = new Matricula();
                            m.setId(idM);
                            gestion.addMatricula(m);
                            System.out.println("Guardada.");
                            break;

                        case 14:
                            System.out.println(">> MODIFICAR MATRÍCULA");
                            System.out.print("ID Matrícula a modificar: "); String idMM = sc.nextLine();
                            Matricula mMod = new Matricula();
                            mMod.setId(idMM);
                            gestion.updateMatricula(mMod);
                            System.out.println("Actualización enviada.");
                            break;

                        case 15: 
                            System.out.println(">> CONSULTAR MATRÍCULA");
                            System.out.print("ID: "); String idMC = sc.nextLine();
                            Matricula mc = gestion.getMatricula(idMC);
                            if(mc != null) System.out.println("Encontrada: ID " + mc.getId());
                            else System.out.println("No existe.");
                            break;
                        
                        case 16:
                            System.out.println(">> BORRAR MATRÍCULA");
                            System.out.print("ID: "); String idMB = sc.nextLine();
                            if(gestion.removeMatricula(idMB)) System.out.println("Eliminada.");
                            else System.out.println("No encontrada.");
                            break;

                        case 0: 
                            System.out.println("Saliendo..."); 
                            break;
                            
                        default: 
                            System.out.println("Opción no válida.");
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Error: Introduce un número.");
                } catch (Exception e) {
                    System.out.println("Error RMI: " + e.getMessage());
                }

                
                if (opcion != 0) {
                   
                    try { 
                    	
                    	Thread.sleep(3000); 
                    
                    } catch (InterruptedException e) {}
                }

            } while (opcion != 0);

        } catch (Exception e) {
            System.err.println("Error crítico: No se pudo conectar al servidor.");
            System.err.println("Asegúrate de que Servidor.java está corriendo.");
        } finally {
            sc.close();
        }
    }
}