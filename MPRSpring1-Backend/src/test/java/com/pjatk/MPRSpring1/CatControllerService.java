package com.pjatk.MPRSpring1;

import com.pjatk.MPRSpring1.CustomExceptions.CatAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class CatControllerService {
    private MockMvc mockMvc;
    @Mock
    private MyRestService service;

    @InjectMocks
    private MyRestController controller;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(
                new MyExceptionController(),controller
        ).build();
    }

    @Test
    public void getByIdReturns200WhenCatIsPresent() throws Exception {
        Cat cat = new Cat("Mietek",2,"White");
        Mockito.when(service.findByID(1L)).thenReturn(cat);
        mockMvc.perform(MockMvcRequestBuilders.get("/cats/1"))
                .andExpect(jsonPath("$.name").value("Mietek"))
                .andExpect(jsonPath("$.age").value(2))
                .andExpect(jsonPath("$.color").value("White"));
    }

    @Test
    public void check400IsReturnedWhenCatAlreadyThere() throws Exception {
        Cat cat = new Cat("Mietek", 2, "White");
        Mockito.doThrow(new CatAlreadyExistsException()).when(service).addCat(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/cats/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Mietek\", \"age\": 2 , \"color\": \"White\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}