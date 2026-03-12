# Sistema de Clínica Médica 🏥

Este é um projeto de Estrutura de Dados desenvolvido em Java que simula o fluxo de atendimento de uma clínica médica. O sistema foi construído para aplicar, na prática, a implementação e manipulação de Tipos Abstratos de Dados (TADs), especificamente conceitos de **Listas, Filas e Pilhas**.

## 🚀 Funcionalidades

- **Gerenciamento de Pacientes:** Cadastro de novos pacientes no sistema.
- **Controle de Fila (TAD Fila):** Adicionar pacientes à fila de espera e realizar a chamada do próximo paciente (FIFO - First In, First Out).
- **Monitoramento:** Visualização em tempo real da fila de espera.
- **Histórico (TAD Lista/Pilha):** Registro completo de atendimentos realizados.
- **Controle de Ações:** Capacidade de desfazer a última operação ou cancelar o último atendimento registrado.

## 💡 Tecnologias e Conceitos Utilizados

- **Linguagem:** Java (Orientação a Objetos)
- **Fundamentos:** Lógica de programação e Tipos Abstratos de Dados (TADs)
- **Interface:** Terminal/Console (CLI)
- **Versionamento:** Git e GitHub

## 📁 Arquitetura do Projeto

O código-fonte está encapsulado no pacote `clinica` para manter a organização lógica do domínio.

```text
sistema-de-atendimento-ed/
│
└── ClinicaMedica/
    ├── src/
    │   └── clinica/               # Pacote principal
    │       ├── Paciente.java      # Entidade base
    │       ├── Consulta.java      # Entidade de atendimento
    │       ├── FilaDeAtendimento.java # Gerenciador da fila
    │       ├── HistoricoDeConsultas.java # Registro de operações
    │       └── ClinicaApp.java    # Classe Main (Menu e Execução)
    ├── out/                       # Diretório gerado para arquivos compilados (.class)
    └── README.md                  # Documentação
✅ Como Executar no Terminal
Como o projeto não utiliza gerenciadores de dependência (como Maven), a compilação deve ser feita manualmente apontando para o pacote correto.

Pré-requisito: Ter o Java JDK instalado e configurado nas variáveis de ambiente.

1. Clone o repositório:

Bash
git clone [https://github.com/eiltonneto/sistema-de-atendimento-ed.git](https://github.com/eiltonneto/sistema-de-atendimento-ed.git)
2. Navegue até o diretório raiz do código Java:

Bash
cd sistema-de-atendimento-ed/ClinicaMedica
3. Compile os arquivos fontes (cria a pasta out e compila o pacote clinica):
No PowerShell (Windows) ou Bash (Linux/Mac):

Bash
javac -d out src/clinica/*.java
4. Execute o programa:

Bash
java -cp out clinica.ClinicaApp
Autor 👨‍💻 Desenvolvido por Eilton Neto GitHub: @eiltonneto
