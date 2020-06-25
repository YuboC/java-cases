
public class oop {
    public static void main(String[] args){

        MyDate my = new MyDate();
        my.year = 2019;
        my.month = 5;
        my.day = 21;

        my.plus(1,30,70);
        System.out.println(my.year + "年" + my.month + "月" + my.day + "日");
    }
}

class MyDate{
    int year;
    int month;
    int day;
    boolean isLeapYear(){
        if(year%4==0 && year%100!=0 || year%400==0){
            return true;
        }
        return false;
    }
    void set(int y, int m, int d){
        year = y;
        month = m;
        day = d;
    }
    void plus(int y, int m, int d){
        day += d;
        month += m;
        year += y;

        while (true) {
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10) {
                if (day > 31) {
                    day -= 31;
                    month++;
                }
            }else if(month == 4 || month == 6 || month == 9 || month == 11){
                if(day > 30){
                    day -= 30;
                    month ++;
                }
            }else if(month == 2){
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                    if (day > 29) {
                        day -= 29;
                        month ++;
                    }
                }else {
                    if (day > 28) {
                        day -= 28;
                        month ++;
                    }
                }
            } else if (month == 12) {
                if (day > 31) {
                    day -= 31;
                    month =1;
                    year++;
                }
            } else if (month > 12) {
                while (month > 12) {
                    month -= 12;
                    year++;
                }
            }
            if(month >= 1 && month <= 12){
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    if (day <= 31) {
                        break;
                    }
                } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                    if (day <= 30) {
                        break;
                    }
                } else if (month == 2) {
                    if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                        if (day <= 29) {
                            break;
                        }
                    }else{
                        if (day <= 28) {
                            break;
                        }
                    }
                }
            }
        }
    }

}
