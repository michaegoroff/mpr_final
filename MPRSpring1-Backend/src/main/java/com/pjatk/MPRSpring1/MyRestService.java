package com.pjatk.MPRSpring1;

import com.pjatk.MPRSpring1.CustomExceptions.CatAlreadyExistsException;
import com.pjatk.MPRSpring1.CustomExceptions.CatNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class MyRestService {

    private CatRepository repository;

    public MyRestService(CatRepository repository){
        this.repository = repository;
        this.repository.save(new Cat("Mietek",2,"White"));
        this.repository.save(new Cat("Slawek",2,"Black"));
    }


    public List<Cat> findAll(){
        List<Cat> cats = new ArrayList<>();
        Iterable<Cat> iter = this.repository.findAll();
        for (Cat i : iter){
            cats.add(i);
        }
        return cats;
    }

    public Cat findByID(Long id) {
        Optional<Cat> cat = this.repository.findById(id);
        if(cat.isPresent()){
            return cat.get();
        }
        else{
            throw new CatNotFoundException();
        }
    }

    public List<Cat> filterByName(String sequence){
        Iterable<Cat> cats = this.repository.findAll();
        List<Cat> catsList = new ArrayList<>();
        cats.forEach(catsList::add);
        catsList.removeIf(cat -> !cat.getName().contains(sequence));
        return catsList;
    }

    public void addCat(Cat cat){
        this.repository.save(cat);
    }

    public void deleteCat(Long id){
        Optional<Cat> optionalCat = this.repository.findById(id);
        if(optionalCat.isPresent()){
            this.repository.deleteById(id);
        }
        else{
            throw new CatNotFoundException();
        }
    }

    public void updateCat(Long id,Cat newcat){
        if(this.repository.findById(id).isPresent()){
            Cat cat = this.repository.findById(id).get();
            cat.setName(newcat.getName());
            cat.setAge(newcat.getAge());
            cat.setColor(newcat.getColor());
            this.repository.save(cat);
        }
        else{
            throw new CatNotFoundException();
        }
    }



}
