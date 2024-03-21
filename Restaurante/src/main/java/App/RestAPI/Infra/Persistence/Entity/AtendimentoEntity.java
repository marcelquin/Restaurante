package App.RestAPI.Infra.Persistence.Entity;

import App.RestAPI.Infra.Persistence.Enum.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "atendimento")
public class AtendimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long mesa;

    @OneToMany
    private List<ItemCardapioEntity> pratos;

    private Double valor;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

}
