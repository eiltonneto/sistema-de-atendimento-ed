package clinica.model;

import java.util.Arrays;

public class HistoricoAtendimentos {
    private Paciente[] atendimentos;
    private int tamanho;

    public HistoricoAtendimentos(int capacidade) {
        atendimentos = new Paciente[capacidade];
        tamanho = 0;
    }

    // Adiciona um paciente ao histórico (com expansão dinâmica)
    public void adicionar(Paciente paciente) {
        if (tamanho >= atendimentos.length) {
            redimensionarCapacidade();
        }
        atendimentos[tamanho++] = paciente;
    }

    // Retorna Histórico de atendimentos (apenas registros válidos)
    public Paciente[] getAtendimentos() {
        return Arrays.copyOf(atendimentos, tamanho);
    }

    // Retorna o tamanho do histórico
    public int getTamanho() {
        return tamanho;
    }

    // Remove e retorna o último paciente atendido
    public Paciente removerERetornarUltimoAtendido() {
        if (tamanho == 0) {
            System.out.println("Nenhum paciente no histórico.");
            return null;
        }
        return removerUltimo();
    }

    // Retorna o último paciente atendido sem removê-lo
    public Paciente getUltimoAtendido() {
        if (tamanho == 0) {
            return null;
        }
        return atendimentos[tamanho - 1];
    }

    // Remove o último paciente atendido
    public Paciente removerUltimo() {
        if (tamanho > 0) {
            Paciente ultimo = atendimentos[--tamanho];
            atendimentos[tamanho] = null; // Evita manter referência desnecessáriaS
            return ultimo;
        }
        return null;
    }

    // Lista todos os atendimentos registrados
    public void listarAtendimentos() {
        if (tamanho == 0) {
            System.out.println("Nenhum atendimento registrado.");
            return;
        }
        System.out.println("Histórico de Atendimentos:");
        for (int i = 0; i < tamanho; i++) {
            System.out.println("ID: " + atendimentos[i].getId() + " - Nome: " + atendimentos[i].getNome());
        }
    }

    // Expande a capacidade do array quando necessário
    private void redimensionarCapacidade() {
        int novaCapacidade = atendimentos.length * 2;
        atendimentos = Arrays.copyOf(atendimentos, novaCapacidade);
        System.out.println("Capacidade do histórico aumentada para " + novaCapacidade);
    }
}
