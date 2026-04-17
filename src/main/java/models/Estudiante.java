package models;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String id;
    private String nombre;
    private String idInsituto;


    public Estudiante() {}
    public Estudiante(String id, String nombre, String idInsituto) {
        this.id = id;
        this.nombre = nombre;
        this.idInsituto = idInsituto;
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

}
