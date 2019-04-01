package com.stackroute.botservice.controller;


import com.stackroute.botservice.domain.QueryData;
import com.stackroute.botservice.service.QueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class BotController {


    KafkaTemplate<Object,Object> kafkaTemplate;


    private QueryServiceImpl queryService;

    public BotController() {
    }

    @Autowired
    public BotController(KafkaTemplate<Object, Object> kafkaTemplate, QueryServiceImpl queryService) {
        this.kafkaTemplate = kafkaTemplate;
        this.queryService = queryService;
    }


    @PostMapping("/send/query")
    public ResponseEntity<?> sendNewQuery(@RequestBody QueryData queryData) {
        QueryData question1=null;
        question1 = queryService.saveQuery(queryData.);
            kafkaTemplate.send("new_query", question1);
            return new ResponseEntity<QueryData>(question1, HttpStatus.CREATED);

    }

    @GetMapping("/query")
    public ResponseEntity<?> getNewAnswer(@RequestBody QueryData query){
        return new ResponseEntity<QueryData>(query, HttpStatus.OK);
    }

}
