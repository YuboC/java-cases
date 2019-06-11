package caizifu;

import java.util.Scanner;

public class Guessing {
    //主方法
    public static void main(String[] args) {
        System.out.println("开始游戏");

        char[] chs = generate();//调用生成方法 获取随机字符数组
        // System.out.println(chs);

        while (true) {
            System.out.print("请输入5个字母：");
            Scanner scan = new Scanner(System.in);
            String shu = scan.next();//.next()得字符；.nextInt()得实数 方法执行时都会造成堵塞，等待用户在命令行输入数据回车确认
            char[] input = shu.toCharArray(); //将字符串转化为字符数组
            int[] result = check(chs, input); //调用对比方法
            int count = 0; //计算猜错次数
            if (result[0] == chs.length) {
                int score = 100 * chs.length - 5 * count;
                System.out.println("恭喜你猜对了" + "得分为" + score);
                break;

            } else {
                count++;
                System.out.println("猜对了" + result[1] + "个，" + "位置对有" + result[0] + "个");
            }
        }
    }

    //随机生成字符数组
    public static char[] generate() {
        char[] chs = new char[5]; //随机字符数组
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G'
                , 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        boolean[] flag = new boolean[letters.length];

        for (int i = 0; i < 5; i++) {
            int c;
            do {
                c = (int) (Math.random() * 26);
            } while (flag[c] == true);
            flag[c] = false;
            chs[i] = letters[c];
        }
        return chs;
    }

    //对比：随机字符数组与用户输入的数组
    public static int[] check(char[] chs, char[] input) {
        int[] result = new int[2];
        for (int i = 0; i < chs.length; i++) {
            for (int b = 0; b < input.length; b++) {
                if (chs[i] == input[b]) {
                    result[1]++;
                    if (i == b) {
                        result[0]++;
                    }
                    break;
                }
            }
        }
        return result;
    }
}