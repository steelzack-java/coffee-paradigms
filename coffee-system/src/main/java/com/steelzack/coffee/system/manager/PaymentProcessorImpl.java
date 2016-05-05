package com.steelzack.coffee.system.manager;

import com.steelzack.coffee.system.concurrency.PaymentCallableImpl;
import com.steelzack.coffee.system.input.CoffeeMachines.CoffeMachine.PaymentTypes.Payment;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

import static com.steelzack.coffee.system.concurrency.EmployeeCallableImpl.SCHEDULED_TASK_FAILED_TO_EXECUTE;

/**
 * Created by joaofilipesabinoesperancinha on 30-04-16.
 */
@Accessors(chain = true)
@Builder
@Getter
@Service
public class PaymentProcessorImpl extends ProcessorImpl implements PaymentProcessor {

    private static final Logger logger = Logger.getLogger(PaymentProcessorImpl.class);
    private Payment chosenPayment;

    @Override
    public void setChosenPayment(Payment chosenPayment) {
        this.chosenPayment = chosenPayment;
    }

    @Override
    public void callPayCoffee() {
        try {
            if (!managedExecutorService.submit(new PaymentCallableImpl(chosenPayment)).get()) {
                logger.error(SCHEDULED_TASK_FAILED_TO_EXECUTE);
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
