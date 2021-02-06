package kurs;

public class Main {

    public static void main(String[] args) {
	    //создаем тарелки с едой, точнее, без еды, изначально пустые
        Plates plates = new Plates();
        //создаем котов с разным аппетитом, изначально голодных
        Cats cats = new Cats();
        //в цикле выводим меню, предлагаем выбрать операцию
        play(cats, plates);
        //закрываем сканер
        Input.close();
    }

    public static void play(Cats cats, Plates plates) {
        int answer;
        do {
            printMenu();
            answer = Input.enterInt("Введите номер операции", 0, 6);
            if (answer == 1) {
                cats.print();
            }
            if (answer == 2) {
                plates.print();
            }
            if (answer == 3) {
                plates.fillIn();
            }
            if (answer == 4) {
                cats.feedCats(plates);
            }
            if (answer == 5) {
                cats.feedCat(plates);
            }
            if (answer == 6) {
                cats.timePassed();
            }
        } while (answer != 0);
    }

    public static void printMenu() {
        System.out.println("---------------------------------");
        System.out.println("1. Информация о котах");
        System.out.println("2. Информация о тарелках");
        System.out.println("3. Добавить еду во все тарелки");
        System.out.println("4. Покормить всех котов");
        System.out.println("5. Покормить выбранного кота с выбранной тарелки");
        System.out.println("6. Прошло время");
        System.out.println("0. Выход");
        System.out.println("---------------------------------");
    }

}
