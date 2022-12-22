package Control;
import java.util.ArrayList;

import entity.Account;
import entity.Customer;


public class AccountController {
	
	public static ArrayList<Account> accounts = new ArrayList<Account>();
	private static int idCounter =1;
	


	public AccountController() {
		// TODO Auto-generated constructor stub
		Account acc =new Account("fares","saad");
		this.signUp(acc);
		Customer cus = new Customer(acc);
		CustomerController.addCustomer(cus);
	}

	public void signUp(Account newAccount) {
		newAccount.setID(String.valueOf(++idCounter));
		accounts.add(newAccount);
	}
	
	
	public boolean isAvail(Account a) {
		
		for (Account element : accounts) {
		    if (element.equal1(a)) {
		        return true;
		    }
		}
		return false;
		
	}
	
	public String getId(Account a) {
		
		for (Account element : accounts) {
		    if (element.equal1(a)) {
		        return element.getID();
		    }
		}
		return null;
		
	}
	
	public String signIn(Account acc) {
		for (Account element : accounts) {
		    if (element.equal1(acc)) {
		        return element.getID();
		    }
		}
		return null;
	} 
	
	
	public boolean isAccUn(Account acc) {
		for (Account element : accounts) {
		    if (element.equalacc(acc)) {
		        return false;
		    }
		}
		return true; 
	}
	
	public double getWalletAmount(String Id) {
		for (Account element : accounts) {
		    if (element.getID().equals(Id)) {
		        return element.getWalletBalance();
		    }
		}
		return 0;
		
	}
	
	public static void addToWallet(double amountTA,String Id) {
		for (Account element : accounts) {
		    if (element.getID().equals(Id)) {
		        element.addToWallet(amountTA);
		    }
		}
	}

}
