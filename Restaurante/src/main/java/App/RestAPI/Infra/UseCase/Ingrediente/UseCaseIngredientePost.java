package App.RestAPI.Infra.UseCase.Ingrediente;


import App.RestAPI.Domain.IngredienteRecord;
import App.RestAPI.Infra.Gateway.IngredienteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseIngredientePost {

    private final IngredienteGateway ingredienteGateway;


    public UseCaseIngredientePost(IngredienteGateway ingredienteGateway) {
        this.ingredienteGateway = ingredienteGateway;
    }

    public ResponseEntity<IngredienteRecord> NovoIngrediente(@RequestParam Long idProduto, @RequestParam String nome, @RequestParam String descrisao,
                                                             @RequestParam Double Quantidade, @RequestParam Double QuantidadeProduto)
    { return ingredienteGateway.NovoIngrediente(idProduto,nome,descrisao, Quantidade, QuantidadeProduto);}

}
