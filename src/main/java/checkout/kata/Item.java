package checkout.kata;

public class Item {
	
	private int price;
	private String name;
	private int total = 0;
	private int[] specialOffer;
	
	Item(String name, int price) {
		this.name = name;
		this.price = price;
		this.specialOffer = new int[]{1, price};
	}
	
	Item(String name, int price, int[] specialOffer) {
		this.name = name;
		this.price = price;
		this.specialOffer = specialOffer;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getTotalPrice() {
		return (total/specialOffer[0] * specialOffer[1]) + ((total % specialOffer[0]) * price);
	}

	public String getName() {
		return name;
	}
	
	public void increaseTotal() {
		total++;
	}
	
	public int getTotal() {
		return total;
	}

	public int[] getSpecialOffer() {
		return specialOffer;
	}
}
