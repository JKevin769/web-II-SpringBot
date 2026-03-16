package ifg.urt.api_carrinho.mock;

import ifg.urt.api_carrinho.model.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static java.awt.AWTEventMulticaster.add;
import static java.lang.ref.Cleaner.create;

public class ProdutoMock {

    public static List<Produto> createList(){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(create(1L,"teclado", "Teclado Mecânico", 250.0, 10));
        produtos.add(create(2L,"Mouse", "Mouse gamer", 100.0, 20 ));
        return produtos;
    }

    private static Produto create(Long id, String nome, String descricao, Double preco, Integer estoque){
        Produto p = new Produto();
        p.setId(id);
        p.setNome(nome);
        p.setDescricao(descricao);
        p.setPreco(preco);
        p.setEstoque(estoque);
        return p;
    }
}