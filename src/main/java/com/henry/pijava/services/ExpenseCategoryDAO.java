package com.henry.pijava.services;


import com.henry.pijava.dto.ExpenseCategoryDTO;

public interface ExpenseCategoryDAO extends DAO<ExpenseCategoryDTO, Integer>{

    void update(ExpenseCategoryDTO e, Integer integer);
}
