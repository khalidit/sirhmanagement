package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the holiday_balance database table.
 * 
 */
@Entity
@Table(name="holiday_balance")
@NamedQuery(name="HolidayBalance.findAll", query="SELECT h FROM HolidayBalance h")
public class HolidayBalance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int holidayBalanceId;

	private short balanceQuantity;

	//bi-directional many-to-one association to Collaborator
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collaboratorId")
	private Collaborator collaborator;

	//bi-directional many-to-one association to HolidayType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="holidayTypeId")
	private HolidayType holidayType;

	//bi-directional many-to-one association to HolidayRequest
	@OneToMany(mappedBy="holidayBalance")
	private List<HolidayRequest> holidayRequests;

	public int getHolidayBalanceId() {
		return this.holidayBalanceId;
	}

	public void setHolidayBalanceId(int holidayBalanceId) {
		this.holidayBalanceId = holidayBalanceId;
	}

	public short getBalanceQuantity() {
		return this.balanceQuantity;
	}

	public void setBalanceQuantity(short balanceQuantity) {
		this.balanceQuantity = balanceQuantity;
	}

	public Collaborator getCollaborator() {
		return this.collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}

	public HolidayType getHolidayType() {
		return this.holidayType;
	}

	public void setHolidayType(HolidayType holidayType) {
		this.holidayType = holidayType;
	}

	public List<HolidayRequest> getHolidayRequests() {
		return this.holidayRequests;
	}

	public void setHolidayRequests(List<HolidayRequest> holidayRequests) {
		this.holidayRequests = holidayRequests;
	}

	public HolidayRequest addHolidayRequest(HolidayRequest holidayRequest) {
		getHolidayRequests().add(holidayRequest);
		holidayRequest.setHolidayBalance(this);

		return holidayRequest;
	}

	public HolidayRequest removeHolidayRequest(HolidayRequest holidayRequest) {
		getHolidayRequests().remove(holidayRequest);
		holidayRequest.setHolidayBalance(null);

		return holidayRequest;
	}

}