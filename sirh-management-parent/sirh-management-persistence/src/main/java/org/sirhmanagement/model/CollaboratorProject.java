package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the collaborator_project database table.
 * 
 */
@Entity
@Table(name="collaborator_project")
@NamedQuery(name="CollaboratorProject.findAll", query="SELECT c FROM CollaboratorProject c")
public class CollaboratorProject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int collaboratorProjectId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	//bi-directional many-to-one association to ActivityReport
	@OneToMany(mappedBy="collaboratorProject")
	private List<ActivityReport> activityReports;

	//bi-directional many-to-one association to ClientProject
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="clientProjectId")
	private ClientProject clientProject;

	//bi-directional many-to-one association to Collaborator
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collaboratorId")
	private Collaborator collaborator;

	public int getCollaboratorProjectId() {
		return this.collaboratorProjectId;
	}

	public void setCollaboratorProjectId(int collaboratorProjectId) {
		this.collaboratorProjectId = collaboratorProjectId;
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

	public List<ActivityReport> getActivityReports() {
		return this.activityReports;
	}

	public void setActivityReports(List<ActivityReport> activityReports) {
		this.activityReports = activityReports;
	}

	public ActivityReport addActivityReport(ActivityReport activityReport) {
		getActivityReports().add(activityReport);
		activityReport.setCollaboratorProject(this);

		return activityReport;
	}

	public ActivityReport removeActivityReport(ActivityReport activityReport) {
		getActivityReports().remove(activityReport);
		activityReport.setCollaboratorProject(null);

		return activityReport;
	}

	public ClientProject getClientProject() {
		return this.clientProject;
	}

	public void setClientProject(ClientProject clientProject) {
		this.clientProject = clientProject;
	}

	public Collaborator getCollaborator() {
		return this.collaborator;
	}

	public void setCollaborator(Collaborator collaborator) {
		this.collaborator = collaborator;
	}

}