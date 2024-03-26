package App.RestAPI.Domain;

import java.util.List;

public record NotaRecord(

        String fornecedorNome,
        String fornecedorRazaoSocial,
        Long fornecedorCnpj,
        String numeroNota,
        String valorNota

) {
}
