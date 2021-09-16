package at.hinterndorfer.health.api;

import at.hinterndorfer.health.entity.Quote;
import at.hinterndorfer.health.model.dto.QuoteDTO;
import at.hinterndorfer.health.repository.QuoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteApiDelegateImpl implements QuoteApiDelegate {
    private static final int DEFAULT_PAGE_LIMIT = 10;

    private final QuoteRepository quoteRepository;
    private final ModelMapper modelMapper;

    public QuoteApiDelegateImpl(QuoteRepository quoteRepository, ModelMapper modelMapper) {
        this.quoteRepository = quoteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<QuoteDTO>> quote(Long tag, Integer page, Integer limit) {
        if (limit == null) {
            limit = QuoteApiDelegateImpl.DEFAULT_PAGE_LIMIT;
        }
        if (page == null) {
            long count = quoteRepository.count();
            double pages = Math.ceil((count * 1.0) / limit);
            page = (int) (Math.random() * pages);
        }

        Pageable pageable = PageRequest.of(page, limit);

        Page<Quote> quoteList = tag == null ? quoteRepository.findAll(pageable) : quoteRepository.findByTags_Id(tag, pageable);
        List<QuoteDTO> quoteDTOList = quoteList
                .stream()
                .map(entity -> modelMapper.map(entity, QuoteDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(quoteDTOList);
    }
}
