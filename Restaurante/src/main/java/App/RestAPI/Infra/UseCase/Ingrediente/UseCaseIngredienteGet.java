package App.RestAPI.Infra.UseCase.Ingrediente;

import App.RestAPI.Domain.Ingrediente;
import App.RestAPI.Infra.Gateway.IngredienteGateway;
import App.RestAPI.Infra.Persistence.Entity.IngredienteEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseIngredienteGet {

    private final IngredienteGateway ingredienteGateway;


    public UseCaseIngredienteGet(IngredienteGateway ingredienteGateway) {
        this.ingredienteGateway = ingredienteGateway;
    }

    public ResponseEntity<List<IngredienteEntity>> ListarIngredientes()
    { return ingredienteGateway.ListarIngredientes();}

    public ResponseEntity<Ingrediente> BuscaIngredientePorId(@RequestParam Long id)
    { return ingredienteGateway.BuscaIngredientePorId(id);}
}
