package Control;
import java.util.ArrayList;

import entity.Customer;
import entity.PayByCashOnDelivery;
import entity.PayByCreditCard;
import entity.PayByWallet;
import entity.Payment;
import entity.PaymentMethod;
import entity.Service;

public class CustomerController {
	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	public static PaymentMethod payMethod;
	
	
	//adds a new Customer to the database which is Volatile:)
	public static void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	public static void addBill(String accid, Payment bill) {
		for (Customer element : customers) {
		    if (element.account.getID() == accid) {
		        element.addBill(bill);
		    }
		}
	}
	
	public static void setMethod(String s) {
		if(s.equals("cash"))
			payMethod = new PayByCashOnDelivery();
		else if(s.equals("wallet"))
			payMethod = new PayByWallet();
		else
			payMethod = new PayByCreditCard();

	}
	
	public static double getFees(Service s) {
		return payMethod.calculateFees(s);
	}
	
	public boolean checkPayment(String ID,double amount) {
		//search for the customer with ID
		for (Customer element : customers) {
			if(element.account.getID() == ID) {
				//checks if the customer has that amount in wallet
				if(element.account.pay(amount)) {
					//make an payment instance to add to the customer 
					Payment p = new Payment(amount,ServiceController.getServiceName(),ID);
					element.addBill(p);
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	public static ArrayList<Payment> getPayments(String ID) {
		
	    ArrayList<Payment> payments = new ArrayList<Payment>();
	    //search for the customer with ID
	  	for (Customer element : customers) {
	  		if(element.account.getID() == ID) {
	  			payments = element.getBills();
	  		}
	  	}
	    return payments;
	}
	
	public static void listPayments(String ID) {
		System.out.println("ID" + "        " + "Service" + "         " + "Amount");
	    ArrayList<Payment> payments = getPayments(ID);
	    for (Payment element : payments) {
	  		System.out.println(element.getID() + "        " + element.getService() + "        " + element.getamount());
	  	}
	    
	    return;
	}

	public static void issueRefund(String accid, Payment bill) {
		// TODO Auto-generated method stub
		AdminController.addRefund(accid, bill);
	}

	public static Payment getBill(String accid, String id) {
		// TODO Auto-generated method stub
	    //search for the customer with ID
	  	for (Customer element : customers) {
	  		if(element.account.getID() == accid) {
	  			ArrayList<Payment> payments = getPayments(accid);
	  			for (Payment elemen : payments) {
	  		  		if(elemen.getID() == id) {
	  		  			return elemen;
	  		  		}
	  		  	}
	  		}
	  	}
	    return null;
	}
	
	public static void acceptedRefund(String accid, String id) {
		// TODO Auto-generated method stub
		Payment bill = getBill(accid, id);
		AccountController.addToWallet(bill.getamount(), accid);
	}
	
}
