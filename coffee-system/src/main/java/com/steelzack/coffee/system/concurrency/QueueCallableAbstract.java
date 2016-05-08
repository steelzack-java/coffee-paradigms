package com.steelzack.coffee.system.concurrency;

import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.steelzack.coffee.system.manager.ProcessorAbstract.SCHEDULED_TASK_FAILED_TO_EXECUTE;

/**
 * Created by joao on 8-5-16.
 */
@Getter
public  abstract  class QueueCallableAbstract implements QueueCallable {
    private final static Logger logger = Logger.getLogger(QueueCallableAbstract.class);

    final List<Future<Boolean>> allResults = new ArrayList<>();

    final List<Callable<Boolean>> allCallables = new ArrayList<>();

    @Override
    public void waitForCalls() {
        allResults.stream().forEach( //
                booleanFuture -> { //
                    try { //
                        if (booleanFuture.get() != null && !booleanFuture.get()) { //
                            logger.error(SCHEDULED_TASK_FAILED_TO_EXECUTE); //
                        }
                    } catch (NullPointerException | InterruptedException | ExecutionException e) { //
                        logger.error(e.getMessage(), e); //
                    }
                }
        );
    }

    @Override
    public List<Callable<Boolean>> getAllCallables() {
        return allCallables;
    }

    @Override
    public void addSubmitResult(Future<Boolean> submitResult) {
        allResults.add(submitResult);
    }



}