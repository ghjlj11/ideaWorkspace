package com.ghj.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Reference
    TicketService ticketService;

    public void buy(){
        System.out.println("拿到了+" + ticketService.tackTicket());
    }
}
