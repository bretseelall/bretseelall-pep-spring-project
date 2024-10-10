package com.example.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.*;
import com.example.service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {
    /*
     * POST localhost:8080/register
     * POST localhost:8080/login
     * POST localhost:8080/messages
     * GET localhost:8080/messages
     * GET localhost:8080/messages/{messageId}
     * DELETE localhost:8080/messages/{messageId}
     * PATCH localhost:8080/messages/{messageId}
     * GET localhost:8080/accounts/{accountId}/messages
     */
    private AccountService accountService;

    public SocialMediaController(AccountService accountService){
      this.accountService = accountService;
    }

     @PostMapping("/register")
     public ResponseEntity<Account> postNewUser(@RequestBody Account account){
      Account addedAccount = accountService.postNewUser(account);
      if(addedAccount != null)
        return ResponseEntity.status(200).body(account);
      else
        return ResponseEntity.status(409).body(account);
     }

}
