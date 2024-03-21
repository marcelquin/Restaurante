package App.RestAPI.Infra.UseCase.Produto;

import App.RestAPI.Domain.ProdutoRecord;
import App.RestAPI.Infra.Gateway.ProdutoGateway;
import App.RestAPI.Infra.Persistence.Entity.ProdutoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseProdutoGet {

    private final ProdutoGateway produtoGateway;


    public UseCaseProdutoGet(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public ResponseEntity<List<ProdutoEntity>> ListarProdutos()
    { return produtoGateway.ListarProdutos();}

    public ResponseEntity<ProdutoRecord> BuscarProdutoPorId(@RequestParam Long id)
    { return produtoGateway.BuscarProdutoPorId(id);}

}
