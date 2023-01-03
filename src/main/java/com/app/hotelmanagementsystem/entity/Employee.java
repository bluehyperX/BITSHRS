package com.app.hotelmanagementsystem.entity;


import javax.persistence.*;

@Entity
@Table
public class Employee {

    public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public Long getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(Long idNumber) {
		this.idNumber = idNumber;
	}
	public Long getNationalIdentificationNumber() {
		return nationalIdentificationNumber;
	}
	public void setNationalIdentificationNumber(Long nationalIdentificationNumber) {
		this.nationalIdentificationNumber = nationalIdentificationNumber;
	}
	public String getPlaceOfResidence() {
		return placeOfResidence;
	}
	public void setPlaceOfResidence(String placeOfResidence) {
		this.placeOfResidence = placeOfResidence;
	}
	public String getStreetAndNumberOfResidence() {
		return streetAndNumberOfResidence;
	}
	public void setStreetAndNumberOfResidence(String streetAndNumberOfResidence) {
		this.streetAndNumberOfResidence = streetAndNumberOfResidence;
	}
	public String getJobPosition() {
		return jobPosition;
	}
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}
	public String getJobStartDate() {
		return jobStartDate;
	}
	public void setJobStartDate(String jobStartDate) {
		this.jobStartDate = jobStartDate;
	}
	public String getContractExpirationDate() {
		return contractExpirationDate;
	}
	public void setContractExpirationDate(String contractExpirationDate) {
		this.contractExpirationDate = contractExpirationDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
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
