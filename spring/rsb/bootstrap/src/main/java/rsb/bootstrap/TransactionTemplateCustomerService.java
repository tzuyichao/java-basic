package rsb.bootstrap;

import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Collection;

public class TransactionTemplateCustomerService extends BaseCustomerService {
    private TransactionTemplate transactionTemplate;

    public TransactionTemplateCustomerService(DataSource ds, TransactionTemplate tm) {
        super(ds);
        this.transactionTemplate = tm;
    }

    @Override
    public Collection<Customer> save(String... names) {
        return this.transactionTemplate.execute(s -> super.save(names));
    }

    @Override
    public Customer findById(Long id) {
        return this.transactionTemplate.execute(s -> super.findById(id));
    }

    @Override
    public Collection<Customer> findAll() {
        return this.transactionTemplate.execute(s -> super.findAll());
    }
}
