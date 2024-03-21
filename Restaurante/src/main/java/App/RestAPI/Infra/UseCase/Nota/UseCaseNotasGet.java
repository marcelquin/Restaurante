package App.RestAPI.Infra.UseCase.Nota;

import App.RestAPI.Domain.NotaRecord;
import App.RestAPI.Infra.Gateway.NotaGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseNotasGet {

    private final NotaGateway notaGateway;

    public UseCaseNotasGet(NotaGateway notaGateway) {
        this.notaGateway = notaGateway;
    }

    public ResponseEntity<List<NotaGateway>> ListarNotas()
    { return notaGateway.ListarNotas();}

    public ResponseEntity<NotaRecord> BuscaNotaPorId(@RequestParam Long id)
    { return notaGateway.BuscaNotaPorId(id);}
}
