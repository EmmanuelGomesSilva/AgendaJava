import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Agenda agenda = new Agenda();
        // Ao iniciar, já carrego os contatos que estavam salvos no arquivo
        agenda.carregarContatosDoArquivo();

        int opcao;

        do {
            System.out.println("=== AGENDA ===");
            System.out.println("1 - Adicionar contato.");
            System.out.println("2 - Remover contato.");
            System.out.println("3 - Listar contatos.");
            System.out.println("4 - Alterar contato.");
            System.out.println("5 - Salvar contatos em arquivo.");
            System.out.println("0 - Sair");
            System.out.println("Escolha uma opção:");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do teclado

            if (opcao == 0) {
                System.out.println("Saindo...");
                break; // Sai do programa
            }

            switch (opcao) {
                case 1:
                    // Pede nome e telefone e adiciona na agenda
                    String nome;
                    do {
                        System.out.println("Informe o nome:");
                        nome = scanner.nextLine().trim();
                        if (nome.isEmpty()) {
                            System.out.println("Nome não pode ser vazio. Tente novamente.");
                        }
                    } while (nome.isEmpty());
                    String telefone;
                    do {
                        System.out.println("Informe o número de telefone: ");
                        telefone = scanner.nextLine().trim();
                        if (telefone.isEmpty()) {
                            System.out.println("Telefone não pode ser vazio. Tente novamente.");
                        }
                    } while (telefone.isEmpty());


                    agenda.adicionar(nome, telefone);
                    break;

                case 2:
                    // Pede nome pra remover o contato
                    System.out.println("Informe o nome do contato a ser removido: ");
                    String nomeRemove = scanner.nextLine();

                    agenda.remover(nomeRemove);
                    break;

                case 3:
                    // Lista tudo que tem na agenda
                    agenda.listar();
                    break;

                case 4:
                    // Altera telefone de um contato existente
                    System.out.println("Informe o nome do contato que deseja alterar: ");
                    String nomeAlterar = scanner.nextLine();

                    System.out.println("Informe o novo número de telefone: ");
                    String novoTel = scanner.nextLine();

                    agenda.alterarContato(nomeAlterar, novoTel);
                    break;

                case 5:
                    // Salva tudo no arquivo pra não perder nada
                    agenda.salvarContatosEmArquivos();
                    break;

                default:
                    // Se a opção não for nenhuma das acima
                    System.out.println("Opção inválida!");
            }

        } while (true); // Continua no menu até escolher sair
    }
}
