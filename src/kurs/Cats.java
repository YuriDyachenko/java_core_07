package kurs;
/*
массив котов и все операции с этим массивом
*/
public class Cats {
    private final Cat[] cats;

    public Cats() {
        cats = new Cat[5];
        cats[0] = new Cat("Барсик", 12, 5);
        cats[1] = new Cat("Горбачев", 15, 8);
        cats[2] = new Cat("Мишка", 10, 4);
        cats[3] = new Cat("Непоседа", 8, 3);
        cats[4] = new Cat("Ананасик", 9, 4);
    }

    //печать
    public void print() {
        System.out.println("КОТЫ:");
        for (Cat cat : cats) {
            cat.print();
        }
    }

    //все ли коты сыты
    public boolean isFull() {
        for (Cat cat : cats) {
            if (!cat.isFull()) {
                return false;
            }
        }
        return true;
    }

    //ищет кота по имени
    public Cat findByName(String name) {
        for (Cat cat : cats) {
            if (cat.getName().equals(name)) {
                return cat;
            }
        }
        return null;
    }

    //прошло время, некоторые коты могли стать голодными
    public void timePassed() {
        int hours = Input.enterInt("Сколько часов прошло", 1, 24);
        for (Cat cat : cats) {
            cat.timePassed(hours);
        }
        print();
    }

    //покормить выбранного кота из выбранной тарелки
    public void feedCat(Plates plates) {
        Cat cat;
        //будем повторять, пока не введут правильное имя с учетом регистра
        while (true) {
            String catName = Input.enterString("Какого кота покормить");
            cat = findByName(catName);
            if (cat != null) {
                break;
            }
            System.out.printf("Кот по имени %s не найден, попробуйте еще раз!\n", catName);
        }
        Plate plate;
        //будем повторять, пока не введут правильное имя с учетом регистра
        while (true) {
            String plateName = Input.enterString("Из какой тарелки");
            plate = plates.findByName(plateName);
            if (plate != null) {
                break;
            }
            System.out.printf("Тарелка (%s) не найдена, попробуйте еще раз!\n", plateName);
        }
        //здесь специально ничего не проверяем, чтобы отработал метод eats полностью
        cat.eats(plate);
        //и ничего не выводим, чтобы видеть сообщения именно метода eats
    }

    //покормить всех котов из всех тарелок
    public void feedCats(Plates plates) {
        if (isFull()) {
            System.out.println("Все коты сыты, никого кормить не нужно!");
            return;
        }
        if (plates.getAmount() == 0) {
            System.out.println("В тарелках пусто, никого покормить не получится!");
            return;
        }
        //перебираем всех котов
        for (Cat cat : cats) {
            //сытых котов пропускаем
            if (cat.isFull()) {
                continue;
            }
            //тарелку нужно искать не просто так, а чтобы в ней было
            //достаточно еды для кота конкретного
            Plate plate = plates.findWithAmount(cat.getAppetite());
            //если нет подходящей тарелки, именно этого кота покормить не получится
            if (plate == null) {
                continue;
            }
            //даем коту тарелку целиком, нам все равно, сколько там еды
            //он скушает свою порцию, остальное останется
            cat.eats(plate);
        }
        //ничего не выводим, чтобы видеть сообщения именно метода eats
    }
}
