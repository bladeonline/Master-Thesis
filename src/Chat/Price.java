package Chat;

public class Price {
	
	
	double price;
	double changing;
	public Price(double price, double changing) {
		super();
		
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
