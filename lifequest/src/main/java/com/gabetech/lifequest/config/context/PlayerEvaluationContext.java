package com.gabetech.lifequest.config.context;

import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.expression.spel.support.StandardTypeLocator;

public class PlayerEvaluationContext extends StandardEvaluationContext {

    public PlayerEvaluationContext(Object rootObject) {
        super(rootObject);
        StandardTypeLocator typeLocator = (StandardTypeLocator) this.getTypeLocator();

        typeLocator.registerImport("com.gabetech.lifequest.model.enums");
    }
}
