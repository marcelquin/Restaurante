package App.RestAPI.Infra.Persistence.Entity;

import App.RestAPI.Infra.Persistence.Enum.UnidadeMedida;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "produto")
public class ProdutoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nota_id", referencedColumnName = "id")
    private NotaEntity notaFiscal;

    private String nome;

    private String descrisao;

    private Double valor;

    private Double quantidade;

    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;
}
