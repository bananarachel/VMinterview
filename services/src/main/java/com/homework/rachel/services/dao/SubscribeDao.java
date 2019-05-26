package com.homework.rachel.services.dao;

import com.homework.rachel.services.entity.Subscribe;
import org.springframework.data.repository.CrudRepository;

public interface SubscribeDao extends CrudRepository<Subscribe, Integer>, SubscribeDaoCustom {

}
