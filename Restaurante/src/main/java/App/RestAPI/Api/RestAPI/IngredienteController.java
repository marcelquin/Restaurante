package App.RestAPI.Api.RestAPI;

import App.RestAPI.Domain.IngredienteRecord;
import App.RestAPI.Infra.Persistence.Entity.IngredienteEntity;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredienteDelete;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredienteGet;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredientePost;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredientePut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingrediente")
public class IngredienteController {

    private final UseCaseIngredienteGet ingredienteGet;
    private final UseCaseIngredientePost ingredientePost;
    private final UseCaseIngredientePut ingredientePut;
    private final UseCaseIngredienteDelete ingredienteDelete;

    public IngredienteController(UseCaseIngredienteGet ingredienteGet, UseCaseIngredientePost ingredientePost, UseCaseIngredientePut ingredientePut, UseCaseIngredienteDelete ingredienteDelete) {
        this.ingredienteGet = ingredienteGet;
        this.ingredientePost = ingredientePost;
        this.ingredientePut = ingredientePut;
        this.ingredienteDelete = ingredienteDelete;
    }
    @GetMapping("/ListarIngredientes")
    public ResponseEntity<List<IngredienteEntity>> ListarIngredientes()
    { return ingredienteGet.ListarIngredientes();}

    @GetMapping("/BuscaIngredientePorId")
    public ResponseEntity<IngredienteRecord> BuscaIngredientePorId(@RequestParam Long id)
    { return ingredienteGet.BuscaIngredientePorId(id);}

    @PostMapping("/NovoIngrediente")
    public ResponseEntity<IngredienteRecord> NovoIngrediente(@RequestParam Long idProduto, @RequestParam String nome, @RequestParam String descrisao,
                                                             @RequestParam Double Quantidade, @RequestParam Double QuantidadeProduto)
    { return ingredientePost.NovoIngrediente(idProduto, nome, descrisao, Quantidade, QuantidadeProduto);}

    @PutMapping("/ALterarIngrediente")
    public ResponseEntity<IngredienteRecord> ALterarIngrediente(@RequestParam Long idIngrediente,@RequestParam Long idProduto, Double Quantidade, @RequestParam Double QuantidadeProduto)
    { return ingredientePut.ALterarIngrediente(idIngrediente, idProduto, Quantidade, QuantidadeProduto);}

    @DeleteMapping("/DeletarIngrediente")
    public ResponseEntity<IngredienteRecord> DeletarIngrediente(@RequestParam Long id)
    {return ingredienteDelete.DeletarIngrediente(id);}
}
