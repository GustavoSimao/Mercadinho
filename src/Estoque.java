import java.util.ArrayList;
import java.util.List;

public class Estoque {

    private Long id;
    private List<Produtos> produtosEstoque = new ArrayList<>();
    private Integer codigo;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
    private Integer validade;

    public Estoque() {}

    public void adicionarProduto(Produtos produtoAdicionado) {
        produtosEstoque.add(produtoAdicionado);
        System.out.printf("📦 %s adicionado ao estoque%n", produtoAdicionado.nome());
    }

    public List<Produtos> getProdutosEstoque() {
        return new ArrayList<>(produtosEstoque);
    }

    public void listarEstoque() {
        System.out.println("\n=== ESTOQUE ATUAL ===");
        produtosEstoque.forEach(produtoEmEstoque ->
                System.out.printf("%s (Qtd: %d)%n", produtoEmEstoque.nome(), produtoEmEstoque.quantidade())
        );
    }
}


