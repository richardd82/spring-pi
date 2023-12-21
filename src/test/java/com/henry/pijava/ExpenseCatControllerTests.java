package com.henry.pijava;

import com.henry.pijava.controllers.ExpenseCategoryController;
import com.henry.pijava.dto.ExpenseCategoryDTO;
import com.henry.pijava.services.ExpenseCategoryDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExpenseCatControllerTests {
    private MockMvc mockMvc;
    @Mock
    private ExpenseCategoryDAO expenseCategoryDAO;
    @InjectMocks
    private ExpenseCategoryController expenseCategoryController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(expenseCategoryController).build();
    }
    @Test
    public void testGetAllExpensesCat() throws Exception {
        List<ExpenseCategoryDTO> expensesCat = new ArrayList<>();
        expensesCat.add(new ExpenseCategoryDTO("categorie 1"));
        expensesCat.add(new ExpenseCategoryDTO("categorie 2"));
        expensesCat.add(new ExpenseCategoryDTO("categorie 3"));
        expensesCat.add(new ExpenseCategoryDTO("categorie 4"));
        expensesCat.add(new ExpenseCategoryDTO("categorie 5"));

        when(expenseCategoryDAO.readAll()).thenReturn(expensesCat);

        MvcResult result = mockMvc.perform(get("/api/expenseCats"))
                .andExpect(status().isOk())
                .andReturn();
        verify(expenseCategoryDAO, times(1)).readAll();
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
    @Test
    public void testGetExpenseCatById() throws Exception {
        ExpenseCategoryDTO expenseCat= new ExpenseCategoryDTO("categorie 5");
        expenseCat.setCategory_Id(5);
        when(expenseCategoryDAO.read(1)).thenReturn(expenseCat);
        MvcResult result = mockMvc.perform(get("/api/expenseCats/1"))
                .andExpect(status().isOk())
                .andReturn();
        verify(expenseCategoryDAO, times(1)).read(1);
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
    @Test
    public void createExpenseCatTest() throws Exception {
        ExpenseCategoryDTO newExpenseCat= new ExpenseCategoryDTO("categorie 6");
        List<ExpenseCategoryDTO> expenseCategoryDTOs = new ArrayList<>();
        expenseCategoryDTOs.add(newExpenseCat);

        String expenseJson = "[{\"categoryName\":\"categorie 6\"}]";

        MvcResult result = mockMvc.perform(post("/api/expenseCats")
                        .contentType("application/json")
                        .content(expenseJson))
                .andExpect(status().isOk())
                .andReturn();

        verify(expenseCategoryDAO, times(1)).insert(any(ExpenseCategoryDTO.class));

        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
    @Test
    public void updateExpenseCatTest() throws Exception {
        int expenseId = 1;
        ExpenseCategoryDTO updateExpenseCat= new ExpenseCategoryDTO("categorie 5");
        expenseCategoryDAO.update(updateExpenseCat, expenseId);

        String expenseJson = "{\"categoryName\":\"CATEGORIE 10\"}";
        MvcResult result = mockMvc.perform(put("/api/expenseCats/"+expenseId)
                        .contentType("application/json")
                        .content(expenseJson))
                .andExpect(status().isOk())
                .andReturn();
        verify(expenseCategoryDAO, times(1)).update(updateExpenseCat, expenseId);
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void deleteExpenseCatTest() throws Exception {
        int expenseId = 1;
        when(expenseCategoryDAO.delete(expenseId)).thenReturn(null);

        mockMvc.perform(delete("/api/expenseCats/" + expenseId))
                .andExpect(status().isOk());

        verify(expenseCategoryDAO, times(1)).delete(expenseId);
    }
}
