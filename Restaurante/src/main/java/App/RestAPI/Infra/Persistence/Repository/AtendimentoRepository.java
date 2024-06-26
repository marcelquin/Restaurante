package App.RestAPI.Infra.Persistence.Repository;

import App.RestAPI.Infra.Persistence.Entity.AtendimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepository extends JpaRepository<AtendimentoEntity,Long> {
}
