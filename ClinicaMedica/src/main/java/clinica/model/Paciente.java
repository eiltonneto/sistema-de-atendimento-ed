package clinica.model; // Declaração de pacote

public class Paciente {
    private String nome;
    private int id;
    private String motivoConsulta;
    private String prioridade; // 1: Emergência, : Urgência, 3: Normal

    public Paciente(String nome, int id, String motivoConsulta, String prioridade){
        prioridade = prioridade.trim(); // Remove espaços extras
        if (!prioridade.equalsIgnoreCase("Emergência") &&
            !prioridade.equalsIgnoreCase("Urgência")&&
            !prioridade.equalsIgnoreCase("Normal")){
            throw new IllegalArgumentException("Prioridade inválida! Deve ser 'Emergência', 'Urgência' ou 'Normal'.");
        }
        this.nome = nome;
        this.id = id;
        this.motivoConsulta = motivoConsulta;
        this.prioridade = prioridade;
    }

    // Métodos Getters para retornar as variáveis
    public String getNome(){
        return nome;
    }

    public int getId(){
        return id;
    }

    public String getMotivoConsulta(){
        return motivoConsulta;
    }

    public String getPrioridade(){return prioridade;}

    // Método para obter o valor númerico da prioridade (usado na ordenação fila)
    public int getValorPrioridade(){
       return switch (prioridade.toLowerCase()){
            case "emergência" -> 1;
            case "urgência" -> 2;
            case "normal" -> 3;
           default -> throw new IllegalStateException("Prioridade inválida!");
        };
    }
    // toString para exibição
    @Override
    public String toString() {
        return "Paciente{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", motivoConsulta='" + motivoConsulta + '\'' +
                ", prioridade=" + prioridade +
                '}';
    }

    //Método formatado para exibição
    public String exibirFormatado(){
        return String.format("| %-20s | %-10d | %-30s | %-10S", getNome(), getId(), getMotivoConsulta(), getPrioridade());
    }
}
