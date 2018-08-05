package com.cg.test;

import static org.junit.Assert.* ;

import org.junit.Before;
import org.junit.Test;

import com.cg.bean.Account;
import com.cg.exception.AccountException;
import com.cg.service.AccountService;
import com.cg.service.AccountServiceImpl;

public class AccountTest {

private AccountService service= new AccountServiceImpl();
	


@Before
public void init() {
service = new AccountServiceImpl();
}

@Test
public void testCreateNewAccountForMobile() {
Account acc = new Account();
acc.setPhoneNo("12546");
acc.setName("raju");
acc.setEmailId("raju@gmail.com");
acc.setBalance(100.0);
try {
service.createNewAccount(acc);
} catch (AccountException e) {
assertEquals("Phone number should be 10 digits", e.getMessage());
}
}
 
@Test
public void testCreateNewAccountForName() {
Account acc = new Account();
acc.setPhoneNo("1234567899");
acc.setName("raju");
acc.setEmailId("raju@gmail.com");
acc.setBalance(100.0);
try {
service.createNewAccount(acc);
} catch (AccountException e) {
assertEquals("Name should start with capital letter with minimum 3 characters and should contain only alphabets", e.getMessage());
}
}
 
@Test
public void testCreateNewAccountForName1() {
Account acc = new Account();
acc.setPhoneNo("1234567899");
acc.setName("Raju12");
acc.setEmailId("raju@gmail.com");
acc.setBalance(100.0);
try {
service.createNewAccount(acc);
} catch (AccountException e) {
assertEquals("Name should start with capital letter with minimum 3 characters and should contain only alphabets", e.getMessage());
}
}

@Test
public void testCreateNewAccountForNameIsEmpty() {
Account acc = new Account();
acc.setPhoneNo("1234567899");
acc.setName("");
acc.setEmailId("raju@gmail.com");
acc.setBalance(200.0);
try {
service.createNewAccount(acc);
} catch (AccountException e) {
assertEquals("Name cannot be empty", e.getMessage());
}
}

/* 
@Test
public void testCreateNewAccountForEmailId() {
Account acc = new Account();
acc.setPhoneNo("1234567899");
acc.setName("Rajukumar");
acc.setEmailId("Raju@");
acc.setBalance(200.0);
try {
service.createNewAccount(acc);
} catch (AccountException e) {
assertEquals("Enter valid emailid", e.getMessage());
}
}*/
 

 
@Test
public void testCreateNewAccount() {
Account acc = new Account();
acc.setPhoneNo("1234567890");
acc.setName("Raju kumar");
acc.setEmailId("Raju@gmail.com");
acc.setBalance(200.0);
 
try {
String s=service.createNewAccount(acc);
assertNotNull(s);
} catch (AccountException e) {

 
}
 
}
 
@Test
public void testShowBalanceForMobileNo() {

try {
service.showBalance("36521");
} catch (AccountException e) {

assertEquals("Mobile number should contain 10 digits",e.getMessage());
}
}
 

/*@Test
public void testShowBalanceForMobileNoDoesNotExist() {

try {
service.showBalance("8456321217");
} catch (AccountException e) {
assertEquals("The mobile number does not exist",e.getMessage());
}
}*/

/*@Test
public void testShowBalanceForMobileNoInDb() {

try {
service.showBalance("9978456321");
} catch (AccountException e) {

//assertEquals("Mobile number should contain 10 digits",e.getMessage());
}
}*/

@Test
public void testDepositForMobileNo() {
Account acc=new Account();
acc.setPhoneNo("95059345");
try {
service.deposit(acc.getPhoneNo(), 230);
} catch (AccountException e) {
assertEquals("Mobile number should contain 10 digits",e.getMessage());
}
}
/*@Test
public void testDepositForMobileNoDoesNotExist() {
Account acc=new Account();
acc.setPhoneNo("9505936322");
try {
service.deposit(acc.getPhoneNo(), 230);
} catch (AccountException e) {
assertEquals("The mobile number does not exist",e.getMessage());
}
}*/
@Test
public void testDepositForDepositAmt1() {
Account acc=new Account();
acc.setPhoneNo("9912365478");
try {
service.deposit(acc.getPhoneNo(),-230);
} catch (AccountException e) {
assertEquals("Deposit amount must be greater than zero",e.getMessage());
}
}
 
/*@Test
public void testDeposit() {
Account acc=new Account();
acc.setPhoneNo("8974563215");
try {
Account acc1=service.deposit(acc.getPhoneNo(), 230);
assertNotNull(acc1);
} catch (AccountException e) {
 
	assertEquals("The mobile number does not exist",e.getMessage());
}
}*/
 
@Test
public void testWithDrawForMobileNo() {
Account acc=new Account();
acc.setPhoneNo("95059345");
try {
service.withdraw(acc.getPhoneNo(), 230);
} catch (AccountException e) {
// TODO Auto-generated catch block
assertEquals("Mobile number should contain 10 digits",e.getMessage());
}
}
/*@Test
public void testWithdrawForMobileNoDoesNotExist() {
Account acc=new Account();
acc.setPhoneNo("9505934512");
try {
service.withdraw(acc.getPhoneNo(), 230);
} catch (AccountException e) {
// TODO Auto-generated catch block
assertEquals("The mobile number does not exist",e.getMessage());
}
}*/
@Test
public void testWithdrawForAmt() {
Account acc=new Account();
acc.setPhoneNo("9912365478");
try {
service.withdraw(acc.getPhoneNo(), -230);
} catch (AccountException e) {
// TODO Auto-generated catch block
assertEquals("The amount to be withdrawn should be less than available balance and Entered amount should be greater than zero",e.getMessage());
}
}
 
/*@Test
public void testWithdrawForMobileNoThatExist() {
Account acc=new Account();
acc.setPhoneNo("8974563215");
try {
service.withdraw(acc.getPhoneNo(), 230);
} catch (AccountException e) {
	assertEquals("The mobile number does not exist",e.getMessage());
}
}*/



@Test
public void testFundTransferForMobileNo() {
Account acc=new Account();
Account acc1=new Account();
acc.setPhoneNo("9912365478");
acc1.setPhoneNo("154896");
try {
service.fundTransfer(acc.getPhoneNo(),acc1.getPhoneNo(), 230);
} catch (AccountException e) {
// TODO Auto-generated catch block
assertEquals("Mobile number should contain 10 digits",e.getMessage());
}
}
/*@Test
public void testFundTransferForMobileNoDoesNotExist() {
Account acc=new Account();
Account acc1=new Account();
acc.setPhoneNo("8563214456");
acc1.setPhoneNo("7896541232");
try {
service.fundTransfer(acc.getPhoneNo(), acc1.getPhoneNo(),  230);
} catch (AccountException e) {
// TODO Auto-generated catch block
assertEquals("The mobile number does not exist",e.getMessage());
}
}*/
@Test
public void testFundTransferForNegativeAmt() {
Account acc=new Account();
Account acc1=new Account();
acc.setPhoneNo("9978456321");
acc1.setPhoneNo("9912365478");
try {
service.fundTransfer(acc.getPhoneNo(), acc1.getPhoneNo(),  -230);
} catch (AccountException e) {
// TODO Auto-generated catch block
assertEquals("The amount to be withdrawn should be greater than available balance and Entered amount should be greater than zero",e.getMessage());
}
}
/*@Test
public void testFundTransfer() {
Account acc=new Account();
Account acc1=new Account();
acc.setPhoneNo("9505928555");
acc1.setPhoneNo("9848468242");
try {
assertTrue(service.fundTransfer(acc.getPhoneNo(), acc1.getPhoneNo(),  230));
} catch (AccountException e) {
	assertEquals("The mobile number does not exist",e.getMessage());
}
}*/


@Test
public void testPrinttransactionDetailsForMobileNo() {
Account acc=new Account();
acc.setPhoneNo("125463");
try {
Account acc1=service.printTransactionDetails(acc.getPhoneNo());
assertNotNull(acc1);
} catch (AccountException e) {
	assertEquals("Mobile number should contain 10 digits",e.getMessage());
}
 
}

/*@Test
public void testPrinttransactionDetailsForMobileNoDoesNotExist() {
Account acc=new Account();
acc.setPhoneNo("8563214456");
try {
Account acc1=service.printTransactionDetails(acc.getPhoneNo());
assertNotNull(acc1);
} catch (AccountException e) {
	assertEquals("The mobile number does not exist",e.getMessage());
}
 
}*/


/*@Test
public void testPrinttransactionDetails() {
Account acc=new Account();
acc.setPhoneNo("8974563215");
try {
Account acc1=service.printTransactionDetails(acc.getPhoneNo());
assertNotNull(acc1);
} catch (AccountException e) {
	assertEquals("The mobile number does not exist",e.getMessage());
}
 
}
	*/

}
