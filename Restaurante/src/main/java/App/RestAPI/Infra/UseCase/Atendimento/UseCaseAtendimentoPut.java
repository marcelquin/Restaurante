package App.RestAPI.Infra.UseCase.Atendimento;


import App.RestAPI.Domain.AtendimentoRecord;
import App.RestAPI.Infra.Gateway.AtendimentoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseAtendimentoPut {

    private final AtendimentoGateway atendimentoGateway;

    public UseCaseAtendimentoPut(AtendimentoGateway atendimentoGateway) {
        this.atendimentoGateway = atendimentoGateway;
    }

    public ResponseEntity<AtendimentoRecord> AdicionarItemCardapio(Long idAtendimento, Long idItemCardapio, Double quantidade)
    { return atendimentoGateway.AdicionarItemCardapio(idAtendimento, idItemCardapio, quantidade);}
    public ResponseEntity<AtendimentoRecord> IniciarPedido(@RequestParam Long idAtendimento)
    { return atendimentoGateway.IniciarPedido(idAtendimento);}
    public ResponseEntity<AtendimentoRecord> PedidoPronto(@RequestParam Long idAtendimento)
    { return atendimentoGateway.PedidoPronto(idAtendimento);}
    public ResponseEntity<AtendimentoRecord> PedidoEntregue(@RequestParam Long idAtendimento)
    { return atendimentoGateway.PedidoEntregue(idAtendimento);}
    public ResponseEntity<AtendimentoRecord> PedidoFinalizado(@RequestParam Long idAtendimento)
    { return atendimentoGateway.PedidoFinalizado(idAtendimento);}

    public ResponseEntity<AtendimentoRecord> CancelarPedido(@RequestParam Long idAtendimento)
    { return atendimentoGateway.CancelarPedido(idAtendimento);}



}
