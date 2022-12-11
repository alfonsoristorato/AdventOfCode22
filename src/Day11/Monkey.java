package Day11;

import java.util.List;

public class Monkey {
    public Integer id;
    public List<Long> itemsHeld;
    public String operationCommand;
    public Integer test;
    public Integer ifTrue;
    public Integer ifFalse;
    public Integer itemsInspected;

    public Integer getItemsInspected() {
        return itemsInspected;
    }

    public Monkey(Integer id, List<Long> itemsHeld, String operationCommand, Integer test, Integer ifTrue,
            Integer ifFalse) {
        this.id = id;
        this.itemsHeld = itemsHeld;
        this.operationCommand = operationCommand;
        this.test = test;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
        this.itemsInspected = 0;
    }

    public Long doOperation(Long item, String operationCommand) {
        Character operation = operationCommand.charAt(0);
        String operationValue = operationCommand.split("\\s")[1].replaceAll("\\s", "");
        Long operationValueInt = !operationValue.equals("old") ? Integer.parseInt(operationValue) : item;

        switch (operation) {
            case '+' -> {
                item = item + operationValueInt;
            }
            case '*' -> {
                item = item * operationValueInt;
            }

        }
        return item;
    }

}
