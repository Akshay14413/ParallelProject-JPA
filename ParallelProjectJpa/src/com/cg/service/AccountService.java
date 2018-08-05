package com.cg.service;

import com.cg.bean.Account;
import com.cg.exception.AccountException;

public interface AccountService {
	String createNewAccount(Account acc)throws AccountException;
	double showBalance(String mobileNo) throws AccountException;
	Account deposit(String mobileNo,double depositAmt) throws AccountException;
	Account withdraw(String mobileNo,double withdrawAmt) throws AccountException;
	Account printTransactionDetails(String mobileNo) throws AccountException;
	boolean fundTransfer(String sourceMobileNo,String destMobileNo,double transferAmt) throws AccountException;

}