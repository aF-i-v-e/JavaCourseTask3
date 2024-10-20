# Вариант 3: Учет и анализ личных расходов.
Пользователи могут добавлять доходы и
расходы, а система позволяет проанализировать эти данные по категориям.

## ПРАКТИЧЕСКАЯ РАБОТА № 4. Базы данных, ORM Hibernate, Spring Data.

### Пример значений свойств для application.properties:
* DB_USERNAME=postgres
* DB_PASSWORD=password
* DB_TYPE=POSTGRESQL
* DB_NAME=example
* DB_PORT=5432
* DB_HOSTNAME=localhost
* DB_DIALECT=org.hibernate.dialect.PostgreSQLDialect
* DB_ACTION=update
* SERIALIZATION_FAIL_EMPTY_BEANS=false

-------------------------------------------------------

## ПРАКТИЧЕСКАЯ РАБОТА № 5. Spring WEB, REST API

Endpoint для работы с кастомными методами репозитория AccountUserRepository (REST-контроллер):
http://localhost:8080/custom/account_users/findByLogin?login=iivanov
http://localhost:8080/custom/account_users/findByRole?role=user

Endpoint для работы с отображением данных через HTML-страницу (WEB-контроллер):
http://localhost:8080/custom/account_users/view/list

Интерфейс swagger-ui доступен по адресу:
http://localhost:8080/swagger-ui/index.html

-------------------------------------------------------

## ПРАКТИЧЕСКАЯ РАБОТА № 6. Spring Security

Для того чтобы создать одного пользователя и одного администратора, запустите один раз тесты
testCreateUser() и testCreateUserAdmin() файла AccountUserRepositoryCustomTest.java.

Регистрация пользователя в системе учёта и анализа личных расходов:
http://localhost:8080/registration

Вход пользователя в систему учёта и анализа личных расходов:
http://localhost:8080/login

Выход пользователя из системы учёта и анализа личных расходов:
http://localhost:8080/logout

-------------------------------------------------------