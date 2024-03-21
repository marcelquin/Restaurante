package App.RestAPI.Infra.Persistence.Repository;

import App.RestAPI.Infra.Persistence.Entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Long> {
}
