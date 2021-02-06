package kurs;
/*
изначально кот голоден, может кушать только определенное
количество еды за раз, если в тарелке меньше, то отказывается
когда становится сыт, фиксируем, через сколько часов проголодается
уменьшаем это поле с "течением времени", чтобы снять флаг сыт
*/
public class Cat {
    private final String name;
    private final int appetite;
    private boolean full;
    //сколько часов будет сытым
    private final int hoursWillBeFull;
    //через сколько часов проголодается
    private int hoursWillGetHungry;

    public Cat(String name, int appetite, int hoursWillBeFull) {
        this.name = name;
        this.appetite = appetite;
        this.hoursWillBeFull = hoursWillBeFull;
        full = false;
        hoursWillGetHungry = 0;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean isFull() {
        return full;
    }

    public String getName() {
        return name;
    }

    //прошло время, кот мог проголодаться
    public void timePassed(int hours) {
        if (hours <= 0) {
            System.out.printf("Время не стоит на месте и не течет вспять!\n");
            return;
        }
        //уменьшаем "через сколько проголодается"
        hoursWillGetHungry -= hours;
        //если ушли в минус, обнуляем
        if (hoursWillGetHungry < 0) {
            hoursWillGetHungry = 0;
        }
        //и снимаем сытость, если она есть
        if (hoursWillGetHungry == 0 && isFull()) {
            full = false;
        }
    }

    //кот говорит
    public void says(String msg) {
        System.out.printf("%s: %s\n", name, msg);
    }

    //кот кушает
    public void eats(Plate plate) {
        if (full) {
            says("я уже сыт, не хочу!");
            return;
        }
        int plateAmount = plate.getAmount();
        String plateName = plate.getName();
        if (plateAmount == 0) {
            says(String.format("я не идиот, тут же (%s) пусто!", plateName));
            return;
        }
        if (plateAmount < appetite) {
            says(String.format("для меня этого (%d) слишком мало, надо %d!", plateAmount, appetite));
            return;
        }
        plate.decrease(appetite);
        full = true;
        //начинаем обратный отсчет времени, "сколько часов будет сытым"
        //назначаем в "через сколько станет голодным"
        hoursWillGetHungry = hoursWillBeFull;
        says(String.format("покушал %d отсюда (%s)...", appetite, plateName));
    }

    //печать
    public void print() {
        says(String.format("аппетит у меня %d - %s", appetite, full ?
                String.format("сыт, проголодаюсь через %dч", hoursWillGetHungry) : "голоден"));
    }
}
