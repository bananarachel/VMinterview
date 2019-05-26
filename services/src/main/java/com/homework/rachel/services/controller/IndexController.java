package com.homework.rachel.services.controller;

import com.homework.rachel.services.dao.CustomerDao;
import com.homework.rachel.services.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private CustomerDao cus_repo;

    @RequestMapping(method = RequestMethod.GET)
    public String getIndex(Model model)
    {
        List<Customer>  customers = (List<Customer>) cus_repo.findAll();
        model.addAttribute("customers", customers);
        return "index";
    }
}
