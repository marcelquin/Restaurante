package App.RestAPI.Infra.UseCase.Produto;

import App.RestAPI.Domain.ProdutoRecord;
import App.RestAPI.Infra.Gateway.ProdutoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseProdutoDelete {

    private final ProdutoGateway produtoGateway;


    public UseCaseProdutoDelete(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }


    public ResponseEntity<ProdutoRecord> DeletarProduto(@RequestParam Long idProduto)
    { return produtoGateway.DeletarProduto(idProduto);}
}
