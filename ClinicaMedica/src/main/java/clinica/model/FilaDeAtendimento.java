package clinica.model;

public class FilaDeAtendimento {
    private Paciente[] fila;
    private int tamanho;

    public FilaDeAtendimento(int capacidade) {
        fila = new Paciente[capacidade];
        tamanho = 0;
    }

    public void adicionar(Paciente paciente) {
        if (tamanho >= fila.length) {
            System.out.println("Fila cheia! Não é possível adicionar mais pacientes.");
            return;
        }

        int posicao = tamanho;
        for (int i = tamanho - 1; i >= 0; i--) {
            if (fila[i].getValorPrioridade() > paciente.getValorPrioridade()) {
                fila[i + 1] = fila[i];
                posicao = i;
            } else {
                break;
            }
        }
        fila[posicao] = paciente;
        tamanho++;
    }

    public Paciente chamarProximo() {
        if (tamanho == 0) {
            System.out.println("Fila vazia! Nenhum paciente para chamar.");
            return null;
        }
        Paciente paciente = fila[0];
        System.arraycopy(fila, 1, fila, 0, tamanho - 1);
        fila[--tamanho] = null;
        return paciente;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    public Paciente getPaciente(int indice) {
        if (indice >= 0 && indice < tamanho) {
            return fila[indice];
        } else {
            throw new IndexOutOfBoundsException("Índice fora dos limites da fila.");
        }
    }

    public void exibirFormatado() {
        System.out.println("+----------------------------+------------+------------------------------------------------+");
        System.out.println("| Nome                       | ID         | Motivo da Consulta                              ");
        System.out.println("+----------------------------+------------+------------------------------------------------+");
        for (int i = 0; i < tamanho; i++) {
            System.out.println(fila[i].exibirFormatado());
        }
        System.out.println("+----------------------------+------------+------------------------------------------------+");
    }

    public void removerPaciente(Paciente paciente) {
        for (int i = 0; i < tamanho; i++) {
            if (fila[i].getId() == paciente.getId()) {
                System.arraycopy(fila, i + 1, fila, i, tamanho - i - 1);
                fila[--tamanho] = null;
                return;
            }
        }
    }
}
