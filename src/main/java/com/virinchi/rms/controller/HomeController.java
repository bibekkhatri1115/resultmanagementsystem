package com.virinchi.rms.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.virinchi.rms.controller.core.SecuredController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class HomeController extends SecuredController {

    private final static String QUEUE_NAME = "Message_Queue";

    public HomeController() {
        pageTitle = "Dashboard";
        activeMenu = "dashboard";
    }

    @GetMapping()
    public String index(Model model) {
        return "redirect:/mark/quick";
    }

    @GetMapping("/send")
    public void sender(@RequestParam("message")String message) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false,
                    null);
            channel.basicPublish("", QUEUE_NAME, null,
                    message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception exe) {
            exe.printStackTrace();
        }
    }
}
