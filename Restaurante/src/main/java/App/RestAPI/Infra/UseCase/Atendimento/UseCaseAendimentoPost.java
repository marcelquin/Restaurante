package App.RestAPI.Infra.UseCase.Atendimento;


import App.RestAPI.Domain.AtendimentoRecord;
import App.RestAPI.Infra.Gateway.AtendimentoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseAendimentoPost {

    private final AtendimentoGateway atendimentoGateway;

    public UseCaseAendimentoPost(AtendimentoGateway atendimentoGateway) {
        this.atendimentoGateway = atendimentoGateway;
    }

    public ResponseEntity<AtendimentoRecord> NovoAtendimento(@RequestParam Long[] idItemCardapio, @RequestParam Long mesa)
    { return atendimentoGateway.NovoAtendimento(idItemCardapio, mesa);}


}
