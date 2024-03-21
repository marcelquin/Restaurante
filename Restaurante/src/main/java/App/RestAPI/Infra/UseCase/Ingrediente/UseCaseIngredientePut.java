package App.RestAPI.Infra.UseCase.Ingrediente;

import App.RestAPI.Domain.Ingrediente;
import App.RestAPI.Infra.Gateway.IngredienteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseIngredientePut {

    private final IngredienteGateway ingredienteGateway;


    public UseCaseIngredientePut(IngredienteGateway ingredienteGateway) {
        this.ingredienteGateway = ingredienteGateway;
    }

    public ResponseEntity<Ingrediente> ALterarIngrediente(@RequestParam Long idIngrediente, @RequestParam Long idProduto, Double Quantidade)
    { return ingredienteGateway.ALterarIngrediente(idIngrediente, idProduto, Quantidade);}

}
