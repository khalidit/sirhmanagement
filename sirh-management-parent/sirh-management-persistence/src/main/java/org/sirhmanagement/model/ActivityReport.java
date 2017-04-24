package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the activity_report database table.
 * 
 */
@Entity
@Table(name="activity_report")
@NamedQuery(name="ActivityReport.findAll", query="SELECT a FROM ActivityReport a")
public class ActivityReport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int activityReportId;

	@Temporal(TemporalType.DATE)
	private Date date;

	private Boolean isFullDay;

	//bi-directional many-to-one association to CollaboratorProject
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collaboratorProjectId")
	private CollaboratorProject collaboratorProject;

	public int getActivityReportId() {
		return this.activityReportId;
	}

	public void setActivityReportId(int activityReportId) {
		this.activityReportId = activityReportId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getIsFullDay() {
		return this.isFullDay;
	}

	public void setIsFullDay(Boolean isFullDay) {
		this.isFullDay = isFullDay;
	}

	public CollaboratorProject getCollaboratorProject() {
		return this.collaboratorProject;
	}

	public void setCollaboratorProject(CollaboratorProject collaboratorProject) {
		this.collaboratorProject = collaboratorProject;
	}

}