package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the holiday_period database table.
 * 
 */
@Entity
@Table(name="holiday_period")
@NamedQuery(name="HolidayPeriod.findAll", query="SELECT h FROM HolidayPeriod h")
public class HolidayPeriod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int holidayPeriodId;

	private String periodName;

	//bi-directional many-to-one association to HolidayRequest
	@OneToMany(mappedBy="holidayPeriod1")
	private List<HolidayRequest> holidayRequests1;

	//bi-directional many-to-one association to HolidayRequest
	@OneToMany(mappedBy="holidayPeriod2")
	private List<HolidayRequest> holidayRequests2;

	public int getHolidayPeriodId() {
		return this.holidayPeriodId;
	}

	public void setHolidayPeriodId(int holidayPeriodId) {
		this.holidayPeriodId = holidayPeriodId;
	}

	public String getPeriodName() {
		return this.periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public List<HolidayRequest> getHolidayRequests1() {
		return this.holidayRequests1;
	}

	public void setHolidayRequests1(List<HolidayRequest> holidayRequests1) {
		this.holidayRequests1 = holidayRequests1;
	}

	public HolidayRequest addHolidayRequests1(HolidayRequest holidayRequests1) {
		getHolidayRequests1().add(holidayRequests1);
		holidayRequests1.setHolidayPeriod1(this);

		return holidayRequests1;
	}

	public HolidayRequest removeHolidayRequests1(HolidayRequest holidayRequests1) {
		getHolidayRequests1().remove(holidayRequests1);
		holidayRequests1.setHolidayPeriod1(null);

		return holidayRequests1;
	}

	public List<HolidayRequest> getHolidayRequests2() {
		return this.holidayRequests2;
	}

	public void setHolidayRequests2(List<HolidayRequest> holidayRequests2) {
		this.holidayRequests2 = holidayRequests2;
	}

	public HolidayRequest addHolidayRequests2(HolidayRequest holidayRequests2) {
		getHolidayRequests2().add(holidayRequests2);
		holidayRequests2.setHolidayPeriod2(this);

		return holidayRequests2;
	}

	public HolidayRequest removeHolidayRequests2(HolidayRequest holidayRequests2) {
		getHolidayRequests2().remove(holidayRequests2);
		holidayRequests2.setHolidayPeriod2(null);

		return holidayRequests2;
	}

}