package com.henry.pijava.dto;

public class ExpenseCategoryDTO implements Comparable<ExpenseCategoryDTO> {
    private int category_Id;
    private String categoryName;

    public ExpenseCategoryDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    public ExpenseCategoryDTO() {

    }

    public int getCategory_Id() {

        return category_Id;
    }

    public void setCategory_Id(int category_Id) {

        this.category_Id = category_Id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String setCategoryName(String categoryName) {

        this.categoryName = categoryName;
        return categoryName;
    }
    @Override
    public int compareTo(ExpenseCategoryDTO oC) {
        // Comparar por category_Id
        return Integer.compare(this.category_Id, oC.category_Id);
    }
}
