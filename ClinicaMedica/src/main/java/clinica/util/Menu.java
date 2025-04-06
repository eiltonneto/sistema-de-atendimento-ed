package clinica.util;

import clinica.model.*;
import java.util.Scanner;

public class Menu {
    private final ListaPacientes listaPacientes;
    private final FilaDeAtendimento fila;
    private final HistoricoAtendimentos historico;
    private final PilhaOperacoes pilhaOperacoes;
    private final Scanner scanner;

    public Menu(ListaPacientes listaPacientes, FilaDeAtendimento fila,
                HistoricoAtendimentos historico, PilhaOperacoes pilhaOperacoes) {
        this.listaPacientes = listaPacientes;
        this.fila = fila;
        this.historico = historico;
        this.pilhaOperacoes = pilhaOperacoes;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n======== Sistema de Clínica Médica =======");
            System.out.println("1. Cadastrar Paciente");
            System.out.println("2. Adicionar Paciente à Fila de Atendimento");
            System.out.println("3. Chamar Próximo Paciente");
            System.out.println("4. Ver Fila de Espera");
            System.out.println("5. Ver Histórico de Atendimentos");
            System.out.println("6. Desfazer Última Operação");
            System.out.println("7. Cancelar Último Atendimento");
            System.out.println("0. Sair");
            opcao = lerOpcaoInt("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarPaciente();
                case 2 -> adicionarPacienteAFila();
                case 3 -> chamarProximoPaciente();
                case 4 -> verFilaDeEspera();
                case 5 -> verHistoricoAtendimentos();
                case 6 -> desfazerUltimaOperacao();
                case 7 -> cancelarAtendimento();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private int lerOpcaoInt(String mensagem){
        int opcao = -1;
        while (true){
            try {
                System.out.println(mensagem);
                opcao = Integer.parseInt(scanner.nextLine().trim());
                break;
            }catch (NumberFormatException e){
                System.out.println("Entrada inválida! Digite um número inteiro.");
            }
        }
        return opcao;
    }

    private void cadastrarPaciente() {
        System.out.print("Nome do paciente: ");
        String nome = scanner.nextLine().trim();
        int id;
        while (true){
            System.out.print("ID do paciente:");
            String entrada = scanner.nextLine();
            try {
                id = Integer.parseInt(entrada);
                if (listaPacientes.buscarPorId(id) != null){
                    System.out.println("ID já existente. Tente outro.");
                    continue;
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("ID inválido. Digite um número inteiro.");
            }
        }

        System.out.print("Motivo da consulta: ");
        String motivo = scanner.nextLine().trim();

        String prioridade;
        while (true){
            System.out.print("Prioridade do paciente [Emergência, Urgência, Normal]: ");
            prioridade = scanner.nextLine().trim();
            if(prioridade.equalsIgnoreCase("emergência") ||
                prioridade.equalsIgnoreCase("urgência") ||
                prioridade.equalsIgnoreCase("normal")){
                break;
            }else {
                System.out.println("Prioridade inválida. Digite Emergência, Urgência ou Normal.");
            }
        }

        try {
            Paciente paciente = new Paciente(nome, id, motivo, prioridade);
            listaPacientes.adicionar(paciente);
            pilhaOperacoes.push(new Operacao(TipoOperacoes.CADASTRAR, paciente));
            System.out.println("Paciente cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void adicionarPacienteAFila() {
        int id;
        while (true) {
            System.out.print("ID do paciente: ");
            String entrada = scanner.nextLine();
            try {
                id = Integer.parseInt(entrada);
                break;
            } catch (NumberFormatException e) {
                System.out.println("ID inválido. Digite um número inteiro.");
            }
        }

        Paciente paciente = listaPacientes.buscarPorId(id);
        if (paciente != null) {
            fila.adicionar(paciente);
            pilhaOperacoes.push(new Operacao(TipoOperacoes.ADICIONAR_FILA, paciente));
            System.out.println("Paciente adicionado à fila de atendimento!");
        } else {
            System.out.println("Paciente não encontrado!");
        }
    }

    private void chamarProximoPaciente() {
        Paciente paciente = fila.chamarProximo();
        if (paciente != null) {
            historico.adicionar(paciente);
            pilhaOperacoes.push(new Operacao(TipoOperacoes.CHAMAR_PACIENTE, paciente));
            System.out.println("Atendendo: " + paciente.getNome());
        } else {
            System.out.println("Nenhum paciente na fila de espera.");
        }
    }

    private void verFilaDeEspera() {
        System.out.println("\n========== Fila de Espera ===========");
        fila.exibirFormatado();
    }

    private void cancelarAtendimento(){
        //1. verifica se há atendimentos no histórico
        Paciente ultimoAtendido = historico.getUltimoAtendido();

        if (ultimoAtendido == null){
            System.out.println("Nenhum paciente foi atendido ainda!");
            return;
        }
        // 2. mostra os dados do ultimo atendido
        System.out.println("\nUltimo atendido: "+ ultimoAtendido.exibirFormatado());

        String confirmacao;
        while (true) {
            System.out.print("Confirmar cancelamento? (Sim/Não): ");
            confirmacao = scanner.nextLine().trim();
            if (confirmacao.equalsIgnoreCase("sim") || confirmacao.equalsIgnoreCase("não")) {
                break;
            } else {
                System.out.println("Entrada inválida. Responda com 'Sim' ou 'Não'.");
            }
        }

        if(confirmacao.equalsIgnoreCase("sim")) {
            Paciente pacienteCancelado = historico.removerERetornarUltimoAtendido();
            fila.adicionar(pacienteCancelado);
            pilhaOperacoes.push(new Operacao(TipoOperacoes.CANCELAR_ATENDIMENTO, pacienteCancelado));
            System.out.println("Atendimento cancelado com sucesso: " + pacienteCancelado.getNome());
        } else {
            System.out.println("Cancelamento não confirmado. Operação abortada.");
        }

    }

    private void verHistoricoAtendimentos() {
        System.out.println("\n======== Histórico de Atendimentos ========");
        Paciente[] atendimentos = historico.getAtendimentos();
        for (int i = 0; i < historico.getTamanho(); i++) {
            System.out.println(atendimentos[i].exibirFormatado());
        }
    }

    private void desfazerUltimaOperacao() {
        Operacao ultimaOperacao = pilhaOperacoes.pop();
        if (ultimaOperacao == null) {
            System.out.println("Nenhuma operação para desfazer.");
            return;
        }

        Paciente paciente = ultimaOperacao.getPaciente();

        switch (ultimaOperacao.getTipo()) {
            case CADASTRAR -> {
                listaPacientes.removerPorId(paciente.getId());
                System.out.println("Cadastro desfeito: " + paciente.getNome());
            }
            case ADICIONAR_FILA -> {
                fila.removerPaciente(paciente);
                System.out.println("Remoção da fila desfeita: " + paciente.getNome());
            }
            case CHAMAR_PACIENTE -> {
                historico.removerUltimo();
                fila.adicionar(paciente);
                System.out.println("Chamada desfeita: " + paciente.getNome() + " voltou para a fila");
            }
            case CANCELAR_ATENDIMENTO -> {
            // Reverte o cancelamento: tira da fila e coloca no histórico
            fila.removerPaciente(ultimaOperacao.getPaciente());
            historico.adicionar(ultimaOperacao.getPaciente());
                System.out.println("Cancelamento desfeito: "+ ultimaOperacao.getPaciente().getNome()+"Voltou ao atendimento.");
            }
        }
    }
}