package com.sandi.authorizationsvc.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "CUSTOMER-SVC")
public interface UserClient {

    @RequestMapping("/api/v1/customer/{customerName}")
    public String getCustomerbyCustomerName(@PathVariable("customerName") String customerName);

}
