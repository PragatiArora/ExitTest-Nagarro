/**
 * 
 */
package com.nagarro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.repository.CustomerRepository;

/**
 * @author vijaysharma01
 *
 */

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	@Autowired 
	private CustomerRepository customerRepository;
}