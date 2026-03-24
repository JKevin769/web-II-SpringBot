package br.ifg.urt.api_carrinho.service;
import br.ifg.urt.api_carrinho.model.Produto;
import br.ifg.urt.api_carrinho.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProdutoService {

//    //Logger padrão do java
//    private static final Logger logger = Logger.getLogger(ProdutoService.class.getName());
//
//    private final List<Produto> produtos = ProdutoMock.createList();
//
//    public Produto findById(Long id){
//        logger.info("Buscando produto com ID: " + id);
//
//        return produtos.stream()
//                .filter(p -> p.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> {
//                    logger.warning("Produto ID " + id + "não encontrado");
//                    return new RuntimeException("Produto não encontrado");
//                });
//    }
//
//
//    private final AtomicLong counter = new AtomicLong();
//
//    public Produto create(Produto produto) {
//        logger.info("Criando um produto!");
//        produto.setId(counter.incrementAndGet());
//        produtos.add(produto);
//        return produto;
//    }
//    public Produto update(Produto produto) {
//        logger.info("Atualizando um produto!");
//        Produto existing = findById(produto.getId());
//        existing.setNome(produto.getNome());
//        existing.setPreco(produto.getPreco());
//        existing.setEstoque(produto.getEstoque());
//        return existing;
//    }
//    public void delete(Long id) {
//        logger.info("Removendo um produto!");
//        Produto existing = findById(id);
//        produtos.remove(existing);
//    }

    private static final Logger logger = Logger.getLogger(ProdutoService.class.getName());

    private final ProdutoRepository repository; // Agora usamos o Repository real

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }
    //Utiliza o metodo default do Repository para simplificar a busca.
    public Produto findById(Long id) {
        logger.info("Buscando produto no banco com ID: " + id);
        return repository.findByIdOrThrow(id);
    }

    public List<Produto> findAll() {
        logger.info("Buscando todos os produtos no banco.");
        return repository.findAll();
    }

    public Produto create(Produto produto) {
        logger.info("Salvando novo produto no banco: " + produto.getNome());
        return repository.save(produto); // O ID é gerado pelo MySQL/JPA automaticamente
    }
    @Transactional
    public Produto update(Produto produto) {
        logger.info("Atualizando produto ID: " + produto.getId());
        // Verificamos se existe antes de atualizar
        Produto existing = repository.findByIdOrThrow(produto.getId());

        existing.setNome(produto.getNome());
        existing.setPreco(produto.getPreco());
        existing.setDescricao(produto.getDescricao());
        existing.setEstoque(produto.getEstoque());
        // No JPA, o save() serve para criar ou atualizar
        return repository.save(existing);
    }

    public void delete(Long id) {
        logger.info("Removendo produto ID: " + id);
    Produto existing = repository.findByIdOrThrow(id);
    repository.delete(existing);
}
@Transactional
public Produto baixarEstoque(Long id, Integer qtd) {
    // Fluxo limpo: Busca -> Processa Regra no Modelo -> Persiste
    Produto p = repository.findByIdOrThrow(id);
    logger.info("Baixa de estoque: " + p.getNome() + " | Qtd: " + qtd);
    // A lógica de negócio reside na Entidade (Modelo Rico)
    p.baixarEstoque(qtd);
    // Salvamos o produto atualizado no banco de dados
    Produto produtoAtualizado = repository.save(p);
    // O log de sucesso é registrado após a confirmação da atualização no banco
    logger.info("Estoque atualizado com sucesso. Novo estoque: " +produtoAtualizado.getEstoque());
    // retorna o produto atualizado para o controller, que por sua vez retorna para o cliente
    return produtoAtualizado;
    }



}