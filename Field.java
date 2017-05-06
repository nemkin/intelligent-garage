public class Block {

	public enum Type {
		Road,
		Wall,
		ParkingSpot,
		Gate
	}	

	public Type type;
	
	public Car car;
	public Sring agent;
	
	public boolean obstacle() {
		return (type == Wall || car!=null || agent!=null);
	}	
}
