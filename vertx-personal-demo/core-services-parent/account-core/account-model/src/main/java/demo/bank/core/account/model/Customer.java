package demo.bank.core.account.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

/**
 * Customer entity
 *
 */
@DataObject(generateConverter = true)
@ApiModel(description = "Represents a customer of the system")
@JsonInclude(Include.NON_NULL)
public class Customer {

    @ApiModelProperty(value = "The ID of the customer", required = true)
    @NotNull(message = "customer ID cannot be null")
    private Integer id;
    @ApiModelProperty(value = "The name of the customer", required = true)
    private String name;

    public Customer() {
    }
    
    public Customer(JsonObject jsonObject) {
		CustomerConverter.fromJson(jsonObject, this);
	}

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public JsonObject toJson() {
		JsonObject json = new JsonObject();
		CustomerConverter.toJson(this, json);
	    return json;
	}
}