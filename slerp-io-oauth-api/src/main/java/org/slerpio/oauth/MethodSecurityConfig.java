//package org.slerpio.oauth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
//import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
//	@Autowired
//	WebSecurityConfig securityConfig;
//
//	@Override
//	protected MethodSecurityExpressionHandler createExpressionHandler() {
//		DefaultMethodSecurityExpressionHandler dmseh = new DefaultMethodSecurityExpressionHandler();
//		dmseh.setPermissionEvaluator(new CustomPermissionEvaluator());
//		return dmseh;
//
//	}
//}