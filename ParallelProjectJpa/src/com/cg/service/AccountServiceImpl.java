package com.cg.service;

 


import com.cg.bean.Account;
import com.cg.dao.AccountDao;
import com.cg.dao.AccountDaoImpl;
import com.cg.exception.AccountException;

public class AccountServiceImpl implements AccountService {
	AccountDao accDao=new AccountDaoImpl();
	@Override
	public String createNewAccount(Account acc) throws AccountException {
		
		if(!acc.getPhoneNo().matches("\\d{10}")){
			throw new AccountException("Phone number should be 10 digits");
		}
		if(acc.getName().isEmpty()||acc.getName()==null){
			throw new AccountException("Name cannot be empty");
		}
		if(!acc.getName().matches("[A-Z][A-Za-z]{3,}")){
			throw new AccountException("Name should start with capital letter with minimum 3 characters and should contain only alphabets");
		}
		if (acc.getEmailId().matches("[a-z0-9]+@[a-z]+\\.com")) {
			throw new AccountException("Enter valid emailid");
			}
			if (acc.getBalance() <= 0) {
			throw new AccountException("Balance should be greater than zero");
			}
		
		return accDao.createNewAccount(acc);
	}
	@Override
	public double showBalance(String mobileNo) throws AccountException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new AccountException("Mobile number should contain 10 digits");
			}
			return accDao.showBalance(mobileNo);
	}
	@Override
	public Account deposit(String mobileNo,double depositAmt) throws AccountException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new  AccountException("Mobile number should contain 10 digits");
			}
			
			if (depositAmt <= 0) {
			throw new AccountException("Deposit amount must be greater than zero");
			}
			Account acc = accDao.deposit(mobileNo,depositAmt);
			return acc;
	}
	@Override
	public Account withdraw(String mobileNo,double withdrawAmt) throws AccountException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new AccountException("Mobile number should contain 10 digits");
			}
		
			if (withdrawAmt <= 0) {
			throw new AccountException("The amount to be withdrawn should be less than available balance and Entered amount should be greater than zero");
			}
			Account acc = accDao.withdraw(mobileNo,withdrawAmt);
			return acc;
	}
	@Override
	public Account printTransactionDetails(String mobileNo)
			throws AccountException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new AccountException("Mobile number should contain 10 digits");
			}
		return accDao.printTransactionDetails(mobileNo);
	}
	@Override
	public boolean fundTransfer(String sourceMobileNo, String destMobileNo,
			double transferAmt) throws AccountException {
		if (!sourceMobileNo.matches("\\d{10}")) {
			throw new AccountException("Mobile number should contain 10 digits");
			}
			if (!destMobileNo.matches("\\d{10}")) {
			throw new AccountException("Mobile number should contain 10 digits");
			}
			if(transferAmt<=0){
				throw new AccountException("The amount to be withdrawn should be greater than available balance and Entered amount should be greater than zero");
			}
			AccountService service = new AccountServiceImpl();
			 service.withdraw(sourceMobileNo, transferAmt);
		     service.deposit(destMobileNo, transferAmt);
			return true;
	}
}
