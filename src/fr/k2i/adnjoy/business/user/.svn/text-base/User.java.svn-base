package fr.k2i.adnjoy.business.user;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.k2i.adnjoy.business.BusinessObject;
import fr.k2i.adnjoy.business.Country;
import fr.k2i.adnjoy.business.ad.NotationAd;
import fr.k2i.adnjoy.business.jackpot.won.WonJackPot;
import fr.k2i.adnjoy.business.score.Score;


@Entity
@Table (name="TBL_USER")
public class User extends BusinessObject {

	private static final long serialVersionUID = -6628282541651206498L;

	@Id @GeneratedValue (strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	private String email;
	private String password;
	private String pseudo;
	private String address1;
	private String address2;
	private String zipCode;
	private String city;
	private String phone;
	private String mobile;
	private String firstName;
	private String lastName;
	private Boolean validated;
	private String validateUrl;
	private Sex sex;
	private Right right = Right.User;
	private Job job;
	private Date createdDate;
	
	private Date birthday;
	
	private Country country;

	private List<WonJackPot> wins;
	
	private FriendsGroup friendsGroup;
//	private List<FriendsGroup> friendsgroups;
//	private List<FriendsGroup> created;
	
	private List<Score> scores;
	
	private List<Profile> profiles;
	private List<NotationAd> notes;
	
	private String avator;
	
	@Column(unique=true,nullable=false)
	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@Column(unique=true,nullable=false)
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	@Column(nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(nullable=false)
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@Column(nullable=false)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Column(nullable=false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(nullable=false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(nullable=false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	public List<WonJackPot> getWins() {
		return wins;
	}

	public void setWins(List<WonJackPot> wins) {
		this.wins = wins;
	}

//	@ManyToMany(
//			targetEntity=FriendsGroup.class,
//			cascade={CascadeType.PERSIST, CascadeType.MERGE}
//			)
//	@JoinTable(
//			name="TBL_USER_FRIENDSGROUP",
//			joinColumns=@JoinColumn(name="USER_ID"),
//			inverseJoinColumns=@JoinColumn(name="FRIENDSGROUP_ID")
//			)
//	public List<FriendsGroup> getFriendsgroups() {
//		return friendsgroups;
//	}
//
//	public void setFriendsgroups(List<FriendsGroup> friendsgroups) {
//		this.friendsgroups = friendsgroups;
//	}
//
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="USER_ID_GROUP_CREATED")
//	public List<FriendsGroup> getCreated() {
//		return created;
//	}
//
//	public void setCreated(List<FriendsGroup> created) {
//		this.created = created;
//	}

//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinColumn(name="USER_ID")
//	public List<TVScore> getScores() {
//		return scores;
//	}
//
//	public void setScores(List<TVScore> scores) {
//		this.scores = scores;
//	}
	
	@ManyToOne( cascade = {CascadeType.ALL} )
	@JoinColumn(name="FRIENDSGROUP_ID")
	public FriendsGroup getFriendsGroup() {
		return friendsGroup;
	}
	
	public void setFriendsGroup(FriendsGroup friendsGroup) {
		this.friendsGroup = friendsGroup;
	}
	
	@ManyToMany(
			targetEntity=Profile.class,
			cascade={CascadeType.PERSIST, CascadeType.MERGE}
			)
	@JoinTable(
			name="TBL_USER_PROFILE",
			joinColumns=@JoinColumn(name="USER_ID"),
			inverseJoinColumns=@JoinColumn(name="PROFILE_ID")
			)
	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	public List<NotationAd> getNotes() {
		return notes;
	}

	public void setNotes(List<NotationAd> notes) {
		this.notes = notes;
	}
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="COUNTRY_ID")
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}


	@OneToMany(cascade=CascadeType.ALL,mappedBy="user")
	public List<Score> getScores() {
		return scores;
	}

	/**
	 * @param scoresBt the scoresBt to set
	 */
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}


	public Boolean getValidated() {
		return validated;
	}

	public void setValidated(Boolean validated) {
		this.validated = validated;
	}

	@Column(unique=true,nullable=true)
	public String getValidateUrl() {
		return validateUrl;
	}

	public void setValidateUrl(String validateUrl) {
		this.validateUrl = validateUrl;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public User() {
		super();
	}

	@Column(name="RIGHT_ID")
	@Enumerated(EnumType.ORDINAL)
	public Right getRight() {
		return right;
	}

	public void setRight(Right right) {
		this.right = right;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@JoinColumn(name="JOB_ID")
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Temporal(TemporalType.DATE)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getAvator() {
		return avator;
	}

	public void setAvator(String avator) {
		this.avator = avator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (getEmail() == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!getEmail().equals(other.getEmail()))
			return false;
		if (getPseudo() == null) {
			if (other.getPseudo() != null)
				return false;
		} else if (!getPseudo().equals(other.getPseudo()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getPseudo()+" id:["+getId()+"]";
	}
	
	
}
