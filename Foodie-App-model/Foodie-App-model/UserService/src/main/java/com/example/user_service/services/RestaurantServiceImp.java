/*package com.example.user_service.services;

import com.example.user_service.domain.Restaurant;
import com.example.user_service.repository.RestaurantRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImp implements IRestaurantService{
    private List<Restaurant> restaurants = new ArrayList<>();
    private RestaurantRepository restaurantRepository;
    //private UserRepository userRepository;
    @Autowired
    public RestaurantServiceImp(RestaurantRepository restaurantRepository)
    {
        this.restaurantRepository=restaurantRepository;
    }
    @Autowired
    private ObjectMapper mapper;
    /*@Autowired
    public RestaurantServiceImp(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }*/
    /*@PostConstruct
    public void loadRestaurants() {
        //converting Java objects to JSON and vice versa
        //ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Restaurant>> typeReference = new TypeReference<>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/restaurant.json");

        try {
            restaurants = mapper.readValue(inputStream, typeReference);
            restaurantRepository.saveAll(restaurants);
            //userRepository.saveAll(restaurants);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Restaurant> findByLocationAndNameOrCuisine(String location, String search) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getLocation().equalsIgnoreCase(location) &&
                        (restaurant.getName().equalsIgnoreCase(search) || restaurant.getCuisine().equals(search)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> findByLocation(String location) {
        return restaurants.stream()
                .filter(restaurant->restaurant.getLocation().equalsIgnoreCase(location))
                .collect(Collectors.toList());
    }
}*/