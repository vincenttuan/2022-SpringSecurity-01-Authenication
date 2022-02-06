package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 若要自訂登入邏輯則要繼承 WebSecurityConfigurerAdapter
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService UserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 表單提交
		http.formLogin()
			// loginpage.html 表單 action 內容
			.loginProcessingUrl("/login")
			// 自定義登入頁面
			.loginPage("/loginpage")
			// 登入成功之後要造訪的頁面
			.successForwardUrl("/")  // welcome 頁面
			// 登入失敗後要造訪的頁面
			.failureForwardUrl("/fail");
		
		// 授權認證
		http.authorizeHttpRequests()
			// 不需要被認證的頁面：/loginpage
			.antMatchers("/loginpage").permitAll()
			// 其他的都要被認證
			.anyRequest().authenticated();
		
		// http.csrf().disable(); // 關閉 csrf 防護
		
		// 登出
		http.logout()
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/loginpage")
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")); // 可以使用任何的 HTTP 方法登出
	}

	// 注意！規定！要建立密碼演算的實例
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
