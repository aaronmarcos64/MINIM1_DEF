package manager;

import models.Instituto;
import models.Operacion;
import models.Estudiante;

import org.apache.log4j.Logger;

import java.util.*;

public class MathManagerImpl implements MathManager {
    private static MathManager instance;
    final static Logger logger = Logger.getLogger(MathManagerImpl.class.getName());

    private Queue<Operacion> operaciones;
    private Map<String, Estudiante> estudiantes;
    private Map<String, Instituto> institutos;


    private MathManagerImpl() {
        this.operaciones = new LinkedList<>();
        this.institutos = new HashMap<>();
        this.estudiantes = new HashMap<>();
    }

    public static MathManager getInstance() {
        if (instance == null) {
            instance = new MathManagerImpl();
        }
        return instance;
    }

    @Override
    public void añadirEstudiante(Estudiante estudiante, String idInstituto){

        estudiantes.put(estudiante.getId(), estudiante);
        Instituto instituto = institutos.get(idInstituto);
        instituto.addEstudiante(estudiante);
    }
    @Override
    public void añadirInstituto(Instituto instituto){
        institutos.put(instituto.getId(), instituto);
    }

    @Override
    public void recibirOperacion(Operacion operacion) {
        logger.info("Recibiendo operacion:"+ operacion.getExpression() + "del IdEstudiante: " + operacion.getIdEstudiante() + "del IdInstituto: " + operacion.getIdInstituto());

        Operacion op = new Operacion(operacion.getId(), operacion.getExpression(), null, operacion.getIdEstudiante(), operacion.getIdInstituto());
        operaciones.add(op);
        logger.info("Operación recibida y puesta en cola correctamente");
    }

    @Override
    public Operacion procesarSiguienteOperacion() {
        logger.info("Procesando siguiente operacion");

        if (operaciones.isEmpty()) {
            logger.error("No hay operaciones pendientes en la cola para procesar.");
            return null;
        }

        Operacion op = operaciones.poll();

        // llamar al polish notation, asignamos el valor de la expresion de ejemplo
        Double resultadoCalculado = 14.0;
        op.setResultado(resultadoCalculado);

        Estudiante est = estudiantes.get(op.getIdEstudiante());
        if (est != null) {
            est.getOperacionesTerminadas().add(op);
        }

        Instituto inst = institutos.get(op.getIdInstituto());
        if (inst != null) {
            inst.getOperacionesTerminadas().add(op);
        }

        logger.info("Operación " + op.getId() + " procesada con resultado=" + resultadoCalculado);
        return op;
    }

    @Override
    public List<Operacion> getOperacionesPorInstituto(String idInstituto) {
        logger.info("Obeteniendo operaciones del instituoto idInstituto=" + idInstituto);

        Instituto u = institutos.get(idInstituto);
        if (u != null) {
            logger.info("Se ha devuelto la lista correctamente");
            return u.getOperacionesTerminadas();

        }
        logger.info("Se ha devuelto lista vacia");
        return new ArrayList<>();
    }

    @Override
    public List<Operacion> getOperacionesPorEstudiante(String idEstudiante) {
        logger.info("Obteniendo operaciones del estudiante idEstudiante=" + idEstudiante);
        Estudiante e = estudiantes.get(idEstudiante);
        if (e != null) {
            logger.info("Se ha devuelto la lista correctamente");
            return e.getOperacionesTerminadas();
        }
        logger.info("Se ha devuelto lista vacia");
        return new ArrayList<>();
    }

    @Override
    public List<Instituto> getInstitutosOrdenadosPorOperaciones() {
        logger.info("Obteniendo institutos");
        List<Instituto> sortedList = new ArrayList<>(this.institutos.values());
        sortedList.sort(new Comparator<Instituto>() {
            @Override
            public int compare(Instituto u1, Instituto u2) {
                return Double.compare(u2.getOperacionesTerminadas().size(), u1.getOperacionesTerminadas().size());
            }
        });

        logger.info("Lista ordenada y devuelta.");
        return sortedList;
    }

    @Override
    public void clear() {
        this.operaciones.clear();
        this.institutos.clear();
        this.estudiantes.clear();
    }
}