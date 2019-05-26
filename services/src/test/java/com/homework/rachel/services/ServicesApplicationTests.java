package com.homework.rachel.services;

import com.homework.rachel.services.dao.CustomerDao;
import com.homework.rachel.services.dao.ServiceDao;
import com.homework.rachel.services.dao.SubscribeDao;
import com.homework.rachel.services.entity.Customer;
import com.homework.rachel.services.entity.Service;
import com.homework.rachel.services.entity.Subscribe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ServicesApplication.class)
public class ServicesApplicationTests {
    @Autowired
    private SubscribeDao subRepo;

    @Autowired
    private CustomerDao cusRepo;

    @Autowired
    private ServiceDao serRepo;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void initData() {
        serRepo.deleteAll();
        cusRepo.deleteAll();
        subRepo.deleteAll();

        Customer customer1 = new Customer();
        customer1.setName("Matt");
        Customer customer2 = new Customer();
        customer2.setName("Rachel");

        cusRepo.save(customer1);
        cusRepo.save(customer2);
        Assert.assertNotNull(customer1.getId());

        Service service1 = new Service();
        service1.setName("Read");
        Service service2 = new Service();
        service2.setName("Write");
        Service service3 = new Service();
        service3.setName("Delete");

        serRepo.save(service1);
        serRepo.save(service2);
        serRepo.save(service3);
        Assert.assertNotNull(service1.getId());
    }

    @Test
    public void testServiceDao() {
        Iterable<Service> services = serRepo.findAll();
        Assert.assertNotNull(services);
        Iterable<Customer> customers = cusRepo.findAll();
        Assert.assertNotNull(customers);
        int cusId = customers.iterator().next().getId();
        int serId = services.iterator().next().getId();

        // try to do a legal subscribe
        ResponseEntity<Boolean> bRes = this.restTemplate.exchange("/customers/subService?cusId=" + cusId + "&serId=" +serId,
                HttpMethod.GET, null, Boolean.class);
        Assert.assertTrue(bRes.getBody());

        // do an illegal subscribe
        bRes = this.restTemplate.exchange("/customers/subService?cusId=" + Integer.MIN_VALUE + "&serId=" +serId,
                HttpMethod.GET, null, Boolean.class);
        Assert.assertFalse(bRes.getBody());

        // check subscribe db
        Iterable<Subscribe> subscribes = subRepo.findAll();
        Assert.assertNotNull(subscribes);
        Subscribe sub = subscribes.iterator().next();
        Assert.assertEquals(cusId, sub.getCusID());
        Assert.assertEquals(serId, sub.getSerID());

        //get subscribed services for cusId
        ResponseEntity<Service[]> sRes = this.restTemplate.exchange("/customers/services?cusId=" + cusId,
                HttpMethod.GET, null, Service[].class);
        Service[] serviceList = sRes.getBody();
        Assert.assertNotNull(serviceList);
        Assert.assertEquals(1, serviceList.length);
        Assert.assertEquals(serId, serviceList[0].getId());

        //get unSubscribed services for cusId
        sRes = this.restTemplate.exchange("/customers/unSubedServices?cusId=" + cusId,
                HttpMethod.GET, null, Service[].class);
        Service[] unSubedServiceList = sRes.getBody();
        Assert.assertEquals(2, unSubedServiceList.length);

        //unsub a unexisted subscribe
        bRes = this.restTemplate.exchange("/customers/unSubService?cusId=" + Integer.MIN_VALUE + "&serId=" +serId,
                HttpMethod.GET, null, Boolean.class);
        Assert.assertFalse(bRes.getBody());

        //unsub an existing subscribe
        bRes = this.restTemplate.exchange("/customers/unSubService?cusId=" + cusId + "&serId=" +serId,
                HttpMethod.GET, null, Boolean.class);
        Assert.assertTrue(bRes.getBody());

        sRes = this.restTemplate.exchange("/customers/services?cusId=" + cusId,
                HttpMethod.GET, null, Service[].class);
        Service[] serviceList2 = sRes.getBody();
        Assert.assertNotNull(serviceList);
        Assert.assertEquals(0, serviceList2.length);
    }
}
