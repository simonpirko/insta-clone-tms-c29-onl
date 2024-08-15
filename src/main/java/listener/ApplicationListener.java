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

        /**
         * Код ниже будет автоматически применять миграцию при запуске программы, если отключить плагин.
         * */
        /*FluentConfiguration configure = Flyway.configure();
        configure.schemas("public");
        Flyway flyway = configure.dataSource("jdbc:postgresql://localhost:5432/postgres", "postgres", "root").load();
        flyway.migrate();*/
    }
}