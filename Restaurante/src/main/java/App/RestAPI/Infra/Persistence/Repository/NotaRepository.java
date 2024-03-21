package App.RestAPI.Infra.Persistence.Repository;

import App.RestAPI.Infra.Persistence.Entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<NotaEntity,Long> {
}
