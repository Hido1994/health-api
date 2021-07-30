package at.hinterndorfer.health.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @JoinTable(name="quote_quote_tag", joinColumns=@JoinColumn(name="quote_id"),
            inverseJoinColumns=@JoinColumn(name="quote_tag_id"))
    List<QuoteTag> quoteTags;
}
