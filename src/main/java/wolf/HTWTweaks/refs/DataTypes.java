package wolf.HTWTweaks.refs;

public enum DataTypes {
    NO_DATA(0),
    HAS_DATA(1);
	
    DataTypes(int newType) {
    	this.type = newType;
    }
    
    private int type;
	
	public int getType() {
		return type;
	}
}
