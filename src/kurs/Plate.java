package kurs;
/*
изначально тарелка пустая, нужно будет наполнить едой
у каждой тарелки может быть своя емкость, до которой ее можно наполнить
*/
public class Plate {
    private final String name;
    private final int capacity;
    private int amount;

    public Plate(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        amount = 0;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    //уменьшить количество еды
    public void decrease(int amount) {
        if (amount <= 0) {
            System.out.printf("%s: неправильное значение для уменьшения = %d\n", name, amount);
            return;
        }
        if (amount > this.amount) {
            System.out.printf("%s: слишком большое значение для уменьшения = %d\n", amount);
            return;
        }
        this.amount -= amount;
        System.out.printf("%s: количество еды уменьшается на %d\n", name, amount);
    }

    //увеличить количества еды
    public void increase(int amount) {
        if (amount <= 0) {
            System.out.printf("%s: неправильное значение для увеличения = %d\n", name, amount);
            return;
        }
        if (amount > getFit()) {
            System.out.printf("%s: слишком большое значение для увеличения = %d\n", name, amount);
            return;
        }
        this.amount += amount;
        System.out.printf("%s: количество еды увеличивается на %d\n", name, amount);
    }

    //печать
    public void print() {
        System.out.printf("%s: количество еды %d из %d\n", name, amount, capacity);
    }

    //сколько еще можно добавить еды
    public int getFit() {
        return capacity - amount;
    }

}
