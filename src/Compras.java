import java.util.ArrayList;
import java.util.List;

public class Compras {

    private List<Produtos> carrinho = new ArrayList<>();
    private double custoTotal;
    private double lucroLiquido;
    private double fluxoCaixa;
    private double dre;

    public Compras() {

    }


    public void adicionarAoCarrinho(Produtos produto, int quantidadeAdicional) {
       carrinho.stream()
               .filter(p -> p.codigo().equals(produto.codigo()))
               .findFirst()
               .orElse(null);

        Produtos noCarrinho = new Produtos(
                produto.id(), produto.codigo(), produto.nome(),
                produto.preco(), quantidadeAdicional, produto.validade()
        );
        carrinho.add(noCarrinho);
        recalcularFinanceiro();
        System.out.printf(produto.nome(), "%s comprado com sucesso%n",quantidadeAdicional);
    }

    private void recalcularFinanceiro() {
        custoTotal = carrinho.stream()
                .mapToDouble(produto -> produto.preco() * produto.quantidade())
                .sum();

        double vendaTotal = custoTotal * 1.32;  // Seu markup
        dre = vendaTotal - custoTotal;
        lucroLiquido = dre * 0.75;  // 25% impostos/operação
        fluxoCaixa = lucroLiquido - 800;
    }


    public void finalizarCompra(Estoque estoque) {
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio!");
            return;
        }

        System.out.println("\n=== FINALIZANDO COMPRA ===");
        System.out.printf("Custo Total: R$%.2f%n", custoTotal);
        System.out.printf("DRE Bruto: R$%.2f%n", dre);
        System.out.printf("Lucro Líquido: R$%.2f%n", lucroLiquido);
        System.out.printf("Fluxo Caixa: R$%.2f%n", fluxoCaixa);

        for (Produtos produto : carrinho) {
            estoque.adicionarProduto(produto);
        }

        System.out.println("Carrinho transferido para ESTOQUE!");
        carrinho.clear();
        recalcularFinanceiro();
    }

    public void listarCarrinho() {
        if (carrinho.isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }
        System.out.printf("%-8s %-20s %-8s R$%8s%n", "COD", "PRODUTO", "QTD", "SUBTOTAL");
        for (Produtos p : carrinho) {
            double subtotal = p.preco() * p.quantidade();
            System.out.printf("%-8s %-20s %-8d R$%8.2f%n",
                    p.codigo(), p.nome(), p.quantidade(), subtotal);
        }
        System.out.printf("TOTAL: R$%.2f%n", custoTotal);
    }





}




