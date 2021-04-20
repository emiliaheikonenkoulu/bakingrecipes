package fi.swd20.bakingRecipes;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/login", "/h2-console/**", "/recipelist", "/recipes").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().ignoringAntMatchers("/h2-console/**")
        .and()
        .csrf().ignoringAntMatchers("/recipelist")
        .and()
        .csrf().ignoringAntMatchers("/recipes")
        .and()
        .headers().frameOptions().sameOrigin()
        .and()
      .formLogin()
        .defaultSuccessUrl("/recipelist", true)
        .permitAll()
        .and()
      .logout()
          .permitAll();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
