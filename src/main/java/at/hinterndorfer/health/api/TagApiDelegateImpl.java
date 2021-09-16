package at.hinterndorfer.health.api;

import at.hinterndorfer.health.entity.Tag;
import at.hinterndorfer.health.model.dto.TagDTO;
import at.hinterndorfer.health.repository.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagApiDelegateImpl implements TagApiDelegate {
    private final TagRepository tagRepository;
    private final ModelMapper modelMapper;

    public TagApiDelegateImpl(TagRepository tagRepository, ModelMapper modelMapper) {
        this.tagRepository = tagRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<TagDTO>> tag() {
        List<Tag> tagList = tagRepository.findByParentTagIsNull();

        List<TagDTO> quoteTagDTOList = tagList
                .stream()
                .map(entity -> modelMapper.map(entity, TagDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(quoteTagDTOList);
    }
}
