package com.henry.pijava.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.henry.pijava.Connections.ConnDB;
import com.henry.pijava.dto.ExpenseCategoryDTO;
import com.henry.pijava.services.ExpenseCategoryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public  class ExpenseCategoryDAOImpl implements ExpenseCategoryDAO {
    @Autowired
    private ConnDB connDB;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public ExpenseCategoryDTO read(Integer key) {
        try {
            // Obtén la conexión desde la clase ConnDB
            connection = ConnDB.getConnection();

            // Define la consulta SQL para insertar un nuevo gasto
            String sql = "SELECT * FROM EXPENSE_CAT WHERE CATEGORY_ID = ?";

            // Prepara la declaración SQL con parámetros
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, key);

            //Obtener el resultado de la consulta
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ExpenseCategoryDTO expenseCategoryDTO = new ExpenseCategoryDTO();
                expenseCategoryDTO.setCategory_Id(resultSet.getInt("CATEGORY_ID"));
                expenseCategoryDTO.setCategoryName(resultSet.getString("CATEGORY_NAME"));

                return expenseCategoryDTO;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar el gasto en la base de datos.", ex);
        } finally {
            // Cierra recursos
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public List<ExpenseCategoryDTO> readAll() {
        try {
            // Obtén la conexión desde la clase ConnDB
            connection = ConnDB.getConnection();

            // Define la consulta SQL para insertar un nuevo gasto
            String sql = "SELECT * FROM EXPENSE_CAT ";

            // Prepara la declaración SQL con parámetros
            preparedStatement = connection.prepareStatement(sql);


            //Obtener el resultado de la consulta
            resultSet = preparedStatement.executeQuery();

            List<ExpenseCategoryDTO> categoryExpenses = new ArrayList<>();

            // Procesa los resultados
            while (resultSet.next()) {
                int categoryId = resultSet.getInt("CATEGORY_ID");
                String categoryName = resultSet.getString("CATEGORY_NAME");

                // Puedes obtener otros campos según sea necesario

                // Crea un ExpenseDTO con los datos obtenidos
                ExpenseCategoryDTO listExpenseCategoryDTO = new ExpenseCategoryDTO();
                listExpenseCategoryDTO.setCategory_Id(categoryId);
                listExpenseCategoryDTO.setCategoryName(categoryName);


                // Agrega el ExpenseDTO a la lista
                categoryExpenses.add(listExpenseCategoryDTO);
            }
            return categoryExpenses;


        } catch (SQLException ex) {
            throw new RuntimeException("Error al consultar los datos.", ex);
        } finally {
            // Cierra recursos
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void insert(ExpenseCategoryDTO e) {
        try {
            // Obtén la conexión desde la clase ConnDB
            connection = ConnDB.getConnection();

            // Define la consulta SQL para insertar un nuevo gasto
            String sql = "INSERT INTO EXPENSE_CAT (CATEGORY_NAME) VALUES (?)";

            // Prepara la declaración SQL con parámetros
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, e.getCategoryName());

            // Ejecuta la consulta
            preparedStatement.execute();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar la categoría en la base de datos.", ex);
        } finally {
            // Cierra recursos
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    @Override
    public void update(ExpenseCategoryDTO e, Integer integer) {
        try{
            // Obtén la conexión desde la clase ConnDB
            connection = ConnDB.getConnection();

            // Define la consulta SQL para insertar un nuevo gasto
            String sql = "UPDATE EXPENSE_CAT SET CATEGORY_NAME = ? WHERE CATEGORY_ID = ?";

            // Prepara la declaración SQL con parámetros
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, e.getCategoryName());
            preparedStatement.setInt(2, integer);

            // Ejecuta la consulta
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar la categoría en la base de datos.", ex);
        } finally {
            // Cierra recursos
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public ExpenseCategoryDTO delete(int e) {
        try{
            // Obtén la conexión desde la clase ConnDB
            connection = ConnDB.getConnection();

            // Define la consulta SQL para insertar un nuevo gasto
            String sql = "DELETE FROM EXPENSE_CAT WHERE CATEGORY_ID = ?";

            // Prepara la declaración SQL con parámetros
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, e);

            // Ejecuta la consulta
            preparedStatement.execute();

            System.out.println("Categoría eliminada correctamente en la base de datos.");

        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar la categoría en la base de datos.", ex);
        } finally {
            // Cierra recursos
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }
}
