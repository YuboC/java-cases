package app;

public class test {
    public static void main(String[] args) {
    java.util.Scanner input = new java.util.Scanner(System.in);

    System.out.print("nia：");
    int year = input.nextInt();
    System.out.print("yue：");
    int yue = input.nextInt();
    System.out.print("ri：");
    int ri = input.nextInt();

    int days = ri;
    for (int i = 0; i < yue ; i++){
        if(i == 4 || i == 6 || i == 9 || i == 11){
            days += 30;
        }

        else if(i == 2){
            if(year % 4 ==0 && year % 100 != 0 || year % 400 == 0){
                days += 29;
            }else{days += 28;}
        }

        else {days += 30;}

    }

    for (int j = 2000; j < year ; j ++){
        if(j % 4 ==0 && j % 100 != 0 || j % 400 ==0){
            days += 366;
        } else {days += 365;
        }


    }

    if (days % 5 == 1 | days % 5 == 2 | days % 5 ==3){
        System.out.println("da yu");
    }else { System.out.println("shai wang");
    }

    }
}



