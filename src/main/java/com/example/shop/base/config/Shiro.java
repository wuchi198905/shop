package com.example.shop.base.config;



import com.example.shop.base.filter.JwtFilter;
import com.example.shop.base.shiro.MyRealm;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig:shiro 配置类,配置哪些拦截,哪些不拦截,哪些授权等等各种配置都在这里
 *
 * 很多都是老套路,按照这个套路配置就行了
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/12
 */

@Configuration
public class Shiro {
    @Bean
    public Authorizer authorizer(){
        return new ModularRealmAuthorizer();
    }
    /**
     * 注入安全过滤器
     * @param securityManager
     * @return
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/login/**", "anon");
        //前后端带login登录的或者其他登录的通通放行
        filterChainDefinitionMap.put("/**/login/**", "anon");
        filterChainDefinitionMap.put("/**.js", "anon");
        filterChainDefinitionMap.put("/data/**", "anon");
        filterChainDefinitionMap.put("/swagger**/**", "anon");
        filterChainDefinitionMap.put("/**/swagger**/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/system/captcha/**", "anon");
        filterChainDefinitionMap.put("/memberInfo/getVerificationMailCode", "anon");
        filterChainDefinitionMap.put("/memberInfo/activity/**", "anon");
        filterChainDefinitionMap.put("/memberInfo/carousel/**", "anon");
        filterChainDefinitionMap.put("/checkcenter/**", "anon");
        filterChainDefinitionMap.put("/memberInfo/areaInfo/**", "anon");
        filterChainDefinitionMap.put("/memberInfo/HomepageDisplayPagination/**", "anon");
        filterChainDefinitionMap.put("/memberInfo/Latestregisteredmembers/**", "anon");
        filterChainDefinitionMap.put("/memberInfo/getVerificationCode/**", "anon");
        filterChainDefinitionMap.put("/memberInfo/registered/**", "anon");
        filterChainDefinitionMap.put("/memberInfo/qeeruserName/**", "anon");
        filterChainDefinitionMap.put("/memberInfo/case/**", "anon");
        filterChainDefinitionMap.put("/memberInfo/Latestregisteredmemberspages/**", "anon");


        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<String, Filter>(1);
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        filterChainDefinitionMap.put("/**", "jwt");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注入安全管理器
     * @param myRealm
     * @return
     */
   // @Bean
    public SecurityManager securityManager(MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);

        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;

       }
}
