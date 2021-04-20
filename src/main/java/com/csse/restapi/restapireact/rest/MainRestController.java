package com.csse.restapi.restapireact.rest;

import com.csse.restapi.restapireact.entities.CardTasks;
import com.csse.restapi.restapireact.entities.Cards;
import com.csse.restapi.restapireact.entities.Items;
import com.csse.restapi.restapireact.entities.Users;
import com.csse.restapi.restapireact.jwt.JwtTokenGenerator;
import com.csse.restapi.restapireact.models.JwtRequest;
import com.csse.restapi.restapireact.models.UserDTO;
import com.csse.restapi.restapireact.models.UserDTO2;
import com.csse.restapi.restapireact.repositories.CardRepository;
import com.csse.restapi.restapireact.repositories.TaskRepository;
import com.csse.restapi.restapireact.services.ItemService;
import com.csse.restapi.restapireact.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api")
public class MainRestController {

    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            Users user = (Users) authentication.getPrincipal();
            return user;
        }
        return null;
    }

    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/allitems")
    public ResponseEntity<?> getAllItems(){

        List<Items> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @PostMapping(value = "/additem")
    public ResponseEntity<?> addItem(@RequestBody Items item){
        itemService.addItem(item);
        return ResponseEntity.ok(item);
    }

    @PutMapping(value = "/saveitem")
    public ResponseEntity<?> saveItem(@RequestBody Items item){
        itemService.saveItem(item);
        return ResponseEntity.ok(item);
    }

    @GetMapping(value = "/getitem/{id}")
    public ResponseEntity<?> getItem(@PathVariable(name = "id") Long id){
        Items item = itemService.getItem(id);
        if(item!=null){
            return ResponseEntity.ok(item);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/deleteitem")
    public ResponseEntity<?> deleteItem(@RequestBody Items item){
        Items checkItem = itemService.getItem(item.getId());
        if(checkItem!=null){
            itemService.deleteItem(checkItem);
            return ResponseEntity.ok(checkItem);
        }
        return ResponseEntity.ok(item);
    }



    @GetMapping(value = "/allcards")
    public ResponseEntity<?> getAllCards(){


        Users user = getUser();


        List<Cards> cards = itemService.getAllCards ();
        List<Cards> cards2 = new ArrayList<> ();
        for (Cards r: cards
        ) {
            if(r.getUser ().getId ().equals (user.getId ())){
                cards2.add (r);
            }

        }

        return new ResponseEntity<>(cards2, HttpStatus.OK);
    }

    @GetMapping(value = "/search/{sname}")
    public ResponseEntity<?> search(@PathVariable(name = "sname") String sname){

        List<Cards> cards = itemService.getAllCards ();
        List<Cards> cards2 = new ArrayList<> ();
        for (Cards c: cards
             ) {
            if(c.getName ().contains (sname)){
                cards2.add (c);
            }
        }

        return new ResponseEntity<>(cards2, HttpStatus.OK);
    }


    @PostMapping(value = "/addcard")
    public ResponseEntity<?> addCard(@RequestBody Cards card) {
        Users user = getUser ();

        card.setUser(user);
        System.out.println (user);
        itemService.addCard(card);
        return ResponseEntity.ok(card);
    }

    @PutMapping(value = "/savecard")
    public ResponseEntity<?> saveCard(@RequestBody Cards item){
        itemService.saveCard (item);
        return ResponseEntity.ok(item);
    }

    @GetMapping(value = "/getcardedit/{id}")
    public ResponseEntity<?> getCard(@PathVariable(name = "id") Long id){
        Cards item = itemService.getCard (id);
        if(item!=null){
            return ResponseEntity.ok(item);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/gettasks/{id}")
    public ResponseEntity<?> getTasks(@PathVariable(name = "id") Long id){



         List<CardTasks> items = itemService.getAllTasks ();




        List<CardTasks> items2 = new ArrayList<> ();
        for (CardTasks r: items
             ) {
            if(r.getCard ().getId ().equals (id)){
                items2.add (r);
            }

        }

        if(items2!=null){
            return ResponseEntity.ok(items2);
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(value = "/deletecard")
    public ResponseEntity<?> deleteCard(@RequestBody Cards item){
        Cards checkItem = itemService.getCard (item.getId());
        if(checkItem!=null){
            itemService.deleteCard (checkItem);
            return ResponseEntity.ok(checkItem);
        }
        return ResponseEntity.ok(item);
    }

    @PutMapping("/cardTaskDone/{id}")
    public CardTasks updateCardTask(@PathVariable Long id){
        CardTasks old = itemService.getTask(id);
        old.setDone(!old.isDone());
        return itemService.saveTask(old);
    }
    @PostMapping("/addCardTask/{id}")
    public CardTasks addTask(@PathVariable Long id, @RequestBody CardTasks cardTask){

        cardTask.setCard(itemService.getCard (id));
        return itemService.saveTask(cardTask);
    }

    @GetMapping(value = "/profile")
    public ResponseEntity<?> profilePage(){
        Users user = getUser();
        System.out.println (user.getEmail ());
        return new ResponseEntity<>(new UserDTO (user.getId(), user.getEmail(),user.getFullName (), user.getRoles()), HttpStatus.OK);
    }



    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody Users user){
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }
    @PutMapping(value = "/updProfile")
    public ResponseEntity<?> updProfile(@RequestBody UserDTO userd){
        Users user = userService.getUserByE (userd.getEmail ());
        user.setFullName (userd.getFullName ());
        userService.saveUser (user);
        return ResponseEntity.ok(userd);
    }
    @Autowired
    PasswordEncoder passwordEncoder;

    @PutMapping("/changePass")
    public ResponseEntity<?> changePassword(@RequestBody UserDTO2 request) {
        Users existedUser = userService.getUserByE (request.getEmail());

        if (passwordEncoder.matches(request.getOld (), existedUser.getPassword())) {
            if (request.getNeww ().equals (request.getRenew ())) {

                existedUser.setPassword (passwordEncoder.encode (request.getNeww ()));
                userService.saveUser (existedUser);
                return ResponseEntity.ok (existedUser);

            }

        }

        return ResponseEntity.badRequest().body("ERROR");
    }

}