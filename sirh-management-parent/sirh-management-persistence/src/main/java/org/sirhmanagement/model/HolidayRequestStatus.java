package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the holiday_request_status database table.
 * 
 */
@Entity
@Table(name="holiday_request_status")
@NamedQuery(name="HolidayRequestStatus.findAll", query="SELECT h FROM HolidayRequestStatus h")
public class HolidayRequestStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int holidayRequestStatusId;

	private String statusName;

	//bi-directional many-to-one association to HolidayRequest
	@OneToMany(mappedBy="holidayRequestStatus")
	private List<HolidayRequest> holidayRequests;

	public int getHolidayRequestStatusId() {
		return this.holidayRequestStatusId;
	}

	public void setHolidayRequestStatusId(int holidayRequestStatusId) {
		this.holidayRequestStatusId = holidayRequestStatusId;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<HolidayRequest> getHolidayRequests() {
		return this.holidayRequests;
	}

	public void setHolidayRequests(List<HolidayRequest> holidayRequests) {
		this.holidayRequests = holidayRequests;
	}

	public HolidayRequest addHolidayRequest(HolidayRequest holidayRequest) {
		getHolidayRequests().add(holidayRequest);
		holidayRequest.setHolidayRequestStatus(this);

		return holidayRequest;
	}

	public HolidayRequest removeHolidayRequest(HolidayRequest holidayRequest) {
		getHolidayRequests().remove(holidayRequest);
		holidayRequest.setHolidayRequestStatus(null);

		return holidayRequest;
	}

}