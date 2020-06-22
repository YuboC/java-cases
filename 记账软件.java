package app;

public class test2 {
    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        boolean flag = true;
        int balance = 10000;
        String details = "收支\t收支金额\t\t账户金额\t\t说    明\n";
        while(flag) {
            System.out.println("-------记账-------");
            System.out.println("\t1 收支明细");
            System.out.println("\t2 登记收入");
            System.out.println("\t3 登记支出");
            System.out.println("\t4 退出");
            System.out.print("\t请选择（1-4）：");
            int select = input.nextInt();

            switch (select) {
                case 1:
                    System.out.println(details);
                    break;
                case 2:
                    System.out.print("输入收入金额：");
                    int money = input.nextInt();

                    System.out.print("收入说明：");
                    String info = input.next();

                    balance += money;
                    //收入    1000    11000   劳务费
                    details += "收入\t" + money + "\t\t" + balance + "\t\t" + info + "\n";
                    break;
                case 3:
                    System.out.print("输入收入金额：");
                    money = input.nextInt();
                    System.out.print("支出说明：");
                    info = input.next();
                    balance -= money;
                    //支出    1000    11000   劳务费
                    details += "支出\t" + money + "\t\t" + balance + "\t\t" + info + "\n";
                    break;
                case 4:
                    System.out.print("确定退出？(Y/N)");
                    char confirm = input.next().charAt(0);
                    if (confirm == 'Y' || confirm == 'y') {
                        flag = false;
                    }
            }
        }
    }
}
