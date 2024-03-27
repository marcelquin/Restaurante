package App.RestAPI.Api.RestAPI;

import App.RestAPI.Domain.AtendimentoRecord;
import App.RestAPI.Infra.Persistence.Entity.AtendimentoEntity;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoDelete;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoGet;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoPost;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("atendimento")
@Tag(name = "atendimento",
     description = "Manipula informações relacionadas a entidade")
public class AtendimentoController {

    private final UseCaseAtendimentoGet atendimentoGet;
    private final UseCaseAtendimentoPost atendimentoPost;
    private final UseCaseAtendimentoPut atendimentoPut;
    private final UseCaseAtendimentoDelete atendimentoDelete;


    public AtendimentoController(UseCaseAtendimentoGet atendimentoGet, UseCaseAtendimentoPost atendimentoPost, UseCaseAtendimentoPut atendimentoPut, UseCaseAtendimentoDelete atendimentoDelete) {
        this.atendimentoGet = atendimentoGet;
        this.atendimentoPost = atendimentoPost;
        this.atendimentoPut = atendimentoPut;
        this.atendimentoDelete = atendimentoDelete;
    }

    @Operation(summary = "Lista Registros de tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarAtendimentos")
    public ResponseEntity<List<AtendimentoEntity>> ListarAtendimentos()
    { return atendimentoGet.ListarAtendimentos();}

    @Operation(summary = "Busca Registro por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarAtendimentoPorId")
    public ResponseEntity<AtendimentoRecord> BuscarAtendimentoPorId(@RequestParam Long id)
    { return atendimentoGet.BuscarAtendimentoPorId(id);}


    @Operation(summary = "Salva novo registro no banco de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoAtendimento")
    public ResponseEntity<AtendimentoRecord> NovoAtendimento(@RequestParam Long mesa, @RequestParam Long numeroPessoas)
    { return atendimentoPost.NovoAtendimento(mesa, numeroPessoas);}

    @Operation(summary = "Adiciona novo item ao registro", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AdicionarItemCardapio")
    public ResponseEntity<AtendimentoRecord> AdicionarItemCardapio(Long idAtendimento, Long idItemCardapio, Double quantidade)
    { return atendimentoPut.AdicionarItemCardapio(idAtendimento, idItemCardapio, quantidade);}


    @Operation(summary = "Altera Status do pedido", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/IniciarPedido")
    public ResponseEntity<AtendimentoRecord> IniciarPedido(@RequestParam Long idAtendimento)
    { return atendimentoPut.IniciarPedido(idAtendimento);}

    @Operation(summary = "Altera Status do pedido", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/PedidoPronto")
    public ResponseEntity<AtendimentoRecord> PedidoPronto(@RequestParam Long idAtendimento)
    { return atendimentoPut.PedidoPronto(idAtendimento);}


    @Operation(summary = "Altera Status do pedido", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/PedidoEntregue")
    public ResponseEntity<AtendimentoRecord> PedidoEntregue(@RequestParam Long idAtendimento)
    { return atendimentoPut.PedidoEntregue(idAtendimento);}

    @Operation(summary = "Altera Status do pedido", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/PedidoFinalizado")
    public ResponseEntity<AtendimentoRecord> PedidoFinalizado(@RequestParam Long idAtendimento)
    { return atendimentoPut.PedidoFinalizado(idAtendimento);}


    @Operation(summary = "Altera Status do pedido", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/CancelarPedido")
    public ResponseEntity<AtendimentoRecord> CancelarPedido(@RequestParam Long idAtendimento)
    { return atendimentoPut.CancelarPedido(idAtendimento);}

    @Operation(summary = "Deleta registro por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @DeleteMapping("/DeletarAtendimento")
    public ResponseEntity<AtendimentoRecord> DeletarAtendimento(@RequestParam Long id)
    { return atendimentoDelete.DeletarAtendimento(id);}

}
