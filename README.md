# Процесс работы над проектом

1. **Получение обновлений из `main` и создание ветки:**
    - Перед началом работы над новой фичей переключитесь на ветку `main`.
    - Получите все актуальные обновления с помощью команды `git pull origin main`.
    - Создайте новую ветку для своей фичи с помощью команды `git checkout -b feature/branch-name`.


2. **Обновление структуры базы данных:**
    - В проекте используется FlyWay Database Migrations. В `pom.xml` необходимо внести Ваши данные для подключения к БД (url, user, password) в теге `<configuration>` плагина FlyWay. Если необходимо, обновите структуру базы данных с помощью плагина FlyWay (команда flyway:migrate находится в окне Maven).
    - Добавлен код автоматической миграции при запуске приложения с использованием констант из PostgresConnection.


3. **Завершение работы над фичей:**
    - Если были внесены изменения в структуру бд, в каталоге `src/main/resources/db/migration` создайте новый файл с внесенными изменениями (только Ваши изменения). Имя файла задается в формате `V1_X__Description.sql'`, где X-числовой номер следующей версии по порядку, Description - краткое описание изменений (несколько слов разделяются нижним подчеркиванием). 
    - Смержите свою ветку с <b>актуальной</b> версией `main` с помощью команды `git merge main`.
    - Решить возможные конфликты.
    - Создать PR.

**Оффтоп** - `Credentials к бд находятся в классе PostgresConnection`
