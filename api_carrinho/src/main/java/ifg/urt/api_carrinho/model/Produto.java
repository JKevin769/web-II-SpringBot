package ifg.urt.api_carrinho.model;


import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable {


    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer estoque;

    public Produto(){}

    public Produto(Long id, String nome, String descricao, Double preco, Integer estoque) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Produto produto)) return false;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(descricao, produto.descricao) && Objects.equals(preco, produto.preco) && Objects.equals(estoque, produto.estoque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, preco, estoque);
    }



    public void baixarEstoque(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade para baixar deve ser maior que zero.");
        }
        if (this.estoque < quantidade) {
            throw new RuntimeException("\"Estoque insuficiente para o produto: \" + this.nome + \". Disponível: \" + this.estoque + \", Solicitado: \" + quantidade");

        }
        this.estoque -= quantidade;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}



