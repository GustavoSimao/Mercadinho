import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class BancoDeDados {

    private static List<Produtos> produtos = new ArrayList<>();

    public static void carregarCSV(String caminhoArquivo) {

        String linha = "";
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/gusta/Downloads/bancoDeDados.csv"))) {

            br.readLine();

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(separador);

                Long id = Long.parseLong(dados[0]);
                String codigo = dados[1];
                String nome = dados[2];
                Double preco = Double.parseDouble(dados[3]);
                Integer quantidade = Integer.parseInt(dados[4]);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date validade = sdf.parse(dados[5]);

                Produtos produto = new Produtos(id, codigo, nome, preco, quantidade, validade);

                produtos.add(produto);
            }

        } catch (IOException e) {
            System.out.println("Falha ao ler arquivo: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Data inválida na linha: " + linha + " - " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Número inválido na linha: " + linha + " - " + e.getMessage());
        }


    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public Produtos buscarPorNumeroUsuario(int numeroUsuario) {
        List<Produtos> todosProdutos = getProdutos();
        if (numeroUsuario < 1 || numeroUsuario > todosProdutos.size()) {
            System.out.printf("Número inválido! Use 1 até %d\n", todosProdutos.size());
            return null;
        }

        int indiceArray = numeroUsuario - 1;
        return todosProdutos.get(indiceArray);
    }


}