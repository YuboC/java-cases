###　Recursion | 递归

[TOC]

### 斐波那契数列

#### Fibo( )

- **00:00 -- 04:00 斐波那契数列**

  ```java
  // base case: F(0) = 0; F(1) = 1;
  // Recursion rule: F(n) = F(n-1) + F(n-2)
  int fibo (int n){
      //check if current level is the base case:
      if(n <= 0){ //corner case n=0; case 1 n=0
          return 0;
      } else if(n == 1){
          return 1;
      }
      //Recursion rule:
      return fibo(n-1) + fibo(n-2); 
  }
  
  /* Describe the code in English:
  This lib takes one variable as input. and also its return type equal to an integer value. From the line 1 to line 6 is gonna define the base case of the recursion fucntion. when the input variable equal to 0 or 1, basically it just return the corresponding value. And its recursion rule is defined on line 7 that returning the aggregate value of previous 2 cycle of recursion value.
  */
  ```



- **08:10 -- 21:41 斐波那契数列递归树复杂度分析**

  - **Recursion Tree:**                                                   |    Call Stack     底

    [1]                  **F(4)  n=4**                         O(1)		 |	 level 1		   ↑
    				 	/                \ 									   |							|
    [2]             **F(3)**      +      F(2)                  O(2)		 |	 level 2			|
    			     /        \   	    /       \							   |							 |
    [3] 		**F(2)**  +  F(1)   F(1)  +  F(0)	    O(4)		 | 	level 3            |
    		   /         \														 |							 |
    [4]     **F(1)**  +  F(0)						           ...			 |     level 4			顶
    		  |																	|
    **immediately return 1**

  ```markdown
  **Call Stack Mechanism**: can be regarded as a `global acccessible information` that tells you what happend before each `break point` in each level:  
   
   fibo(n-1) `break point` + fibo(n-2)
   - level == 1, local info: n== 4（local variable）
   - level == 2, local info: n== 3
   ...
  
  **Space Complexity**: sum of RAM been used at each leve of Call Stack. sum of the space complexity of all nodes on the black path(from down to bottom). In this case, it is O(n) == O(4)
  
  **Time Complexity**: Sum of all the nodes (size of nodes may defferent)
  1 + 2^1 + 2^3 + 2^4 + 2^n-1 = O(2^n-1) or O(2^n)
  ```



####  Sample Question a^b

*27:00 -- 40:00*

```
How to calculate a^b (where a is an integer and b is also an integer, we do not care about the corner case where a = 0 or b < 0 for now)
```

- Solution 0:

  ```java
  public int power(int a, int b){
      if(b == 0){    //base case
          return 1;
      }else return a*power(b - 1); //recursion rule
  }
  ```

  - 复杂度分析：

    ​	b  =  n  =  1000			|				Time complexity = O(n)

    ​	|									|				Space complexity = O(n)

    ​	b  =  999					   |

    ​	|  =  998					   |

    ​	...								   |

    ​	b  =  0						   |

    

- Solution 1:

  ```java
  public int power(int a, int b){
      if(b == 0){
          return 1;
      }else if(b % 2 == 0){
          return power(a, b/2) * power(a, b/2);
      }else{
          return a * power(a, b/2) * power(a, b/2);
      }
  }
  ```

  - 复杂度分析：

    ​														nodes

    ​						**b  =  n  =  10**				1			  |			Time complexity  =  sum of nodes

    ​					/				\									   |										    =  1 + 2 + 4 + ... + 2^log_2(n) 

    ​				**5**					5					  2			 |											 =   **O(n)**   (Same as solution 0)

    ​			/		\													   |			Space complexity = O(log(n))

    ​		**2**			  2									4  		   |

    ​	/		\															   |

     **1**			1										 	8  		   |

  - 优化：

    ```java
    public long power(int a, int b){
        if(b == 0){
            return 1;
        }
        long half_result = power(a, b/2);
        if(b % 2 == 0){
            return half_result * half_result;
        }else{
            return a * half_result * half_result;
        }
    }
    ```

  ----

  

### Sorting Algorithms

 1. Categories:

    ​	a. Selection sort

    ​	b. Merge sort

    ​	c. Quick sort

2. What are they good for

   ​	a. More general scope are covered

   ​	b. Combined with system design (Discussed in System Design)

   ​	c. When solving a design problem. When should I use Merge sort vs QuickSort,

   ​		for instance?

3. How thorough should I know about them and/or use them?

----

### Selection Sort

*48:40 -- *

- Example: 		`int a[i] = {-1, -3, 7, 4}   =>   {-3, -1, 4, 7} ascending order`

  iteration 1: find global min -3				{-1, -3, 7, 4}    =>  -3  {-1,  7,  4}

  iteration 2: find global min in the rest   -3 {-1, 7, 4}   =>  -3   -1  {7,   4}

  iteration 3: find global min in the rest   -3   -1{7, 4}   =>  -3   -1   4   {7}

  iteration 4: find global min and done.

  ```java
  //selection sort an array a[] with size n
  public void selectionSort(int[] a, int n){
      for (int i = 0; i < n-1; i++){//outer loop: how many iterations
          int globalMin = i;
          for(int j = i + 1; j < n; j++){//inner loop: find the global min from the rest elements
              if a[j] < a[globalMin]{
                  globalMin = j; // record the index of the smallest element.
              }
          }
          int temp = a[i]; // swap the global min with a[i]
          a[i] = a[globalMin];
          a[globalMin] = tem;
      }
  }
  ```

  -  时间复杂度分析：

    `for(int i = 0; i < n -1; i++){`  //outer loop: how many iterations

    ​		`for(int j = i + 1; j < n; j++){ `//inner loop: how many elements

    iteration i = 0:	inner		   (0..n-1) = 4

    iteration i = 1:	inner n-1	(1..n-1) = 3

    iteration i = 2:	inner n-2	(2..n-1) = 2

    iteration i = 3:	inner n-3	(3..n-1) = 1

    **Time** = 1+2+3+4+...+n = n(n+1)/2   -->  n^2  --> **O(n^2)**

    **Space** = O(1)  //only local variable needed, no object been newed in heap

    

----

#### 讲解code要点

​	**A complete answer will include the following:**  *51:16 --*

1. Document your assumptions

2. Explain your approach and how you intend to solve the problem

3. provide code comments where applicable

4. Explain the big-O run time complexity of your solution. Justify your answer.

5. Identify any additional data structures you used and justify why you used them.

6. Only provide your best answer to each part of the question.

   ```markdown
   //Selection Sort:
   中文：
   首先Selection sort 的基本原理是， 每一轮筛选出来没有排好序元素的最小值，使其与未排序元素中最左端交换位置，则未排序元素的最小值完成首轮排序，归为最左端（ascending）。在剩余未排序的元素中，重复上述操作，直到最大的元素排到最右端。
   英文：
   First of all, lets look at the code. The line zero define the signature of input function/selection sort function. Basically it takes 2 arguments as input, the first one is input array and the second one is the size of the array. With the return type of void, it will return nothing. The main logic of this hleper function consists 0f 2 for loops. For the outer loop on line 1, it defines how many iterations it needs to run and i represent the start index of each iteration. For the inner loop on line 3, it defines the start of each inner iteration and will find the global min of unsorted elements.
   ```

   

#### selection sort举一反二

​		*58:48 --*

```markdown
Question1 (Amazon):
Given an array stored in Stack1, how to sort the numbers by using addtional `two tacks` (2 stacks solution will be discuss later)

`Stack1`(store input)	[3 1 2 4]
`Stack2`(buffer)		[]						int global_min = 
`Stack3`(store result)	[]

pick elemnent one by one from `Stack1` to `Stack2`, and assign the smallest value to global_min. At the end of each round, put global_min into `Stack2` and move rest of elements in buffer back to its origin location into `Stack1`
```

----



### Merge sort

*1:06:30 --*

`index 0 1 2 ... ... 7	|   a[0...7]  ==>  a[0...3] merge a[4...7]`

​									a[n] = **5, 2, 7, 4, 1, 3, 8, 6**								

​											/		`切一刀`		\							time = O(1) → if liked list O(n)

​									**5,2,7,4**						1386					  

​								/	`一刀`\	   				 / `两刀 `  \				 time = O(2) → if linked list O(n)

​							**5,2**				7,4		 	1,3		 	8,6		  

​						/		\			/		\		/		\		/		\	   time = O(4) → if linked list O(n)

​					  **5**		  2		7		  4	  1		3	   8		6	   ...

​																`n个元素切n/2刀`		  time = O(n/2) → if linked list O(n)

Space on heap = n + n/2 + n/4 + n/8 + ... + 2 + 1 = O(n)

Space on stack = O(log n)

`Total Space complexity = O(n) + O(log n) = O(n)`

`Time complexity = 1 + 2 + 4 + ... + n/2 = O(n) | if linked list O(n logn)`

How many levels above this line = log n, each level have O(1) space complexity

----

How many levels below this line = log n, each level have O(n) time complexity

`Time complexity = n + n + ... + n = O(n log n)`

​	 		   	 5		  2		7		  4	  1		3	   8		6	   ...

​					  \		/			\		/		   \	 /		   \	 /	

 						2,5				4,7		 		3,1		 	6,8		 this level time complexity = O(n)



​							\				/					  	\			/

​								2,4,5,7								1,3,6,8			  this level time complexity = O(n)

​										\								/

​											1,2,3,4,5,6,7,8								 this level time complexity = O(n)

`Total time = O(n) + O(n log n) = O(n log n)  |  if linked list O(n log n)`

`Extra space = n/2 + n/4 + n/8 + ... + 2 + 1 = O(n) | if linked list O(log n)`



- Main Function (split the array until into single element)

  ```java 
  public int [] mergeSort(int[] array, int left, int right){
      if(left == right){
          return new int[]{array[left]};
      }
      //recursion is below
      int mid = left + (right - left)/2;
      int [] leftResult = mergeSort(array, left, mid);
  //break point...
      int [] rightResult= mergeSort(array, mid+1, right);
      return merge(leftResult, rightResult)//谁小移谁
  }
  ```

  

- Helper function(merge two sorted array)

  ```java
  //this is not the Optimal solution
  private int[] merge(int[] leftResult, int[]rightResult){
      //allocate an array result[] to buffer the solution
      int[] result = new int[leftResult.length + rightResult.length];
      
      int leftIndex = 0;
      int rightIndex = 0;
      int resultIndex = 0;
      while(leftIndex < leftResult.length && rightIndex < rightResult.length){
          if(leftResult[leftIndex] < rightResult[rightIndex]){
              result[resultIndex++] = leftResult[leftIndex++];
          }else{
              result[resultIndex++] = rightResult[rightIndex++];
          }
      }
      while(leftIndex < leftResult.length){
          result[resultIndex++] = leftResult[leftIndex++];
      }
      while(right < rightResult.length){
          result[resultIndex++] = rightResult[rightIndex++];
      }
      return result;
  }
  ```

- Answer (not the optimal)

  ```java
  public class Solution {
    public int[] mergeSort(int[] array) {
      if(array==null||array.length<=1){
        return array;
      }
      int left = 0;
      int right= array.length -1;
      return split(array, left, right);
      }
    private int[] split(int[] array, int left, int right){
      if(left == right){
        return new int []{array[left]};
      }
      int mid = left + (right - left)/2;
      int[] leftHalf = split(array, left, mid);
      int[] rightHalf= split(array, mid+1, right);
      return merge(leftHalf, rightHalf);
    }
    private int[] merge(int[] leftHalf, int[] rightHalf){
      int leftIndex = 0;
      int rightIndex = 0;
      int resultIndex = 0;
      int[] result = new int[leftHalf.length + rightHalf.length];
      while(leftIndex < leftHalf.length && rightIndex < rightHalf.length){
        if(leftHalf[leftIndex] < rightHalf[rightIndex]){
          result[resultIndex++] = leftHalf[leftIndex++];
        }else{
          result[resultIndex++] = rightHalf[rightIndex++];
        }
      }
      while(leftIndex < leftHalf.length){
        result[resultIndex++] = leftHalf[leftIndex++];
      }
      while(rightIndex < rightHalf.length){
        result[resultIndex++] = rightHalf[rightIndex++];
      }
      return result;
    }
  }
  
  ```

  

- Optimal Solution:

  ```java
  public class MergeSort{
      public int[] mergeSort(int[] array){
          if(array == null){ //check corner case if null array
              return array;
          }
      //allocate helper array to help merge step,
      //so that we guarantee no more than O(n) space is used.
      //The space complexity is O(n) in this case.
      int[] helper = new int[array.length];
      mergeSort(array, helper, 0, array.length -1);
      return array;
  	}
      private void mergeSort(int[] array, int[] helper, int left, int right){
          if(left >= right){
              return;
          }
          int mid = left + (right - left)/2;
          mergeSort(array, helper, left, mid);
          mergeSort(array, helper, mid + 1, right);
          merge(array, helper, left, mid, right);
      }
      private void merge(int[] array, int[] helper, int left, int mid, int right){
          //copy the content to helper array and we will merge from the
         	//helper array.
          for (int i = left; i <= right; i++){
              helper[i] = array[i];
          }
          int leftIndex = left;
          int rightIndex = mid + 1;
          while (leftIndex <= mid && rightIndex <= right){
              if(helper[leftIndex] <= helper[rightIndex]){
                  array[left++] = helper[leftIndex++];
              }else{
                  array[left++] = helper[rightIndex++];
              }
          }
          //if we sitll have some elements at left side, we need to copy them
          while(leftIndex <= mid){
              array[left++] = hleper[leftIndex++];
          } 
          //if there are some elements at right side, we do not need to copy 
          //because they are alreaddy in their position
      }
      public static void main(String[]args){
          MergeSort solution = new MergeSort();
          
          //test cased to cover possible situations
          int[] array = null;
          array = solution.mergeSort(array);
          System.out.println(Array.toString(array));
          
          array = new int[0];
          array = solution.mergeSort(array);
          System.out.println(Arrays.toString(array));
          
          array = new int[]{4,3,2,1};
          array = solution.mergeSort(array);
          System.out.println(Arrays.toString(array));
          
          array = new int[]{2,4,5,3,1,2};
          array = solution.mergeSort(array);
          System.out.println(Arrays.toString(array));
      }
      
  ```

  

----



#### 	举一反二

- Question 3: Could we use Merge Sort to sort a linked list? What is the time complexity if so?

​			`Yes, same time complexity`

- Question 4: Given a string [A1B2C3D4], how to convert it to another string [ABCD1234]

####     举一反三

- Question 5: Convert string [ABCD1234] to [A1B2C3D4]. please do it in place (in place means do it with original input string rather than use an additional string.

----



### Quick Sort

- 核心物理意义：挡板思想（两个挡板 i & j，三个区域a, b, c）
  - [ 0 ... j **)**: i 的左侧（不包含）全部为比pivot 小的数
  - [ i ... j ]: i 和 j 之间为未知探索区域
  - **(** j ... n-1 ]: j 的右侧（不包含）全部为比pivot大或等于的数字

  1. `randomly select a pivot number: 5`

     |  1   |  9   |  8   |   5   |  3   |
     | :--: | :--: | :--: | :---: | :--: |
     |      |      |      | pivot |      |

  2. `put 5 to the right-most position (swap 5 , 3) `

     |  1   |  9   |  8   |  3   |   5   |
     | :--: | :--: | :--: | :--: | :---: |
     |  i   |      |      |  j   | pivot |

3. `check [i], 1<5, so remain where it be, just i++`

   |  1   |  9   |  8   |  3   |   5   |
   | :--: | :--: | :--: | :--: | :---: |
   |      |  i→  |      |  j   | pivot |

4. `check[i], 9 > 5, put 9 to the right of j, swap([i], [j]), and then j--`

   |  1   |  3   |  8   |  9   |   5   |
   | :--: | :--: | :--: | :--: | :---: |
   |      |  i   |  ←j  |      | pivot |

5. `check[i], 3 < 5, so remain where it be, just i++`

   |  1   |  3   |  8   |  9   |   5   |
   | :--: | :--: | :--: | :--: | :---: |
   |      |      | i→ j |      | pivot |

6. `check[j], 8 > 5, so put 8 to the right of j, swap(8,8), j--`

   |  1   |  3   |  8   |  9   |   5   |
   | :--: | :--: | :--: | :--: | :---: |
   |      |  ←j  |  i   |      | pivot |

7. `Finally, put pivot to the FINAL position (by calling swap(8, 5))`

   |  1   |  3   |  5   |  9   |  8   |
   | :--: | :--: | :--: | :--: | :--: |
   |      |  ←j  |  i   |      |      |

- 代码实现：

  ```java
  public class QuickSort{
      public int[] quickSort(int[] array){
          //check null first
          if(array == null){
              return array;
          }
          quickSort(array, 0, array.length - 1);
          return array;        
      }
      
      public void quickSort(int[] array, int left, int right){
          if(left >= right){
              return;
          }
          int pivotPos = partition(array, left, right);
          quickSort(array, left, pivotPos - 1);
          quickSort(array, pivotPos + 1, right);
      }
      
      private int partition(int[] array, int left, int right){
          int pivotIndex = pivotIndex(left, right);
          int pivot = array[pivotIndex];
          //swap pivot to the rightmost position first
          swap(array, pivotIndex, right);
          int leftBound = left;
          int rigthBound= right - 1;
          while(leftBound <= rightBound){
              if(array[leftBound] < pivot){
                  leftBound++;
              }else if(array[rightBound] >= pivot){
                  rightBound--;
              }else{
                  swap(array, leftBound++, rightBound--);
              }
          }
          //swap back the pivot element.
          swap(array, leftBound, right);
          return leftBound;
      }
      
      private int pivotIndex(int[] array, int left, int right){
        return left + (int) (Math.random()) * (right - left + 1);
      }
      private void swap(int[] array, int left, int right){
          int temp = array[left];
          array[left] = array[right];
          array[right]= temp;
      }
  }
  ```
  
  

#### 举一反二

- **Question 6： What is the worst case scenario for quicksort? **

  `worst case: 每次pivot都选在到待排区域的极值`

  `O(n^2)`

  step0: xxxxxxxxxxxxxxxxxxxP0empty		n 

  step1: emptyP1xxxxxxxxxxxxxxxxxxx	  n - 1

  ...

  n steps

  worst case **time** = n + n - 1 + n - 2 + ... + 1 = O(n^2)

  worst case **space** = O(n)

  average case time = O(n log n), space =O(log n)

  `best case: 每次pivot都选在中间位置`

  

#### 举一反三 Rainbow Sort

- **Question 8：Three colors `abcccabbcbbacaa` → `aaaaa bbbbb xxxx ccccc`**

  ​	start: xxxxxxxxxxxxxxxxxxxxxx (unsorted)

  ​			   i										 k

  ​			   j

  ​	initializaion (3个挡板， 4个区域，同向/反向而行)：

  1. i = 0 → ：	all letters to the left-hand side of i (not including i) are all "a"s

  2.  j = 0 → ：	all letters in [ i, j ) ( including i, but not including j ) are all "b"s

  ​		     				 j is the current index

  ​		[ j, k ] : 		unexplored area (including j and k)

  ​	3. k = ← n - 1: all letters to the right-hand side of k (not including k) are all "c"s

  ​	**aaaaa bbbb Xxxxx Ccccc**

  ​				[i          j]      k   

  

  Case 1:	input [j] == a		swap (i, j) , j++ , i++

  Case 2:	input [j] == b		j++

  Case 3:	input [j] == c		swap (j, k) ,  k--

- 代码实现

  ```java
  public class RainbowSort{
      public int[] rainbowSort(int[] array){
          if(array == null || array.length <= 1){
              return array;
          }
          //three bounds:
          //1.the left side of neg is -1 (exclusive of neg)
          //2.the right side of one is 1(exclusive of one)
          //3.the part between neg and zero is 0 (exlcusive of zero)
          //4.between zero and one is to be discovered (inclusive of both)
          int neg = 0;
          int zero = 0;
          int one = array.length - 1;
          //one of three value could be assigned when zero moves: -1, 0, 1
          while(zero <= one){
              if(array[zero] == -1){
                  swap(array, zero++, neg++);
              }else if(array[zero] == 0){
                  zero++;
              }else{
                  swap(array, zero, one--);                
              }
          }
          return array;
      } 
      public void swap(int[] array, int a, int b){
          int temp = array[a];
          array[a] = array[b];
          array[b] = temp;
      }
  }
  
  ```
  



### Move 0s to the end of the array

- Question:

  `Given an array of integers, move all the 0s to the right end of the array.`

  `The relative order of the elements in the original array does not need to be maintained.`

  ````java
  //My code:
  public class Solution {
    public int[] moveZero(int[] array) {
      if(array==null || array.length <= 1){
        return array;
      }
      int right = array.length -1;
      int left = 0;
      while(left < right){
        while(array[right] == 0 && left < right){
        right--;
      }
        if(array[left] == 0){
          array[left++] = array[right];
          array[right--]= 0;
        }else{
          left++;
        }
      }
      return array;
    }
  }
  
  ````

  ```java
  //Optimal solution
  public class Solution {
    public int[] moveZero(int[] array) {
      if(array==null || array.length <= 1){
        return array;
      }
      int right = array.length -1;
      int left = 0;
      while(left < right){
        while(array[right] == 0 && left < right){
        right--;
      }
        if(array[left] == 0){
          array[left++] = array[right];
          array[right--]= 0;
        }else{
          left++;
        }
      }
      return array;
    }
  }
  
  ```

  