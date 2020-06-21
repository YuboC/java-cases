package app;

public class test {
    public static void main(String[] args) {
    int [] arr = {5,1,2,3,9,0};
    for(int i = 1; i < arr.length; i++){

        for(int j = 0; j < arr.length - i; j ++){
            if(arr[j] > arr[j+1]){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
    for (int i = 0; i < arr.length; i++){
        System.out.print(arr[i] + " ");
    }
    }
}

//goal: [0,1,2,3,5,9] or [9,5,3,2,1,0]
//round 1:
//1st loop: arr[0] ~ arr[1], exchange, [1,5,2,3,9,0]
//2nd loop: arr[1] ~ arr[2], exchange, [1,2,5,3,9,0]
//3rd loop: arr[2] ~ arr[3], exchange, [1,2,3,5,9,0]
//4th loop: arr[3] ~ arr[4], keep, [1,2,3,5,9,0]
//5th loop: arr[4] ~ arr[5], exchange, [1,2,3,5,0,9]
//the largest one had been settled

//round 2:
//1st loop: arr[0] ~ arr[1], keep, [1,2,3,5,0,9]
//2nd loop: arr[1] ~ arr[2], keep, [1,2,3,5,0,9]
//3rd loop: arr[2] ~ arr[3], keep, [1,2,3,5,0,9]
//4th loop: arr[3] ~ arr[4], exchange, [1,2,3,0,5,9]

//round 3:
//1st loop: arr[0] ~ arr[1], keep, [1,2,3,5,0,9]
//2nd loop: arr[1] ~ arr[2], keep, [1,2,3,5,0,9]
//3rd loop: arr[2] ~ arr[3], keep, [1,2,3,5,0,9]