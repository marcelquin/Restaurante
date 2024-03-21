package App.RestAPI.Infra.UseCase.Atendimento;

import App.RestAPI.Domain.Atendimento;
import App.RestAPI.Infra.Gateway.AtendimentoGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseAendimentoPut {

    private final AtendimentoGateway atendimentoGateway;

    public UseCaseAendimentoPut(AtendimentoGateway atendimentoGateway) {
        this.atendimentoGateway = atendimentoGateway;
    }

    public ResponseEntity<Atendimento> AdicionarItemCardapio(@RequestParam Long idAtendimento, @RequestParam Long[] idItemCardapio)
    { return atendimentoGateway.AdicionarItemCardapio(idAtendimento, idItemCardapio);}



}
