package test.user.dao.util;

public enum UnitActions {

	INSERT("INSERT"), 
	UPDATE("UPDATE"), 
	DELETE("DELETE"), 
	SELECT("SELECT");

	String action;

	private UnitActions(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

}
