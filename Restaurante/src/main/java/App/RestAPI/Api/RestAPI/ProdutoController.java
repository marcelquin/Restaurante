package App.RestAPI.Api.RestAPI;

import App.RestAPI.Domain.ProdutoRecord;
import App.RestAPI.Infra.Persistence.Entity.ProdutoEntity;
import App.RestAPI.Infra.Persistence.Enum.UnidadeMedida;
import App.RestAPI.Infra.UseCase.Produto.UseCaseProdutoDelete;
import App.RestAPI.Infra.UseCase.Produto.UseCaseProdutoGet;
import App.RestAPI.Infra.UseCase.Produto.UseCaseProdutoPost;
import App.RestAPI.Infra.UseCase.Produto.UseCaseProdutoPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
@Tag(name = "produto",
        description = "Manipula informações relacionadas a entidade")
public class ProdutoController {

    private final UseCaseProdutoGet produtoGet;
    private final UseCaseProdutoPut produtoPut;
    private final UseCaseProdutoDelete produtoDelete;
    private final UseCaseProdutoPost produtoPost;

    public ProdutoController(UseCaseProdutoGet produtoGet, UseCaseProdutoPut produtoPut, UseCaseProdutoDelete produtoDelete, UseCaseProdutoPost produtoPost) {
        this.produtoGet = produtoGet;
        this.produtoPut = produtoPut;
        this.produtoDelete = produtoDelete;
        this.produtoPost = produtoPost;
    }
    @Operation(summary = "Lista Registros do banco de dados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarProdutos")
    public ResponseEntity<List<ProdutoEntity>> ListarProdutos()
    { return produtoGet.ListarProdutos();}

    @Operation(summary = "Busca Registro do banco de dados por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarProdutoPorId")
    public ResponseEntity<ProdutoRecord> BuscarProdutoPorId(@RequestParam Long id)
    { return produtoGet.BuscarProdutoPorId(id);}

    @Operation(summary = "Salva novo produto no banco de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoProduto")
    public ResponseEntity<ProdutoRecord> NovoProduto(@RequestParam Long idNota, @RequestParam String nome,
                                                     @RequestParam String descrisao, @RequestParam Double valorNotaProduto,
                                                     @RequestParam Double quantidade, @RequestParam UnidadeMedida unidadeMedida,
                                                     @RequestParam Double porcentegemLucroProduto)
    { return produtoPost.NovoProduto(idNota, nome, descrisao, valorNotaProduto, quantidade,unidadeMedida, porcentegemLucroProduto);}

    @Operation(summary = "Adiciona estoque ao produto no banco de dados", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AdicioanrEstoque")
    public ResponseEntity<ProdutoRecord> AdicioanrEstoque(@RequestParam Long idnota, @RequestParam Long idProduto,
                                                          @RequestParam Double valorNota,@RequestParam Double quantidade,
                                                          @RequestParam Double porcentegemLucroProduto)
    { return produtoPut.AdicioanrEstoque(idnota, idProduto, valorNota, quantidade, porcentegemLucroProduto);}

    @Operation(summary = "Edita Registro do banco de dados", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarDadosProduto")
    public ResponseEntity<ProdutoRecord> AlterarDadosProduto(@RequestParam Long idProduto, @RequestParam String nome,
                                                             @RequestParam String Descrisao)
    { return produtoPut.AlterarDadosProduto(idProduto, nome, Descrisao);}

    @Operation(summary = "Deleta Registro do banco de dados por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @DeleteMapping("/DeletarProduto")
    public ResponseEntity<ProdutoRecord> DeletarProduto(@RequestParam Long idProduto)
    { return produtoDelete.DeletarProduto(idProduto);}
}
