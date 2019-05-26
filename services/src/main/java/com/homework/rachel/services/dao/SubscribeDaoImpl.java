package com.homework.rachel.services.dao;

import com.homework.rachel.services.entity.Customer;
import com.homework.rachel.services.entity.Service;
import com.homework.rachel.services.entity.Subscribe;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Queue;

public class SubscribeDaoImpl implements SubscribeDaoCustom {
    @PersistenceContext
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Subscribe> findSubscribesByCusID(int cusId) {
        String ql = "select s from Subscribe as s where cusID = :cusId";
        Query query = em.createQuery(ql);
        query.setParameter("cusId", cusId);
        List<Subscribe> subscribes = query.getResultList();
        return subscribes;
    }

    @Override
    public Subscribe findSubscribeByCusIDAndSerID(int cusId, int serId) {
        String ql = " select s " +
                " from Subscribe s " +
                " where s.cusID = :cusId and s.serID = :serId";
        Query query = em.createQuery(ql);
        query.setParameter("cusId", cusId);
        query.setParameter("serId", serId);
        List<Subscribe> subs = query.getResultList();
        if(subs == null || subs.size() == 0) return null;
        return subs.get(0);
    }

    @Override
    public Customer findCustomerBySubscribeID(int subId) {
        String ql = " select c " +
                " from Subscribe s " +
                " join Customer c on s.cusID = c.id" +
                " where s.id = :subId";
        Query query = em.createQuery(ql);
        query.setParameter("subId", subId);
        Customer customer = (Customer) query.getSingleResult();
        return customer;
    }

  /*  @Override
    public void deleteSubscribeById(int id) {

    }

    @Override
    public void deleteSubscribesByCusID(int cusId) {

    }*/
}
