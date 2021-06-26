package at.hinterndorfer.office.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
public class Exercise {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;
}
