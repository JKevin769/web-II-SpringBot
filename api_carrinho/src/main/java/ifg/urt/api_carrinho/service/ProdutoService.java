package ifg.urt.api_carrinho.service;
import ifg.urt.api_carrinho.mock.ProdutoMock;
import ifg.urt.api_carrinho.model.Produto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class ProdutoService {

    //Logger padrão do java
    private static final Logger logger = Logger.getLogger(ProdutoService.class.getName());

    private final List<Produto> produtos = ProdutoMock.createList();

    public Produto findById(Long id){
        logger.info("Buscando produto com ID: " + id);

        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    logger.warning("Produto ID " + id + "não encontrado");
                    return new RuntimeException("Produto não encontrado");
                });
    }


    private final AtomicLong counter = new AtomicLong();

    public Produto create(Produto produto) {
        logger.info("Criando um produto!");
        produto.setId(counter.incrementAndGet());
        produtos.add(produto);
        return produto;
    }
    public Produto update(Produto produto) {
        logger.info("Atualizando um produto!");
        Produto existing = findById(produto.getId());
        existing.setNome(produto.getNome());
        existing.setPreco(produto.getPreco());
        existing.setEstoque(produto.getEstoque());
        return existing;
    }
    public void delete(Long id) {
        logger.info("Removendo um produto!");
        Produto existing = findById(id);
        produtos.remove(existing);
    }

}