package edu.ucam.servidor;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;



public class Servidor {

	
	public static void main(String[] args) {
       
		try {

        	LocateRegistry.createRegistry(1099);

            GestionUniversidadImpl gestion = new GestionUniversidadImpl();

            Naming.rebind("//localhost/Universidad", gestion);

            System.out.println("Servidor RMI listo.");

        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}