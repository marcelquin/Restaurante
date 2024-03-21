package App.RestAPI.Infra.Persistence.Repository;

import App.RestAPI.Infra.Persistence.Entity.PratoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PratoRepository extends JpaRepository<PratoEntity,Long> {
}
