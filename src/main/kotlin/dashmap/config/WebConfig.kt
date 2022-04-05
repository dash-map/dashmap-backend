package dashmap.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http
            ?.authorizeRequests()
            ?.antMatchers("/api/users")?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()
            ?.csrf()?.disable()
            ?.oauth2Login()
            ?.loginPage("/oauth2login")
            ?.redirectionEndpoint()
            ?.baseUri("/oauth2/callback/*")
            ?.and()
            ?.userInfoEndpoint()?.userService()

    }
}