package App.RestAPI.Infra.Persistence.Repository;

import App.RestAPI.Infra.Persistence.Util.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity,Long> {
}
