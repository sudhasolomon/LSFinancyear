package com.financyear.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financyear.dao.CustomerAccountsDao;
import com.financyear.model.CustomerAccounts;
import com.financyear.service.CustomerAccountService;

@Service
@Transactional
public class CustomerAccountServiceImpl implements CustomerAccountService {

	@Autowired
	private CustomerAccountsDao customerAcDao;
	
	public void saveCustomerAccounts(CustomerAccounts customerAccounts) {
		 customerAcDao.save(customerAccounts); 
	}

	public List<CustomerAccounts> getCustomerAccountsBYId(Integer parseInt) {
		 
		return customerAcDao.getCustomerAccountsBYId(parseInt);
	}

}
