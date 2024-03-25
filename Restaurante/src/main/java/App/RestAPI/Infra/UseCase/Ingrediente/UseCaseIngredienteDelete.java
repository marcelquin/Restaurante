package App.RestAPI.Infra.UseCase.Ingrediente;


import App.RestAPI.Domain.IngredienteRecord;
import App.RestAPI.Infra.Gateway.IngredienteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseIngredienteDelete {

    private final IngredienteGateway ingredienteGateway;


    public UseCaseIngredienteDelete(IngredienteGateway ingredienteGateway) {
        this.ingredienteGateway = ingredienteGateway;
    }

    public ResponseEntity<IngredienteRecord> DeletarIngrediente(@RequestParam Long id)
    { return ingredienteGateway.DeletarIngrediente(id);}

}
