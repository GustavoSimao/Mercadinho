import java.util.Scanner;

public class Mercadinho {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Compras compras = new Compras();
        Estoque estoque = new Estoque();
        BancoDeDados bd = new BancoDeDados();


        BancoDeDados.carregarCSV("C:/Users/gusta/Downloads/bancoDeDados.csv");  // Agora usa o param (corrigir BD se ainda hardcode)


        if (bd.getProdutos().isEmpty()) {
            System.out.println("Nenhum produto carregado. Verifique CSV.");
            return;
        }

        System.out.printf("%-5s %-8s %-30s %-10s %-10s %-12s%n",
                "ID", "CODIGO", "NOME", "PRECO", "QTD", "VALIDADE");
        for (Produtos produto : bd.getProdutos()) {
            System.out.printf("%-5d %-8s %-30s %-10.2f %-10d %-12s%n",
                    produto.id(), produto.codigo(), produto.nome(),
                    produto.preco(), produto.quantidade(), produto.validade());
        }

        System.out.print("Digite o índice do produto que deseja comprar: ");

            int indice = teclado.nextInt() - 1;
            if (indice >= 0 && indice < bd.getProdutos().size()) {
                Produtos primeiroProduto = bd.getProdutos().get(indice);
                compras.adicionarAoCarrinho(primeiroProduto, 2);
                compras.listarCarrinho();
                estoque.adicionarProduto(primeiroProduto);
                estoque.listarEstoque();
            } else {
                System.out.println("Índice inválido!");
            }

            
        compras.finalizarCompra(estoque);



    }
}
