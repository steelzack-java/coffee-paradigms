package com.jesperancinha.coffee.system.concurrency;

import com.jesperancinha.coffee.api.concurrency.StartupCallable;

/**
 * Created by joao on 8-5-16.
 */
public class StartupCallableImpl extends QueueCallableAbstract implements StartupCallable {
    @Override
    public Boolean call() throws Exception {
        return true;
    }
}
