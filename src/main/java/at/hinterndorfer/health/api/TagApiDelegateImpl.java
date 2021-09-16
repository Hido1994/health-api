package at.hinterndorfer.health.api;

import at.hinterndorfer.health.entity.Tag;
import at.hinterndorfer.health.model.dto.TagDTO;
import at.hinterndorfer.health.repository.TagRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagApiDelegateImpl implements TagApiDelegate {
    private final TagRepository tagRepository;

    public TagApiDelegateImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public ResponseEntity<TagDTO> tag() {
        Optional<Tag> rootTag = tagRepository.findById(1l);

        TagDTO quoteTagDTOList = rootTag.get().toDtoWithChildTags();
        return ResponseEntity.ok(quoteTagDTOList);
    }
}
