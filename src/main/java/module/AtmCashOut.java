package module;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import module.model.AtmBill;
import module.model.CashOutBill;

/**
 * This class is responsable to manage the cash-out process of ATM 
 * @author emuraro
 *
 */
public class AtmCashOut {
	
	private AtmBill atmBill;
	
	public AtmCashOut(){
		atmBill = new AtmBill();
	}
	
	/**
	 * Select the bills to cash-out
	 * @param value List of bill value and quantity to spit out
	 * @return
	 */
	public List<CashOutBill> cashOutMoney(Integer value) {
		List<CashOutBill> cashOutResult = new ArrayList<CashOutBill>();
		//the available bills at ATM
		List<Integer> bills = atmBill.getValidBills();
		//Check if the value to cash-out is less than the minimal bill value
		if (!atmBill.validateCashOut(value)){
			return null;
		}
		Integer subValue = value;
		for(int index=0; index < bills.size(); index++){
			//It finds the bill immediately greater than the amount to chash-out 
			if (value >= bills.get(index) ){
				//Calculating the necessary amount of this bill
				CashOutBill cashOutBill = calculateAmountBill(bills.get(index), subValue);
				if (cashOutBill != null){
					//decreasing the total amount to cash-out
					subValue -= cashOutBill.getAmount();
					cashOutResult.add(cashOutBill);
				}
				if (subValue == 0){//when the temporary cash-out is zero (0), it finds all the bills 
					return cashOutResult;
				}else if (subValue < 0){
					//can not spit out the value
					return null;
				}
			}
		}
		//can not spit out the value
		return null;
	}
	
	//Calculating the necessary amount of this bill
	private CashOutBill calculateAmountBill(Integer bill, Integer cashOutValue){
		Integer quantity = cashOutValue/bill;
		if (quantity <=0){
			return null;
		}
		return new CashOutBill(bill, quantity);
	}
	
	public String cashOutListToString(List<CashOutBill> cashOutResult){
		StringBuilder stb = new StringBuilder();
		for (CashOutBill cashOutBill : cashOutResult) {
			stb.append("\nSpit out ")
			.append(cashOutBill.getQuantity())
			.append(" of $")
			.append(cashOutBill.getBill())
			.append(" bill");
		}
		return stb.toString();
	}
	
	public static void main(String[] args) {
		AtmCashOut atmCashOut = new AtmCashOut();
		List<CashOutBill> cashOutResult = null;
		Scanner sc = new Scanner(System.in);
		int readValue = -1;
		System.out.println("ATM Cash-out module");
		System.out.println("To finalize the module type -1");
		do{
			System.out.println("Please, enter the value");
			readValue = sc.nextInt();
			cashOutResult = atmCashOut.cashOutMoney(readValue);
			if (cashOutResult == null){
				System.out.println("It is not possible to cash-out the requested value: " + readValue);
			}else{
				System.out.println(atmCashOut.cashOutListToString(cashOutResult));
			}
		}while(readValue != -1);

		sc.close();
	}

}
