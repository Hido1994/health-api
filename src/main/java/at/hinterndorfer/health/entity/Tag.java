package at.hinterndorfer.health.entity;

import at.hinterndorfer.health.api.TagApiDelegateImpl;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name", "parent_tag_id" }) })
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
}
