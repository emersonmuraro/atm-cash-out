package module.model;

public class CashOutBill {
	
	private int bill;
	private int quantity;
	
	public CashOutBill(){}
	
	public CashOutBill(int bill, int quantity) {
		this.setBill(bill);
		this.setQuantity(quantity);
	}

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getAmount(){
		return bill*quantity;
	}

}
