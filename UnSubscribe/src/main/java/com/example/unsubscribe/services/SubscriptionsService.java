package com.example.unsubscribe.services;

import com.example.unsubscribe.dto.PaginationDTO;
import com.example.unsubscribe.dto.SubscriptionDTO;

import java.util.List;
import java.util.Optional;

public interface SubscriptionsService {
    PaginationDTO<SubscriptionDTO> getAll(Integer page, Integer count);

    Optional<SubscriptionDTO> getById(Integer id);
    List<SubscriptionDTO> getAllSubscription();

    List<SubscriptionDTO> searchByKeyword(String name);

    SubscriptionDTO save(SubscriptionDTO subscriptionDTO);

    void delete(Integer id);
}
