package App.RestAPI.Infra.UseCase.Atendimento;


import App.RestAPI.Domain.AtendimentoRecord;
import App.RestAPI.Infra.Gateway.AtendimentoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseAendimentoDelete {

    private final AtendimentoGateway atendimentoGateway;

    public UseCaseAendimentoDelete(AtendimentoGateway atendimentoGateway) {
        this.atendimentoGateway = atendimentoGateway;
    }

    public ResponseEntity<AtendimentoRecord> DeletarAtendimento(@RequestParam Long id)
    { return atendimentoGateway.DeletarAtendimento(id);}


}
