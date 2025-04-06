package clinica.model;

public class PilhaOperacoes {
    private Operacao[] operacoes;
    private int topo;

    public PilhaOperacoes(int capacidade) {
        operacoes = new Operacao[capacidade];
        topo = -1;
    }

    // Empilha uma operação sem exibir mensagens
    public void push(Operacao operacao) {
        if (topo == operacoes.length - 1) {
            redimensionarCapacidade();
        }
        operacoes[++topo] = operacao;
    }

    // Desempilha uma operação sem exibir mensagens
    public Operacao pop() {
        if (topo == -1) {
            return null;
        }
        return operacoes[topo--];
    }

    // Retorna o topo da pilha sem remover
    public Operacao topo() {
        if (topo == -1) {
            return null;
        }
        return operacoes[topo];
    }

    // Verifica se a pilha está vazia
    public boolean isVazia() {
        return topo == -1;
    }

    // Redimensiona a capacidade da pilha quando cheia (sem mensagens)
    private void redimensionarCapacidade() {
        // Cria um novo array com o dobro da capacidade
        Operacao[] novaPilha = new Operacao[operacoes.length * 2];
        for (int i = 0; i < operacoes.length; i++) {
            novaPilha[i] = operacoes[i];
        }
        operacoes = novaPilha;
    }

    // Retorna o número de operações na pilha
    public int getTamanho() {
        return topo + 1;
    }
}
