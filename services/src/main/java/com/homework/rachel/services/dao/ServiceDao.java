package com.homework.rachel.services.dao;

import com.homework.rachel.services.entity.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceDao extends CrudRepository<Service, Integer>, ServiceDaoCustom {

}
