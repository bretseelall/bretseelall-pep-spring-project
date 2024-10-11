package com.example.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.*;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {
    /*
     * POST localhost:8080/register FINISHED
     * POST localhost:8080/login FINISHED
     * POST localhost:8080/messages FINISHED
     * GET localhost:8080/messages FINISHED
     * GET localhost:8080/messages/{messageId} FINISHED
     * DELETE localhost:8080/messages/{messageId}
     * PATCH localhost:8080/messages/{messageId}
     * GET localhost:8080/accounts/{accountId}/messages
     */
    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
      this.accountService = accountService;
      this.messageService = messageService;
    }

     @PostMapping("/register")
     public ResponseEntity<Account> postNewUser(@RequestBody Account account){
      Account addedAccount = accountService.postNewUser(account);
      if(addedAccount != null)
        return ResponseEntity.status(200).body(account);
      else
        return ResponseEntity.status(409).body(account);
     }

     @PostMapping("/login")
     public ResponseEntity<Account> postUserLogin(@RequestBody Account account){
      Account loginAccount = accountService.postUserLogin(account);
      if(loginAccount != null)
        return ResponseEntity.status(200).body(loginAccount);
      else
        return ResponseEntity.status(401).body(loginAccount);
     }

     @PostMapping("/messages")
     public ResponseEntity<Message> postNewUserMessage(@RequestBody Message message){
      Message newMessage = messageService.postNewUserMessage(message);
      if(newMessage != null)
        return ResponseEntity.status(200).body(newMessage);
      else
        return ResponseEntity.status(400).body(newMessage);
     }

     @GetMapping("/messages")
     public ResponseEntity<List<Message>> getAllMessages(){
      return ResponseEntity.status(200).body(messageService.getAllMessages());
     }

     @GetMapping("/messages/{messageId}")
     public ResponseEntity<Message> getMessageById(@PathVariable int messageId){
      System.out.println("HWHWHWHWHWHWHWHWHWHWHWHWHW ---------- The id is: " + messageId);
      return ResponseEntity.status(200).body(messageService.getMessageById(messageId));
     }

}
