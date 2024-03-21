package App.RestAPI.Infra.Persistence.Repository;

import App.RestAPI.Infra.Persistence.Util.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity,Long> {
}
