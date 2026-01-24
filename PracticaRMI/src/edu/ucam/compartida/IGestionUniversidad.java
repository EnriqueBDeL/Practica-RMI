package edu.ucam.compartida;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Titulacion;

public interface IGestionUniversidad extends Remote {

    // TITULACIONES 
    void addTitulacion(Titulacion t) throws RemoteException;
    void updateTitulacion(Titulacion t) throws RemoteException;
    Titulacion getTitulacion(String id) throws RemoteException;
    boolean removeTitulacion(String id) throws RemoteException;
    List<Titulacion> listTitulaciones() throws RemoteException;
    int countTitulaciones() throws RemoteException;

    // ASIGNATURAS 
    void addAsignatura(Asignatura a) throws RemoteException;
    void updateAsignatura(Asignatura a) throws RemoteException; 
    Asignatura getAsignatura(String id) throws RemoteException;
    boolean removeAsignatura(String id) throws RemoteException;
    List<Asignatura> listAsignaturas() throws RemoteException;

    
    // RELACIÓN ASIGNATURA - TÍTULO 
    void addAsignaturaATitulo(String idAsignatura, String idTitulacion) throws RemoteException;
    void removeAsignaturaDeTitulo(String idAsignatura, String idTitulacion) throws RemoteException;
    List<Asignatura> listAsignaturasDeTitulo(String idTitulacion) throws RemoteException;

    // MATRÍCULAS 
    void addMatricula(Matricula m) throws RemoteException;
    void updateMatricula(Matricula m) throws RemoteException;
    Matricula getMatricula(String id) throws RemoteException;
    boolean removeMatricula(String id) throws RemoteException;
}