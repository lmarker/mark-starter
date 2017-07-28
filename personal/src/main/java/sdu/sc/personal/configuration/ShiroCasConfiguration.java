package sdu.sc.personal.configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class ShiroCasConfiguration {
    
    private static final Logger logger = LoggerFactory.getLogger(ShiroCasConfiguration.class);

    private static final String casFilterUrlPattern = "/shiro-cas";

    /**
     * 注册DelegatingFilterProxy（Shiro）
     *
     * @param dispatcherServlet
     * @return
     * @author SHANHY
     * @create  2016年1月13日
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
	FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
	filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
	//  该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理  
	filterRegistration.addInitParameter("targetFilterLifecycle", "true");
	filterRegistration.setEnabled(true);
	filterRegistration.addUrlPatterns("/*");
	return filterRegistration;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
	return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Value("${shiro.cas}") String casServerUrlPrefix,
	    @Value("${shiro.server}") String shiroServerUrlPrefix) {
	DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	CasRealm casRealm = new CasRealm();
	casRealm.setDefaultRoles("ROLE_USER");
	casRealm.setCasServerUrlPrefix(casServerUrlPrefix);
	casRealm.setCasService(shiroServerUrlPrefix + casFilterUrlPattern);
	securityManager.setRealm(casRealm);
	securityManager.setCacheManager(new MemoryConstrainedCacheManager());
	securityManager.setSubjectFactory(new CasSubjectFactory());
	return securityManager;
    }

    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     *
     * @author SHANHY
     * @create  2016年1月14日
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean){
	/////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
	Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
	filterChainDefinitionMap.put(casFilterUrlPattern, "casFilter");
	filterChainDefinitionMap.put("/login", "anon");
	filterChainDefinitionMap.put("/home", "anon");
	filterChainDefinitionMap.put("/register", "anon");
	filterChainDefinitionMap.put("/register/*", "anon");
	filterChainDefinitionMap.put("/logout","logout");
	filterChainDefinitionMap.put("/**", "authc");
	shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * CAS过滤器
     *
     * @return
     * @author SHANHY
     * @create  2016年1月17日
     */
    @Bean(name = "casFilter")
    public CasFilter getCasFilter(@Value("${shiro.cas}") String casServerUrlPrefix,
	    @Value("${shiro.server}") String shiroServerUrlPrefix) {
	CasFilter casFilter = new CasFilter();
	casFilter.setName("casFilter");
	casFilter.setEnabled(true);
	String loginUrl = casServerUrlPrefix + "/login?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
	casFilter.setFailureUrl(loginUrl);
	return casFilter;
    }

    /**
     * ShiroFilter<br/>
     * 注意这里参数中的 StudentService 和 IScoreDao 只是一个例子，因为我们在这里可以用这样的方式获取到相关访问数据库的对象，
     * 然后读取数据库相关配置，配置到 shiroFilterFactoryBean 的访问规则中。实际项目中，请使用自己的Service来处理业务逻辑。
     *
     * @param myShiroCasRealm
     * @param stuService
     * @param scoreDao
     * @return
     * @author SHANHY
     * @create  2016年1月14日
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
	    CasFilter casFilter,
	    @Value("${shiro.cas}") String casServerUrlPrefix,
	    @Value("${shiro.server}") String shiroServerUrlPrefix) {
	ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
	shiroFilterFactoryBean.setSecurityManager(securityManager);
	String loginUrl = casServerUrlPrefix + "/login?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
	shiroFilterFactoryBean.setLoginUrl(loginUrl);
	shiroFilterFactoryBean.setSuccessUrl("/index");
	Map<String, Filter> filters = new HashMap<>();
	filters.put("casFilter", casFilter);
	LogoutFilter logoutFilter = new LogoutFilter();
	logoutFilter.setRedirectUrl(casServerUrlPrefix + "/logout?service=" + shiroServerUrlPrefix);
	filters.put("logout",logoutFilter);
	shiroFilterFactoryBean.setFilters(filters);
	loadShiroFilterChain(shiroFilterFactoryBean);
	return shiroFilterFactoryBean;
    }


}
