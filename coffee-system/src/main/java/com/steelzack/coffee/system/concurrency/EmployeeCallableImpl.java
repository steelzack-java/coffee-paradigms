package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.Employees;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.concurrent.Callable;

@Accessors(chain = true)
@Getter
@Service
public class EmployeeCallableImpl implements Employee, Callable<Boolean> {
    private static final Logger logger = Logger.getLogger(EmployeeCallableImpl.class);
    public static final String SCHEDULED_TASK_FAILED_TO_EXECUTE = "scheduled task faild to execute!";

    private Employees.Employee employee;

    public EmployeeCallableImpl( //
                                 Employees.Employee employee //
    ) {
        this.employee = employee;
    }

    @Override
    public Boolean call() throws Exception {
        logger.info(MessageFormat.format("Employee {0} is waiting in line", employee.getName()));
        return true;
    }

}
