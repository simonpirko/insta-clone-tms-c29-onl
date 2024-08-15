package listener;

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

        //применяются миграции
        FluentConfiguration configure = Flyway.configure();
        configure.schemas("public");
        Flyway flyway = configure.dataSource("jdbc:postgresql://localhost:5432/postgres", "postgres", "bigbase").load();
        flyway.migrate();
    }
}