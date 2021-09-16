package at.hinterndorfer.health.entity;

import at.hinterndorfer.health.model.dto.QuoteDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Data
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 1024)
    private String text;

    @Column
    private String author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "quote_tag", joinColumns = @JoinColumn(name = "quote_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    public QuoteDTO toDto() {
        return new QuoteDTO()
                .id(id)
                .text(text)
                .author(author)
                .tags(tags
                        .stream()
                        .map(Tag::toDtoWithParentTags)
                        .collect(Collectors.toList())
                );
    }
}
