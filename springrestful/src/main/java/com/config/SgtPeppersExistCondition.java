package com.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by yuchen on 2018/11/5.
 */
public class SgtPeppersExistCondition implements Condition{
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return conditionContext.getBeanFactory().containsBean("sgtPeppers");
    }
}
