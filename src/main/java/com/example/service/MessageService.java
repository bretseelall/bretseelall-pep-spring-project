package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message postNewUserMessage(Message message){
        if(message.getMessageText().length() <= 255 && !message.getMessageText().isBlank() && accountRepository.existsById(message.getPostedBy()))
            return messageRepository.save(message);
        else
            return null;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int id){
        if(messageRepository.existsById(id))
            return messageRepository.findById(id).get();
        else
            return null;
    }

    public int deleteMessageById(int id){
        if(messageRepository.existsById(id)){
            messageRepository.deleteById(id);
            return 1;
        }
        return 0;
            
    }

    public int updateMessageById(int id, String messageText){
        if(messageRepository.existsById(id) && !messageText.isBlank() && messageText.length() <= 255){
            Message updatedMessage = messageRepository.findById(id).get();
            updatedMessage.setMessageText(messageText);
            messageRepository.save(updatedMessage);
            return 1;
        }
        return 0;
    }

    public List<Message> getMessagesByUser(int id){
        return messageRepository.findMessagesByUser(id);
    }
}
