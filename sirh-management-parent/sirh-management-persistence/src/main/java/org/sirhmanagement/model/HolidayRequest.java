package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the holiday_request database table.
 * 
 */
@Entity
@Table(name="holiday_request")
@NamedQuery(name="HolidayRequest.findAll", query="SELECT h FROM HolidayRequest h")
public class HolidayRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int holidayRequestId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-one association to Collaborator
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collaboratorId")
	private Collaborator collaborator;

	//bi-directional many-to-one association to HolidayBalance
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="holidayBalanceId")
	private HolidayBalance holidayBalance;

	//bi-directional many-to-one association to HolidayPeriod
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="endPeriodId")
	private HolidayPeriod holidayPeriod1;

	//bi-directional many-to-one association to HolidayPeriod
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="startPeriodId")
	private HolidayPeriod holidayPeriod2;

	//bi-directional many-to-one association to HolidayRequestStatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="holidayRequestStatusId")
	private HolidayRequestStatus holidayRequestStatus;


	public int getHolidayRequestId() {
		return this.holidayRequestId;
	}

	public void setHolidayRequestId(int holidayRequestId) {
		this.holidayRequestId = holidayRequestId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Collaborator getCollaborator() {
		return this.collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}

	public HolidayBalance getHolidayBalance() {
		return this.holidayBalance;
	}

	public void setHolidayBalance(HolidayBalance holidayBalance) {
		this.holidayBalance = holidayBalance;
	}

	public HolidayPeriod getHolidayPeriod1() {
		return this.holidayPeriod1;
	}

	public void setHolidayPeriod1(HolidayPeriod holidayPeriod1) {
		this.holidayPeriod1 = holidayPeriod1;
	}

	public HolidayPeriod getHolidayPeriod2() {
		return this.holidayPeriod2;
	}

	public void setHolidayPeriod2(HolidayPeriod holidayPeriod2) {
		this.holidayPeriod2 = holidayPeriod2;
	}

	public HolidayRequestStatus getHolidayRequestStatus() {
		return this.holidayRequestStatus;
	}

	public void setHolidayRequestStatus(HolidayRequestStatus holidayRequestStatus) {
		this.holidayRequestStatus = holidayRequestStatus;
	}

}