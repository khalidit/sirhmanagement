package org.sirhmanagement.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the collaborator database table.
 * 
 */
@Entity
@NamedQuery(name=Collaborator.FIND_ALL, query="SELECT c FROM Collaborator c")
public class Collaborator implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "Collaborator.findAll";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long collaboratorId;

	@Temporal(TemporalType.DATE)
	private Date arivalDate;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	private String email;

	private String firstName;

	private String lastName;

	//bi-directional many-to-many association to ClientProject
	@ManyToMany(mappedBy="collaborators1")
	private List<ClientProject> clientProjects1;

	//bi-directional many-to-one association to Adress
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="adressId")
	private Adress adress;

	//bi-directional many-to-one association to Civility
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="civilityId")
	private Civility civility;

	//bi-directional many-to-one association to FamilySituation
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="famillySituationId")
	private FamilySituation familySituation;

	//bi-directional many-to-one association to CollaboratorProject
	@OneToMany(mappedBy="collaborator")
	private List<CollaboratorProject> collaboratorProjects;

	//bi-directional many-to-one association to HolidayBalance
	@OneToMany(mappedBy="collaborator")
	private List<HolidayBalance> holidayBalances;

	//bi-directional many-to-one association to HolidayRequest
	@OneToMany(mappedBy="collaborator")
	private List<HolidayRequest> holidayRequests;

	//bi-directional many-to-many association to ClientProject
	@ManyToMany(mappedBy="collaborators2")
	private List<ClientProject> clientProjects2;

	public Long getCollaboratorId() {
		return this.collaboratorId;
	}

	public void setCollaboratorId(Long collaboratorId) {
		this.collaboratorId = collaboratorId;
	}

	public Date getArivalDate() {
		return this.arivalDate;
	}

	public void setArivalDate(Date arivalDate) {
		this.arivalDate = arivalDate;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<ClientProject> getClientProjects1() {
		return this.clientProjects1;
	}

	public void setClientProjects1(List<ClientProject> clientProjects1) {
		this.clientProjects1 = clientProjects1;
	}

	public Adress getAdress() {
		return this.adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Civility getCivility() {
		return this.civility;
	}

	public void setCivility(Civility civility) {
		this.civility = civility;
	}

	public FamilySituation getFamilySituation() {
		return this.familySituation;
	}

	public void setFamilySituation(FamilySituation familySituation) {
		this.familySituation = familySituation;
	}

	public List<CollaboratorProject> getCollaboratorProjects() {
		return this.collaboratorProjects;
	}

	public void setCollaboratorProjects(List<CollaboratorProject> collaboratorProjects) {
		this.collaboratorProjects = collaboratorProjects;
	}

	public CollaboratorProject addCollaboratorProject(CollaboratorProject collaboratorProject) {
		getCollaboratorProjects().add(collaboratorProject);
		collaboratorProject.setCollaborator(this);

		return collaboratorProject;
	}

	public CollaboratorProject removeCollaboratorProject(CollaboratorProject collaboratorProject) {
		getCollaboratorProjects().remove(collaboratorProject);
		collaboratorProject.setCollaborator(null);

		return collaboratorProject;
	}

	public List<HolidayBalance> getHolidayBalances() {
		return this.holidayBalances;
	}

	public void setHolidayBalances(List<HolidayBalance> holidayBalances) {
		this.holidayBalances = holidayBalances;
	}

	public HolidayBalance addHolidayBalance(HolidayBalance holidayBalance) {
		getHolidayBalances().add(holidayBalance);
		holidayBalance.setCollaborator(this);

		return holidayBalance;
	}

	public HolidayBalance removeHolidayBalance(HolidayBalance holidayBalance) {
		getHolidayBalances().remove(holidayBalance);
		holidayBalance.setCollaborator(null);

		return holidayBalance;
	}

	public List<HolidayRequest> getHolidayRequests() {
		return this.holidayRequests;
	}

	public void setHolidayRequests(List<HolidayRequest> holidayRequests) {
		this.holidayRequests = holidayRequests;
	}

	public HolidayRequest addHolidayRequest(HolidayRequest holidayRequest) {
		getHolidayRequests().add(holidayRequest);
		holidayRequest.setCollaborator(this);

		return holidayRequest;
	}

	public HolidayRequest removeHolidayRequest(HolidayRequest holidayRequest) {
		getHolidayRequests().remove(holidayRequest);
		holidayRequest.setCollaborator(null);

		return holidayRequest;
	}

	public List<ClientProject> getClientProjects2() {
		return this.clientProjects2;
	}

	public void setClientProjects2(List<ClientProject> clientProjects2) {
		this.clientProjects2 = clientProjects2;
	}

}