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
    public ResponseEntity<Atendimento> AdicionarItemCardapio(@RequestParam Long idAtendimento, @RequestParam Long[] idItemCardapio);
    public ResponseEntity<Atendimento> DeletarAtendimento(@RequestParam Long id);
}
