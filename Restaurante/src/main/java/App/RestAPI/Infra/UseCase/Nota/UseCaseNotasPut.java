package App.RestAPI.Infra.UseCase.Nota;

import App.RestAPI.Domain.NotaRecord;
import App.RestAPI.Infra.Gateway.NotaGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseNotasPut {

    private final NotaGateway notaGateway;

    public UseCaseNotasPut(NotaGateway notaGateway) {
        this.notaGateway = notaGateway;
    }

    public ResponseEntity<NotaRecord> AlterarNota(@RequestParam Long idNota, @RequestParam String numeroNota)
    { return notaGateway.AlterarNota(idNota, numeroNota);}

}
