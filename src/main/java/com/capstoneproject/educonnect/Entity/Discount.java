package com.capstoneproject.educonnect.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.capstoneproject.educonnect.DTO.DiscountDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "discount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discountid")
	private int discountid;

	@Column(name = "staffid")
	private int staffid;

	@Column(name = "img", length = 200)
	private String img;

	@Column(name = "startdate", columnDefinition = "DATE")
	private String startDate;

	@Column(name = "enddate", columnDefinition = "DATE")
	private String endDate;

	@Column(name = "discount")
	private float discount;

	@Column(name = "title", columnDefinition = "NVARCHAR(200)")
	private String title;

	@Column(name = "desciption", columnDefinition = "TEXT")
	private String desciption;

	@OneToMany(targetEntity = DiscountCourse.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "discountid", referencedColumnName = "discountid")
	List<DiscountCourse> discountCourse;

	public Discount setEntity(Discount discount, DiscountDTO discountDTO) {
		this.setDiscountid(discountDTO.getDiscountid() != 0 ? discountDTO.getDiscountid() : discount.getDiscountid());
		this.setStaffid(discountDTO.getStaffid() != 0 ? discountDTO.getStaffid() : discount.getStaffid());
		this.setImg(discountDTO.getImg() != null ? discountDTO.getImg() : discount.getImg());
		this.setStartDate(discountDTO.getStartDate() != null ? discountDTO.getStartDate() : discount.getStartDate());
		this.setEndDate(discountDTO.getEndDate() != null ? discountDTO.getEndDate() : discount.getEndDate());
		this.setDiscount(discountDTO.getDiscount() != 0 ? discountDTO.getDiscount() : discount.getDiscount());
		this.setTitle(discountDTO.getTitle() != null ? discountDTO.getTitle() : discount.getTitle());
		this.setDesciption(
				discountDTO.getDesciption() != null ? discountDTO.getDesciption() : discount.getDesciption());
		return this;
	}

	public Discount setEntity(DiscountDTO discountDTO) {
		this.setStaffid(discountDTO.getStaffid());
		this.setImg(discountDTO.getImg());
		this.setStartDate(discountDTO.getStartDate());
		this.setEndDate(discountDTO.getEndDate());
		this.setDiscount(discountDTO.getDiscount());
		this.setTitle(discountDTO.getTitle());
		this.setDesciption(discountDTO.getDesciption());
		return this;
	}
}
