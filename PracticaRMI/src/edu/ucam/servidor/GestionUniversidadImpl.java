package edu.ucam.servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ucam.compartida.IGestionUniversidad;
import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Titulacion;

public class GestionUniversidadImpl extends UnicastRemoteObject implements IGestionUniversidad {

    private static final long serialVersionUID = 1L;

    private Map<String, Titulacion> titulaciones;
    private Map<String, Asignatura> asignaturas;
    private Map<String, Matricula> matriculas;

    public GestionUniversidadImpl() throws RemoteException {
        super();
        this.titulaciones = new HashMap<>();
        this.asignaturas = new HashMap<>();
        this.matriculas = new HashMap<>();
    }


    
    @Override
    public synchronized void addTitulacion(Titulacion t) throws RemoteException {
        if (t != null && t.getId() != null) this.titulaciones.put(t.getId(), t);
    }

    @Override
    public synchronized void updateTitulacion(Titulacion t) throws RemoteException {
        if (t != null && t.getId() != null) {
            if (this.titulaciones.containsKey(t.getId())) {
                this.titulaciones.put(t.getId(), t);
            }
        }
    }

    @Override
    public synchronized Titulacion getTitulacion(String id) throws RemoteException {
    	return this.titulaciones.get(id);
    }

    @Override
    public synchronized boolean removeTitulacion(String id) throws RemoteException {
        return this.titulaciones.remove(id) != null;
    }

    @Override
    public synchronized List<Titulacion> listTitulaciones() throws RemoteException {
        return new ArrayList<>(this.titulaciones.values());
    }

    @Override
    public synchronized int countTitulaciones() throws RemoteException {
        return this.titulaciones.size();
    }

   

    @Override
    public synchronized void addAsignatura(Asignatura a) throws RemoteException {
        if (a != null && a.getId() != null) this.asignaturas.put(a.getId(), a);
    }

    @Override
    public synchronized void updateAsignatura(Asignatura a) throws RemoteException {
        if (a != null && a.getId() != null) {
            if (this.asignaturas.containsKey(a.getId())) {
                this.asignaturas.put(a.getId(), a);
            }
        }
    }

    @Override
    public synchronized Asignatura getAsignatura(String id) throws RemoteException {
        return this.asignaturas.get(id);
    }

    @Override
    public synchronized boolean removeAsignatura(String id) throws RemoteException {
        return this.asignaturas.remove(id) != null;
    }

    @Override
    public synchronized List<Asignatura> listAsignaturas() throws RemoteException {
        return new ArrayList<>(this.asignaturas.values());
    }


    @Override
    public synchronized void addAsignaturaATitulo(String idAsig, String idTit) throws RemoteException {
        Titulacion tit = this.titulaciones.get(idTit);
        Asignatura asig = this.asignaturas.get(idAsig);
        if (tit != null && asig != null) tit.addAsignatura(asig);
    }

    @Override
    public synchronized void removeAsignaturaDeTitulo(String idAsig, String idTit) throws RemoteException {
        Titulacion tit = this.titulaciones.get(idTit);
        if (tit != null) tit.removeAsignatura(idAsig);
    }

    @Override
    public synchronized List<Asignatura> listAsignaturasDeTitulo(String idTit) throws RemoteException {
        Titulacion tit = this.titulaciones.get(idTit);
        if (tit != null && tit.getAsignaturas() != null) {
            return new ArrayList<>(tit.getAsignaturas());
        }
        return new ArrayList<>();
    }


    
    
    @Override
    public synchronized void addMatricula(Matricula m) throws RemoteException {
        if (m != null && m.getId() != null) this.matriculas.put(m.getId(), m);
    }

    @Override
    public synchronized void updateMatricula(Matricula m) throws RemoteException {
        if (m != null && m.getId() != null) {
            if (this.matriculas.containsKey(m.getId())) {
                this.matriculas.put(m.getId(), m);
            }
        }
    }

    @Override
    public synchronized Matricula getMatricula(String id) throws RemoteException {
        return this.matriculas.get(id);
    }

    @Override
    public synchronized boolean removeMatricula(String id) throws RemoteException {
        return this.matriculas.remove(id) != null;
    }
}