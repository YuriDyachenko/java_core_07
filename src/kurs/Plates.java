package kurs;
/*
массив тарелок и все операции с этим массивом
*/
public class Plates {
    private final Plate[] plates;

    public Plates() {
        plates = new Plate[4];
        plates[0] = new Plate("блюдце", 5);
        plates[1] = new Plate("тарелка", 10);
        plates[2] = new Plate("миска", 20);
        plates[3] = new Plate("тазик", 50);
    }

    //печать
    public void print() {
        System.out.println("ТАРЕЛКИ:");
        for (Plate plate : plates) {
            plate.print();
        }
    }

    //общее пустое место
    public int getFit() {
        int res = 0;
        for (Plate plate : plates) {
            res += plate.getFit();
        }
        return res;
    }

    //общее количество еды
    public int getAmount() {
        int res = 0;
        for (Plate plate : plates) {
            res += plate.getAmount();
        }
        return res;
    }

    //ищет тарелку с едой, чтобы еды хватило коту с конкретным аппетитом
    public Plate findWithAmount(int appetite) {
        for (Plate plate : plates) {
            if (plate.getAmount() >= appetite) {
                return plate;
            }
        }
        return null;
    }

    //ищет тарелку по имени
    public Plate findByName(String name) {
        for (Plate plate : plates) {
            if (plate.getName().equals(name)) {
                return plate;
            }
        }
        return null;
    }

    //заполнить все тарелки введенным количеством еды
    //предлагает от 1 до общего пустого места
    public void fillIn() {
        int fitAll = getFit();
        if (fitAll == 0) {
            System.out.println("В тарелках больше нет места!");
            return;
        }
        int amount = Input.enterInt("Сколько еды добавить", 1, fitAll);
        if (amount < fitAll) {
            System.out.println("На все тарелки еды не хватит, заполним, сколько получится");
        }
        for (Plate plate : plates) {
            int fit = plate.getFit();
            //если нет места, пропускаем
            if (fit == 0) {
                continue;
            }
            //передаем в тарелку меньшее из двух
            int take = Math.min(amount, fit);
            plate.increase(take);
            //уменьшаем распределяемое количество
            amount -= take;
            if (amount == 0) {
                break;
            }
        }
        print();
    }
}
