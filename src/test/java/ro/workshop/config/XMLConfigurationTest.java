package ro.workshop.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ro.workshop.core.fixture.JPAAssertions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/testApplicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class XMLConfigurationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testMappings() {
        JPAAssertions.assertTableExists(entityManager, "ORDERS");
        JPAAssertions.assertTableExists(entityManager, "ORDER_DETAILS");
        JPAAssertions.assertTableExists(entityManager, "PRINT_STATUS");
        JPAAssertions.assertTableExists(entityManager, "PRINT_MACHINE");
    }
}
