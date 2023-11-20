package com.maker.register.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Aspect
public class TransactionManagerConfig {
    @Bean
    public TransactionManager transactionManager(@Autowired DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager=new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
    @Bean("txAdvice")
    public TransactionInterceptor transactionInterceptor(@Autowired TransactionManager transactionManager){
        TransactionInterceptor transactionInterceptor=new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(transactionManager);
        RuleBasedTransactionAttribute readAttribute=new RuleBasedTransactionAttribute();
        readAttribute.setReadOnly(true);
        readAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        RuleBasedTransactionAttribute requiredAttribute=new RuleBasedTransactionAttribute();
        requiredAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        Map<String, TransactionAttribute> mapAttribute=new HashMap<>();
        mapAttribute.put("add*",requiredAttribute);
        mapAttribute.put("save*",requiredAttribute);
        mapAttribute.put("insert*",requiredAttribute);
        mapAttribute.put("remove*",requiredAttribute);
        mapAttribute.put("delete*",requiredAttribute);
        mapAttribute.put("edit*",requiredAttribute);
        mapAttribute.put("update*",requiredAttribute);
        mapAttribute.put("get*",readAttribute);
        mapAttribute.put("find*",readAttribute);
        mapAttribute.put("query*",readAttribute);
        mapAttribute.put("select*",readAttribute);
        NameMatchTransactionAttributeSource attributeSource=new NameMatchTransactionAttributeSource();
        attributeSource.setNameMap(mapAttribute);
        transactionInterceptor.setTransactionAttributeSource(attributeSource);
        return transactionInterceptor;
    }
    @Bean
    public Advisor transactionAdviceAdvisor(@Autowired TransactionInterceptor transactionInterceptor){
        AspectJExpressionPointcut pointcut=new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public * com.maker.register..service..*.*(..))");
        return new DefaultPointcutAdvisor(pointcut,transactionInterceptor);
    }
}
