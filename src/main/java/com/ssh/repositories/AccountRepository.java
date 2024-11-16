package com.ssh.repositories;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ssh.model.Account;
//import com.ssh.repositories.mappers.AccountRowMapper;

@Repository
public class AccountRepository {

    private final JdbcTemplate jdbc;

    public AccountRepository(DataSource dataSource) {
        jdbc = new JdbcTemplate(dataSource);
    }

    public Account findAccountById(long id) {
        String sql = "SELECT * FROM account WHERE id = ?";

        RowMapper<Account> mapper = (ResultSet rs, int rowNum) -> {
            Account a = new Account();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            a.setAmount(rs.getBigDecimal("amount"));
            return a;
        };
        //return jdbc.queryForObject(sql, new AccountRowMapper(), id);
        return jdbc.queryForObject(sql, mapper, id);
    }

    public void changeAmount(long id, BigDecimal amount) {
        String sql = "UPDATE account SET amount = ? WHERE id = ?";
        jdbc.update(sql, amount, id);

    }

    public List<Account> findAllAccounts() {
        String sql = "SELECT * FROM account";
        RowMapper<Account> mapper = (ResultSet rs, int rowNum) -> {
            Account a = new Account();
            a.setId(rs.getInt("id"));
            a.setName(rs.getString("name"));
            a.setAmount(rs.getBigDecimal("amount"));
            return a;
        };
        return jdbc.query(sql, mapper);
    }

}
