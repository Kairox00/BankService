//package com.indigo.bankservice.broker.consumers;
//
//import com.indigo.bankservice.broker.MQConstants;
//import com.indigo.bankservice.broker.Publisher;
//import com.indigo.bankservice.services.InventoryService;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//
//@Component
//public class MockInventoryConsumer implements IConsumer{
//
//    @Autowired
//    InventoryService service;
//
//    @Autowired
//    Publisher publisher;
//
//    @Autowired
//    MQConstants mqConstants;
//
//
//
//    @Transactional
//    @RabbitListener(queues = {"#{mqConstants.inv_req}"})
//    public void consume(HashMap<String, String> message) {
//        HashMap<String, Object> response = new HashMap<>(message);
//        try{
//            int quantity = Integer.parseInt(message.get("quantity"));
//            String command = message.get("command");
//            if(command.equals("INCREASE_INVENTORY")){
////                service.incInv(message.get("items"));
//                LOGGER.info("increased inventory");
//                response.put("status", "SUCCESS");
//                publisher.publish(mqConstants.inv_res, response);
//            }
//            else{
////                service.decInv(message.get("items"));
//                LOGGER.info("decreased inventory");
//                response.put("status", "SUCCESS");
//                publisher.publish(mqConstants.inv_res, response);
//            }
//        }
//        catch (Exception e){
//            response.put("status", e.getMessage());
//            publisher.publish(mqConstants.inv_res, response);
//        }
//
//    }
//}
