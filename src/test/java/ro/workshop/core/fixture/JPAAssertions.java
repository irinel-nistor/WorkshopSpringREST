package ro.workshop.core.fixture;

import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.hibernate.jdbc.Work;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class JPAAssertions {

    public static void assertTableExists(EntityManager em, final String name) {
        SessionImpl session = (SessionImpl) em.unwrap(Session.class);

        final ResultCollector rc = new ResultCollector();

        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                ResultSet tables = connection.getMetaData().getTables(null, null, "%", null);
                while (tables.next()) {
                    //System.out.println(tables.getString(3));
                    if (tables.getString(3).toUpperCase().equals(name.toUpperCase())) {
                        rc.found = true;
                    }
                }
            }
        });

        assertTrue(String.format("Table %s not found!", name), rc.found);
    }

    static class ResultCollector {
        public boolean found = false;
    }
}
