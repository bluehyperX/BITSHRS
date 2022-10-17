package com.app.hotelmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(
        name = "hotel"
)
public class Hotel {

    @Id
    @SequenceGenerator(
            name = "hotel_sequence",
            sequenceName = "hotel_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hotel_sequence"
    )
    private Long hotelId;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "star",
            nullable = false
    )
    private Integer star;
    @Column(
            name = "location",
            nullable = false
    )
    private String location;
    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    @ToString.Exclude
    private Set<Room> rooms;
}
