package com.henry.pijava.controllers;



import com.henry.pijava.dto.ExpenseCategoryDTO;
import com.henry.pijava.services.ExpenseCategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/expenseCats")
public class ExpenseCategoryController {

    private ExpenseCategoryDAO expenseCategoryDAO;
    public ExpenseCategoryController(ExpenseCategoryDAO expenseCategoryDAO) {
        this.expenseCategoryDAO = expenseCategoryDAO;
    }

    @GetMapping()
    public ResponseEntity<List<ExpenseCategoryDTO>> getAllExpenseCategories() {
        return ResponseEntity.ok(expenseCategoryDAO.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseCategoryDTO> getExpenseCategoryById(@PathVariable int id) {
        return ResponseEntity.ok(expenseCategoryDAO.read(id));
    }

    @PostMapping()
    public ResponseEntity<List<ExpenseCategoryDTO>> createExpenseCategory(@RequestBody List<ExpenseCategoryDTO> expenseCategoryDTOs) {
        try{
            List<ExpenseCategoryDTO> insertedExpenseCategories = new ArrayList<>();
            for(ExpenseCategoryDTO expenseCategoryDTO : expenseCategoryDTOs){
                expenseCategoryDAO.insert(expenseCategoryDTO);
                insertedExpenseCategories.add(expenseCategoryDTO);
            }
            return ResponseEntity.ok(insertedExpenseCategories);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseCategoryDTO> updateExpenseCategory(@RequestBody ExpenseCategoryDTO expenseCategoryDTO, @PathVariable Integer id) {
        expenseCategoryDAO.update(expenseCategoryDTO, id);
        return ResponseEntity.ok(expenseCategoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteExpenseCategory(@PathVariable Integer id) {
        expenseCategoryDAO.delete(id);
        return ResponseEntity.ok(id);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleJsonParseException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("Error en el formato de los datos enviados: " + e.getMessage());
    }
}
