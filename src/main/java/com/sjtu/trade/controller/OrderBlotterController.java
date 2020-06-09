package com.sjtu.trade.controller;

import com.sjtu.trade.dto.LoginRequest;
import com.sjtu.trade.dto.NameDTO;
import com.sjtu.trade.dto.OrderBlotterDTO;
import com.sjtu.trade.serviceimpl.OrderBlotterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@RestController
@Controller
public class OrderBlotterController {

    private final OrderBlotterService orderBlotterService;

    OrderBlotterController(OrderBlotterService orderBlotterService) {
        this.orderBlotterService = orderBlotterService;
    }

    @MessageMapping("/orderBlotter")
    @SendToUser("/topic/orderBlotter")
    public String greeting(OrderBlotterDTO message, Principal principal) throws Exception {
        System.out.print("Received greeting message "+ message.getFutureName() +" from "+principal.getName());
        orderBlotterService.addUsers(new NameDTO(principal.getName(),message.getFutureName()));
        Thread.sleep(1000); // simulated delay
        return "Hello, " + HtmlUtils.htmlEscape(message.getFutureName()) + "!";
    }

}
