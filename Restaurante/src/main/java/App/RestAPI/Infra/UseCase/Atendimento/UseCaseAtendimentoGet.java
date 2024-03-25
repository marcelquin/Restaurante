package App.RestAPI.Infra.UseCase.Atendimento;


import App.RestAPI.Domain.AtendimentoRecord;
import App.RestAPI.Infra.Gateway.AtendimentoGateway;
import App.RestAPI.Infra.Persistence.Entity.AtendimentoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseAtendimentoGet {

    private final AtendimentoGateway atendimentoGateway;

    public UseCaseAtendimentoGet(AtendimentoGateway atendimentoGateway) {
        this.atendimentoGateway = atendimentoGateway;
    }

    public ResponseEntity<List<AtendimentoEntity>> ListarAtendimentos()
    { return atendimentoGateway.ListarAtendimentos();}
    public ResponseEntity<AtendimentoRecord> BuscarAtendimentoPorId(@RequestParam Long id)
    { return atendimentoGateway.BuscarAtendimentoPorId(id);}


}
