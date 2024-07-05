package foro.hub.api.domain.foro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForoHubRepository extends JpaRepository<ForoHub, Long> {
    Page<ForoHub> findByActivoTrue(Pageable paginacion);
}
