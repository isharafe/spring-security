# spring-security

- All you have to do is provide a **UserDetailsService** with **InMemoryUserDetailsManager** in the [SecurityConfig](https://github.com/isharafe/spring-security/blob/in-memory/src/main/java/com/ruchira/learn/springsecurity/config/SecurityConfig.java)
- **InMemory** means users are kept in memory, and these users need to be pre-defined (in code or loaded from some external config file). In this example, I have provided users in the **UserDetailsService** bean in **SecurityConfig**
