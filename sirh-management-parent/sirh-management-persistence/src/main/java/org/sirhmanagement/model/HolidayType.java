package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the holiday_type database table.
 * 
 */
@Entity
@Table(name="holiday_type")
@NamedQuery(name="HolidayType.findAll", query="SELECT h FROM HolidayType h")
public class HolidayType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int holidayTypeId;

	private int defaultQuantity;

	private String name;

	//bi-directional many-to-one association to HolidayBalance
	@OneToMany(mappedBy="holidayType")
	private List<HolidayBalance> holidayBalances;

	public int getHolidayTypeId() {
		return this.holidayTypeId;
	}

	public void setHolidayTypeId(int holidayTypeId) {
		this.holidayTypeId = holidayTypeId;
	}

	public int getDefaultQuantity() {
		return this.defaultQuantity;
	}

	public void setDefaultQuantity(int defaultQuantity) {
		this.defaultQuantity = defaultQuantity;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HolidayBalance> getHolidayBalances() {
		return this.holidayBalances;
	}

	public void setHolidayBalances(List<HolidayBalance> holidayBalances) {
		this.holidayBalances = holidayBalances;
	}

	public HolidayBalance addHolidayBalance(HolidayBalance holidayBalance) {
		getHolidayBalances().add(holidayBalance);
		holidayBalance.setHolidayType(this);

		return holidayBalance;
	}

	public HolidayBalance removeHolidayBalance(HolidayBalance holidayBalance) {
		getHolidayBalances().remove(holidayBalance);
		holidayBalance.setHolidayType(null);

		return holidayBalance;
	}

}