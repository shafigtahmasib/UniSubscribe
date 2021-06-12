package com.example.unsubscribe.services;

import com.example.unsubscribe.models.Subscription;
import com.example.unsubscribe.repositories.SubscriptionRepository;
import com.example.unsubscribe.repositories.UserRepository;
import com.example.unsubscribe.utils.DateUtil;
import com.example.unsubscribe.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class DateSchedule {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void checkDate() throws InterruptedException, ParseException {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        for(Subscription x: subscriptions){
            if(DateUtil.remainingDays(x.getExpirationDate())<=5) {
                System.out.println(x.getExpirationDate());
                SendEmail.send(x.getAppUser().getEmail(),x.getAppUser().getName(),x.getProductName(),DateUtil.remainingDays(x.getExpirationDate()));
                Thread.sleep(400);
                if(DateUtil.remainingDays(x.getExpirationDate())==0){
                   x.setExpirationDate(x.getExpirationDate().plusDays(30));
                   subscriptionRepository.save(x);
                }
            }
        }
    }
}