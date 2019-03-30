package demo.bank.core.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * Account Type entity
 *
 */
@DataObject(generateConverter = true)
@ApiModel(description = "Represents an account type of the system")
@JsonInclude(Include.NON_NULL)
public class AccountType {

    @ApiModelProperty(value = "The ID of the account type", required = true)
    private Integer id;

    @ApiModelProperty(value = "The description of the account type", required = true)
    private String description;

    public AccountType() {
    }
    
    public AccountType(JsonObject jsonObject) {
    	AccountTypeConverter.fromJson(jsonObject, this);
	}

    public AccountType(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JsonObject toJson() {
		JsonObject json = new JsonObject();
		AccountTypeConverter.toJson(this, json);
	    return json;
	}
}