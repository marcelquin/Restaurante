package App.RestAPI.Api.RestAPI;

import App.RestAPI.Domain.AtendimentoRecord;
import App.RestAPI.Infra.Persistence.Entity.AtendimentoEntity;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoDelete;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoGet;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoPost;
import App.RestAPI.Infra.UseCase.Atendimento.UseCaseAtendimentoPut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("atendimento")
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
    @GetMapping("/ListarAtendimentos")
    public ResponseEntity<List<AtendimentoEntity>> ListarAtendimentos()
    { return atendimentoGet.ListarAtendimentos();}

    @GetMapping("/BuscarAtendimentoPorId")
    public ResponseEntity<AtendimentoRecord> BuscarAtendimentoPorId(@RequestParam Long id)
    { return atendimentoGet.BuscarAtendimentoPorId(id);}

    @PostMapping("/")
    public ResponseEntity<AtendimentoRecord> NovoAtendimento(@RequestParam Long[] idItemCardapio, @RequestParam Long mesa)
    { return atendimentoPost.NovoAtendimento(idItemCardapio, mesa);}

    @PutMapping("/AdicionarItemCardapio")
    public ResponseEntity<AtendimentoRecord> AdicionarItemCardapio(@RequestParam Long idAtendimento, @RequestParam Long[] idItemCardapio)
    { return atendimentoPut.AdicionarItemCardapio(idAtendimento, idItemCardapio);}

    @PutMapping("/IniciarPedido")
    public ResponseEntity<AtendimentoRecord> IniciarPedido(@RequestParam Long idAtendimento)
    { return atendimentoPut.IniciarPedido(idAtendimento);}

    @PutMapping("/PedidoPronto")
    public ResponseEntity<AtendimentoRecord> PedidoPronto(@RequestParam Long idAtendimento)
    { return atendimentoPut.PedidoPronto(idAtendimento);}

    @PutMapping("/PedidoEntregue")
    public ResponseEntity<AtendimentoRecord> PedidoEntregue(@RequestParam Long idAtendimento)
    { return atendimentoPut.PedidoEntregue(idAtendimento);}

    @PutMapping("/PedidoFinalizado")
    public ResponseEntity<AtendimentoRecord> PedidoFinalizado(@RequestParam Long idAtendimento)
    { return atendimentoPut.PedidoFinalizado(idAtendimento);}

    @PutMapping("/")
    public ResponseEntity<AtendimentoRecord> CancelarPedido(@RequestParam Long idAtendimento)
    { return atendimentoPut.CancelarPedido(idAtendimento);}

    @DeleteMapping("/DeletarAtendimento")
    public ResponseEntity<AtendimentoRecord> DeletarAtendimento(@RequestParam Long id)
    { return atendimentoDelete.DeletarAtendimento(id);}

}
