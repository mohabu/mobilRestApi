package com.companyname.eshop.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="mobiles")
public  class Mobil {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@NotNull
	@NotBlank(message = "title must be filled")
	@Size(min = 3, max = 25)
	@Column(name = "type")
	private String title;
	
	@PositiveOrZero
	@Column(name = "price")
	private int price;
	
	@NotNull
	@NotBlank(message = "description must be filled")
	@Column(name = "description")
	private String description;
	
	@NotNull
	@NotBlank(message = "image must be filled")
	@Pattern(regexp = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{4})$", message = "Only Base64 data are accepted.") 
	@Lob
	@Column(name = "image")
	private String image;
	
	@CreationTimestamp
	private Date createdDate;
	
    
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFountain() {
		return image;
	}
	public void setFountain(String fountain) {
		this.image = fountain;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	} 
}
