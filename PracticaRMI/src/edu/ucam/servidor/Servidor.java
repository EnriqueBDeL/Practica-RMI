package edu.ucam.servidor;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {

	public static void main(String[] args) {
       
		try {
        	LocateRegistry.createRegistry(1099);

            GestionUniversidadImpl gestion = new GestionUniversidadImpl();

            Naming.rebind("//localhost/Universidad", gestion);

            System.out.println("Servidor RMI listo y esperando peticiones...");

        } catch (java.rmi.server.ExportException e) {
            System.err.println("Error: El puerto 1099 ya esta en uso.");
            System.err.println("Probablemente ya tengas el servidor ejecutandose en otra ventana.");
        } catch (Exception e) {
            System.err.println("Error general en el servidor: " + e.getMessage());
            e.printStackTrace(); 
        }
    }
}