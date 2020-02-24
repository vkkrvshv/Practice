import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите количество купюр номиналов 1000,500,100,30.\n");
        Scanner in = new Scanner(System.in);
        int m1000 = in.nextInt();
        int m500 = in.nextInt();
        int m100 = in.nextInt();
        int m30 = in.nextInt();
        int q0 = 0, q1 = 0, q2 = 0, q3 = 0;
        int sum = m1000 * 1000 + m500 * 500 + m100 * 100 + m30 * 30; //общая сумма имеющихся денег
        System.out.print("Введите сумму, которую хотите получить.\n");
        int m = in.nextInt();
        if (m > sum || m % 10 > 0|| m < 30) System.out.print("Сумму невозможно выдать.");
        else {
            while (m % 100 > 0 && m > 0 && m30 > 0) {
                q3++;
                m = m - 30;
            }
            while (m1000 > 0 && m - 1000 >= 0) {
                m = m - 1000;
                m1000--;
                q0++;
            }
            while (m500 > 0 && m - 500 >= 0) {
                m = m - 500;
                m500--;
                q1++;
            }
            while (m100 > 0 && m - 100 >= 0) {
                m = m - 100;
                m100--;
                q2++;
            }
            if (m == 0)
                System.out.print("1000-х купюр " + q0 + "\n500-рублевых купюр " + q1 + "\n100-рублевых купюр " + q2 + "\n30-рублевых купюр " + q3);
        }
    }
}
