package App.RestAPI.Domain;

import java.time.LocalDate;

public record FornecedorRecord(

        String nome,
        String razaoSocial,
        Long cnpj,
        String areaAtuacao,
        LocalDate dataInicioContrato,
        String email,
        Long telefone

) {
}
