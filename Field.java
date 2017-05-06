public class Field {

	public enum Type {
		Road,
		Wall,
		ParkingSpot,
		Gate
	}	

	public Type type;
	
	public Car car;
	public String agent;
	
	public boolean obstacle() {
		return (type == Type.Wall || car!=null || agent!=null);
	}	
}
