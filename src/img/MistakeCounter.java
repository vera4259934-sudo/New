
/*
public class MistakeCounter {

    private MistakeCounter() {
    }

    public static MistakeCounter getSharedInstance() {
        return sharedInstance;
    }

    private static final MistakeCounter sharedInstance = new MistakeCounter();

    public void setMilkAmount(int amount) {
        milkAmount = amount;
        flourAmount = amount;
        eggAmount= amount;
        cheese1Amount = amount;
        cheese2Amount = amount;
        tomatoAmount = amount;
    }
    public void setFlourAmount(int amount) {
        flourAmount = amount;

    }
    public void setEggAmount(int amount) {
        eggAmount= amount;
    }
    public void setCheese1Amount(int amount) {
        cheese1Amount = amount;
    }

    public void setCheese2Amount(int amount) {
        cheese2Amount = amount;
    }

    public void setTomatoAmount(int amount) {
        tomatoAmount = amount;
    }





    private int milkAmount;
    private int flourAmount;
    private int eggAmount;
    private int cheese1Amount;
    private int cheese2Amount;
    private int tomatoAmount;

    public int getMistakeAmount() {
        int mistakeAmount = 0;
        if(milkAmount != Recipe_1.MILK_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }
        // return mistakeAmount;

        if(flourAmount != Recipe_1.FLOUR_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }
        // return mistakeAmount;

        if(eggAmount != Recipe_1.EGGS_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }
        // return mistakeAmount;

        if(cheese1Amount != Recipe_1.CHEESE1_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }
        // return mistakeAmount;

        if(cheese2Amount != Recipe_1.CHEESE2_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }
        // return mistakeAmount;

        if(tomatoAmount != Recipe_1.TOMATO_RECIPE_AMOUNT) {
            mistakeAmount += 1;
        }
        return mistakeAmount;

    }
}*/