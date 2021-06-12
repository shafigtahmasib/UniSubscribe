package com.example.unsubscribe.dao;

import com.example.unsubscribe.dto.PaginationDTO;
import com.example.unsubscribe.dto.SubscriptionDTO;
import com.example.unsubscribe.models.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionDAO {
    PaginationDTO<SubscriptionDTO> getAll(Integer page, Integer count);

    List<SubscriptionDTO> getAllSubscription();

    Optional<Subscription> getById(Integer id);

    List<SubscriptionDTO> searchByKeyword(String name);

    Subscription save(SubscriptionDTO subscriptionDTO);

    void delete(Integer id);
}
