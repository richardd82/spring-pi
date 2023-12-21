package com.henry.pijava;

import com.henry.pijava.controllers.ExpenseController;
import com.henry.pijava.dto.ExpenseDTO;
import com.henry.pijava.services.ExpenseDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class ExpenseControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ExpenseDAO expenseDAO;
    @InjectMocks
    private ExpenseController expenseController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(expenseController).build();
    }
    @Test
    public void testGetAllExpenses() throws Exception {
        List<ExpenseDTO> expenses = new ArrayList<>();
        expenses.add(new ExpenseDTO(100, "test1", 1));
        expenses.add(new ExpenseDTO(200, "test2", 2));
        expenses.add(new ExpenseDTO(300, "test3", 3));
        expenses.add(new ExpenseDTO(400, "test4", 4));
        expenses.add(new ExpenseDTO(500, "test5", 1));

        when(expenseDAO.readAll()).thenReturn(expenses);

        MvcResult result = mockMvc.perform(get("/api/expense"))
                .andExpect(status().isOk())
                .andReturn();
        verify(expenseDAO, times(1)).readAll();
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
    @Test
    public void testGetExpenseById() throws Exception {
        ExpenseDTO expense = new ExpenseDTO(100, "test1", 1);
        expense.setId(1);
        when(expenseDAO.read(1)).thenReturn(expense);
        MvcResult result = mockMvc.perform(get("/api/expense/1"))
                .andExpect(status().isOk())
                .andReturn();
        verify(expenseDAO, times(1)).read(1);
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
    @Test
    public void createExpenseTest() throws Exception {
        ExpenseDTO newExpense = new ExpenseDTO(600, "test Created", 5);
        List<ExpenseDTO> expenseDTOs = new ArrayList<>();
        expenseDTOs.add(newExpense);

        String expenseJson = "[{\"amount\":600.0,\"description\":\"test Created\",\"categoryId\":5}]";

        MvcResult result = mockMvc.perform(post("/api/expense")
                .contentType("application/json")
                .content(expenseJson))
                .andExpect(status().isOk())
                .andReturn();

        verify(expenseDAO, times(1)).insert(any(ExpenseDTO.class));

        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }
    @Test
    public void updateExpenseTest() throws Exception {
        int expenseId = 1;
        ExpenseDTO updateExpense = new ExpenseDTO(200, "Test no Updated", 3);
        expenseDAO.update(updateExpense, expenseId);

        String expenseJson = "{\"id\":1,\"amount\":200.0,\"description\":\"Test Updated\",\"categoryId\":3}";
               MvcResult result = mockMvc.perform(put("/api/expense/"+expenseId)
                .contentType("application/json")
                .content(expenseJson))
                .andExpect(status().isOk())
                .andReturn();
        verify(expenseDAO, times(1)).update(updateExpense, expenseId);
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println(contentAsString);
    }

    @Test
    public void deleteExpenseTest() throws Exception {
        int expenseId = 1;
        when(expenseDAO.delete(expenseId)).thenReturn(null);

        mockMvc.perform(delete("/api/expense/" + expenseId))
                .andExpect(status().isOk());

        verify(expenseDAO, times(1)).delete(expenseId);
    }

}
