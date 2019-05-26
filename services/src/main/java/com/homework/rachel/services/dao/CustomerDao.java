package com.homework.rachel.services.dao;

import com.homework.rachel.services.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer, Integer>
{

}
