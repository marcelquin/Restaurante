package App.RestAPI.Api.RestAPI;

import App.RestAPI.Domain.IngredienteRecord;
import App.RestAPI.Infra.Persistence.Entity.IngredienteEntity;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredienteDelete;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredienteGet;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredientePost;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredientePut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ingrediente")
@Tag(name = "ingrediente",
        description = "Manipula informações relacionadas a entidade")
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

    @Operation(summary = "Lista Registros do banco de dados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarIngredientes")
    public ResponseEntity<List<IngredienteEntity>> ListarIngredientes()
    { return ingredienteGet.ListarIngredientes();}

    @Operation(summary = "Busca Registro do banco de dados por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscaIngredientePorId")
    public ResponseEntity<IngredienteRecord> BuscaIngredientePorId(@RequestParam Long id)
    { return ingredienteGet.BuscaIngredientePorId(id);}

    @Operation(summary = "Salva novo Registro do banco de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoIngrediente")
    public ResponseEntity<IngredienteRecord> NovoIngrediente(@RequestParam Long idProduto, @RequestParam String nome, @RequestParam String descrisao,
                                                             @RequestParam Double Quantidade, @RequestParam Double QuantidadeProduto)
    { return ingredientePost.NovoIngrediente(idProduto, nome, descrisao, Quantidade, QuantidadeProduto);}

    @Operation(summary = "Edita Registro do banco de dados", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/ALterarIngrediente")
    public ResponseEntity<IngredienteRecord> ALterarIngrediente(@RequestParam Long idIngrediente,@RequestParam Long idProduto, Double Quantidade, @RequestParam Double QuantidadeProduto)
    { return ingredientePut.ALterarIngrediente(idIngrediente, idProduto, Quantidade, QuantidadeProduto);}


    @Operation(summary = "Deleta Registro do banco de dados por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @DeleteMapping("/DeletarIngrediente")
    public ResponseEntity<IngredienteRecord> DeletarIngrediente(@RequestParam Long id)
    {return ingredienteDelete.DeletarIngrediente(id);}
}
