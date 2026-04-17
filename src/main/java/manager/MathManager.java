package manager;

import models.Estudiante;
import models.Instituto;
import models.Operacion;

import java.util.List;

public interface MathManager {

    void añadirEstudiante(Estudiante estudiante, String idInstituto);

    void añadirInstituto(Instituto instituto);

    void recibirOperacion(Operacion operacion);

    Operacion procesarSiguienteOperacion();

    List<Operacion> getOperacionesPorInstituto(String idInstituto);

    List<Operacion> getOperacionesPorEstudiante(String idEstudiante);

    List<Instituto> getInstitutosOrdenadosPorOperaciones();

    void clear();
}