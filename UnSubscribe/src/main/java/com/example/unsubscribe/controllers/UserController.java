package com.example.unsubscribe.controllers;

import com.example.unsubscribe.dto.PaginationDTO;
import com.example.unsubscribe.dto.SubscriptionDTO;
import com.example.unsubscribe.models.Subscription;
import com.example.unsubscribe.services.SubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    SubscriptionsService subService;

//    @GetMapping("/subscriptions")
//    public ResponseEntity<PaginationDTO<Subscription>> getSubs(@RequestParam Integer page, @RequestParam Integer count) {
//        return new ResponseEntity(subService.getAll(page, count), HttpStatus.OK);
//    }
    @GetMapping("/subscriptions")
    public ResponseEntity<List<Subscription>> getSubs() {
        return new ResponseEntity(subService.getAllSubscription(), HttpStatus.OK);
    }

    @PostMapping ("/subscriptions/search")
    public ResponseEntity<PaginationDTO<Subscription>> deleteSubs(@RequestParam String keyword) {
        return new ResponseEntity(subService.searchByKeyword(keyword), HttpStatus.OK);
    }

    @PostMapping("/subscriptions")
    public ResponseEntity<PaginationDTO<Subscription>> addSubs(@RequestBody SubscriptionDTO subscriptionDTO) {
        return new ResponseEntity(subService.save(subscriptionDTO), HttpStatus.OK);
    }
    @DeleteMapping("/subscriptions/{id}")
    public ResponseEntity deleteSubs(@PathVariable Integer id) {
        subService.delete(id);
        System.out.println(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

