package com.ghj.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author 86187
 */
@Service
public class UserService {
    @Reference
    TicketService ticketService;

    public void buy(){
        System.out.println("拿到了+" + ticketService.tackTicket());
    }
}
