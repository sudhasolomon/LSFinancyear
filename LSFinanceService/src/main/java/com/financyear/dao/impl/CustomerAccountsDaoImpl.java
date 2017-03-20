package com.financyear.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.financyear.dao.CustomerAccountsDao;
import com.financyear.model.CustomerAccounts;

@Repository
public class CustomerAccountsDaoImpl extends GenericDaoImpl<CustomerAccounts, Integer> implements CustomerAccountsDao{

	public CustomerAccountsDaoImpl() {
		super(CustomerAccounts.class);
	}

	@SuppressWarnings("unchecked")
	public List<CustomerAccounts> getCustomerAccountsBYId(Integer customerId) {
		List<CustomerAccounts> customerAccount = null;
		if(customerId != null){
		Map<String, Object> params = new HashMap<String,Object>();
		StringBuilder hql = new StringBuilder("from CustomerAccounts c");
		hql.append(" where c.customerId = :customerId");
		params.put("customerId", customerId);
		customerAccount = (List<CustomerAccounts>) findHqlListByParams(hql.toString(), params);
		}
		return customerAccount;
	}

}
