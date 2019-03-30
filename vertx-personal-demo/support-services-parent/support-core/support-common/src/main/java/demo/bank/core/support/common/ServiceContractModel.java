package demo.bank.core.support.common;

import java.util.Map;

public class ServiceContractModel {

	private String id;
	private String name;
	private Map<String, String> propertyMap;
	
	public ServiceContractModel() {
	}

	public ServiceContractModel(String id, String name, Map<String, String> propertyMap) {
		super();
		this.id = id;
		this.name = name;
		this.propertyMap = propertyMap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(Map<String, String> propertyMap) {
		this.propertyMap = propertyMap;
	}

	@Override
	public String toString() {
		return "ServiceContractModel [id=" + id + ", name=" + name + ", propertyMap=" + propertyMap + "]";
	}
	
}
