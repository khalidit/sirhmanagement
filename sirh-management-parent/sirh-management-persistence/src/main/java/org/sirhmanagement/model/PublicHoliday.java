package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the public_holiday database table.
 * 
 */
@Entity
@Table(name="public_holiday")
@NamedQuery(name="PublicHoliday.findAll", query="SELECT p FROM PublicHoliday p")
public class PublicHoliday implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int publicHolidayId;

	@Temporal(TemporalType.DATE)
	private Date date;

	//bi-directional many-to-one association to PublicHolidayCategory
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publicHolidayCategoryId")
	private PublicHolidayCategory publicHolidayCategory;

	public int getPublicHolidayId() {
		return this.publicHolidayId;
	}

	public void setPublicHolidayId(int publicHolidayId) {
		this.publicHolidayId = publicHolidayId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PublicHolidayCategory getPublicHolidayCategory() {
		return this.publicHolidayCategory;
	}

	public void setPublicHolidayCategory(PublicHolidayCategory publicHolidayCategory) {
		this.publicHolidayCategory = publicHolidayCategory;
	}

}