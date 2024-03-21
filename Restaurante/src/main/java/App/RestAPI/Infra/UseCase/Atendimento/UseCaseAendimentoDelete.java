package App.RestAPI.Infra.UseCase.Atendimento;

import App.RestAPI.Domain.Atendimento;
import App.RestAPI.Infra.Gateway.AtendimentoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseAendimentoDelete {

    private final AtendimentoGateway atendimentoGateway;

    public UseCaseAendimentoDelete(AtendimentoGateway atendimentoGateway) {
        this.atendimentoGateway = atendimentoGateway;
    }

    public ResponseEntity<Atendimento> DeletarAtendimento(@RequestParam Long id)
    { return atendimentoGateway.DeletarAtendimento(id);}


}
