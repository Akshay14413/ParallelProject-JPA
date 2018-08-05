package com.cg.dao;


import com.cg.bean.Account;
import com.cg.exception.AccountException;

public interface AccountDao {
	String createNewAccount(Account acc)throws AccountException;
	double showBalance(String mobileNo) throws AccountException;
	Account deposit(String mobileNo,double balance) throws AccountException;
	Account withdraw(String mobileNo,double balance) throws AccountException;
	Account printTransactionDetails(String mobileNo) throws AccountException;
	  public boolean fundTransfer(String sourceMobileNo,String destMobileNo,double transferAmount) throws AccountException;
	}
