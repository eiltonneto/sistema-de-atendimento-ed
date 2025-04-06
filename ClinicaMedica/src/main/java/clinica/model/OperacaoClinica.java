package clinica.model;

public class OperacaoClinica {
    private TipoOperacoes tipo;
    private Paciente paciente;

    // Construtor
    public OperacaoClinica (TipoOperacoes tipo, Paciente paciente){
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
