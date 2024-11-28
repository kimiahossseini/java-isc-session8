package writejsonprettyreadjdbOrm;



public class Employeee {
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String salary;	
	private Departmentt departmentt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Departmentt getDepartmentt() {
		return departmentt;
	}
	public void setDepartmentt(Departmentt departmentt) {
		this.departmentt = departmentt;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", salary=" + salary + ", department=" + departmentt + "]";
	}
	

	
	
	
}
