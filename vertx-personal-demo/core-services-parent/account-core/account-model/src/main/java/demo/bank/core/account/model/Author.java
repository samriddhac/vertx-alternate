package demo.bank.core.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Represents an Author of the system")
@JsonInclude(Include.NON_NULL)
public class Author {

	@ApiModelProperty(value = "The ID of the Author")
	private Integer ID;

	@ApiModelProperty(value = "The ID of the books")
	private Integer idBook;


	@ApiModelProperty(value = "FirstName")
	private String firstName;

	@ApiModelProperty("LastName")
	private String lastName;

	public Author() {
	}

	public Author(Integer iD, Integer idBook, String firstName, String lastName) {
		super();
		ID = iD;
		this.idBook = idBook;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getIdBook() {
		return idBook;
	}

	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
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


}
