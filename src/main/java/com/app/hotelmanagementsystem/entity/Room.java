package com.app.hotelmanagementsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(
        name = "room"
)
public class Room {

    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    private Long roomId;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "price"
    )
    private Float price;

    @ManyToOne
    @JoinColumn(
            name = "hotel_id",
            referencedColumnName = "hotelId"
    )
    private Hotel hotel;
}
