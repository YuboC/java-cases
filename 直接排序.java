package app;

public class test {
    public static void main(String[] args) {
    int [] arr = {5,1,2,3,9,0};
    for(int i = 0; i < arr.length; i++){
        int min = arr[i];
        int index = i;
        for(int j = i + 1 ; j < arr.length; j ++){
            if(arr[j] < min){
                min = arr[j];
                index = j;

            }
        }
        if (index != i){
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }
    for(int i = 0; i < arr.length; i ++){
        System.out.print(arr[i] + " ");
    };
    }
}



