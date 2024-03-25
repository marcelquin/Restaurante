package App.RestAPI.Api.RestAPI;

import App.RestAPI.Domain.ItemCardarioRecord;
import App.RestAPI.Infra.Persistence.Entity.ItemCardapioEntity;
import App.RestAPI.Infra.UseCase.Ingrediente.UseCaseIngredientePost;
import App.RestAPI.Infra.UseCase.ItemCardapio.UseCaseItemCardapioDelete;
import App.RestAPI.Infra.UseCase.ItemCardapio.UseCaseItemCardapioPost;
import App.RestAPI.Infra.UseCase.ItemCardapio.UseCaseItemCardapioPut;
import App.RestAPI.Infra.UseCase.ItemCardapio.UseCaseItemPedidoGet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("itemCardapio")
@Tag(name = "itemCardapio",
        description = "Manipula informações relacionadas a entidade")
public class ItemCardapioController {

    private final UseCaseItemPedidoGet itemPedidoGet;
    private final UseCaseItemCardapioPost itemCardapioPost;
    private final UseCaseItemCardapioPut itemCardapioPut;
    private final UseCaseItemCardapioDelete itemCardapioDelete;

    public ItemCardapioController(UseCaseItemPedidoGet itemPedidoGet, UseCaseItemCardapioPost itemCardapioPost, UseCaseItemCardapioPut itemCardapioPut, UseCaseItemCardapioDelete itemCardapioDelete) {
        this.itemPedidoGet = itemPedidoGet;
        this.itemCardapioPost = itemCardapioPost;
        this.itemCardapioPut = itemCardapioPut;
        this.itemCardapioDelete = itemCardapioDelete;
    }

    @Operation(summary = "Lista Registros do banco de dados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarItemCardapio")
    public ResponseEntity<List<ItemCardapioEntity>> ListarItemCardapio()
    { return itemPedidoGet.ListarItemCardapio();}

    @Operation(summary = "Busca Registro do banco de dados por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarItemCardapioPorId")
    public ResponseEntity<ItemCardarioRecord> BuscarItemCardapioPorId(@RequestParam Long id)
    { return itemPedidoGet.BuscarItemCardapioPorId(id);}

    @Operation(summary = "Salva novo Registro do banco de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoItemCardapio")
    public ResponseEntity<ItemCardarioRecord> NovoItemCardapio(@RequestParam Long[] idIngredientes, @RequestParam String nome,
                                                               @RequestParam String descrisao, @RequestParam Double porcentagemproducao)
    { return itemCardapioPost.NovoItemCardapio(idIngredientes, nome, descrisao, porcentagemproducao);}

    @Operation(summary = "Edita Registro do banco de dados", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/EditarItemCardapio")
    public ResponseEntity<ItemCardarioRecord> EditarItemCardapio(@RequestParam Long idItemCardapio, @RequestParam String nome,
                                                                 @RequestParam String descrisao)
    { return itemCardapioPut.EditarItemCardapio(idItemCardapio, nome, descrisao);}

    @Operation(summary = "Altera ingredientes do item cardapio", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarIngredientes")
    public ResponseEntity<ItemCardarioRecord> AlterarIngredientes(@RequestParam Long idItemCardapio, @RequestParam Long[] idIngredientes)
    { return itemCardapioPut.AlterarIngredientes(idItemCardapio, idIngredientes);}

    @Operation(summary = "Deleta Registro do banco de dados por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @DeleteMapping("/ExcluirItemCardapio")
    public ResponseEntity<ItemCardarioRecord> ExcluirItemCardapio(@RequestParam Long idItemCardapio)
    { return itemCardapioDelete.ExcluirItemCardapio(idItemCardapio);}
}
