package App.RestAPI.Infra.UseCase.Produto;

import App.RestAPI.Domain.ProdutoRecord;
import App.RestAPI.Domain.ProdutoRequest;
import App.RestAPI.Infra.Gateway.ProdutoGateway;
import App.RestAPI.Infra.Persistence.Enum.UnidadeMedida;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseProdutoPut {

    private final ProdutoGateway produtoGateway;


    public UseCaseProdutoPut(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public ResponseEntity<ProdutoRecord> AlterarDadosProduto(@RequestParam Long idProduto, @RequestParam String nome,
                                                             @RequestParam String Descrisao)
    { return produtoGateway.AlterarDadosProduto(idProduto, nome, Descrisao);}

    public ResponseEntity<ProdutoRecord> AdicioanrEstoque(@RequestParam Long idnota, @RequestParam Long idProduto,
                                                          @RequestParam Double valorNota, @RequestParam Double quantidade,
                                                          @RequestParam Double porcentegemLucroProduto)
    {return  produtoGateway.AdicioanrEstoque(idnota, idProduto, valorNota, quantidade,porcentegemLucroProduto);}



}
