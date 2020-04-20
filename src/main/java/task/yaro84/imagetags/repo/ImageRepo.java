package task.yaro84.imagetags.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import task.yaro84.imagetags.domain.ImageEnt;

import java.util.List;

public interface ImageRepo extends JpaRepository<ImageEnt, Long> {
    List<ImageEnt> findAll();
}
