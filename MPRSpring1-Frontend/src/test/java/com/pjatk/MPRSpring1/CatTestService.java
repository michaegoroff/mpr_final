package com.pjatk.MPRSpring1;

import org.hibernate.annotations.AttributeAccessor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class CatTestService {

    private MockMvc mockMvc;
    private AutoCloseable openMocks;
    private MyRestService catService;

    @BeforeEach
    public void init(){
        openMocks = MockitoAnnotations.openMocks(this);
        catService = new MyRestService();
    }

    @AfterEach
    public void tearDown() throws Exception{
        openMocks.close();
    }

}

