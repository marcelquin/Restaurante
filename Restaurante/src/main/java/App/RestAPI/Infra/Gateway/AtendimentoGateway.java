package App.RestAPI.Infra.Gateway;

import App.RestAPI.Domain.AtendimentoRecord;
import App.RestAPI.Infra.Persistence.Entity.AtendimentoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AtendimentoGateway {

    public ResponseEntity<List<AtendimentoEntity>> ListarAtendimentos();
    public ResponseEntity<AtendimentoRecord> BuscarAtendimentoPorId(@RequestParam Long id);
    public ResponseEntity<AtendimentoRecord> NovoAtendimento(@RequestParam Long[] idItemCardapio, @RequestParam Long mesa);
    //verifica quantidade de ingredientes ->acessa itemcardap->reserva quantidade
    public ResponseEntity<AtendimentoRecord> AdicionarItemCardapio(@RequestParam Long idAtendimento, @RequestParam Long[] idItemCardapio);
    public ResponseEntity<AtendimentoRecord> IniciarPedido(@RequestParam Long idAtendimento);
    //acessar itemcardap -> reduzir o valor do item reservado no mesmo
    // ex: 4 bifes pedidos 4 reservados acessa get numero itemreservado - numero
    public ResponseEntity<AtendimentoRecord> PedidoPronto(@RequestParam Long idAtendimento);
    public ResponseEntity<AtendimentoRecord> PedidoEntregue(@RequestParam Long idAtendimento);
    public ResponseEntity<AtendimentoRecord> PedidoFinalizado(@RequestParam Long idAtendimento);
    //mediante pagamento
    public ResponseEntity<AtendimentoRecord> CancelarPedido(@RequestParam Long idAtendimento);
    //acessa itemcartop-> get numero ingredienteReservado -> soma a quantidade
    public ResponseEntity<AtendimentoRecord> DeletarAtendimento(@RequestParam Long id);
}
