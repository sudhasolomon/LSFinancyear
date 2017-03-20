package com.financyear.service;

import java.util.List;

import com.financyear.model.CustomerAccounts;

public interface CustomerAccountService 
{

	public void saveCustomerAccounts(CustomerAccounts customerAccounts);

	public List<CustomerAccounts> getCustomerAccountsBYId(Integer parseInt);

}
