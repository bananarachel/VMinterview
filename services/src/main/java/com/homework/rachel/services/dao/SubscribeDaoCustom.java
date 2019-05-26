package com.homework.rachel.services.dao;

import com.homework.rachel.services.entity.Customer;
import com.homework.rachel.services.entity.Service;
import com.homework.rachel.services.entity.Subscribe;

import java.util.List;

public interface SubscribeDaoCustom{
    /**
     * find subscribe list for assigned customer
     * @param cusId id of the customer
     * @return list of subscribes
     */
    List<Subscribe> findSubscribesByCusID(int cusId);

    Subscribe findSubscribeByCusIDAndSerID(int cusId, int serId);

    Customer findCustomerBySubscribeID(int subId);

    /*void deleteSubscribeById(int id);
    void deleteSubscribesByCusID(int cusId);*/
}
