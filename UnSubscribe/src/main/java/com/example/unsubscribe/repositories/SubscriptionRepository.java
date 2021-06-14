package com.example.unsubscribe.repositories;

import com.example.unsubscribe.models.AppUser;
import com.example.unsubscribe.models.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    Page<Subscription> findByAppUser(AppUser appUser, Pageable pageable);
    List<Subscription> findByAppUser(AppUser appUser);


}
