package com.financyear.dao;

import java.util.List;

import com.financyear.model.CustomerAccounts;

public interface CustomerAccountsDao extends GenericDao<CustomerAccounts, Integer> {

	public	List<CustomerAccounts> getCustomerAccountsBYId(Integer parseInt);

}
