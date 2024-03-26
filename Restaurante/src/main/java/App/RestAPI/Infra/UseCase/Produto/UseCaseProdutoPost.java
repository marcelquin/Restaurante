package App.RestAPI.Infra.UseCase.Produto;

import App.RestAPI.Domain.ProdutoRecord;
import App.RestAPI.Infra.Gateway.ProdutoGateway;
import App.RestAPI.Infra.Persistence.Enum.UnidadeMedida;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseProdutoPost {

    private final ProdutoGateway produtoGateway;


    public UseCaseProdutoPost(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }


    public ResponseEntity<ProdutoRecord> NovoProduto(@RequestParam Long idNota, @RequestParam String nome,
                                                      @RequestParam String descrisao, @RequestParam Double valorNotaProduto,
                                                      @RequestParam Double quantidade, @RequestParam UnidadeMedida unidadeMedida,
                                                      @RequestParam Double porcentegemLucroProduto)
    { return produtoGateway.NovoProduto(idNota, nome, descrisao, valorNotaProduto, quantidade, unidadeMedida, porcentegemLucroProduto);}
}
