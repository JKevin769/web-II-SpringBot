package ifg.urt.api_carrinho.controller;


import ifg.urt.api_carrinho.model.Produto;
import ifg.urt.api_carrinho.service.ProdutoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return service.findById(id);
    }
}
