package manager;

import components.ReversePolishNotation;
import components.ReversePolishNotationImpl;
import models.Instituto;
import models.Operacion;
import models.Estudiante;

import org.apache.log4j.Logger;

import java.util.*;

public class MathManagerImpl implements MathManager {
    private static MathManager instance;
    private ReversePolishNotation calculadora;
    final static Logger logger = Logger.getLogger(MathManagerImpl.class.getName());

    private Queue<Operacion> operaciones;
    private List<Operacion> historialOperaciones;
    private Map<String, Estudiante> estudiantes;
    private Map<String, Instituto> institutos;


    private MathManagerImpl() {
        this.operaciones = new LinkedList<>();
        this.historialOperaciones = new ArrayList<>();
        this.institutos = new HashMap<>();
        this.estudiantes = new HashMap<>();
        this.calculadora = new ReversePolishNotationImpl();
    }

    public static MathManager getInstance() {
        if (instance == null) {
            instance = new MathManagerImpl();
        }
        return instance;
    }

    @Override
    public void añadirEstudiante(Estudiante estudiante){
        logger.info("Añadiendo al estudiante: " + estudiante.getNombre());
        estudiantes.put(estudiante.getId(), estudiante);
    }
    @Override
    public void añadirInstituto(Instituto instituto){
        logger.info("Añadiendo el instituto: " + instituto.getName());
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
        Double resultadoCalculado = calculadora.calcular(op.getExpression());
        op.setResultado(resultadoCalculado);
        historialOperaciones.add(op);
        logger.info("Operación " + op.getId() + " procesada con resultado=" + resultadoCalculado);
        return op;
    }

    @Override
    public List<Operacion> getOperacionesPorInstituto(String idInstituto) {
        logger.info("Obteniendo operaciones del instituto idInstituto=" + idInstituto);
        List<Operacion> ls = new ArrayList<>();
        for (Operacion o : historialOperaciones) {
            if (o.getIdInstituto().equals(idInstituto)) {
                ls.add(o);
            }
        }
        logger.info("Se ha devuelto la lista correctamente" + ls.size());
        return ls;
    }

    @Override
    public List<Operacion> getOperacionesPorEstudiante (String idEstudiante) {
        logger.info("Obteniendo operaciones del estudiante idEstudiante=" + idEstudiante);
        List<Operacion> lu = new ArrayList<>();
        for (Operacion o: historialOperaciones) {
            if (o.getIdEstudiante().equals(idEstudiante)) {
                lu.add(o);
            }
        }
        logger.info("Se ha devuelto la lista correctamente" + lu.size());
        return lu;
    }

    @Override
    public List<Instituto> getInstitutosOrdenadosPorOperaciones() {
        logger.info("Obteniendo institutos ordenados por operaciones");
        Map<String, Integer> counter = new HashMap<>();
        for (String idInstituto : institutos.keySet()) {
            counter.put(idInstituto, 0);
        }
        for (Operacion op : operaciones) {
            String idInst = op.getIdInstituto();
            if (counter.containsKey(idInst)) {
                counter.put(idInst, counter.get(idInst) + 1);
            }
        }
        List<Instituto> sortedList = new ArrayList<>(this.institutos.values());
        sortedList.sort(new Comparator<Instituto>() {
            @Override
            public int compare(Instituto i1, Instituto i2) {
                // Orden descendente (de mayor a menor)
                Integer count1 = counter.get(i1.getId());
                Integer count2 = counter.get(i2.getId());
                return count2.compareTo(count1); // Cambia el orden de count1 y count2 para ascendente
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
        this.historialOperaciones.clear();
    }
}