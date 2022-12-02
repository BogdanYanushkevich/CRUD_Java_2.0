# CRUD_Java_2.0
## Требования:
Придерживаться шаблона MVC (пакеты model, repository, service, controller, view).

Для миграции БД использовать https://www.liquibase.org/

Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito).

Слои:

model - POJO клаcсы

repository - классы, реализующие доступ к текстовым файлам

controller - обработка запросов от пользователя

view - все данные, необходимые для работы с консолью



Например: Developer, DeveloperRepository, DeveloperController, DeveloperView и т.д.


Для репозиторного слоя желательно использовать базовый интерфейс:
interface GenericRepository<T,ID>

interface DeveloperRepository extends GenericRepository<Developer, Long>

class JdbcDeveloperRepositoryImpl implements DeveloperRepository

Для импорта библиотек использовать Maven
Результатом выполнения проекта должен быть отдельный репозиторий на github, с описанием задачи, проекта и инструкцией по локальному запуску проекта.

Технологии: Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito

## ИНСТРУКЦИЯ ПО ЗАПУСКУ ПРИЛОЖЕНИЯ:

1. Предварительно убедитесь, что на вашем компьютере установлены JVM, JRE, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito.
2. Скачать программу CRUDAPP.jar по ссылке https://github.com/BogdanYanushkevich/CRUD_Java_2.0/raw/master/CRUD_Java_2.0.jar                                                                                                                                                             
3. Вызвать командную строку сочетанием клавиш WIN+R.
4. Прописать в командной строке: cd пробел и путь к папке со скачанной программой + Enter.
5. Запустить программу, прописав в командной строке: java -jar CRUD_Java_2.0.jar
