package edu.ucam.servidor;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Servidor {

	public static void main(String[] args) {
       
		try {
			
			
        	LocateRegistry.createRegistry(1010); 

            GestionUniversidadImpl gestion = new GestionUniversidadImpl();

            Naming.rebind("rmi://localhost:1010/ServidorUniversidad", gestion); //Hemos utilizado la misma dirección que en el ejemplo de clase "EjemploUniversidadRMI.zip".

            
            
            System.out.println("Servidor RMI listo y esperando peticiones...");

            
        } catch (java.rmi.server.ExportException e) {
           
        	System.err.println("Error: El puerto 1010 ya esta en uso.");
       
        } catch (Exception e) {
           
        	System.err.println("Error general en el servidor: " + e.getMessage());
           
        	e.printStackTrace(); 
        	
        }
    }
}