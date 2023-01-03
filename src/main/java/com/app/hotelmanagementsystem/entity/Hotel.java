package com.app.hotelmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "hotel"
)
public class Hotel {

    public Long getHotelId() {
		return hotelId;
	}
	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	
	public Long getDistance() {
		return distance;
	}
	public void setDistance(Long distance) {
		this.distance = distance;
	}
	public Set<Room> getRooms() {
		return rooms;
	}
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
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
            name = "photo"
           
    )
    private String photo;
    @Column(
            name = "description"
           
    )
    private String description;
    @Column(
            name = "amenities"
          
    )
    private String amenities;
    public String getAmenities() {
		return amenities;
	}
	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}
	public String getDesc() {
		return description;
	}
	public void setDesc(String desc) {
		this.description = desc;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Column(
            name = "distance",
            nullable = false
    )
    private Long distance;
    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    @ToString.Exclude
    private Set<Room> rooms;
}
