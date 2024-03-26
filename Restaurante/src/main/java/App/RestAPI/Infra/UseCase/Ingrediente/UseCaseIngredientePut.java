package App.RestAPI.Infra.UseCase.Ingrediente;

import App.RestAPI.Domain.IngredienteRecord;
import App.RestAPI.Infra.Gateway.IngredienteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseIngredientePut {

    private final IngredienteGateway ingredienteGateway;


    public UseCaseIngredientePut(IngredienteGateway ingredienteGateway) {
        this.ingredienteGateway = ingredienteGateway;
    }

    public ResponseEntity<IngredienteRecord> ALterarIngrediente(@RequestParam Long idIngrediente, @RequestParam Long idProduto, Double Quantidade, @RequestParam Double QuantidadeProduto)
    { return ingredienteGateway.ALterarIngrediente(idIngrediente, idProduto, Quantidade, QuantidadeProduto);}


    public ResponseEntity<IngredienteRecord> AdicionarIngredienteEstoque(@RequestParam Long idIngrediente, @RequestParam Double Quantidade)
    { return ingredienteGateway.AdicionarIngredienteEstoque(idIngrediente, Quantidade);}

}
