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
        name = "guest"
)
public class Guest {

    @Id
    @SequenceGenerator(
            name = "guest_sequence",
            sequenceName = "guest_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "guest_sequence"
    )
    private Long guestId;
    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "email_address"
    )
    private String emailAddress;
    @Column(
            name = "phone_number",
            nullable = false
    )
    private String phoneNumber;
    @Column(
            name = "id_number",
            nullable = false
    )
    private Long idNumber;
    @Column(
            name = "check_in_date",
            nullable = false
    )
    private String checkInDate;
    @Column(
            name = "check_in_time",
            nullable = false
    )
    private String checkInTime;
    @Column(
            name = "check_out_date"
    )
    private String checkOutDate;
    @Column(
            name = "check_out_time"
    )
    private String checkOutTime;
}
