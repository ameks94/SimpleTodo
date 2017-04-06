There are some valuable points to start work:
    1. Jetty runner must be installed into intellij idea;
    2. Base url is http://localhost:8080/simpletodo/ (port is configurable through JettyRunner)
    3. MySQL5 should be installed
    4. Lombok plugin should be added to intellij (also annotation processing should be turned on)

Some points of configuration:
    1. Models should be located at org.simple.todo.model
    2. For transaction management use @Transaction annotation
    3. DAO uses EntityManager (via @PersistenceContext annotation)
    4. Services are injected throughout @Autowired annotation