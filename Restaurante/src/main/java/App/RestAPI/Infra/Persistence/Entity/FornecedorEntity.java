package App.RestAPI.Infra.Persistence.Entity;

import App.RestAPI.Infra.Persistence.Util.ContatoEntity;
import App.RestAPI.Infra.Persistence.Util.EnderecoEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Fornecedor")
public class FornecedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String razao_Social;

    private Long cnpj;

    private String areaAtuacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id",referencedColumnName = "id")
    private EnderecoEntity endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Contato_id",referencedColumnName = "id")
    private ContatoEntity contato;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDate dataInicioContrato;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

}
