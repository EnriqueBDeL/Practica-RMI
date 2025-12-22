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
    public void addTitulacion(Titulacion t) throws RemoteException {
        if (t != null) {
            this.titulaciones.add(t);
        }
    }

    @Override
    public void updateTitulacion(Titulacion tNuevo) throws RemoteException {
        for (int i = 0; i < titulaciones.size(); i++) {
            if (titulaciones.get(i).getId().equals(tNuevo.getId())) {
                titulaciones.set(i, tNuevo);
                return;
            }
        }
    }

    @Override
    public Titulacion getTitulacion(String id) throws RemoteException {
        for (Titulacion t : titulaciones) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public boolean removeTitulacion(String id) throws RemoteException {
        for (int i = 0; i < titulaciones.size(); i++) {
            if (titulaciones.get(i).getId().equals(id)) {
                titulaciones.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Titulacion> listTitulaciones() throws RemoteException {
        return new ArrayList<>(titulaciones);
    }

    @Override
    public int countTitulaciones() throws RemoteException {
        return titulaciones.size();
    }

    @Override
    public void addAsignatura(Asignatura a) throws RemoteException {
        if (a != null) {
            this.asignaturas.add(a);
        }
    }

    @Override
    public Asignatura getAsignatura(String id) throws RemoteException {
        for (Asignatura a : asignaturas) {
            if (a.getId().equals(id)) return a;
        }
        return null;
    }

    @Override
    public boolean removeAsignatura(String id) throws RemoteException {
        for (int i = 0; i < asignaturas.size(); i++) {
            if (asignaturas.get(i).getId().equals(id)) {
                asignaturas.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Asignatura> listAsignaturas() throws RemoteException {
        return new ArrayList<>(asignaturas);
    }

    @Override
    public void addAsignaturaATitulo(String idAsignatura, String idTitulacion) throws RemoteException {
        Titulacion tit = getTitulacion(idTitulacion);
        Asignatura asig = getAsignatura(idAsignatura);

        if (tit != null && asig != null) {
             tit.getAsignaturas().add(asig);
        } else {
            throw new RemoteException("Error: Título o Asignatura no encontrados.");
        }
    }

    @Override
    public void removeAsignaturaDeTitulo(String idAsignatura, String idTitulacion) throws RemoteException {
        Titulacion tit = getTitulacion(idTitulacion);
        if (tit != null) {
            List<Asignatura> listaAsig = (List<Asignatura>) tit.getAsignaturas();
            
            for (int i = 0; i < listaAsig.size(); i++) {
                if (listaAsig.get(i).getId().equals(idAsignatura)) {
                    listaAsig.remove(i);
                    return;
                }
            }
        }
    }
    
    @Override
    public List<Asignatura> listAsignaturasDeTitulo(String idTitulacion) throws RemoteException {
        Titulacion tit = getTitulacion(idTitulacion);
        if (tit != null) {
            return (List<Asignatura>) tit.getAsignaturas(); 
        }
        return new ArrayList<>(); 
    }

    @Override
    public void addMatricula(Matricula m) throws RemoteException {
        if (m != null) {
            this.matriculas.add(m);
        }
    }

    @Override
    public void updateMatricula(Matricula mNuevo) throws RemoteException {
        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i).getId().equals(mNuevo.getId())) {
                matriculas.set(i, mNuevo);
                return;
            }
        }
    }

    @Override
    public Matricula getMatricula(String id) throws RemoteException {
        for (Matricula m : matriculas) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }

    @Override
    public boolean removeMatricula(String id) throws RemoteException {
        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i).getId().equals(id)) {
                matriculas.remove(i);
                return true;
            }
        }
        return false;
    }
}