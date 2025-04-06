package clinica.model;

import java.util.Arrays;

public class ListaPacientes {
    private Paciente[] pacientes;
    private int tamanho;

    // Construtor
    public ListaPacientes(int capacidade) {
        this.pacientes = new Paciente[capacidade];
        this.tamanho = 0;
    }

    // Adicionar um paciente à lista (com redimensionamento dinâmico)
    public void adicionar(Paciente paciente) {
        if (tamanho >= pacientes.length) {
            redimensionarCapacidade(); // Expande a capacidade se necessário
        }
        pacientes[tamanho++] = paciente;
    }

    // Busca um paciente pelo ID
    public Paciente buscarPorId(int id) {
        for (int i = 0; i < tamanho; i++) {
            if (pacientes[i].getId() == id) {
                return pacientes[i];
            }
        }
        return null; // Retorna null se o paciente não for encontrado
    }

    // Remove um paciente pelo ID
    public void removerPorId(int id) {
        for (int i = 0; i < tamanho; i++) {
            if (pacientes[i].getId() == id) {
                // Move todos os elementos após o paciente removido uma posição para trás
                for (int j = i; j < tamanho - 1; j++) {
                    pacientes[j] = pacientes[j + 1];
                }
                pacientes[--tamanho] = null; // Limpa o último elemento
                System.out.println("Paciente removido com sucesso!");
                return;
            }
        }
        System.out.println("Paciente não encontrado!");
    }

    // Retorna a lista de pacientes cadastrados
    public Paciente[] getPacientes() {
        return Arrays.copyOf(pacientes, tamanho); // Retorna apenas os elementos válidos
    }

    // Retorna o tamanho da lista
    public int getTamanho() {
        return tamanho;
    }

    // Lista todos os pacientes cadastrados
    public void listarPacientes() {
        if (tamanho == 0) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        System.out.println("Lista de Pacientes:");
        for (int i = 0; i < tamanho; i++) {
            System.out.println("ID: " + pacientes[i].getId() + " - Nome: " + pacientes[i].getNome());
        }
    }

    // Método privado para expandir a capacidade do array
    private void redimensionarCapacidade() {
        int novaCapacidade = pacientes.length * 2;
        pacientes = Arrays.copyOf(pacientes, novaCapacidade);
        System.out.println("Capacidade aumentada para " + novaCapacidade);
    }
}
