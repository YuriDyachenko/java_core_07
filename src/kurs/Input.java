package kurs;
/*
здесь мы будем вводить разные данные с проверкой и без ошибок
*/
import java.util.Scanner;

public abstract class Input {
    //некрасиво, а что делать
    private static final Scanner scanner = new Scanner(System.in);

    //чтобы закрыть сканер в конце программы
    public static void close() {
        scanner.close();
    }

    //ввод числа с обработкой ошибок ввода и проверкой границ (включительно)
    public static int enterInt(String msg, int leftBound, int rightBound) {
        int res;
        while (true) {
            System.out.printf("%s (число от %d до %d): ", msg, leftBound, rightBound);
            try {
                res = scanner.nextInt();
                if (res < leftBound) {
                    System.out.printf("Ввели число меньше, чем %d!\n", leftBound);
                    continue;
                }
                if (res > rightBound) {
                    System.out.printf("Ввели число больше, чем %d!\n", rightBound);
                    continue;
                }
                break;
            } catch (Exception e) {
                //обязательно нужно почистить неправильный ввод
                //а то цикл станет бесконечным
                scanner.nextLine();
                System.out.println("Что-то не то ввели!");
            }
        }
        return res;
    }

    //ввод строки
    public static String enterString(String msg) {
        System.out.printf("%s: ", msg);
        return scanner.next();
    }
}
