package App.RestAPI.Infra.Persistence.Repository;

import App.RestAPI.Infra.Persistence.Entity.IngredienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<IngredienteEntity,Long> {
}
