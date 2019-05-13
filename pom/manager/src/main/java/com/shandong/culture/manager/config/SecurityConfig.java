package com.shandong.culture.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dm.security.verification.VerificationCodeFilter;
import com.dm.security.verification.VerificationCodeStorage;
import com.dm.security.verification.support.SessionScopeVerificationCodeStorage;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userService;

	@Autowired
	private AuthenticationSuccessHandler loginSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler loginFailureHandler;

	@Autowired
	private AuthenticationEntryPoint entryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests().antMatchers(HttpMethod.POST,
		// "/login").hasAnyRole("ADMIN", "USER").anyRequest()
		// .authenticated().and()
		// 以下地址不需要认证
		http.authorizeRequests()
				.antMatchers(
						"/login.html**",
						"/members/registry**",
						"/members/userVerification**",
						"/verificationCode**/**")
				.permitAll();
		http.authorizeRequests().antMatchers("/h2-console**/**").permitAll();
		// 因为h2-console使用了frame,spring security默认是不允许frame访问的
		// 这个配置允许frame的同源访问
		http.headers().frameOptions().sameOrigin(); //
		// 对资源进行授权验证，这个和下面的interceptor是互斥的，如果启用下面的interceptor,则不要启用这个配置
		http.authorizeRequests().anyRequest().authenticated();
		http.formLogin().successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)
				.and().logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
				.and().httpBasic().disable();
		http.csrf().disable();

		// 添加登出成功请求handler
//		http.logout().logoutSuccessHandler(logoutSuccessHandler());
		// 添加入口点，使用平台配置的401入口点
		http.exceptionHandling().authenticationEntryPoint(entryPoint);

		// 增加自定义资源过滤器，对资源进行详细过滤，（可选项）
//		http.addFilterBefore(interceptor(), FilterSecurityInterceptor.class);

		// session管理相关
		// maximumSessions设置每个用户最大会话数，
		// maxSessionsPreventsLogin:为true时会阻止新用户上线，false时会踢掉原来会话,建议设置为false,默认是false
		// 因为当用户直接关闭浏览器时，并不一定会发出登出请求，这就导致服务器的session不会失效。从而导致新的会话不能上线
		// expiredUrl：当用户session失效时，会发送302响应到指定的地址。我们需要处理相关的请求，如果是spa应该怎么
		// 当再次发送请求是，就会进入entrypoint进行登录过程了
		// http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false).expiredUrl("/login");
		// 当登录成功之后，更换会话标识，IBM Secrity AppScan 会话标识未更新错误
//		http.sessionManagement().sessionFixation().changeSessionId();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	/**
	 * 必须添加这个bean， 这个listener会在用户登出时，通知容器某个session已经失效，这时，用户可以在别的地方上线
	 * 
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}

	@Bean
	public FilterRegistrationBean<VerificationCodeFilter> filterRegistrationBean() {
		FilterRegistrationBean<VerificationCodeFilter> filter = new FilterRegistrationBean<VerificationCodeFilter>();
		filter.setFilter(verificationCodeFilter());
		filter.addUrlPatterns("/members/registry");
		return filter;
	}

	/**
	 * 验证码过滤器
	 * 
	 * @return
	 */
	@Bean
	public VerificationCodeFilter verificationCodeFilter() {
		VerificationCodeFilter filter = new VerificationCodeFilter();
		filter.requestMatcher(new AntPathRequestMatcher("/members/registry**", HttpMethod.POST.toString()));
		return filter;
	}

	/**
	 * 验证码存储
	 * 
	 * @return
	 */
	@Bean
	@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
	public VerificationCodeStorage verificationCodeStorage() {
		return new SessionScopeVerificationCodeStorage();
	}

}
