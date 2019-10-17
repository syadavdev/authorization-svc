package com.sandi.authorizationsvc.service.client;

import com.sandi.authorizationsvc.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "CUSTOMER-SVC")
public interface CustomerClient {

    @RequestMapping("/api/v1/customer/{customerName}")
    public Customer getCustomerbyCustomerName(@PathVariable("customerName") String customerName);

}
