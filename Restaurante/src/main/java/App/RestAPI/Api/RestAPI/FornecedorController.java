package App.RestAPI.Api.RestAPI;

import App.RestAPI.Domain.FornecedorRecord;
import App.RestAPI.Infra.Persistence.Entity.FornecedorEntity;
import App.RestAPI.Infra.UseCase.Fornecedor.UseCaseFornecedorDelete;
import App.RestAPI.Infra.UseCase.Fornecedor.UseCaseFornecedorGet;
import App.RestAPI.Infra.UseCase.Fornecedor.UseCaseFornecedorPost;
import App.RestAPI.Infra.UseCase.Fornecedor.UseCaseFornecedorPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("fornecedor")
@Tag(name = "fornecedor",
        description = "Manipula informações relacionadas a entidade")
public class FornecedorController {

    private final UseCaseFornecedorGet fornecedorGet;
    private final UseCaseFornecedorPost fornecedorPost;
    private final UseCaseFornecedorPut fornecedorPut;
    private final UseCaseFornecedorDelete fornecedorDelete;

    public FornecedorController(UseCaseFornecedorGet fornecedorGet, UseCaseFornecedorPost fornecedorPost, UseCaseFornecedorPut fornecedorPut, UseCaseFornecedorDelete fornecedorDelete) {
        this.fornecedorGet = fornecedorGet;
        this.fornecedorPost = fornecedorPost;
        this.fornecedorPut = fornecedorPut;
        this.fornecedorDelete = fornecedorDelete;
    }

    @Operation(summary = "Lista Registros do banco de dados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/listarFornecedor")
    public ResponseEntity<List<FornecedorEntity>> listarFornecedor()
    { return fornecedorGet.listarFornecedor();}

    @Operation(summary = "Busca Registro do banco de dados por id", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarFornecedorPorId")
    public ResponseEntity<FornecedorRecord> BuscarFornecedorPorId(@RequestParam Long id)
    { return fornecedorGet.BuscarFornecedorPorId(id);}

    @Operation(summary = "Salva novo Registro do banco de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoFonecedor")
    public ResponseEntity<FornecedorRecord> NovoFonecedor(@RequestParam String nome, @RequestParam String razaoSocial,
                                                          @RequestParam Long cnpj, @RequestParam String areaAtuacao, @RequestParam LocalDate dataInicioContrato,
                                                          @RequestParam String Logradouro, @RequestParam String numero,
                                                          @RequestParam String bairro, @RequestParam String complemento,
                                                          @RequestParam Long cep, @RequestParam String cidade,
                                                          @RequestParam String Estado, @RequestParam String email,
                                                          @RequestParam Long telefone)
    { return fornecedorPost.NovoFonecedor(nome, razaoSocial, cnpj, areaAtuacao, dataInicioContrato, Logradouro, numero, bairro, complemento, cep, cidade, Estado, email, telefone);}

    @Operation(summary = "Edita Registro do banco de dados", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarDados")
    public ResponseEntity<FornecedorRecord> AlterarDados(@RequestParam Long idFornecedor, String nome, @RequestParam String razaoSocial,
                                                         @RequestParam Long cnpj)
    { return fornecedorPut.AlterarDados(idFornecedor, nome, razaoSocial, cnpj);}

    @Operation(summary = "Altera Endereço do registro", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarEndereco")
    public ResponseEntity<FornecedorRecord> AlterarEndereco(@RequestParam Long idFornecedor,@RequestParam String Logradouro, @RequestParam String numero,
                                                            @RequestParam String bairro, @RequestParam String complemento,
                                                            @RequestParam Long cep, @RequestParam String cidade,
                                                            @RequestParam String Estado )
    { return fornecedorPut.AlterarEndereco(idFornecedor, Logradouro, numero, bairro, complemento, cep, cidade, Estado);}

    @Operation(summary = "Altera Contate do registro", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AlterarContato")
    public ResponseEntity<FornecedorRecord> AlterarContato(@RequestParam Long idFornecedor, @RequestParam String email,
                                                           @RequestParam Long telefone)
    { return fornecedorPut.AlterarContato(idFornecedor, email, telefone);}

    @Operation(summary = "Deleta Registro do banco de dados por id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @DeleteMapping()
    public ResponseEntity<FornecedorRecord> DeletarFornecedorPorId(@RequestParam Long id)
    { return fornecedorDelete.DeletarFornecedorPorId(id);}

}
