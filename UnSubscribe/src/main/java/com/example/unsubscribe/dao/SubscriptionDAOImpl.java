package com.example.unsubscribe.dao;

import com.example.unsubscribe.dto.PaginationDTO;
import com.example.unsubscribe.dto.SubscriptionDTO;
import com.example.unsubscribe.models.AppUser;
import com.example.unsubscribe.models.Subscription;
import com.example.unsubscribe.repositories.SubscriptionRepository;
import com.example.unsubscribe.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SubscriptionDAOImpl implements SubscriptionDAO {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public PaginationDTO<SubscriptionDTO> getAll(Integer page, Integer count) {
        Pageable pageable = PageRequest.of(page, count);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser appUser = userRepository.findByEmail(userDetails.getUsername());
        Page<Subscription> p = subscriptionRepository.findByAppUser(appUser, pageable);
        List<SubscriptionDTO> subscriptionDTOS = p.getContent().stream().map(m -> modelMapper.map(m, SubscriptionDTO.class)).collect(Collectors.toList());
        System.out.println(subscriptionDTOS);
        return new PaginationDTO(p.hasNext(), p.hasPrevious(), p.getTotalPages(), p.getNumber(), p.getTotalElements(), subscriptionDTOS);
    }

    @Override
    public List<SubscriptionDTO> getAllSubscription() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser appUser = userRepository.findByEmail(userDetails.getUsername());
        List<SubscriptionDTO> subscriptionDTOS = subscriptionRepository.findByAppUser(appUser).stream().map(i -> modelMapper.map(i, SubscriptionDTO.class)).collect(Collectors.toList());
        return subscriptionDTOS;
    }

    @Override
    public Optional<Subscription> getById(Integer id) {
        return subscriptionRepository.findById(id);
    }

    //    @Override
//    public PaginationDTO<SubscriptionDTO> searchByKeyword(String name, Integer page, Integer count) {
//        Long totalItemCount = subscriptionRepository.count();
//        Integer currentPage = page;
//        Integer pageCount = (int) Math.ceil(totalItemCount / count);
//        Boolean hasNext = pageCount > currentPage;
//        Boolean hasPrevious = currentPage > 0;
//        Integer skip = count * (currentPage - 1);
//        List<Subscription> subscriptions = subscriptionRepository.searchByName(name, skip, count);
//        System.out.println(subscriptionRepository.searchByName(name, skip, count));
//        return new PaginationDTO(hasNext, hasPrevious, pageCount, currentPage, totalItemCount, subscriptions);
//    }
    @Override
    public List<SubscriptionDTO> searchByKeyword(String name) {
        List<Subscription> subscriptions = subscriptionRepository.findAll().stream().filter(i -> i.getProductName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
        ;
        return subscriptions.stream().map(i -> modelMapper.map(i, SubscriptionDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Subscription save(SubscriptionDTO subscriptionDTO) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser appUser = userRepository.findByEmail(userDetails.getUsername());
        Subscription subscription = modelMapper.map(subscriptionDTO, Subscription.class);
        if (subscriptionDTO.getId() != null && getById(subscriptionDTO.getId()).isPresent()) {
            Subscription subDb = getById(subscriptionDTO.getId()).get();
            Integer intervalDb = subDb.getTimeInterval();
            LocalDate dateDb = subDb.getExpirationDate();
            if (!intervalDb.equals(subscriptionDTO.getTimeInterval())) {
                dateDb = LocalDate.now().plusDays(subscriptionDTO.getTimeInterval());
            }
            subscriptionDTO.setExpirationDate(dateDb);
        } else {
            subscription.setExpirationDate(LocalDate.now().plusDays(subscriptionDTO.getTimeInterval()));
            subscription.setAppUser(appUser);
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public void delete(Integer id) {
        subscriptionRepository.deleteById(id);
    }
}
