import java.util.LinkedList;

public class Dentist {
	String profileUrl;
	String name;
	String phone;
	String website;
	String address;
	String payment_options;
	String insurance;
	String gender;
	String specialties;
	String language;
	String education;
	String practice_description;
	
	public Dentist(){
		super();
	}

	public Dentist(String profileUrl, String name, String phone, String website, String address, String payment_options,
			String insurance, String gender, String specialties, String language, String education,
			String practice_description) {
		super();
		this.profileUrl = profileUrl;
		this.name = name;
		this.phone = phone;
		this.website = website;
		this.address = address;
		this.payment_options = payment_options;
		this.insurance = insurance;
		this.gender = gender;
		this.specialties = specialties;
		this.language = language;
		this.education = education;
		this.practice_description = practice_description;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPayment_options() {
		return payment_options;
	}

	public void setPayment_options(String payment_options) {
		this.payment_options = payment_options;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSpecialties() {
		return specialties;
	}

	public void setSpecialties(String specialties) {
		this.specialties = specialties;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPractice_description() {
		return practice_description;
	}

	public void setPractice_description(String practice_description) {
		this.practice_description = practice_description;
	}

	
}
