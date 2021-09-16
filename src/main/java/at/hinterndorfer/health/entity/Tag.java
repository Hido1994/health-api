package at.hinterndorfer.health.entity;

import at.hinterndorfer.health.model.dto.TagDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "parent_tag_id"})})
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Tag parentTag;

    @OneToMany(mappedBy = "parentTag")
    List<Tag> childTags;

    @ManyToMany(mappedBy = "tags")
    List<Quote> quotes;

    public TagDTO toDtoWithChildTags() {
        return new TagDTO()
                .id(id)
                .name(name)
                .childTags(childTags
                        .stream()
                        .map(Tag::toDtoWithChildTags)
                        .collect(Collectors.toList())
                );
    }

    public TagDTO toDtoWithParentTags() {
        return new TagDTO()
                .id(id)
                .name(name)
                .parentTag(parentTag != null ? parentTag.toDtoWithParentTags() : null);
    }
}
