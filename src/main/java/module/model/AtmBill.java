package module.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author emuraro
 *
 */
public class AtmBill {
	
	private List<Integer> bills;
	
	public AtmBill(){
		bills = new ArrayList<Integer>();
		bills.add(100);
		bills.add(50);
		bills.add(20);
		bills.add(10);
	}
	
	public List<Integer> getValidBills(){
		return bills;
	}
	
	public void addBill(Integer billValue){
		bills.add(billValue);
		Collections.sort(bills, new Comparator<Integer>() {
			public int compare(Integer bill2, Integer bill1){
				return bill1.compareTo(bill2);
			}
		});
	}
	
	public boolean removeBill(Integer billValue){
		return bills.remove(billValue);
	}
	
	public boolean validateCashOut(Integer value){
		int indexMinimalValue  = bills.size() -1;
		return (indexMinimalValue >= 0 && value >= bills.get(indexMinimalValue));
	}
	
}
