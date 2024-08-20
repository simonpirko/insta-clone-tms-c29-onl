package listener;

import connection.PostgresConnection;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * Первичные инициализации при запуске приложения
 * */
@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //автоматическая миграция при запуске приложения
        FluentConfiguration configure = Flyway.configure();
        configure.schemas("public");
        Flyway flyway = configure.dataSource(PostgresConnection.getURL(), PostgresConnection.getUSER(), PostgresConnection.getPASSWORD()).load();
        flyway.migrate();
    }
}