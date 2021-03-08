package cl.lfuentes.a1_lambda.model;

public class ContactoRequest {
	
	private String id;
	private String firstName;
	private String lastName;
	private String status;
	
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public ContactoRequest(String id, String firstName, String lastName, String status) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
	}
	
	public ContactoRequest() {
	}

}
