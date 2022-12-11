package Day11;

import java.math.BigInteger;
import java.util.List;

public class Monkey {
    public Integer id;
    public List<BigInteger> itemsHeld;
    public String operationCommand;
    public Integer test;
    public Integer ifTrue;
    public Integer ifFalse;
    public Long itemsInspected;

    public Long getItemsInspected() {
        return itemsInspected;
    }

    public Monkey(Integer id, List<BigInteger> itemsHeld, String operationCommand, Integer test, Integer ifTrue,
            Integer ifFalse) {
        this.id = id;
        this.itemsHeld = itemsHeld;
        this.operationCommand = operationCommand;
        this.test = test;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
        this.itemsInspected = (long) 0;
    }

    public BigInteger doOperation(BigInteger item, String operationCommand, Boolean divideWorry) {
        Character operation = operationCommand.charAt(0);
        String operationValue = operationCommand.split("\\s")[1].replaceAll("\\s", "");
        BigInteger operationValueInt = !operationValue.equals("old") ? new BigInteger(operationValue) : item;

        switch (operation) {
            case '+' -> {
                item = item.add(operationValueInt);
            }
            default -> {
                item = item.multiply(operationValueInt);
            }

        }
        if (!divideWorry) {
            // as BigInt would overflow its max, we reduce its value bye calculating
            // the remainder of itself against the multiplication of all divideBy
            item = item.remainder(BigInteger.valueOf(9_699_690));
        }
        return item;
    }

}
