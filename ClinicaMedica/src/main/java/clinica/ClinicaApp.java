package clinica;

import clinica.model.FilaDeAtendimento;
import clinica.model.HistoricoAtendimentos;
import clinica.model.ListaPacientes;
import clinica.model.PilhaOperacoes;
import clinica.util.Menu;

// Classe Principal
public class ClinicaApp {
    public static void main(String[] args) {
        ListaPacientes listaPacientes = new ListaPacientes(100);
        FilaDeAtendimento fila = new FilaDeAtendimento(10);
        HistoricoAtendimentos historico = new HistoricoAtendimentos(100);
        PilhaOperacoes pilhaOperacoes = new PilhaOperacoes(50);
        Menu menu = new Menu(listaPacientes, fila, historico, pilhaOperacoes);
        menu.exibirMenu();

    }
}
