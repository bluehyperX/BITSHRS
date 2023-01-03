package com.app.hotelmanagementsystem.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(
		name = "tables"
)

public class Tables {
	public Long getTableId() {
		return TableId;
	}

	public void setTableId(Long tableId) {
		TableId = tableId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Id
    @SequenceGenerator(
            name = "table_sequence",
            sequenceName = "table_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "table_sequence"
    )
    private Long TableId;
	 @Column(
	            name = "type",
	            nullable = false
	    )
	    private String type;
	    @Column(
	            name = "price"
	    )
	    private Float price;

	    @ManyToOne
	    @JoinColumn(
	            name = "restaurant_id",
	            referencedColumnName = "restaurnatId"
	    )
	  //  private Hotel hotel
private Restaurant restaurant;


}
