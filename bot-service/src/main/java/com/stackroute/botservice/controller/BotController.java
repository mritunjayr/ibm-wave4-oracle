package com.stackroute.botservice.controller;


import com.stackroute.botservice.domain.QueryAnsListWithConcept;
import com.stackroute.botservice.domain.QueryAnswer;
import com.stackroute.botservice.domain.QuestionDTO;
import com.stackroute.botservice.domain.SendQuery;
import com.stackroute.botservice.service.QueryService;
import com.stackroute.botservice.service.QueryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/* Created on : 27/03/2019 by gopal */

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class BotController {

    private KafkaTemplate<Object, Object> kafkaTemplate;
    private QueryService queryService;



    @Autowired
    public BotController(KafkaTemplate<Object, Object> kafkaTemplate, QueryService queryService) {
        this.kafkaTemplate = kafkaTemplate;
        this.queryService = queryService;

    }

    @PostMapping("/send/queryAnswer")
    public ResponseEntity<?> sendNewQuery(@RequestBody SendQuery sendQuery) {
        String question= sendQuery.getQueryAnswer().getQuestion();
        RestTemplate restTemplate = new RestTemplate();
        String correctedQuery = restTemplate.getForObject("http://localhost:8595/api/v1/getCorrectedQuery/" + question, String.class);
        String concepts = restTemplate.getForObject("http://localhost:8383/api/v1/concepts/" + question, String.class);
       // List<QueryAnswer> solution = restTemplate.getForObject("http://localhost:8082/api/v1/answer/" + concepts , List.class);
        QueryAnsListWithConcept queryAnsListWithConcept = new QueryAnsListWithConcept();
        queryAnsListWithConcept.setConcept(concepts);

        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setConcept(concepts);
        questionDTO.setQuestion(question);

        System.out.println("+++++++++++++++++++"+question.toString());
        kafkaTemplate.send("new_query", questionDTO);
        System.out.println("------------------"+queryAnsListWithConcept.toString());
        queryAnsListWithConcept = queryService.saveQuery(queryAnsListWithConcept);
        return new ResponseEntity<QueryAnsListWithConcept>(queryAnsListWithConcept, HttpStatus.CREATED);

    }
}

// Saving it in mongodb

//userQuery = queryService.saveQuery(userQuery);

// Sending it to manual-answer service in case not answered
// kafkaTemplate.send("new_query", userQuery.getQueryAnswer());

// Default answer for now
