package demo.bank.core.account.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Represents Currency Conversion Result")
@JsonInclude(Include.NON_NULL)
public class CurrencyConversionAmount {

	@ApiModelProperty(value = "The amount of the converted currency", required = true)
    private BigDecimal amount;
	
	public CurrencyConversionAmount() {
		super();
	}
	
	public CurrencyConversionAmount(BigDecimal amount) {
		super();
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
