# spring-security using JWT

- In [SecurityConfig](https://github.com/isharafe/spring-security/blob/jwt/src/main/java/com/ruchira/learn/springsecurity/config/SecurityConfig.java)
  - disable session creation. we use jwt tokens, so a session won't be used and no need of that
  - disable "csrf". since we use rest api calls, We don't generated csrf tokens. so if we didn't disable this spring security will check for csrf tokens in post calls and will fail the request because there aren't any
  - expose AuthenticationManager bean, as by default this bean is not exposed. We need this bean to do the manual authentication later
  - add a filter [(JwtTokenFilter)](https://github.com/isharafe/spring-security/blob/jwt/src/main/java/com/ruchira/learn/springsecurity/filter/JwtTokenFilter.java) to decode the jwt and authenticate the request
- Provide a secret key for data hashing in [application.properties](https://github.com/isharafe/spring-security/blob/jwt/src/main/resources/application.properties) so that we can easily configure this value later
- Add a controller ([AuthController](https://github.com/isharafe/spring-security/blob/jwt/src/main/java/com/ruchira/learn/springsecurity/controller/AuthController.java)) for the login. here get the username and password from request and do the authentication (it is better to put the logic in to a service class, but I'm lazy now.. :P )
  - We may also use a **Filter** and intercept the login url and evaluate the logic logic there. This approach is also used in many systems.
- [JwtTokenFilter](https://github.com/isharafe/spring-security/blob/jwt/src/main/java/com/ruchira/learn/springsecurity/filter/JwtTokenFilter.java) will validate the consecutive requests with jwt token
- Using [JwtTokenUtil](https://github.com/isharafe/spring-security/blob/jwt/src/main/java/com/ruchira/learn/springsecurity/util/JwtTokenUtil.java) to generate a JWT token and for decoding a JWT token
