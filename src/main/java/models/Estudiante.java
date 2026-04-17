package models;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String id;
    private String nombre;
    private String idInsituto;
    private List<Operacion> operacionesTerminadas= new ArrayList<>();

    public Estudiante() {}
    public Estudiante(String id, String nombre, String idInsituto) {
        this.id = id;
        this.nombre = nombre;
        this.idInsituto = idInsituto;
        this.operacionesTerminadas = new ArrayList<>();
    }

    public String getIdInsituto() {
        return idInsituto;
    }

    public void setIdInsituto(String idInsituto) {
        this.idInsituto = idInsituto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Operacion> getOperacionesTerminadas() {
        return operacionesTerminadas;
    }

    public void setOperacionesTerminadas(List<Operacion> operacionesTerminadas) {
        this.operacionesTerminadas = operacionesTerminadas;
    }
}
