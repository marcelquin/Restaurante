package App.RestAPI.Infra.UseCase.Ingrediente;

import App.RestAPI.Domain.Ingrediente;
import App.RestAPI.Infra.Gateway.IngredienteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseIngredientePost {

    private final IngredienteGateway ingredienteGateway;


    public UseCaseIngredientePost(IngredienteGateway ingredienteGateway) {
        this.ingredienteGateway = ingredienteGateway;
    }

    public ResponseEntity<Ingrediente> NovoIngrediente(@RequestParam Long idProduto, @RequestParam Double Quantidade)
    { return ingredienteGateway.NovoIngrediente(idProduto, Quantidade);}

}
