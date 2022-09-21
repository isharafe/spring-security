# spring-security using JWT

- In security config
  - disable session creation. we use jwt tokens, so a session won't be used and no need of that
  - disable "csrf". since we use rest api calls, we don't have generated csrf tokens. so if we didn't disable this spring security will check for csrf tokens in post calls and will fail the request because there aren't any
  - expose AuthenticationManager bean, as by default this bean is not exposed. We need this bean to do the manual authentication later
  - add a filter to decode the jwt and authenticate the request (JwtTokenFilter)
- Add a controller for the login. here get the username and password from request and do the authentication (ideally put the logic in to a service class)
- JwtTokenFilter will validate the consecutive requests with jwt token
