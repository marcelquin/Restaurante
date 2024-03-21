package App.RestAPI.Infra.Gateway;

import App.RestAPI.Domain.Ingrediente;
import App.RestAPI.Infra.Persistence.Entity.IngredienteEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IngredienteGateway {

    public ResponseEntity<List<IngredienteEntity>> ListarIngredientes();

    public ResponseEntity<Ingrediente> BuscaIngredientePorId(@RequestParam Long id);
    public ResponseEntity<Ingrediente> NovoIngrediente(@RequestParam Long idProduto, @RequestParam Double Quantidade);
    public ResponseEntity<Ingrediente> ALterarIngrediente(@RequestParam Long idIngrediente,@RequestParam Long idProduto, Double Quantidade);
    public ResponseEntity<Ingrediente> DeletarIngrediente(@RequestParam Long id);
}
