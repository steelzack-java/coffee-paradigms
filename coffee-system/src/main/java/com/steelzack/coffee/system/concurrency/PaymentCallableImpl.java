package com.steelzack.coffee.system.concurrency;

import com.steelzack.coffee.system.input.CoffeeMachines;
import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by joaofilipesabinoesperancinha on 01-05-16.
 */
public class PaymentCallableImpl implements Payment, Callable<Boolean> {

    private final Logger logger = Logger.getLogger(PaymentCallableImpl.class);
    private CoffeeMachines.CoffeMachine.PaymentTypes.Payment chosenPayment;

    public PaymentCallableImpl(CoffeeMachines.CoffeMachine.PaymentTypes.Payment chosenPayment)
    {
        this.chosenPayment = chosenPayment;
    }

    @Override
    public Boolean call() throws Exception {
        logger.info(MessageFormat.format("Payment with {0}", chosenPayment.getName()));
        TimeUnit.MILLISECONDS.sleep(chosenPayment.getTime());
        return true;
    }
}
