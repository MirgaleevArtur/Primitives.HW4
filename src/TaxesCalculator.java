import java.util.Scanner;

public class TaxesCalculator {

    private int earnings; //доходы
    private int spendings; //расходы

    private static final String EARNINGS_TAXES = "УСН доходы"; //УСН - налог 6% от доходов
    private static final String EARNINGS_MINUS_SPENDINGS_TAXES = "УСН доходы минус расходы"; //УСН - налог 15% от разницы доходов и расходов

    public void taxSystemChoose() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите операцию и введите её номер:\n" +
                    "1. Добавить новый доход\n" +
                    "2. Добавить новый расход\n" +
                    "3. Выбрать систему налогообложения");

            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("end")) {
                break;
            }

            int operation = Integer.parseInt(input);
            switch (operation) {
                case 1:
                    System.out.print("Введите сумму дохода: ");
                    String moneyEarnStr = scanner.nextLine();
                    int moneyEarn = Integer.parseInt(moneyEarnStr);
                    earnings += moneyEarn;
                    break;

                case 2:
                    System.out.print("Введите сумму расхода: ");
                    String moneySpendStr = scanner.nextLine();
                    int moneySpend = Integer.parseInt(moneySpendStr);
                    spendings += moneySpend;
                    break;

                case 3:
                    taxChooser();
                    break;

                default:
                    System.out.println("Такой операции нет");

            }

        }

        System.out.println("Программа завершена!");
    }


    public void taxChooser() {
        int taxEarningsMinusSpendings = taxEarningsMinusSpendingsSystem(earnings, spendings);
        int taxEarningsOnly = taxEarningsSystem(earnings);

        if (taxEarningsOnly < taxEarningsMinusSpendings) {
            System.out.println("Мы советуем вам " + EARNINGS_TAXES);
            System.out.println("Ваш налог составит: " + taxEarningsOnly + " рублей");
            System.out.println("Налог на другой системе: " + taxEarningsMinusSpendings + " рублей");
            System.out.println("Экономия: " + (taxEarningsMinusSpendings - taxEarningsOnly) + " рублей");
        } else if (taxEarningsOnly > taxEarningsMinusSpendings) {
            System.out.println("Мы советуем вам " + EARNINGS_MINUS_SPENDINGS_TAXES);
            System.out.println("Ваш налог составит: " + taxEarningsMinusSpendings + " рублей");
            System.out.println("Налог на другой системе: " + taxEarningsOnly + " рублей");
            System.out.println("Экономия: " + (taxEarningsOnly - taxEarningsMinusSpendings) + " рублей");
        } else {
            System.out.println("Ваш налог составит: " + taxEarningsMinusSpendings + " рублей");
            System.out.println("Налог на другой системе: " + taxEarningsOnly + " рублей");
            System.out.println("Можете выбрать любую систему налогообложения");
        }
    }

    //расчет УСН - налог 15% от разницы доходов и расходов
    public static int taxEarningsMinusSpendingsSystem(int earnings, int spendings) {
        int tax = (earnings - spendings) * 15 / 100;
        if (tax >= 0) {
            return tax;
        } else {
            return 0;
        }
    }

    //расчет УСН - налог 6% от доходов
    public static int taxEarningsSystem(int earnings) {
        int tax = earnings * 6 / 100;
        if (tax >= 0) {
            return tax;
        } else {
            return 0;
        }
    }
}

