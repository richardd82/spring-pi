package com.henry.pijava.controllers;



import com.henry.pijava.dto.ExpenseDTO;
import com.henry.pijava.services.ExpenseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private ExpenseDAO expenseDAO;
    public ExpenseController(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }

    @GetMapping()
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses() {

        return ResponseEntity.ok(expenseDAO.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable int id) {
//        System.out.println("id: " + id);
//        System.out.println(expenseDAO.read(id));
        return ResponseEntity.ok(expenseDAO.read(id));
    }

    @PostMapping()
    public ResponseEntity<List<ExpenseDTO>> createExpense(@RequestBody List<ExpenseDTO> expenseDTOs) {
        try {
            List<ExpenseDTO> insertedExpenses = new ArrayList<>();
            for (ExpenseDTO expenseDTO : expenseDTOs) {
                expenseDAO.insert(expenseDTO);
                insertedExpenses.add(expenseDTO);
            }
            return ResponseEntity.ok(insertedExpenses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDTO> updateExpense(@RequestBody ExpenseDTO expenseDTO, @PathVariable Integer id) {
        expenseDAO.update(expenseDTO, id);
        return ResponseEntity.ok(expenseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Integer id) {
        expenseDAO.delete(id);
        System.out.println("Deleted expense with id: " + id + " successfully.");
        return ResponseEntity.ok("Deleted expense with id: " + id + " successfully.");
    }


}
