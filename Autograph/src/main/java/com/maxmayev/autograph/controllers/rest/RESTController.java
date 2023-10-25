package com.maxmayev.autograph.controllers.rest;
import com.maxmayev.autograph.domain.Message;
import com.maxmayev.autograph.services.rest.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/rest", produces = "application/json")
@CrossOrigin(origins = "*")

public class RESTController {
    private RestService restService;


    @Autowired
    public RESTController(RestService restService) {
        this.restService = restService;
    }

    @PutMapping("/consumer/{id}")
    public ResponseEntity<?> saveOrUpdateConsumer(@RequestBody Message message, @PathVariable Long id){
        return restService.saveOrUpdateMessage(message,id)!= null ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/consumers/{id}")
    public void deleteConsumer(@PathVariable Long id){
        restService.deleteConsumer(id);
    }




}
