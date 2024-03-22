package App.RestAPI.Domain;

import java.util.List;

public record NotaRecord(

        String fornecedorNome,
        String fornecedorRazaoSocial,
        Long fornecedorCnpj,
        List<String> produtos,
        String numeroNota,
        Double valorNota

) {
}
