package com.maxmayev.autograph.controllers.consumer;
import com.maxmayev.autograph.domain.Message;
import com.maxmayev.autograph.services.consumer.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/rest", produces = "application/json")
@CrossOrigin(origins = "*")

public class ConsumerController {
    private ConsumerService consumerService;


    @Autowired
    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @PutMapping("/consumer/{id}")
    public ResponseEntity<?> saveOrUpdateConsumer(@RequestBody Message message, @PathVariable Long id){
        return consumerService.saveOrUpdateMessage(message,id)!= null ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/consumers/{id}")
    public void deleteConsumer(@PathVariable Long id){
        consumerService.deleteConsumer(id);
    }




}
