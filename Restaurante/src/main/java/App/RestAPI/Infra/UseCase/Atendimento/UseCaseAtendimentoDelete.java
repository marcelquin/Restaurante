package App.RestAPI.Infra.UseCase.Atendimento;


import App.RestAPI.Domain.AtendimentoRecord;
import App.RestAPI.Infra.Gateway.AtendimentoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseAtendimentoDelete {

    private final AtendimentoGateway atendimentoGateway;

    public UseCaseAtendimentoDelete(AtendimentoGateway atendimentoGateway) {
        this.atendimentoGateway = atendimentoGateway;
    }

    public ResponseEntity<AtendimentoRecord> DeletarAtendimento(@RequestParam Long id)
    { return atendimentoGateway.DeletarAtendimento(id);}


}
