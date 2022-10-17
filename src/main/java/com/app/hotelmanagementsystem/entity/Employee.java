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
@Table
public class Employee {

    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long employeeId;
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
            name = "date_of_birth",
            nullable = false
    )
    private String dateOfBirth;
    @Column(
            name = "phone_number",
            nullable = false
    )
    private String phoneNumber;
    @Column(
            name = "email_address"
    )
    private String emailAddress;
    @Column(
            name = "id_number",
            nullable = false
    )
    private Long idNumber;
    @Column(
            name = "national_identification_number"
    )
    private Long nationalIdentificationNumber;
    @Column(
            name = "place_of_residence",
            nullable = false
    )
    private String placeOfResidence;
    @Column(
            name = "street_and_number_of_residence",
            nullable = false
    )
    private String streetAndNumberOfResidence;
    @Column(
            name = "job_position",
            nullable = false
    )
    private String jobPosition;
    @Column(
            name = "job_start_date",
            nullable = false
    )
    private String jobStartDate;
    @Column(
            name = "contract_expiration_date"
    )
    private String contractExpirationDate;
    @Column(
            name = "notes"
    )
    private String notes;
}
