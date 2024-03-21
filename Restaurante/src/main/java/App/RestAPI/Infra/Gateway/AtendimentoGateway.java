package App.RestAPI.Infra.Gateway;

import App.RestAPI.Domain.Atendimento;
import App.RestAPI.Infra.Persistence.Entity.AtendimentoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AtendimentoGateway {

    public ResponseEntity<List<AtendimentoEntity>> ListarAtendimentos();
    public ResponseEntity<Atendimento> BuscarAtendimentoPorId(@RequestParam Long id);
    public ResponseEntity<Atendimento> NovoAtendimento(@RequestParam Long[] idItemCardapio, @RequestParam Long mesa);
    //verifica quantidade de ingredientes ->acessa itemcardap->reserva quantidade
    public ResponseEntity<Atendimento> AdicionarItemCardapio(@RequestParam Long idAtendimento, @RequestParam Long[] idItemCardapio);
    public ResponseEntity<Atendimento> IniciarPedido(@RequestParam Long idAtendimento);
    //acessar itemcardap -> reduzir o valor do item reservado no mesmo
    // ex: 4 bifes pedidos 4 reservados acessa get numero itemreservado - numero
    public ResponseEntity<Atendimento> PedidoPronto(@RequestParam Long idAtendimento);
    public ResponseEntity<Atendimento> PedidoEntregue(@RequestParam Long idAtendimento);
    public ResponseEntity<Atendimento> PedidoFinalizado(@RequestParam Long idAtendimento);
    //mediante pagamento
    public ResponseEntity<Atendimento> CancelarPedido(@RequestParam Long idAtendimento);
    //acessa itemcartop-> get numero ingredienteReservado -> soma a quantidade
    public ResponseEntity<Atendimento> DeletarAtendimento(@RequestParam Long id);
}
