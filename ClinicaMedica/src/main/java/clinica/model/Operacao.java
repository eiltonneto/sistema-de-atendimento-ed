// Arquivo: src/main/java/clinica/model/Operacao.java
package clinica.model;

public class Operacao {
    private final TipoOperacoes tipo;
    private final Paciente paciente;


    public Operacao(TipoOperacoes tipo, Paciente paciente) {
        this.tipo = tipo;
        this.paciente = paciente;
    }

    public TipoOperacoes getTipo() {
        return tipo;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}
