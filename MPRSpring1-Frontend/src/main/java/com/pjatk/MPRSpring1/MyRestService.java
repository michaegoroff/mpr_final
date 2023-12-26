package com.pjatk.MPRSpring1;

import com.pjatk.MPRSpring1.CustomExceptions.CatAlreadyExistsException;
import com.pjatk.MPRSpring1.CustomExceptions.CatNotFoundException;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class MyRestService {

    private final String API_URL = "http://localhost:8080/";
    RestClient restClient;

    public MyRestService(){
        restClient = RestClient.create();
    }


    public Iterable<Cat> findAll(){
        List<Cat> cats = restClient
                .get()
                .uri(API_URL + "cats")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        return  cats;

    }

    public Cat findByID(Long id) {
        Cat cat = restClient
                .get()
                .uri(API_URL+"cats/" + id)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        if(cat == null){
            throw new CatNotFoundException();
        }
        return cat;
    }

    public List<Cat> filterByName(String sequence){
        List<Cat> cats = restClient
                .get()
                .uri(API_URL + "cats/filter/" + sequence)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        cats.removeIf(cat -> !cat.getName().equals(sequence));
        return cats;
    }

    public void addCat(Cat cat){
        restClient.post()
                .uri(API_URL + "cats/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(cat)
                .retrieve()
                .toBodilessEntity();
    }

    public void deleteCat(Long id){
        restClient.delete()
                .uri(API_URL + "cats/delete/" + id)
                .retrieve()
                .toBodilessEntity();
    }

    public void updateCat(Long id,Cat newcat){
        restClient.put()
                .uri(API_URL + "cats/put/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(newcat)
                .retrieve()
                .toBodilessEntity();
    }

}
