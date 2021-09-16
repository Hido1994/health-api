package at.hinterndorfer.health.repository;

import at.hinterndorfer.health.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByParentTagIsNull();
}
