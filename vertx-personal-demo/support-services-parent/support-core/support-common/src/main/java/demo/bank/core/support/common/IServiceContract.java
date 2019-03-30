package demo.bank.core.support.common;

public interface IServiceContract {

	public enum PROPERTY {
		ID("id"),
		NAME("name"),
		PROPERTYSOURCE("propertySource");
		
		private final String text;
		
		PROPERTY(final String text) {
	        this.text = text;
	    }
		
		public String toString() {
	        return text;
	    }
		
		public static PROPERTY fromString(String text) {
	        for (PROPERTY property : PROPERTY.values()) {
	            if (property.text.equalsIgnoreCase(text)) {
	                return property;
	            }
	        }
	        return null;
	    }
	}
}
