package com.example.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
      return ResponseEntity.status(200).body(messageService.getMessageById(messageId));
     }

     @DeleteMapping("/messages/{messageId}")
     public ResponseEntity deleteMessageById(@PathVariable int messageId){
      if(messageService.deleteMessageById(messageId) == 1)
      {
        return ResponseEntity.status(200).body(1);
      }
      else
        return ResponseEntity.status(200).body("");
     }

     @PatchMapping("/messages/{messageId}")
     public ResponseEntity updateMessageById(@PathVariable int messageId, @RequestBody Message message){
      int val = messageService.updateMessageById(messageId, message.getMessageText());
      if(val == 0)
        return ResponseEntity.status(400).body(0);
      else
        return ResponseEntity.status(200).body(1);
     }

     @GetMapping("/accounts/{accountId}/messages")
     public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable int accountId){
      return ResponseEntity.status(200).body(messageService.getMessagesByUser(accountId));
     }

}
