package edu.ucam.cliente;

import java.rmi.Naming;

import edu.ucam.compartida.IGestionUniversidad;
import edu.ucam.domain.Titulacion;

public class Cliente {

	public static void main(String[] args) {
      
		try {
        
			
			IGestionUniversidad gestion = (IGestionUniversidad) Naming.lookup("//localhost/Universidad");


            Titulacion nuevaTit = new Titulacion(); 

            gestion.addTitulacion(nuevaTit); 
            
        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}