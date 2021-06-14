package com.example.unsubscribe.services;

import com.example.unsubscribe.dao.SubscriptionDAO;
import com.example.unsubscribe.dto.PaginationDTO;
import com.example.unsubscribe.dto.SubscriptionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionsServiceImpl implements SubscriptionsService {
    @Autowired
    SubscriptionDAO subscriptionDAO;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public PaginationDTO<SubscriptionDTO> getAll(Integer page, Integer count) {
        return subscriptionDAO.getAll(page, count);
    }
    public List<SubscriptionDTO> getAllSubscription() {
        return subscriptionDAO.getAllSubscription();
    }

    @Override
    public Optional<SubscriptionDTO> getById(Integer id) {
        return Optional.of(modelMapper.map(subscriptionDAO.getById(id), SubscriptionDTO.class));
    }

    @Override
    public List<SubscriptionDTO> searchByKeyword(String name) {
        return subscriptionDAO.searchByKeyword(name);
    }

    @Override
    public SubscriptionDTO save(SubscriptionDTO subscriptionDTO) {
        return modelMapper.map(subscriptionDAO.save(subscriptionDTO), SubscriptionDTO.class);
    }

    @Override
    public void delete(Integer id) {
        subscriptionDAO.delete(id);
    }
}
