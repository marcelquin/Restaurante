package App.RestAPI.Api.RestAPI;

import App.RestAPI.Domain.NotaRecord;
import App.RestAPI.Domain.ProdutoRequest;
import App.RestAPI.Infra.Persistence.Entity.NotaEntity;
import App.RestAPI.Infra.UseCase.Nota.UseCaseNotasGet;
import App.RestAPI.Infra.UseCase.Nota.UseCaseNotasPost;
import App.RestAPI.Infra.UseCase.Nota.UseCaseNotasPut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notaFiscal")
public class NotaController {

    private final UseCaseNotasGet notasGet;
    private final UseCaseNotasPost notasPost;
    private final UseCaseNotasPut notasPut;

    public NotaController(UseCaseNotasGet notasGet, UseCaseNotasPost notasPost, UseCaseNotasPut notasPut) {
        this.notasGet = notasGet;
        this.notasPost = notasPost;
        this.notasPut = notasPut;
    }

    @GetMapping("/ListarNotas")
    public ResponseEntity<List<NotaEntity>> ListarNotas()
    { return notasGet.ListarNotas();}

    @GetMapping("/BuscaNotaPorId")
    public ResponseEntity<NotaRecord> BuscaNotaPorId(@RequestParam Long id)
    { return notasGet.BuscaNotaPorId(id);}

    @PostMapping("/NovaNota")
    public ResponseEntity<NotaRecord> NovaNota(@RequestParam Long idFornecedor, @RequestBody ProdutoRequest[] produtoRequest,
                                               @RequestParam Double valorNota, @RequestParam String numeroNota, @RequestParam Double porcentegemLucroProduto)
    { return notasPost.NovaNota(idFornecedor, produtoRequest, valorNota, numeroNota, porcentegemLucroProduto);}
    @PutMapping("/AlterarNota")
    public ResponseEntity<NotaRecord> AlterarNota(@RequestParam Long idNota, @RequestParam String numeroNota)
    { return notasPut.AlterarNota(idNota, numeroNota);}
}
