package com.cg.dao;

  

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;








import com.cg.bean.Account;
import com.cg.exception.AccountException;

public class AccountDaoImpl implements AccountDao {
	EntityManagerFactory fact=Persistence.createEntityManagerFactory("JPA1");
	EntityManager em=fact.createEntityManager();
	//private static HashMap<String,Account>accMap=AccountDb.getAccDb();
	@Override
	public String createNewAccount(Account acc) throws AccountException {
		em.getTransaction().begin();
		em.persist(acc);
		em.getTransaction().commit();
		return acc.getPhoneNo();
	}
	@Override
	public double showBalance(String mobileNo) throws AccountException {
		String strqry="select e from Account e where e.mobile_no=?";
		TypedQuery<Account> query=em.createQuery(strqry,Account.class);
		query.setParameter(1,mobileNo);
		Account acc=query.getSingleResult();
   if(!mobileNo.equals(acc.getPhoneNo())){
	throw new AccountException("the mobile number is not there in the data base");}
   else{
		return acc.getBalance();
   }
	}
	@Override
	public Account deposit(String mobileNo,double balance) throws AccountException {
		em.getTransaction().begin();
		String strqry="select e from Account e where e.mobile_no=?";
		TypedQuery<Account> query=em.createQuery(strqry,Account.class);
		query.setParameter(1,mobileNo);
		Account acc=query.getSingleResult();
		if(acc==null){
			throw new AccountException("the mobile number is not there in the data base");
		}
		double d=acc.getBalance()+balance;
		acc.setBalance(d);
		em.merge(acc);
		em.getTransaction().commit();
		return acc;
	}
	@Override
	public Account withdraw(String mobileNo,double balance) throws AccountException {
		em.getTransaction().begin();
		String strqry="select e from Account e where e.mobile_no=?";
		TypedQuery<Account> query=em.createQuery(strqry,Account.class);
		query.setParameter(1,mobileNo);
		Account acc=query.getSingleResult();
		if(acc==null){
			throw new AccountException("the mobile number is not there in the data base");
		}
		double d=acc.getBalance()-balance;
		acc.setBalance(d);
		em.merge(acc);
		em.getTransaction().commit();
		
		return acc;
	}
	@Override
	public Account printTransactionDetails(String mobileNo)
			throws AccountException {
		String strqry="select e from Account e where e.mobile_no=?";
		TypedQuery<Account> query=em.createQuery(strqry,Account.class);
		query.setParameter(1,mobileNo);
		Account acc=query.getSingleResult();
   if(acc==null){
	throw new AccountException("the mobile number is not there in the data base");

}
		return acc;
	}
	@Override
	public boolean fundTransfer(String sourceMobileNo, String destMobileNo,
			double transferAmount) throws AccountException {

			String strqry="select e from Account e where e.mobile_no=?";
				TypedQuery<Account> query=em.createQuery(strqry,Account.class);
				query.setParameter(1,sourceMobileNo);
				Account acc=query.getSingleResult();
				String strqry1="select e from Account e where e.mobile_no=?";
				TypedQuery<Account> query1=em.createQuery(strqry1,Account.class);
				query1.setParameter(1,destMobileNo);
				Account acc1=query.getSingleResult();
			if(acc==null){
				throw new AccountException("the mobile number is not there in the data base");
			}
			else if(acc1==null)
			{
				throw new AccountException("the mobile number is not there in the data base");
			}
			else
			{
			return true;
			}
	}

	
}
