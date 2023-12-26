package com.pjatk.MPRSpring1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyRestController {


    private final MyRestService restService;


    @Autowired
    public MyRestController(MyRestService restService){
        this.restService = restService;
    }

    @GetMapping("cats")
    public List<Cat> getAllCats(){return this.restService.findAll();}

    @GetMapping("cats/{id}")
    public Cat getCatById(@PathVariable("id") Long id){
        return this.restService.findByID(id);
    }

    @PostMapping("cats/add")
    public void addCat(@RequestBody Cat cat){
        this.restService.addCat(cat);
    }

    @DeleteMapping("cats/delete/{id}")
    public void deleteCat(@PathVariable("id") Long id){
        this.restService.deleteCat(id);
    }

    @PutMapping("cats/put/{id}")
    public void updateCat(@PathVariable("id") Long id,@RequestBody Cat newcat){
        this.restService.updateCat(id,newcat);
    }

    @GetMapping("cats/filter/{seq}")
    public List<Cat> filterBy(@PathVariable("seq") String seq){
        return this.restService.filterByName(seq);
    }


}
