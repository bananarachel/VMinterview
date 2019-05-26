package com.homework.rachel.services.dao;

import com.homework.rachel.services.entity.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class ServiceDaoImpl implements ServiceDaoCustom{

    @PersistenceContext
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Service> findUnSubscribedByCusID(int cusId) {
        String ql = "select s from Service as s " +
                " where not exists " +
                " (select b.serID from Subscribe as b where b.cusID = :cusId and s.id = b.serID)";
        Query query = em.createQuery(ql);
        query.setParameter("cusId", cusId);
        List<Service> services = query.getResultList();
        return services;
    }

    @Override
    public List<Service> findSubscribedByCusID(int cusId) {
        String ql = "select s from Service as s " +
                " join Subscribe as b" +
                " on s.id = b.serID" +
                " where b.cusID = :cusId";
        Query query = em.createQuery(ql);
        query.setParameter("cusId", cusId);
        List<Service> services = query.getResultList();
        return services;
    }

    @Override
    public boolean subServiceForCusID(int cusId, int serId) {
        return false;
    }

    @Override
    public boolean unsubServiceForCusID(int cusId, int serId) {
        return false;
    }
}
