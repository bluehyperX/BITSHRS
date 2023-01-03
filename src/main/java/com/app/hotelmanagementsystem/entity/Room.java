package com.app.hotelmanagementsystem.entity;


import javax.persistence.*;

@Entity
@Table(
        name = "room"
)
public class Room {

    public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

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
    
    @Column(
            name = "type"
    )
    private String type;

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne
    @JoinColumn(
            name = "hotel_id",
            referencedColumnName = "hotelId"
    )
    private Hotel hotel;
}
