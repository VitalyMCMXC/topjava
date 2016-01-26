# Занятие 4 онлайн проекта <a href="https://github.com/JavaWebinar/topjava05">Topjava</a>

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFfkxqbVpwZUd5anQ2TXE4bm5HbXhtVmkxMUxFSjhNQ1hXYVVTTTZEMzkzN2s">Материалы урока</a>

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFdVhaMklZQVNkUGc">HW3: JdbcUserMealRepositoryImpl + UserMealServiceTest</a>
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFLXBtTHVKUGRZRk0">1_ HW3.patch</a>**
> В meals добавил составной индекс `INDEX meals_unique_user_datetime_idx ON meals(user_id, date_time)`:

 - все запросы в таблицу meals у нас идут с `user_id`
 - по полю `date_time` также есть запросы + мы по нему сортируем список результатов

 те они- хорошие кандидаты для индексирования. На id как на primary key индекс создается автоматически


- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFQWhPOTM2RVpfZmc">2_ HW3_ Optional.patch</a>**

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFU0Z2R190eDllYmM">Spring: инициализация и популирование DB</a>
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFaDA2UGlGaWN0WVU">3_ init_ and_ populate_ db.patch</a>**
> - IDEA может `${jdbc.initLocation}` подчеркивать красным - иногда тупит... Главное- чтобы приложение работало.
> - `@Sql` в тестах заменяет `@Before public void setUp()`, те выполняется перед каждым тестом

-  <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html#jdbc-initializing-datasource-xml">Инициализация базы при старте приложения</a>
-  Инициализация базы из java: DbPopulator. <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#integration-testing-annotations-spring">Spring Testing Annotations</a>
-  <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#resources">Абстракция Resource</a>

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFNTNWV04weDBGSmc">Починка mock: подмена контекста при тестировании</a>
- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFejVYQmV4MXJIbXM">4_ create_ mock_ test_ ctx.patch</a>**

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFVWZYcHoyUF9qX2M">ORM. Hibernate. JPA.</a>
> ВНИМАНИЕ: патч меняет postgres.properties, в котором у вас свои креденшелы к базе.
 
-  **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFemRMaWwwMWdhUzg">5_ make_ entity_ add_ jpa.patch</a>**
>  Upgrade Hibernate to 5.0.6 needed to add `javax.transaction.jta`, see <a href="https://hibernate.atlassian.net/browse/HHH-10307">5.0.4 requires javax.transaction.SystemException</a>

- <a href="http://habrahabr.ru/post/265061/">JPA и Hibernate в вопросах и ответах</a>
-  <a href="http://ru.wikipedia.org/wiki/ORM">ORM</a>. 
-  <a href="http://en.wikibooks.org/wiki/Java_Persistence/Mapping">Mapping</a>. Описания модели Hibernate (hbm.xml/annotation)
-  <a href="https://ru.wikipedia.org/wiki/Hibernate_(библиотека)">Hibernate</a>. Другие ORM: <a href="http://en.wikipedia.org/wiki/TopLink">TopLink</a>, <a href="http://en.wikipedia.org/wiki/EclipseLink">ElipseLink</a>, <a href="http://en.wikipedia.org/wiki/Ebean">EBean</a> (<a href="http://www.playframework.com/documentation/2.2.x/JavaEbean">used in Playframework</a>).
-  <a href="http://ru.wikipedia.org/wiki/Java_Persistence_API">JPA (wiki)</a>. <a href="https://en.wikipedia.org/wiki/Java_Persistence_API">JPA (english wiki)</a>. <a href="http://www.jpab.org/All/All/All.html">JPA Performance Benchmark</a>
-  Подключение к проекту Spring ORM и Hibernate
-  <a href="http://en.wikibooks.org/wiki/Java_Persistence/Inheritance">Отображения наследования объектов на таблицы</a>
-  <a href="http://en.wikibooks.org/wiki/Java_Persistence/Identity_and_Sequencing">Стратегии генерации PK</a>
-  Добавление <a href="http://validator.hibernate.org">hibernate-validator</a>. <a href="http://stackoverflow.com/questions/14730329/jpa-2-0-exception-to-use-javax-validation-package-in-jpa-2-0">JSR-303 -> JSR-349</a>
-  <a href="http://devcolibri.com/2046">Описание связей в модели. Ленивая загрузка объекта.</a>
-  Конфигурирование JPA. Сканировние Entities. <a href="http://docs.jboss.org/hibernate/entitymanager/3.6/reference/en/html/architecture.html#d0e61">JPA definitions</a>
-  <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/expressions.html">Spring expressions: выражения в конфигурации</a>
-  Создание JPA Facet. Назначение DataSource.
-  Имплементация JpaUserRepository через EntityManagerFactory/ SessionFactory
-  Использование TypedQuery и @NamedQuery. Назначение параметров по индексу и имени.
-  <a href="http://docs.jboss.org/hibernate/orm/4.2/devguide/en-US/html/ch11.html">HQL</a>/ <a href="http://ru.wikipedia.org/wiki/Java_Persistence_Query_Language">JPQL</a>.
-  <a href="http://www.objectdb.com/java/jpa/query/criteria">JPA Criteria API</a>. <a href="http://www.querydsl.com/">Unified Queries for Java</a>
-  <a href="https://weblogs.java.net/blog/montanajava/archive/2014/06/17/using-java-8-datetime-classes-jpa">Java 8 Time API Converter</a>

- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFT1B0dnBDQUxjYkk">6_ jpa_ impl_ transaction.patch</a>**
-  <a href="http://ru.wikipedia.org/wiki/Транзакция_(информатика)">Транзакция. ACID. Уровни изоляции транзакций.</a> 
-  Подключаем транзакции. <a href="http://www.tutorialspoint.com/spring/spring_transaction_management.htm">Spring Transaction Management</a>
-  <a href="https://jira.spring.io/browse/DATAJPA-601">readOnly и Propagation.SUPPORTS</a>
-  <a href="http://habrahabr.ru/post/232381/">@Transactional в тестах. Настройка EntityManagerFactory</a>

-  Ресурсы:
   - <a href="http://www.byteslounge.com/tutorials/spring-transaction-propagation-tutorial">Spring transaction propagation tutorial</a>
   - <a href="https://dzone.com/refcardz/getting-started-with-jpa">Getting Started with JPA</a>
   - <a href="http://www.youtube.com/watch?v=YzOTZTt-PR0">Николай Алименков — Босиком по граблям Hibernate</a>
   - <a href="http://stackoverflow.com/questions/8994864/how-would-i-specify-a-hibernate-pattern-annotation-using-a-regular-expression">Validate by RegExp</a>
   - <a href="http://en.wikibooks.org/wiki/Java_Persistence">Java Persistence</a>
   - <a href="http://hibernate.org/">Hibernate</a>
   - <a href="http://stackoverflow.com/questions/26482495/which-transaction-manager-should-i-use-jta-vs-jpa">JTA vs JPA</a> 
   - <a href="http://docs.spring.io/spring-framework/docs/4.0.x/spring-framework-reference/html/transaction.html">Spring Framework transaction management</a>
   - <a href="http://www.objectdb.com/java/jpa/persistence/managed#Entity_Object_Life_Cycle">Working with JPA Entity Objects</a>
   - <a href="http://www.ibm.com/developerworks/ru/library/j-ts1/">Стратегии работы с транзакциями: Распространенные ошибки</a>
   - <a href="http://habrahabr.ru/post/208400/">Принципы работы СУБД. MVCC</a>
   - <a href="https://ru.wikipedia.org/wiki/MVCC">MVCC</a>

## <a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFSTJEQ1Rvd3Jvc2c">Добавляем поддержку HSQLDB</a>
>  ВНИМАНИЕ: патч меняет postgres.properties

- **<a href="https://drive.google.com/open?id=0B9Ye2auQ_NsFLThpcGpsLXZLWW8">7_ hsqldb_ support.patch</a>**


## Домашнее задание HW4
-  <a href="http://stackoverflow.com/questions/23718383/jpa-support-for-java-8-new-date-and-time-api">JPA support for Java 8 new date and time API</a>
-  <a href="http://stackoverflow.com/questions/31965179/whats-new-in-hibernate-5">What's new in Hibernate 5?</a>
-  <a href="https://github.com/junit-team/junit/wiki/Rules">JUnit @Rules</a>

Required

    Сделать UserMeal Hibernate entity 
    Добавить поддержку LocalDateTime
    Имплементировать и протестировать JpaUserMealRepositoryImpl 

Optional

    Добавить в тесты UserMealServiceTest функциональность @Rule 
     (проверку Exception и вывод в лог времени выполнения каждого теста).

### Ваши вопросы
-  Есть несколько аналогичных "встроенных" баз данных. H2, HSQLDB, Derby, SQLite. Почему был выбран HSQLDB?
>  Просто с ней приходилось работать. H2 еще наиболее популярна, планирую использовать ее новом курсе по spring-boot.

-  Чистого JPA не существует, т.е. это всего лишь интерфейс, спецификация?
   Говорим JPA, подразумеваем какой-то ORM фрэймворк? А что тогда используют чистый jdbc, Spring-jdbc, MyBatis? MyBatis не реализует JPA?
> JPA- это спецификация, ее реализации- Hibernate, OpenJPA, EclipceLink.
  Spring-Jdbc, MyBatis, db-utils не реализуют JPA, это обертки к JDBC. JPA также реализована поверх JDBC.

- В зависимостях maven `hibernate-entitymanager` тянет за собой `jboss-logging`. Как будет происходить логгирование?
> Гуглим: <a href="http://stackoverflow.com/questions/11639997/how-do-you-configure-logging-in-hibernate-4-to-use-slf4j">How do you configure logging in Hibernate 4 to use SLF4J</a>. В нашем проекте автоматически подхватывается `logback-classic`.

### Подсказки по HW04
-  IDEA не понимает в @NamedQuery `..  m.dateTime BETWEEN ..`. На функциональность это не влияет.
-  Тк. JPQL работает с объектами мы не можем использовать userId например для сохранения. Можно сделать пример так:

        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);

   При этом от User нам нужет только id, т.е. реального запроса в базу за юзером не будет- проверьте по логам Hibernate.
   А в JPQL запросах можно писать: `m.user.id=:userId`

-  При реализации `JpaUserMealRepositoryImpl` предпочтительно не использовать try-catch в логике реализации. Но если очень хочется, то ловить только специфичекские эксепшены (пр. `NoResultExceptio`), чтобы, например, при отсутствии коннекта к базе приложение отвечало адекватно.

-  После выставления переменной окружения IDEA нужно рестартовать, обратите внимание на слеши в пути windows. Проверить, видит ли java переменную окуржения можно так: `System.getenv("TOPJAVA_ROOT")`. Еще вариант: запускать приложение с JVM опцией: `-DTOPJAVA_ROOT=...` , тогда она доступна из java как `System.getProperty("TOPJAVA_ROOT")`. 

- Мы будем смотреть генерацию db скриптов из модели, для корректоной генерации нужно в `UserMeal` добавить `uniqueConstraints`
