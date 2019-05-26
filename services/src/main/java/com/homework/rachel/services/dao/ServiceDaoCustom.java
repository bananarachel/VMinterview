package com.homework.rachel.services.dao;

import com.homework.rachel.services.entity.Service;

import java.util.List;

public interface ServiceDaoCustom {
    List<Service> findUnSubscribedByCusID(int cusId);
    List<Service> findSubscribedByCusID(int cusId);
    boolean subServiceForCusID(int cusId, int serId);
    boolean unsubServiceForCusID(int cusId, int serId);
}
