package com.snackhub.gateway;

import com.snackhub.domain.Customer;
import com.snackhub.exception.DomainException;
import com.snackhub.exception.ErrorName;

import java.sql.*;
import java.util.Optional;

public class CustomerGateway {

    public Optional<Customer> getCustomerByCPF(String cpf) throws Exception {
        String dbUrl = System.getenv("url_database");
        String username = System.getenv("user");
        String password = System.getenv("password");

        try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
            String sql = "SELECT * FROM customers WHERE cpf = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, cpf);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Long id = resultSet.getLong("id");
                        Customer customer = new Customer(id, cpf);
                        return Optional.of(customer);
                    }
                }
            }
        } catch (SQLException e) {
            throw new DomainException(ErrorName.DATA_BASE_ERROR, e);
        }

        return Optional.empty();
    }
}
