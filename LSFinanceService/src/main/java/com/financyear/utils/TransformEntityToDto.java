package com.financyear.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.financyear.dto.CustomerAccountsDto;
import com.financyear.dto.CustomerDto;
import com.financyear.dto.UserDto;
import com.financyear.model.Customer;
import com.financyear.model.CustomerAccounts;
import com.financyear.model.User;
import com.mysql.jdbc.Util;

public class TransformEntityToDto {

	public static List<CustomerDto> getCustomerDetails(List<Customer> customerList){
		List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();
		
		
		
		
		Iterator<?> itr1 = customerList.iterator();
		while(itr1.hasNext()){
			Customer customer = (Customer) itr1.next();
			CustomerDto customerDto = new CustomerDto();
			customerDto.setCustomerId(customer.getCustomerId().toString());
			customerDto.setName(Utils.nullIfBlank(customer.getName()));
			customerDto.setPhone(Utils.nullIfBlank(customer.getPhoneNumber()));
			customerDto.setAddress(Utils.nullIfBlank(customer.getAddress()));
			customerDto.setStartDate(Utils.convertDateToString_IndiaWithSlashes(customer.getStartDate()));
			customerDto.setFinanceStatus(Utils.nullIfBlank(customer.getFinanceStatus()));
			customerDto.setTotalAmount(customer.getTotalAmount() != null?  String.valueOf(customer.getTotalAmount()) : null);
			customerDto.setInterest(customer.getRateOfInterest() != null ? String.valueOf(customer.getRateOfInterest()) : null);
			customerDto.setAmountToCustomer(customer.getAmountToCustomer() != null ? String.valueOf(customer.getAmountToCustomer()) : null);
			customerDto.setFinanceType(Utils.nullIfBlank(customer.getFinanceType()));
			customerDto.setNoOfDays(customer.getDays() != null ? customer.getDays().toString() : null);
			customerDto.setAmountPerType(customer.getAmountPerType() != null ? String.valueOf(customer.getAmountPerType()) : null);
			customerDto.setPaid(customer.getPaid() != null ? String.valueOf(customer.getPaid()): String.valueOf(0.00));
			customerDto.setDue(customer.getDue()!=null?String.valueOf(customer.getDue()): String.valueOf(0.00));
			
			customerDto.setCreatedBy(customer.getCreatedBy());
			customerDto.setCreatedOn(Utils.convertDateToString_IndiaWithSlashes(customer.getCreatedOn()));
			
			customerDtos.add(customerDto);
			
		}
		
		return customerDtos;
	}

	
	public static void getCustomerAccountDetails(List<CustomerDto> customerDto,
			List<CustomerAccounts> customerList) {
		
		DecimalFormat dc = new DecimalFormat("#.##");
		 Iterator  itr = customerList.iterator();
		 while(itr.hasNext()){
			 CustomerDto dto  = new CustomerDto(); 
			 CustomerAccounts accounts = (CustomerAccounts) itr.next();
			 dto.setName( Utils.nullIfBlank(accounts.getCustomer().getName()));
			 dto.setCustomerId(Utils.nullIfBlank(accounts.getCustomerId().toString()));
			 dto.setStartDate(Utils.convertDateToString_IndiaWithSlashes(accounts.getCustomer().getStartDate()));
			 dto.setTotalAmount(Utils.nullIfBlank(String.valueOf(accounts.getCustomer().getTotalAmount())));
			 dto.setPaidDate(Utils.convertDateToString_IndiaWithSlashes(accounts.getPaidOn()));
			 dto.setOnDateCollection(Utils.nullIfBlank(String.valueOf(accounts.getOnDateCollection())));
			 dto.setPaid(Utils.nullIfBlank(String.valueOf(accounts.getPaid())));
			 dto.setPaymentStatus(Utils.nullIfBlank(accounts.getPaymentStatus()));
			 dto.setDue(Utils.nullIfBlank(String.valueOf(accounts.getDue())));
			 dto.setPostponedTo(accounts.getPostponedTo()!=null ? Utils.convertDateToString_IndiaWithSlashes(accounts.getPostponedTo()) : null);
			 customerDto.add(dto);
		 }
		 
		
	}


	public static void getCustomerForEdit(CustomerDto customerDto,
			Customer customer) {
		customerDto.setCustomerId(Utils.nullIfBlank(customer.getCustomerId().toString()));
		customerDto.setAddress(Utils.nullIfBlank(customer.getAddress()));
		customerDto.setAmountPerType(Utils.nullIfBlank(String.valueOf(customer.getAmountPerType())));
		customerDto.setAmountToCustomer(Utils.nullIfBlank(String.valueOf(customer.getAmountToCustomer())));
		customerDto.setFinanceStatus(Utils.nullIfBlank(customer.getFinanceStatus()));
		customerDto.setFinanceType(Utils.nullIfBlank(customer.getFinanceType()));
		customerDto.setInterest(Utils.nullIfBlank(String.valueOf(customer.getRateOfInterest())));
		customerDto.setName(Utils.nullIfBlank(customer.getName()));
		customerDto.setNoOfDays(Utils.nullIfBlank(String.valueOf(customer.getDays())));
		customerDto.setPhone(Utils.nullIfBlank(customer.getPhoneNumber()));
		customerDto.setStartDate(Utils.convertDateToString_IndiaWithSlashes(customer.getStartDate()));
		customerDto.setPaid(Utils.nullIfBlank(String.valueOf(customer.getPaid())));
		customerDto.setTotalAmount(Utils.nullIfBlank(String.valueOf(customer.getTotalAmount())));
	}


	public static UserDto getLogedInUserDto(UserDto dto, User user) {
		if(user != null){
		dto.setUserName(Utils.nullIfBlank(user.getUserName()));
		dto.setFirstName(Utils.nullIfBlank(user.getFirstName()));
		dto.setLastName(Utils.nullIfBlank(user.getLastName()));
		dto.setPassword(Utils.nullIfBlank(user.getPassword()));
		dto.setConfirmPassword(Utils.nullIfBlank(user.getConfirmPassword()));
		dto.setAddress(Utils.nullIfBlank(user.getAddress()));
		dto.setEmail(Utils.nullIfBlank(user.getEmail()));
		dto.setPhoneNumber(Utils.nullIfBlank(user.getPhoneNumber()));
		dto.setUserId(Utils.nullIfBlank(user.getId().toString()));
		}
		return dto;
	}


	public static CustomerDto getCustomerAccountsByMonth(
			List<Customer> customerList) {
		CustomerDto customerDto = new CustomerDto();
		Double financyearTotalSpent = 0.00;
		Double financyearTotalCollected = 0.00;
		if(customerList != null){
		Iterator<?> itr = customerList.iterator();
		while(itr.hasNext()){
			Customer customer = (Customer) itr.next();
			if(customer.getTotalAmount()!= null)
			financyearTotalSpent += customer.getTotalAmount();
			
			if(customer.getPaid()!= null)
			financyearTotalCollected += customer.getPaid();
		}
		
		Double remainingFinanceToCollect = financyearTotalSpent - financyearTotalCollected; 
		
		
		customerDto.setFinancyearTotalSpent(String.valueOf(financyearTotalSpent));
		customerDto.setFinancyearTotalCollected(String.valueOf(financyearTotalCollected));
		customerDto.setRemainingFinanceToCollect(String.valueOf(remainingFinanceToCollect));
		
	}
		return customerDto;
	}
}