# Webhook Demo

This is a simple demonstration of a Spring Boot webhook based off this [YouTube tutorial](https://www.youtube.com/watch?v=tshKOgRLYn0). I cleaned up some bad practices from the tutorial.

## Thoughts

- I used `H2 Database` instead of a `MySQL` driver.
- In the tutorial, `@Controller` was used. I replaced it with `@RestController` as a view was not provided or necessary.
- `@RequestBody` is not required for each method when you use `@RestController`.
- In fact, most conventions in the tutorial follow a typical `SpringMVC` implementation.
- `Spring Initializr` has stopped including a maven jar. I always excluded it from my repositories on GitHub.
- In `VSCode`, you can have problems when you add another application to an exisiting Java workspace. It is best to `restart` after adding another application to your folder.  There needs to be only folder in the Java workplace.  It doesn't matter if that one folder contains multiple applications.
- The `students` array was initialized inside the controller method.  This is problematic, as the array of students will be overwritten everytime the method is accessed.  It is better to initialize the ArrayList inside the model entity.  
- Multiple ways to use `optional` in the controller.  By utilizing `orElseThrow` and a `SchoolDataNotFoundException`, I was able to avoid using `optional` directly.
- I replaced `RestTemplate` with newer `RestClient`.  The change actually increases LOC.  Both are still synchronous and blocking.

## Continued Development

- Need to investigate best way to test a webhook.
- Idempotence for webhooks is an area I could research.

## Useful Resources

- [Baeldung](https://www.baeldung.com/spring-boot-h2-database) - spring boot h2 database
- [Geeks for Geeks](https://www.geeksforgeeks.org/difference-between-controller-and-restcontroller-annotation-in-spring/) - difference between controller and rest controller annotation in spring
- [Stack Overflow](https://stackoverflow.com/questions/77003280/how-to-put-prefix-when-create-a-custom-id-generator-in-java-spring-boot) - how to put prefix when create a custom id generator in java spring boot
- [Stack Overflow](https://stackoverflow.com/questions/61826895/how-to-avoid-visual-studio-code-warning-myfile-java-is-a-non-project-file-o) - how to avoid visual studio code warning myfile java is a non project file
- [dzone](https://dzone.com/articles/spring-boot-32-replace-your-resttemplate-with-rest#:~:text=Introduction%20of%20WebClient,suited%20for%20building%20reactive%20applications.) - replace your rest template
- [Baeldung](https://www.baeldung.com/spring-webclient-resttemplate) - spring webclient resttemplate
- [Spring Docs](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html) - rest clients
- [Blog](https://robertniestroj.hashnode.dev/think-twice-before-using-onetomany) - think twice before using onetomany
- [YouTube](https://www.youtube.com/watch?v=HeZIIm7Okl0) - Java Optionals, a Practical Walkthrough - Java Programming
- [Blog](https://medium.com/swlh/null-checking-done-right-with-optionals-78003c9329a7) - null checking done right with optionals
