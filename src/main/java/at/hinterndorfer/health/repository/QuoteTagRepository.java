package at.hinterndorfer.health.repository;

import at.hinterndorfer.health.entity.QuoteTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteTagRepository extends JpaRepository<QuoteTag, Long> {
}
