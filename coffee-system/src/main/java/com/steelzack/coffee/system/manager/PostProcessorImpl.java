package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.ActionCallable;
import com.steelzack.coffee.system.concurrency.PostActionCallableImpl;
import com.steelzack.coffee.system.concurrency.QueueCallable;
import com.steelzack.coffee.system.input.Employees.Employee;
import com.steelzack.coffee.system.input.Employees.Employee.Actions.PostAction;
import com.steelzack.coffee.system.queues.QueueAbstract;
import com.steelzack.coffee.system.queues.QueuePostActivityImpl;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;

@Accessors(chain = true)
@Getter
@Service
public class PostProcessorImpl extends ProcessorAbstract implements PostProcessor {

    private static final Logger logger = Logger.getLogger(PostProcessorImpl.class);

    @Autowired
    private QueuePostActivityImpl queuePostActivity;

    @Override
    public void callPostActions( //
            Employee employee, //
            final String name, //
            List<PostAction> postActions, //
            final QueueCallable parentCallable //
    ) {
        PostActionCallableImpl postActionCallable = new PostActionCallableImpl(name);
        parentCallable.getAllCallables().add(postActionCallable);
        postActions.stream().forEach( //
                postActionCallable::addPostAction //
        ); //
    }

    @Override
    public QueueAbstract getExecutorServiceQueue() {
        return queuePostActivity;
    }

    @Override
    public String getExecutorName(Callable<Boolean> callable) {
        return ((ActionCallable)callable).getName();
    }

    @Override
    public void addQueueSize(int queueSize, String name) {
        queuePostActivity.setQueueSize(queueSize, name);
    }

    @Override
    public void initExecutors() {
        queuePostActivity.initExecutors();
    }

    @Override
    public void stopExectutors() {
        queuePostActivity.stopExecutors();
    }
}
