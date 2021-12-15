package rsb.bootstrap;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class BaseCustomerService implements CustomerService {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Customer> rowMapperWithIdx = (rs, i) ->
            new Customer(rs.getLong(1), rs.getString(2));

    protected BaseCustomerService(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public Collection<Customer> save(String... names) {
        List<Customer> customerList = new ArrayList<>();

        for(String name : names) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            this.jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO customers(name) values (?)",
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, name);
                return ps;
            }, keyHolder);
            long keyHolderKey = Objects.requireNonNull(keyHolder.getKey()).longValue();
            Customer customer = this.findById(keyHolderKey);
            customerList.add(customer);
        }

        return customerList;
    }

    @Override
    public Customer findById(Long id) {
        String sql = "SELECT id, name FROM customers WHERE id = ?";

        return this.jdbcTemplate.queryForObject(sql, this.rowMapperWithIdx, id);
    }

    @Override
    public Collection<Customer> findAll() {
        return this.jdbcTemplate.query("SELECT id, name FROM customers", rowMapperWithIdx);
    }
}
