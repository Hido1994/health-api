package at.hinterndorfer.health.repository;

import at.hinterndorfer.health.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuoteRepository extends PagingAndSortingRepository<Quote, Long> {

    Page<Quote> findByTags_Id(Long id, Pageable pageable);
}
