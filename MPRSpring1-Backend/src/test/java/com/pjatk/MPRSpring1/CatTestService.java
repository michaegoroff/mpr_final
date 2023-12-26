package com.pjatk.MPRSpring1;

import org.hibernate.annotations.AttributeAccessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatTestService {
    @Mock
    private CatRepository repository;
    private AutoCloseable openMocks;
    private MyRestService catService;

    @BeforeEach
    public void init(){
        openMocks = MockitoAnnotations.openMocks(this);
        catService = new MyRestService(repository);
    }

    @AfterEach
    public void tearDown() throws Exception{
        openMocks.close();
    }



    @Test
    public void SaveSaves(){
        String name = "stefan";
        Integer age = 4;
        String color = "Yellow";
        Cat cat = new Cat(name,age,color);
        ArgumentCaptor<Cat> captor = ArgumentCaptor.forClass(Cat.class);
        Mockito.when(repository.save(captor.capture())).thenReturn(cat);
        catService.addCat(cat);
        Mockito.verify(repository,Mockito.times(1)).save(Mockito.any());
        Cat catFromSaveCall = captor.getValue();
        assertEquals(cat,catFromSaveCall);
    }

    @Test
    public void TestFilter(){
        String sequence = "awek";
        List<Cat> expected = new ArrayList<>();
        Cat cat1 = new Cat("Slawek",2,"Black");
        Cat cat2 = new Cat("awek",4,"Red");
        Cat cat3 = new Cat("Boris",5,"Yellow");
        expected.add(cat2);
        List<Cat> allCats= List.of(cat1,cat2,cat3);
        Mockito.when(repository.findAll()).thenReturn(allCats);
        List<Cat> results = catService.filterByName(sequence);
        assertEquals(expected,results);
    }
}
