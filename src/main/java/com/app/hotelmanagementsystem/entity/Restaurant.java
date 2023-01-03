package com.app.hotelmanagementsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.ToString;

import java.util.Set;

@Entity
@Table(name="restaurant")
public class Restaurant {

	@Id
	@SequenceGenerator(
            name = "restaurant_sequence",
            sequenceName = "restaurant_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "restaurant_sequence"
    )
	private Long restaurnatId;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "distance",
            nullable = false
    )
    private Integer distance;
    @Column(
            name = "description"
    )
    private String description;
    @Column(
    		name="photo"
    		
    		)
    private String photo;
    @Column(
    		name="star",
    		nullable = false
    		)
    private String star;
    @Column(
    		name="amenities"
    		
    		)
    private String amenities;
    
    public String getDesc() {
		return description;
	}
	public void setDesc(String desc) {
		this.description = desc;
	}
	public String getAmenities() {
		return amenities;
	}
	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    private Set<Tables> tables;
	public Long getRestaurnatId() {
		return restaurnatId;
	}
	public void setRestaurnatId(Long restaurnatId) {
		this.restaurnatId = restaurnatId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
	
	public Set<Tables> getTables() {
		return tables;
	}
	public void setTables(Set<Tables> tables) {
		this.tables = tables;
	}
}
