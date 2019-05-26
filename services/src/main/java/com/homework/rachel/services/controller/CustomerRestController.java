package com.homework.rachel.services.controller;

import com.homework.rachel.services.dao.CustomerDao;
import com.homework.rachel.services.dao.ServiceDao;
import com.homework.rachel.services.dao.SubscribeDao;
import com.homework.rachel.services.entity.Customer;
import com.homework.rachel.services.entity.Service;
import com.homework.rachel.services.entity.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {
    @Autowired
    private SubscribeDao subRepo;

    @Autowired
    private CustomerDao cusRepo;

    @Autowired
    private ServiceDao serRepo;

    @RequestMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Iterable<Customer> getCustomer(Model model) {
        Iterable<Customer> customers = cusRepo.findAll();
        model.addAttribute("customers", customers);
        return customers;
    }

    @RequestMapping(value = "/getBySub", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Customer getCustomerBySubscribeId
            (@RequestParam(value = "subId") int sId) {
        return subRepo.findCustomerBySubscribeID(sId);
    }

    @RequestMapping(value = "/services", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Iterable<Service> getSubscribedServiceByCustomerId
            (@RequestParam(value = "cusId") int cId) {
        return serRepo.findSubscribedByCusID(cId);
    }

    @RequestMapping(value = "/unSubedServices", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Iterable<Service> getUnSubscribedServiceByCustomerId
            (@RequestParam(value = "cusId") int cId) {
        return serRepo.findUnSubscribedByCusID(cId);
    }

    @RequestMapping(value = "/unSubService", method = RequestMethod.GET)
    public boolean unSubscribeServiceByCustomerId
            (@RequestParam(value = "cusId") int cId, @RequestParam(value = "serId") int sId) {
        if(cusRepo.findById(cId) == null)
        {
            System.out.println("Illegal User can not subscribe service");
            return false;
        }
        if(serRepo.findById(sId) == null)
        {
            System.out.println("Subscribe unsupported service failed.");
            return false;
        }
        Subscribe subscribe = subRepo.findSubscribeByCusIDAndSerID(cId, sId);
        if(subscribe== null)
        {
            System.out.println("Unsubscrbied Service can not be deleted");
            return false;
        }
        subRepo.deleteById(Integer.valueOf(subscribe.getId()));
        return true;
    }

    @RequestMapping(value = "/subService", method = RequestMethod.GET)
    public boolean subscribeServiceByCustomerId
            (@RequestParam(value = "cusId") int cId, @RequestParam(value = "serId") int sId) {
        if(!cusRepo.findById(cId).isPresent())
        {
            System.out.println("Illegal User can not subscribe service");
            return false;
        }
        if(!serRepo.findById(sId).isPresent())
        {
            System.out.println("Subscribe unsupported service failed.");
            return false;
        }
        if(subRepo.findSubscribeByCusIDAndSerID(cId, sId) != null)
        {
            System.out.println("Service already has been subscribed to the user");
            return false;
        }
        Subscribe sub = new Subscribe();
        sub.setCusID(cId);
        sub.setSerID(sId);
        return subRepo.save(sub) != null;
    }
}
