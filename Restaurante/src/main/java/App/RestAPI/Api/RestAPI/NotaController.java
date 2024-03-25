package App.RestAPI.Api.RestAPI;

import App.RestAPI.Domain.NotaRecord;
import App.RestAPI.Domain.ProdutoRequest;
import App.RestAPI.Infra.Persistence.Entity.NotaEntity;
import App.RestAPI.Infra.UseCase.Nota.UseCaseNotasGet;
import App.RestAPI.Infra.UseCase.Nota.UseCaseNotasPost;
import App.RestAPI.Infra.UseCase.Nota.UseCaseNotasPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notaFiscal")
@Tag(name = "notaFiscal",
        description = "Manipula informações relacionadas a entidade")
public class NotaController {

    private final UseCaseNotasGet notasGet;
    private final UseCaseNotasPost notasPost;
    private final UseCaseNotasPut notasPut;

    public NotaController(UseCaseNotasGet notasGet, UseCaseNotasPost notasPost, UseCaseNotasPut notasPut) {
        this.notasGet = notasGet;
        this.notasPost = notasPost;
        this.notasPut = notasPut;
    }

    @Operation(summary = "Lista Registros do banco de dados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarNotas")
    public ResponseEntity<List<NotaEntity>> ListarNotas()
    { return notasGet.ListarNotas();}

    @Operation(summary = "Busca Registro do banco de dados por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscaNotaPorId")
    public ResponseEntity<NotaRecord> BuscaNotaPorId(@RequestParam Long id)
    { return notasGet.BuscaNotaPorId(id);}

    @Operation(summary = "Salva novo Registro do banco de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovaNota")
    public ResponseEntity<NotaRecord> NovaNota(@RequestParam Long idFornecedor, @RequestBody ProdutoRequest[] produtoRequest,
                                               @RequestParam Double valorNota, @RequestParam String numeroNota, @RequestParam Double porcentegemLucroProduto)
    { return notasPost.NovaNota(idFornecedor, produtoRequest, valorNota, numeroNota, porcentegemLucroProduto);}

    @Operation(summary = "Edita Registro do banco de dados", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarNota")
    public ResponseEntity<NotaRecord> AlterarNota(@RequestParam Long idNota, @RequestParam String numeroNota)
    { return notasPut.AlterarNota(idNota, numeroNota);}
}
