import manager.MathManager;
import manager.MathManagerImpl;
import models.Estudiante;
import models.Instituto;
import models.Operacion;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MathManagerTest {

    private MathManager bm;

    @Before
    public void setUp() {
        // Inicializamos el Singleton antes de cada test
        this.bm = MathManagerImpl.getInstance();

        // Creamos institutos i estudiantes de prueba
        this.bm.añadirInstituto(new Instituto("I1", "EETAC"));
        this.bm.añadirInstituto(new Instituto("I2", "UAB"));
        Estudiante aa = new Estudiante("E1","Aaron","I1");
        Estudiante to = new Estudiante("E2","Toni","I1");
        Estudiante ye = new Estudiante("E3","Yerald","I2");
        this.bm.añadirEstudiante(aa);
        this.bm.añadirEstudiante(to);
        this.bm.añadirEstudiante(ye);
        Operacion op1 = new Operacion("op1", "5 1 2 + 4 * + 3 -",null, "E1", "I1");
        Operacion op2 = new Operacion("op2", "3 2 +",null, "E2", "I1");
        Operacion op3 = new Operacion("op3", "4 2 *",null, "E3", "I2");

        this.bm.recibirOperacion(op1);
        this.bm.recibirOperacion(op2);
        this.bm.recibirOperacion(op3);
}

@After
public void tearDown() {
    // Limpiamos las estructuras de datos después de cada test
    bm.clear();
}

@Test
public void testProcesarOperaciones() {

    Operacion procesada1 = bm.procesarSiguienteOperacion();
    Assert.assertEquals("op1", procesada1.getId());
    Assert.assertEquals(Double.valueOf(14.0), procesada1.getResultado());

    Operacion procesada2 = bm.procesarSiguienteOperacion();
    Assert.assertEquals("op2", procesada2.getId());
    Assert.assertEquals(Double.valueOf(5.0), procesada2.getResultado());
}

@Test
public void testListadosPorInstitutoYEstudiante() {
    bm.procesarSiguienteOperacion();
    bm.procesarSiguienteOperacion();
    bm.procesarSiguienteOperacion();


    List<Operacion> opsInst1 = bm.getOperacionesPorInstituto("I1");
    Assert.assertEquals(2, opsInst1.size());

    List<Operacion> opsInst2 = bm.getOperacionesPorInstituto("I2");
    Assert.assertEquals(1, opsInst2.size());

    List<Operacion> opsAaron = bm.getOperacionesPorEstudiante("E1");
    Assert.assertEquals(1, opsAaron.size());
    Assert.assertEquals("op1", opsAaron.get(0).getId());
}

@Test
public void testOrdenacionInstitutos() {
    bm.procesarSiguienteOperacion();
    bm.procesarSiguienteOperacion();
    bm.procesarSiguienteOperacion();
    List<Instituto> institutosOrdenados = bm.getInstitutosOrdenadosPorOperaciones();

    Assert.assertEquals(2, institutosOrdenados.size());
    Assert.assertEquals("I1", institutosOrdenados.get(0).getId());
    Assert.assertEquals("I2", institutosOrdenados.get(1).getId());
}
}