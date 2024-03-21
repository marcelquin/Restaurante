package App.RestAPI.Infra.UseCase.Ingrediente;

import App.RestAPI.Domain.Ingrediente;
import App.RestAPI.Infra.Gateway.IngredienteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseIngredienteDalete {

    private final IngredienteGateway ingredienteGateway;


    public UseCaseIngredienteDalete(IngredienteGateway ingredienteGateway) {
        this.ingredienteGateway = ingredienteGateway;
    }

    public ResponseEntity<Ingrediente> DeletarIngrediente(@RequestParam Long id)
    { return ingredienteGateway.DeletarIngrediente(id);}

}
