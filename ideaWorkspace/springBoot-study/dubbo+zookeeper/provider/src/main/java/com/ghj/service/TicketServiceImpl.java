package com.ghj.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author 86187
 */
@Component
@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public String tackTicket() {
        return "《狂神说Java》";
    }
}