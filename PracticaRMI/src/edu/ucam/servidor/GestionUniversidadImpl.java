package edu.ucam.servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import edu.ucam.compartida.IGestionUniversidad;
import edu.ucam.domain.Asignatura;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Titulacion;

public class GestionUniversidadImpl extends UnicastRemoteObject implements IGestionUniversidad {

    private static final long serialVersionUID = 1L;

    private List<Titulacion> titulaciones;
    private List<Asignatura> asignaturas;
    private List<Matricula> matriculas;

    public GestionUniversidadImpl() throws RemoteException {
        super();
        this.titulaciones = new ArrayList<>();
        this.asignaturas = new ArrayList<>();
        this.matriculas = new ArrayList<>();
    }

    @Override
    public synchronized void addTitulacion(Titulacion t) throws RemoteException {
        if (t != null) {
            this.titulaciones.add(t);
        }
    }

    @Override
    public synchronized void updateTitulacion(Titulacion tNuevo) throws RemoteException {
        if (tNuevo == null) return;
        for (int i = 0; i < titulaciones.size(); i++) {
            if (titulaciones.get(i).getId().equals(tNuevo.getId())) {
                titulaciones.set(i, tNuevo);
                return;
            }
        }
    }

    @Override
    public synchronized Titulacion getTitulacion(String id) throws RemoteException {
        if (id == null) return null;
        for (Titulacion t : titulaciones) {
            if (t.getId().equals(id)) return t;
        }
        return null;
    }

    @Override
    public synchronized boolean removeTitulacion(String id) throws RemoteException {
        if (id == null) return false;
        for (int i = 0; i < titulaciones.size(); i++) {
            if (titulaciones.get(i).getId().equals(id)) {
                titulaciones.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized List<Titulacion> listTitulaciones() throws RemoteException {
        return new ArrayList<>(titulaciones);
    }

    @Override
    public synchronized int countTitulaciones() throws RemoteException {
        return titulaciones.size();
    }

    @Override
    public synchronized void addAsignatura(Asignatura a) throws RemoteException {
        if (a != null) {
            this.asignaturas.add(a);
        }
    }

    @Override
    public synchronized Asignatura getAsignatura(String id) throws RemoteException {
        if (id == null) return null;
        for (Asignatura a : asignaturas) {
            if (a.getId().equals(id)) return a;
        }
        return null;
    }

    @Override
    public synchronized boolean removeAsignatura(String id) throws RemoteException {
        if (id == null) return false;
        for (int i = 0; i < asignaturas.size(); i++) {
            if (asignaturas.get(i).getId().equals(id)) {
                asignaturas.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized List<Asignatura> listAsignaturas() throws RemoteException {
        return new ArrayList<>(asignaturas);
    }

    @Override
    public synchronized void addAsignaturaATitulo(String idAsignatura, String idTitulacion) throws RemoteException {
        Titulacion tit = getTitulacion(idTitulacion);
        Asignatura asig = getAsignatura(idAsignatura);

        if (tit != null && asig != null) {
            tit.getAsignaturas().add(asig);
        }
    }

    @Override
    public synchronized void removeAsignaturaDeTitulo(String idAsignatura, String idTitulacion) throws RemoteException {
        Titulacion tit = getTitulacion(idTitulacion);
        
        if (tit != null && tit.getAsignaturas() != null) {
            Asignatura aBorrar = null;
            for (Asignatura a : tit.getAsignaturas()) {
                if (a.getId().equals(idAsignatura)) {
                    aBorrar = a;
                    break;
                }
            }
            if (aBorrar != null) {
                tit.getAsignaturas().remove(aBorrar);
            }
        }
    }

    @Override
    public synchronized List<Asignatura> listAsignaturasDeTitulo(String idTitulacion) throws RemoteException {
        Titulacion tit = getTitulacion(idTitulacion);
        if (tit != null) {
            return new ArrayList<>(tit.getAsignaturas());
        }
        return new ArrayList<>();
    }

    @Override
    public synchronized void addMatricula(Matricula m) throws RemoteException {
        if (m != null) {
            this.matriculas.add(m);
        }
    }

    @Override
    public synchronized void updateMatricula(Matricula mNuevo) throws RemoteException {
        if (mNuevo == null) return;
        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i).getId().equals(mNuevo.getId())) {
                matriculas.set(i, mNuevo);
                return;
            }
        }
    }

    @Override
    public synchronized Matricula getMatricula(String id) throws RemoteException {
        if (id == null) return null;
        for (Matricula m : matriculas) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }

    @Override
    public synchronized boolean removeMatricula(String id) throws RemoteException {
        if (id == null) return false;
        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i).getId().equals(id)) {
                matriculas.remove(i);
                return true;
            }
        }
        return false;
    }
}