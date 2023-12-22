package com.henry.pijava.services.impl;

import com.henry.pijava.Connections.ConnDB;
import com.henry.pijava.dto.ExpenseDTO;
import com.henry.pijava.exceptions.ExpenseInsertException;
import com.henry.pijava.services.ExpenseDAO;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpenseDAOImpl implements ExpenseDAO {
//    @Autowired
    private ConnDB connDB;
    private String currentDate = String.valueOf(LocalDate.now());
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public ExpenseDTO read(Integer key) {
        try {
            // Obtén la conexión desde la clase ConnDB
            connection = ConnDB.getConnection();

            // Define la consulta SQL para insertar un nuevo gasto
            String sql = "SELECT * FROM EXPENSE WHERE id = ?";

            // Prepara la declaración SQL con parámetros
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, key);

            //Obtener el resultado de la consulta
            resultSet = preparedStatement.executeQuery();
            ExpenseDTO expenseDTO = new ExpenseDTO();

            if(resultSet.next()) {

                expenseDTO.setId(resultSet.getInt("id"));
                expenseDTO.setAmount(resultSet.getDouble("amount"));
                expenseDTO.setDescription(resultSet.getString("description"));
                expenseDTO.setCategoryId(resultSet.getInt("category_id"));
                expenseDTO.setDate(LocalDate.parse(resultSet.getString("date")));

            }
            return expenseDTO;


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
    public List<ExpenseDTO> readAll() {
        try {
            // Obtén la conexión desde la clase ConnDB
            connection = ConnDB.getConnection();

            // Define la consulta SQL para insertar un nuevo gasto
            String sql = "SELECT * FROM EXPENSE ";

            // Prepara la declaración SQL con parámetros
            preparedStatement = connection.prepareStatement(sql);


            //Obtener el resultado de la consulta
            resultSet = preparedStatement.executeQuery();

            List<ExpenseDTO> expenses = new ArrayList<>();

            // Procesa los resultados
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                double amount = resultSet.getDouble("AMOUNT");
                String description = resultSet.getString("DESCRIPTION");
                int categoryId = resultSet.getInt("CATEGORY_ID");
                String date = resultSet.getString("DATE");
                // Puedes obtener otros campos según sea necesario

                // Crea un ExpenseDTO con los datos obtenidos
                ExpenseDTO expenseDTO = new ExpenseDTO();
                expenseDTO.setId(id);
                expenseDTO.setAmount(amount);
                expenseDTO.setDescription(description);
                expenseDTO.setCategoryId(categoryId);
                expenseDTO.setDate(LocalDate.parse(date));


                // Agrega el ExpenseDTO a la lista
                expenses.add(expenseDTO);
            }
                return expenses;


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
    }

    @Override
    public void insert(ExpenseDTO e) {
        try {
            // Obtén la conexión desde la clase ConnDB
            connection = ConnDB.getConnection();

            // Define la consulta SQL para insertar un nuevo gasto
            String sql = "INSERT INTO EXPENSE (amount, description, category_id, date) VALUES (?, ?, ?, ?)";

            // Prepara la declaración SQL con parámetros
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, e.getAmount());
            preparedStatement.setString(2, e.getDescription());
            preparedStatement.setInt(3, e.getCategoryId());
            preparedStatement.setDate(4, Date.valueOf(currentDate));


            // Ejecuta la consulta
            preparedStatement.executeUpdate();

            System.out.println("Gasto insertado correctamente en la base de datos.");

        } catch (SQLException ex) {
            throw new ExpenseInsertException("Error al insertar el gasto en la base de datos.", ex);
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
    public void update(ExpenseDTO e, Integer key) {
        try {
            // Obtén la conexión desde la clase ConnDB
            connection = ConnDB.getConnection();

            // Define la consulta SQL para insertar un nuevo gasto
            String sql = "UPDATE EXPENSE SET amount = ?, description = ?, category_id = ? WHERE ID = ?;";
            System.out.println("ID: " + key);

            // Prepara la declaración SQL con parámetros
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, e.getAmount());
            preparedStatement.setString(2, e.getDescription());
            preparedStatement.setInt(3, e.getCategoryId());
            preparedStatement.setInt(4, key);

            // Ejecuta la consulta
            preparedStatement.executeUpdate();

            System.out.println("Gasto modificado correctamente.");

        } catch (SQLException ex) {
            throw new RuntimeException("Error al modificar el Gasto.", ex);
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
    public ExpenseDTO delete(int e) {
        try {
            // Obtén la conexión desde la clase ConnDB
            connection = ConnDB.getConnection();

            // Define la consulta SQL para insertar un nuevo gasto
            String sql = "DELETE FROM EXPENSE WHERE id = ?";

            // Prepara la declaración SQL con parámetros
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, e);

            //Ejecutar la solicitud de eliminación
            preparedStatement.execute();

            System.out.println("Gasto eliminado correctamente en la base de datos.");




        } catch (SQLException ex) {
            throw new RuntimeException("No se eliminó ningún registro.", ex);
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
