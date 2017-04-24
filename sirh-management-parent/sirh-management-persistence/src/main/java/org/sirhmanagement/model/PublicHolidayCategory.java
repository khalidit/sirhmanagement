package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the public_holiday_category database table.
 * 
 */
@Entity
@Table(name="public_holiday_category")
@NamedQuery(name="PublicHolidayCategory.findAll", query="SELECT p FROM PublicHolidayCategory p")
public class PublicHolidayCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int publicHolidayCategoryId;

	private String category;

	//bi-directional many-to-one association to PublicHoliday
	@OneToMany(mappedBy="publicHolidayCategory")
	private List<PublicHoliday> publicHolidays;

	public int getPublicHolidayCategoryId() {
		return this.publicHolidayCategoryId;
	}

	public void setPublicHolidayCategoryId(int publicHolidayCategoryId) {
		this.publicHolidayCategoryId = publicHolidayCategoryId;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<PublicHoliday> getPublicHolidays() {
		return this.publicHolidays;
	}

	public void setPublicHolidays(List<PublicHoliday> publicHolidays) {
		this.publicHolidays = publicHolidays;
	}

	public PublicHoliday addPublicHoliday(PublicHoliday publicHoliday) {
		getPublicHolidays().add(publicHoliday);
		publicHoliday.setPublicHolidayCategory(this);

		return publicHoliday;
	}

	public PublicHoliday removePublicHoliday(PublicHoliday publicHoliday) {
		getPublicHolidays().remove(publicHoliday);
		publicHoliday.setPublicHolidayCategory(null);

		return publicHoliday;
	}

}