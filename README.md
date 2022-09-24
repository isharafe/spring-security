# spring-security

Users need to be authenticated are in a database. When user need to authenticated and presented a username and a password, these data need to be validated with the data in the database. Below main classes have used for this.

- [UserPrinciple](https://github.com/isharafe/spring-security/blob/jdbc/src/main/java/com/ruchira/learn/springsecurity/auth/UserPrinciple.java)
  - Spring security's main concept of a user is a **Principle**. Which represents any entity (a user, another machine, process, whatever...). Spring use **UserDetails** class to represent the **Principle**. Here I extend the UserDetails class and create a new class **UserPrinciple** so that I can configure the behaviors as I want.
  - A **UserPrinciple** object creates with an **User** Model object where we store username and (Hashed) password. Use these details to construct an **UserPrinciple** object and spring will use this object for authentication.
- [UserService](https://github.com/isharafe/spring-security/blob/jdbc/src/main/java/com/ruchira/learn/springsecurity/service/UserService.java)
  - Spring need a way to get complete user details when it is presented with a username.
  - Spring uses **UserDetailsService: loadUserByUsername** to get these details.
  - Here create a new class **UserService** extending UserDetailsService and override the loadUserByUsername method. Now I can load the correct user (from db) using the username
- [SecurityConfig](https://github.com/isharafe/spring-security/blob/jdbc/src/main/java/com/ruchira/learn/springsecurity/config/SecurityConfig.java)
  - This class is used to tell spring how we want it to secure our application
  - Ex: If user needs to access the url "/abcd", he should have a "xyz" role
  - Early days most common way to create this config class was to extend **WebSecurityConfigureradapter**. But in recent spring releases, this class is deprecated. Instead they suggest [other ways to do this](https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter)
  - The **AuthenticationProvider** bean tels spring how the authentication should happen. A DaoAuthenticationProvider has provided for this bean. So that spring will use provided jdbc details to connect with the db and get necesssary data.
  - The db connection details are provided in the [application.properties](https://github.com/isharafe/spring-security/blob/jdbc/src/main/resources/application.properties) file

To ease the test, added a dummy **test** user using a "CommandLineRunner". This will run during the application startup and the code inside will create and save this dummy **User**.
