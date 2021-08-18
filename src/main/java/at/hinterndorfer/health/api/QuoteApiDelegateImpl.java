package at.hinterndorfer.health.api;

import at.hinterndorfer.health.entity.Quote;
import at.hinterndorfer.health.entity.QuoteTag;
import at.hinterndorfer.health.model.dto.QuoteDTO;
import at.hinterndorfer.health.model.dto.QuoteTagDTO;
import at.hinterndorfer.health.repository.QuoteRepository;
import at.hinterndorfer.health.repository.QuoteTagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteApiDelegateImpl implements QuoteApiDelegate {
    private static final int DEFAULT_PAGE_LIMIT = 10;

    private final QuoteRepository quoteRepository;
    private final QuoteTagRepository quoteTagRepository;
    private final ModelMapper modelMapper;

    public QuoteApiDelegateImpl(QuoteRepository quoteRepository, QuoteTagRepository quoteTagRepository, ModelMapper modelMapper) {
        this.quoteRepository = quoteRepository;
        this.quoteTagRepository = quoteTagRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<QuoteDTO>> quote(Long tag, Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page == null ? 0 : page,
                limit == null ? QuoteApiDelegateImpl.DEFAULT_PAGE_LIMIT : limit, Sort.by("id").descending());

        Page<Quote> quoteList = tag == null ? quoteRepository.findAll(pageable) : quoteRepository.findByQuoteTags_Id(tag, pageable);
        List<QuoteDTO> quoteDTOList = quoteList
                .stream()
                .map(entity -> modelMapper.map(entity, QuoteDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(quoteDTOList);
    }

    @Override
    public ResponseEntity<List<QuoteTagDTO>> quoteTag() {
        List<QuoteTag> quoteTagList = quoteTagRepository.findAll();
        List<QuoteTagDTO> quoteTagDTOList = quoteTagList
                .stream()
                .map(entity -> modelMapper.map(entity, QuoteTagDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(quoteTagDTOList);
    }
}
