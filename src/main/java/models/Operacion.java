package models;

public class Operacion {
    private String id;
    private String expression;
    private Double resultado;
    private String idEstudiante;
    private String idInstituto;

    public Operacion() {
    }
    public Operacion(String id, String expression, Double resultado, String idEstudiante, String idInstituto) {
        this.id = id;
        this.expression = expression;
        this.resultado = resultado;
        this.idEstudiante = idEstudiante;
        this.idInstituto = idInstituto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getIdInstituto() {
        return idInstituto;
    }

    public void setIdInstituto(String idInstituto) {
        this.idInstituto = idInstituto;
    }
}
