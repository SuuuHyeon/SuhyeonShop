package inhatc.cse.ksh.suhyeonshop.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.formLogin(form -> form
                .loginPage("/member/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email") // 설정해줘야함 email필드가 여기선 username
                .passwordParameter("password") // 같아서 설정 안해도 됨
                .failureUrl("/member/login/error")
                .permitAll()
        );
        http.authorizeHttpRequests(request ->request
                .requestMatchers("/css/**").permitAll() // 스타일 코드도 등록 해줘야함
                .requestMatchers("/", "/member/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN") // role로 설정하면 ROLE_ 안붙여도됨. authorized로 설정하면 붙여야함
                .anyRequest().authenticated()
        );
        http.logout(Customizer.withDefaults());

        http.exceptionHandling(
                exception -> exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

//        http.csrf(AbstractHttpConfigurer::disable);
//        http.formLogin(Customizer.withDefaults()); //

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
