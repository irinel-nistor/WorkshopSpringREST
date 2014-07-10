package ro.workshop.util;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import org.springframework.beans.BeanUtils;
import ro.workshop.core.domain.*;

public class Functions {

    public static class toRest {

        public static Function<PrintMachine, ro.workshop.rest.domain.PrintMachine> printMachine() {
            return PrintMachineCoreToRest.INSTANCE;
        }

        public static Function<Order, ro.workshop.rest.domain.Order> order() {
            return OrderCoreToRest.INSTANCE;
        }
    }

    public static class toCore {

        public static Function<ro.workshop.rest.domain.Order, Order> order() {
            return OrderRestToCore.INSTANCE;
        }
    }

    private static enum PrintMachineCoreToRest implements Function<PrintMachine, ro.workshop.rest.domain.PrintMachine> {
        INSTANCE;

        @Override
        public ro.workshop.rest.domain.PrintMachine apply(PrintMachine source) {
            ro.workshop.rest.domain.PrintMachine target = new ro.workshop.rest.domain.PrintMachine();
            BeanUtils.copyProperties(source, target);
            return target;
        }
    }

    private static enum OrderCoreToRest implements Function<Order, ro.workshop.rest.domain.Order> {
        INSTANCE;

        @Override
        public ro.workshop.rest.domain.Order apply(Order source) {
            ro.workshop.rest.domain.Order transformed = new ro.workshop.rest.domain.Order();
            transformed.setId(source.getId());
            transformed.setColor(source.getDetails().getColor().toString());
            transformed.setSize(source.getDetails().getSize().toString());
            transformed.setMessage(source.getDetails().getMessage());
            transformed.setStatus(source.getStatus().toString());
            return transformed;
        }
    }

    private static enum OrderRestToCore implements Function<ro.workshop.rest.domain.Order, Order> {
        INSTANCE;

        @Override
        public Order apply(ro.workshop.rest.domain.Order source) {
            Order transformed = new Order();
            transformed.setId(source.getId());
            transformed.setDetails(new OrderDetails());
            fillInDefaultsIfNeeded(source);
            transformed.setStatus(PrintStatus.valueOf(source.getStatus().toUpperCase()));
            transformed.getDetails().setMessage(source.getMessage());
            transformed.getDetails().setColor(Color.valueOf(source.getColor().toUpperCase()));
            transformed.getDetails().setSize(Size.valueOf(source.getSize().toUpperCase()));
            return transformed;
        }

        private void fillInDefaultsIfNeeded(ro.workshop.rest.domain.Order order){
            if (Strings.isNullOrEmpty(order.getStatus())){
                order.setStatus(PrintStatus.WAITING.toString());
            }
            if (Strings.isNullOrEmpty(order.getColor())){
                order.setStatus(Color.BLUE.toString());
            }
            if (Strings.isNullOrEmpty(order.getSize())){
                order.setStatus(Size.MEDIUM.toString());
            }
        }
    }
}
