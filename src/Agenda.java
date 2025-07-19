import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Agenda {
    // Lista que guarda os contatos (nome + telefone)
    private ArrayList<Contato> contatos = new ArrayList<>();

    // Metodo pra adicionar contato, só cria e joga na lista
    public void adicionar(String nome, String telefone) {
        contatos.add(new Contato(nome, telefone));
    }

    // Metodo pra remover contato pelo nome
    public void remover(String nome) {
        Contato contatoRemover = null; // vou guardar aqui quem encontrar pra remover

        // passo por todos os contatos procurando o nome que bate
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                contatoRemover = c; // achei, salvo aqui
                break; // não preciso continuar procurando
            }
        }

        if (contatoRemover != null) { // se achei, tiro da lista
            contatos.remove(contatoRemover);
            System.out.println("Contato removido com sucesso!");
        } else {
            System.out.println("Contato não encontrado");
        }
    }

    // Mostrar toda a lista na tela
    public void listar() {
        System.out.println("=== Lista de Contatos ===");
        for (Contato c : contatos) {
            System.out.println("Nome: " + c.getNome() + " - Telefone: " + c.getTelefone());
        }
    }

    // Alterar telefone do contato pelo nome
    public void alterarContato(String nome, String novoTelefone) {
        boolean encontrado = false; // flag pra saber se achei

        for (Contato c : contatos) {
            if (nome.equalsIgnoreCase(c.getNome())) {
                c.setTelefone(novoTelefone); // atualizo o telefone
                encontrado = true;
                System.out.println("Telefone atualizado com sucesso!");
                break; // não preciso continuar procurando
            }
        }

        if (!encontrado) {
            System.out.println("Contato não cadastrado.");
        }
    }

    // Salvar tudo no arquivo 'contatos.txt'
    public void salvarContatosEmArquivos() {
        try {
            FileWriter escritor = new FileWriter("contatos.txt");
            for (Contato c : contatos) {
                escritor.write(c.getNome() + " - " + c.getTelefone() + "\n"); // escrevo cada contato numa linha
            }
            escritor.close();
            System.out.println("Contatos salvos com sucesso em 'contatos.txt'.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos: " + e.getMessage());
        }
    }

    // Ler os contatos do arquivo 'contatos.txt' e jogar na lista
    public void carregarContatosDoArquivo() {
        try (BufferedReader leitor = new BufferedReader(new FileReader("contatos.txt"))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(" - "); // separo nome e telefone pelo traço
                if (partes.length == 2) {
                    String nome = partes[0];
                    String telefone = partes[1];
                    contatos.add(new Contato(nome, telefone)); // adiciono o contato na lista
                }
            }
            System.out.println("Contatos carregados com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao carregar contatos: " + e.getMessage());
        }
    }
}
