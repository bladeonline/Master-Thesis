package Chat;

public class Price {
	
	
	double price;
	double changing;
	double treshold;
	int direction;
	
	
	
	public Price(double price, double changing, double treshold, int direction) {
		super();
		this.treshold=treshold;
		this.direction=direction;
		this.price = price;
		this.changing = changing;
	}


	private double getPrice() {
		return price;
	}

	private double getChanging() {
		return changing;
	}
	private void setChanging(double changing) {
		this.changing = changing;
	}

}
