package models;

import java.util.ArrayList;
import java.util.List;

public class Instituto {
    private String id;
    private String name;
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Operacion> operacionesTerminadas = new ArrayList<>();
    public Instituto() {};
    public Instituto(String id, String name) {
        this.id = id;
        this.name = name;
        this.operacionesTerminadas = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void addEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public List<Operacion> getOperacionesTerminadas() {

            List<Operacion> o = new ArrayList<>();
            for (Estudiante estudiante : estudiantes) {
                if (estudiante.getOperacionesTerminadas() != null) {
                    o.addAll(estudiante.getOperacionesTerminadas());
                }
            }
            this.operacionesTerminadas = o;

        return this.operacionesTerminadas;
    }

    public void setOperacionesTerminadas(List<Operacion> operacionesTerminadas) {
        this.operacionesTerminadas = operacionesTerminadas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
