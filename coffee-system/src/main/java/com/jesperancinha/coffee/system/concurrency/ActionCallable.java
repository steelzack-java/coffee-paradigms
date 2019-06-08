package com.jesperancinha.coffee.system.concurrency;

import com.jesperancinha.coffee.system.objects.ActionDescriptor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
@Getter
@NoArgsConstructor
public abstract class ActionCallable extends QueueCallableAbstract {

    private static final Logger logger = Logger.getLogger(ActionCallable.class);

    protected String name;

    protected List<ActionDescriptor> actionDescriptorList = new ArrayList<>();

    ActionCallable(String name) {
        this.name = name;
    }

}
