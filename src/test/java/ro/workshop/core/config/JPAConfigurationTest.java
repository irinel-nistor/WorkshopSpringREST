package ro.workshop.core.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ro.workshop.core.fixture.JPAAssertions;

import javax.persistence.EntityManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class JPAConfigurationTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testMappings() {
        JPAAssertions.assertTableExists(entityManager, "ORDERS");
        JPAAssertions.assertTableExists(entityManager, "ORDER_DETAILS");
        JPAAssertions.assertTableExists(entityManager, "PRINT_STATUS");
        JPAAssertions.assertTableExists(entityManager, "PRINT_MACHINE");
    }
}
