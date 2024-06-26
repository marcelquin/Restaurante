package App.RestAPI.Infra.Gateway;

import App.RestAPI.Domain.AtendimentoRecord;
import App.RestAPI.Infra.Persistence.Entity.AtendimentoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AtendimentoGateway {

    public ResponseEntity<List<AtendimentoEntity>> ListarAtendimentos();
    public ResponseEntity<AtendimentoRecord> BuscarAtendimentoPorId(@RequestParam Long id);
    public ResponseEntity<AtendimentoRecord> NovoAtendimento(@RequestParam Long mesa, @RequestParam Long numeroPessoas);

    public ResponseEntity<AtendimentoRecord> AdicionarItemCardapio(Long idAtendimento, Long idItemCardapio, Double quantidade);

    public ResponseEntity<AtendimentoRecord> IniciarPedido(@RequestParam Long idAtendimento);

    public ResponseEntity<AtendimentoRecord> PedidoPronto(@RequestParam Long idAtendimento);
    public ResponseEntity<AtendimentoRecord> PedidoEntregue(@RequestParam Long idAtendimento);
    public ResponseEntity<AtendimentoRecord> PedidoFinalizado(@RequestParam Long idAtendimento);

    public ResponseEntity<AtendimentoRecord> CancelarPedido(@RequestParam Long idAtendimento);

    public ResponseEntity<AtendimentoRecord> DeletarAtendimento(@RequestParam Long id);
}
