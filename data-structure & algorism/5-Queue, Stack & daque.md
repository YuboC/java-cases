# 5-Queue, Stack & daque

[TOC]



|      Abstract data structure      |            |
| :-------------------------------: | :--------: |
|   **queue:** first in first out   | High level |
|   **stack:** last in first out    | High level |
|        double-ended queue:        | High level |
|                                   |            |
|        **Java Interface**         |            |
|          **FIFO**: Queue          |            |
| **LIFO**: Deque, Stack(do no use) |            |
|  **double-ended** queue:  Deque   |            |
|                                   |            |
|        **Implementation**         |            |
|       **array**: ArrayDeque       | Low level  |
|          **Liked list**           | Low level  |

`array 是封装Encapsulate进 class ArrayDeque 里的一个private field，只支持受限制的操作`

#### Stack

- stack 的设计失误（why not use stack）:
  - 在族谱关系表中，stack是vector的子类，由于vector 和arraylist功能一样，相当于表示stack是arraylist的子类。但list支持random access，这是stack不支持的。
  - vector 和是stack都有线程安全上的设计失误，他们在数据结构层面上就考虑了线程安全问题，但真正在工业界是多余的，因为多数时间使用他们的时候，并没有多线程。而因为线程安全考量的限制，导致他们的performance很低，成了系统bottle neck。
  - methods sets return type mixed

- in API doc:
  - A more complete and consistent set of LIFO stack operations is provided by the Deque interface and its implementations, which should be used in preference to this class. For example: 
  - **Deque<Integer> stack = new ArrayDeque<integer>();**



### In Java

```markdown
In Java, we usually refer it to Queue and Deque interface.
A collection to hold multiple elements prior to processing.
Cotains a buffer and specifies a mechanism to choose which is the next element to process.
```

#### **Queue** - FIFO (**exception: PriorityQueue**)

https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html

<Queues typically but do not necessarily order elements in a FIFO manner>

<Among the exceptions are priority queues which order elements according to a supplied comparator or natural ordering>

- **Usages**: Breadth-First Search related problems

- 典型问题：
  - Tree printout by level
  - Sliding window problems (Deque: double ends manipulation)

- **offer()** - offer at the tail - **enqueue**

- **poll()** - poll at the head - **dequeue**

- **peek()** - look at the head without polling it out

  

#### **Deque** - FIFO & LIFO (both queue and stack)

 https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html

- provide both offer at head/ tail, poll at head/tail, and peak at head/tail
- so it can be used as a queue, or a stack

### Examples

#### head -> {} <- tail

```java
00  Queue<integer> queue = new ArrayDeque<>();
01 //Queue<integer> queue = new LikedList<>()
02	queue.offer(1);
03	queue.offer(1);
04	queue.offer(1);
05	while(!queue.isEmpty()){
06  System.out.println(queue.peak());
07  System.out.println(queue.poll());
08}
09//doc: returns
10//head of this queue, or null if this queue is empty
11  System.out.println(queue.peek()); 
12  System.out.println(queue.poll());

[output] 1, 1, 2, 2, 3, 3, null, null
```



#### head(first) -> {3, 2, 1} <- tail(last)

`If you are using deque as stack, you can choose the group of methods:`

`offerFirst()` - push()

`pollFirst()` - pop()

`peekFirst()` - peek()

```java
Deque<Integer> stack = new ArrayDeque<>();
//Deque<Integer> stack = new LinkedList<>();
stack.offerFirst(1);
stack.offerFirst(2);
stack.offerFirst(3);
while (!stack.isEmpty()){
    System.out.println(stack.peekFirst());
    System.out.println(stack.peekFirst());
}
System.out.println(stack.peekFirst());
System.out.println(stack.peekFirst());

[output] 3, 3, 2, 2, 1, 1, null, null
```



#### head(first) -> {3, 2, 1, 4, 5, 6} <- tail(last)

out: 3, 6, 2, 5, 1, 4

```java
Deque<Integer> deque = new ArrayDeque<>();
// Deque<integer> deque = new LikedList<>();
deque.offerFirst(1);
deque.offerFirst(2);
deque.offerFirst(3);
deque.offerLast(4);
deque.offerLast(5);
deque.offerLast(6);
int size = deque.size();
for (int i = 0; i< size; i++){
    if(i % 2 == 0){
        System.out.println(deque.peekFirst());
        System.out.println(deque.pollFirst());        
	}else{
        System.out.println(deque.peekla st());
        System.out.println(deque.pplllast());        
    }
System.out.println(deque.peekFirst());
System.out.println(deque.peekFirst());
System.out.println(deque.peeklast());
System.out.println(deque.peeklast());

[output] 3, 3, 6, 6, 2, 2, 5, 5, 1, 1, 4, 4, null, null, null, null
```



### API Return types 

| Operation | Stack                   | Queue                    | Dequeue                                  |
| --------- | ----------------------- | ------------------------ | ---------------------------------------- |
| Insert    | push()   \|  exception  | offer(e)  \|  add(e)     | offerFirst()  \|  addFirst()       /last |
| Remove    | pop()     \|  exception | poll()      \|  remove() | pollFirst()  \|  removeFirst() /last     |
| Examine   | peek()   \|  null       | peek()    \|  element()  | peekFirst()  \|  gerFirst()        /last |



#### APIs - Queue

| s Types of Operation | return special value(null)      | throw exception |
| -------------------- | ------------------------------- | --------------- |
| Insert               | offer(e) `return false if full` | add(e)          |
| Remove               | poll()                          | remove()        |
| Examine              | peek()                          | element()       |
|                      | `异常后仍可继续运行`            | `program crush` |

- Use the same set of APIs
- What's the point: Do you really need to throw an exception?



#### APIs - Deque

|         | return special value(null) | throw exception | return special value(null) | trow exception |
| ------- | -------------------------- | --------------- | -------------------------- | -------------- |
| Insert  | offerFirst(e)              | addFirst(e)     | offerLast(e)               | addLast(e)     |
| Remove  | pollFirst()                | removeFirst()   | pollFirst()                | removeFirst()  |
| Examine | peekFirst()                | getFirst()      | peekLast()                 | getLast()      |

- other useful methods:
  - isEmpty();
  - size();

- All the operations' cost is O(1)

- most popular implementation calss: ArrayDeque > LinkedList

- no null values in deque

- [why is ArrayDeque better than LinkedList](stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist)

  

#### APIs Stack

why not use stack:

| Stack Method | Equivalent Deque Method            |
| ------------ | ---------------------------------- |
| push(e)      | addFirst(e)      \|   `exception`  |
| pop()        | removeFirst() \|  `exception`      |
| peek()       | peekFirst()      \|  `return null` |

 

### Our Own Implementation

```markdown
Stack, Queue and Deque are not premier data structures, their implementation depend on other data structures - what could be the candidates?  -  `sequence type(线性的)`
```

- Why do we need our own implementation?
  - Know the internals for better design decisions
  - Interview problems themselves
  - Reflects some important engineering methods/tricks
- In general, two ways:
  - use array
  - use Linked list



#### Use Linked list implement stack

```java
class ListNode{
    int value;
    ListNode next;
    //ListNode prev
    public ListNode(int value){
        this.value = value;
    }
}

public  class stack{
    {
     private ListNode head;//默认为null
    }
    public void  push(int value){
        ListNode newIn = new ListNode(value);
        newIn.next = head; // 头入头出
        head = newIn;
    }
    public Integer peek(){
        if(head == null){
            return null;
        }
        return head.value;
    }
    public Integer pop(){ //int不能为null
        if(head == null){
            return null;
        }    
        ListNode pre = head;
        head = head.next;
        pre.next = null; //防呆avoid hacking，best practice
        return pre.value;
        }
}
```

#### use Linked list implement Queue

```java
Class ListNode{
    int value;
    ListNode next;
    public ListNode(int value){
        this.value = value;
    }
}

public class Queue {
    private ListNode head;
    private ListNode tail;
    
    public void offer (int ele) {
        ListNode newIn= new ListNode(ele);
        if (tail == null && head == null) { 尾入避免npe
            tail = newIn;
            head = newIn;
        } else {
        tail.next = newIn; //尾入头出
        tail = tail.next;
        }
    }
    public Integer element() {
        if(head == null) {
            return null;
        }
        return head.value;
    }
    public Integer poll() {
		
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        head = head.next;
        
        if(head == null){//if only one element
            tail = null; //update the tail
        }
        pre.next = null;
        return pre.value;
    }
}
```



####　use array to implement stack

- first, we need to maintain the position of head and tail - use two pointers

- offer()  =>  put one element, tail++ (tail points to the next avaliable position)

- poll()   =>  grab element at head, head++ (head points to the next element in queue)

  |      |      | 13   | 14   | 5    | 6    | 7    | 8    | 9    | 10   |
  | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
  |      |      | head |      |      |      |      |      |      | tail |

- **First,** what if `tail == array.length - 1` and we need to do `tail++`

  **Circular way** - we can connect the start and end of the array, so that it is a cycle. Index of array.length <=> index of 0

  **Quick tip:**

  - 三元: head = head + 1 == array.length ? 0 : head + 1;
  - mod: head = (head  - 1) % array.length;



- **Second**, we need to know when empty, when full

  head == tail → empty

  head == tail → full

  - two solutions:

    1. **record size**, size = o (empty), size == array.length(full) [如果不允许维护size，则选2]

    2. (head + 1) % array.length == tail → empty, head == tail →full, capacity = array.length -1 (next of head points to the head of the queue)

       |      |      |      |      |      |      |      |      |      |      |
       | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
       | head | tail |      |      |      |      |      |      |      |      |

       |      | 12   | 13   |      |      |      |      |      |      |      |
       | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
       | head |      |      | tail |      |      |      |      |      |      |

       |      | 12   | 13   | 14   | 5    | 6    | 7    | 8    | 9    |      |
       | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
       | head |      |      |      |      |      |      |      |      | tail |

       |              | 12   | 13   | 14   | 5    | 6    | 7    | 8    | 9    | 10   |
       | ------------ | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
       | head \| tail |      |      |      |      |      |      |      |      |      |

       * head 上始终为空
       * 满了后：声明一个1.5倍size array，从head + 1 开始copy

```java
public class BoundedStack{
    private int[] array;
    private int head;
    public BoundedStack(int cap){
        //check up
        array = new int[cap];
        head = -1;
    }
    
    public boolean push(int ele){
        if(head == array.length - 1){
            return false;
        }
        array[++head] = ele;
        return true;
    }
    
    public Integer pop(){
        return head == -1 ? null : array[head--];
    }
    public Integer top(){
        return head = -1 ? null : array[head];
    }
}
```

- cycling Queue with size recorded

```java
public class sizeStack{
    private int[] array;
    private int head;
    private int size;
    public sizeStack(int cap){
        array = new int[cap];
        head = -1;
    }
    public push(int ele){
        if(size == 0){
            return null;
        }
        Integer result = array[head];
        //(head + 1) % array.length;
        head = head + 1 == array.length ? 0 : head + 1;
        size--;
        return result;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public boolean isFull(){
        return size == array.length;
    }
}
```

- cycling Queue without sized

```java

public class cycleStack{
    private int[] array;
    private int head;
    private int tail;
    public cycleStack(int cap){
    	array = new int[cap];
        head = 0;
        tail = 1;
    }
    public push(int ele){
        if((head + 1) % array.length == tail){
            tail = head + 1;
            array[tail] = ele;
            
        } 
    }
    public peek(){
        
    }
    
}
```



#### use array to implement Queue

```java
public class BoundedQueue{
    private int head;
    private int tail;
    private int[] array;
    
    public BoundedQueue(int cap){
        array = new Integer[cap];
        head = 0;
        tail = 1;
    }
    public boolean offer(int ele){
        if(head == tail){
            return false;
        }
        array[tail] = ele;
        tail = tail + 1 == array.length ? 0 : tail + 1;
        return true;
    }
    public Integer peek(){
        if((head + 1) % array.length == tail){
            return null;
        }
        return array[head + 1];
    }
    public Integer poll(){
        if((head + 1) % array.length == tail){
            return null;
        }
        Integer result = array[head + 1];//out of bound when head = array.length - 1
        head = head + 1 == array.length ? 0 : head + 1;
        return result;
    }
    
    public boolean isEmpty(){
        return (head + 1) % array.length == tail;
    }
    public boolean isFull(){
        return head == tail;
    }
    public int size(){
        if(tail > head){
            return tail - head -1;
        }else {
            return tail + array.length -1 - head;
        }
    }
}
```



### Four popular (queue-stack related) interview questions:

- Stack的移动有什么常见特性：

  - stack1 全部元素move到stack2，元素在stack2顺序完全reserve
  - 然后全部元素 移回stack1，则回到stack1的元素顺序不变。**amortized的时间复杂度分摊到每一个move的元素时间往往可以变为O(1)**

- 什么问题要往Stack上考虑

  从左到右linear scan 一个array/string时，如果要不断回头看左边最新的元素时，往往要用到stack

  1. String的repeatedly deduplication. cabba → caa → c
  2. Reverse Polish Notaion 逆波兰表达式的计算 a x (b+c) → abc+x
  3. Histogram 中找最大长方形





#### **Question1：How to sort numbers with three (or two) stacks**

- three stack:

  Stack 1 (input)[ 3  1  2  4

  stack 2 (Buffer)[								global min=

  stack 3 (result)[

  ```java
  
  ```

  

- two stack: time complexity = `O(n ^ 2).

  Stack 1 (input) [  3  1  2  4				global min = 

  Stack 2 (Buffer | result)					counter_of_global_min =

  - Method1 : before each iteration, we can use a counter to record how many elements are there in stack2.
  - Method2 :  While(stack2.size() > 0 && stack2.pop().value >= global_min)

  ```java
  public class SortTwoStacks{
      public void sort(LinkedList<Integer> s1) {
      LinkedList<Integer> s2 = new LinkedList<Integer>();
      if (s1 == null || s1.size() < 1) {
        return;
      }
      sort(s1, s2);
    }
  
    //Step1: poll all from stack1, and keep the globalMin
    private void sort(LinkedList<Integer> input, LinkedList<Integer> buffer){
      while (!input.isEmpty()) {
        int globalMin = Integer.MAX_VALUE;
        int counter = 0;
        while (!input.isEmpty()) {
          int cur = input.pollFirst();
          if (cur < globalMin) {
            globalMin = cur;
            counter = 1;
          } else if (cur == globalMin) {
            counter++;
          }
          buffer.offerFirst(cur);
        }
        //move all ele back to stack1, but global mins back to buffer
        //the last round mins won't be picked out with >= globalMin;
        while (!buffer.isEmpty() && buffer.peekFirst() >= globalMin) {
          int cur2 = buffer.pollFirst(); //poll one by one
          if (cur2 != globalMin) { //put ele back to stack1, except globalMin
            input.offerFirst(cur2);
          }
        }
        //globalMins go back to buffer and stays until the while loop ends
        while (counter-- > 0) {
          buffer.offerFirst(globalMin);
        }
      }
      //Step2: move sorted eles in buffer back to input
      //then we have ascending order when access to stack 1
      while (!buffer.isEmpty()){
        input.offerFirst(buffer.pollFirst());
      }
    }
  }
  ```

  



#### Question 2: How could we implement a queue by using two stacks?**

Queue: 	← 5 6 7 8 ←

Stack1 [			   ←

Stack2 [  8 7 6 5 →

```markdown
Solution:
1. when a new element X comes in, we just call stack1.push(X)
2. When an old element gets poped out
	a. Case2.1, if Stack2 is NOT empty, we just call stack2.pop()
	b. Case2.2, if Stack2 is empty, we move all element from stack1 to stack2 one by one(if any).And then call stack2.pop()
`Stack1:` is the only stack to store the new elements when adding a new element into the queue
`Stack2:` is the only stack to pop old element out of the queue
		Case 1: if Stack2 is not empty, then we can just pop	--O(1)
		Case 2: if Stack2 is empty, we move all data from Stack1 to Stack2 one by one, and then pop outthe top element from the stack2		--O(n)

`Time complexity:`
Enqueue(): O(1)
Dequeue(): worst case O(n), when stack2 is empty, e.g case2.2, N elements gets poped out of stack1-O(n), and then gets added into stack2-O(n), and then call stack2.pop()-O(1). total time = O(n + n + 1)

`Amortized time complexity:`
n = 1000
Stack1 [ 1000 999 ... 2
Stack2 [ 
1st time we call pop: 1000 + 1000 + 1 = 2001
2nd time we call pop: 1
...
1000 time we call pop: 1
Amortized time for 1000 times = (2001 + 1 x 999)/1000 = 3000/1000 = 3

`Amortized time vs Average time:`
- Amortized time: for any specific input, we amortize all operations within this input
- Average time: the average of all possible inputs in the input space(e.g quick sort)
```

```java
public class QueueByTwoStacks {
  //the scope of these two is belong to the whole class
  private LinkedList<Integer> in;
  private LinkedList<Integer> out;

  public QueueByTwoStacks() {
    //these two only belong to the constructor
    //other method cannot use, bocs out of scope
    in = new LinkedList<Integer>();
    out = new LinkedList<Integer>();
    
  }
  
  public Integer poll() {
    move();
    //return out.isEmpty ? null : out.pollFirst();
    if(out.isEmpty()){
      return null;
    }else{
      return out.pollFirst();
    }
  }

  public void offer(int element) {
    in.offerFirst(element);
  }
  
  public Integer peek() {
    move();
    return out.isEmpty() ? null : out.peekFirst();
  }
  
  public int size() {
    //return in.size() + out.size();
    move();
    int count = 0;
    while(!out.isEmpty()){
      in.offerFirst(out.pollFirst());
      count++;
    }
    return count;
  }
  
  public boolean isEmpty() {
    //return in.size() == 0 && out.size() == 0;
    return (in.isEmpty() && out.isEmpty());
  }

  public void move(){
    if(out.isEmpty()){
      while(!in.isEmpty()){
      out.offerFirst(in.pollFirst());
      }
    }
  }
}
```

#### Question 3: How to implement the min()  function when using stack with time complexity O(1) for each operation. 

*Google on-site*

```morkdown
requirement: create a new API to stack
always need two or three stacks to be used togather
```

​	 stack1[ 1  2   5   2   -1   5

minStack[ 1  1  1   1   -1    -1

- Solution: keep the adding & removing action in synchronization with each other `min()` returns the `stack2.top() `value as the result (同步加同步减)

  - Stack1: is used to store input elements
  - Stack2: is used to store the min element so far in Stack1 (as its top element)
  - Time = O(1)
  - Space = O(n)

  ```java
  public class StackWithMin {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;
  
    public StackWithMin() {
      stack = new LinkedList<Integer>();
      minStack = new LinkedList<Integer>();
    }
    
    public int pop() {
      if(stack.isEmpty()){
        return -1;
      }
      Integer result = stack.pollFirst();
      if (minStack.peekFirst().equals( result)) {
        minStack.pollFirst();
      }
      return result;
    }
    
    public void push(int element) {
      stack.offerFirst(element);
      if (minStack.isEmpty() || element <= minStack.peekFirst()) {
        minStack.offerFirst(element);
      }
    }
    
    public int top() {
      if (stack.isEmpty()) {
      return -1;
      }
      return stack.peekFirst();
    }
    
    public int min() {
      if(minStack.isEmpty()){
        return -1;
      }
      return minStack.peekFirst();
    }
  }
  ```

  

- Follow Up Question: **How to optimize the usage of stack2 in average cases?(assuming there are a lot of duplicate elements in input)**

  - Method 1: (use a counter)
    - stack2(min) [ <1, 13>, <-1, 8>
  - Method 2: (nut use counter, use .`size()`) record its index
    - stack2(min) [<1, size(1st entry)>, when deleting, value == 1 && size() > size(1st)
  - 增减性 & 重复性

  ```java
  public class StackWithMin {
      private Deque<Integer> stack = new ArrayDeque<>();
      private ArrayList<int[]> curMin = new ArrayList<List[]>();
      //
      public StackWithMin(){
          
      }
      
      public int pop(){
          if (stack.isEmpty()){
              return -1;
          }
          //stack: [1, 2, 3, 1]
          //curMin: [[1, 2], [], [curMin, count]]
          if (stack.peek() <= curMin.get(curMin.size() - 1)[0]){
      		curMin.get(curMin.size() - 1)[1] -= 1;
         		if (curMin.get(curMin.size() - 1)[1] == 0) {
                  curMin.remove(curMin.size() - 1);
              }
          }
         	return stack.pollFirst();
      }
      
      //curMin[0] is the current min, curMin[1] is the count of min
      public void push(int element){
          stack.offerFirst(element);
          if (curMin.isEmpty() || element < curMin.get(curMin.size() - 1)[0]){
              int[] cur = {element, 1};
              curMin.add(cur);
          } else if (element == curMin.get(curMin.size() - 1)[0]) {
              curMin.get(curMin.size() - 1)[1] ++;
          }
      }
      
      public int top(){
          if (stack.isEmpty()){
              return -1;
          }
          return stack.peek();
      }
      
      public in min() {
          if (stack.isEmpty()){
              return -1;
          }
          return curMin.get(curMin.size() - 1)[0];
      }
  }
  ```

  





#### Question 4: How to use multiple Stacks to implement a deque

Left		XxxxxxxxxxxxxxxxxxxxxxxxxxxxxX		Right

4 APIs:	Left.add() 						Right.add()

​				Left.remove()					Right.remove()

- Solution 1: use **two stacks** to implement Queue

  Deque = 

  Stack1: to simulate the left end of the deque

  Stack2: to simulate the right end of the deque

  ```markdown
  	CART
  Clarify: need to implement 4 APIs
  Assumptions: ok, at least I believe it need two stacks to do that.
  Result: tests
  
  API1: Left.add() = Stack1.push()					--O(1)
  
  API2: Right.add() = Stack2.push()			  		--O(1)
  
  API3: Left.remove() = 
  ​		Case1: if stack1 is not empty: Stack1.pop() --O(1)
  ​		Case2: if stack2 is empty: then move all 	
  ​				elements from stack2 to stack1 and  --O(n)
  ​				then call stack.pop()
  API4: Right.remove(): similar to API3
  
  worst case:(each time remove the empty side)
  		] Stack1 Stack2[ 1 2 3 4 5 6 7 8
  L.remove()	-- 2n + 1
  R.remove()	-- 2(n-1) + 1
  L.remove()	-- 2(n-2) + 1
  R.remove()	-- 2(n-3) + 1
  L.remove()	--
  ```

  ```java
  
  ```

  

- Solution 2: Use **three Stacks** to improve the time complexity of remove() operation.

  ​		we use buffer stack, say **Stack3** to buffer the elements, when moving all elements from right part to left part. To make sure the number of  elements in Left and Right part are roughl 50% of all elements. In detail, Stack3 is used as the buffer to store the top half elements, so that the bottom half elements can be moved to **Stack1**

  ```markdown
  out: 	1 2 3 4 5 6 7 8
  	Xxxxxxxxx ][ xxxxxxxX
  input	empty ][ 1 2 3 4 5 6 7 8
  
  High level Objective:
  	move 1/2 elements from right hand side to left hand side
  expectation(final goal): move 1 2 3 4 to left  
  
  Middle | Details：
  	Step1: move top 1/2 elements from stack2 to stacks -- n/2 + n/2 (8 7 6 5)
  	Step2: move bottom 1/2 elements from stack2 to stack1- n/2 + n/2
  	Step3: move all elements from stack3 back to stack1 -- n/2 + n/2
  	Step4: call stack1.pop()	-- o(1)
  	Total time = O(3n + 1)
  1st time we call left.remove()	3n + 1
  2nd time we call left.remove()		 1
  3rd ..
  n/2 th time call					 1
  Amortize time = (3n + 1 + 1 x (n/2 - 1))/(n/2) = (3n + 1 + n/2 - 1)/(n/2) = O(7) = O(1)
  ```

  ```java
  public class Solution {
    private Deque<Integer> left;
    private Deque<Integer> right;
    private Deque<Integer> buffer;
  
    public Solution() {
      left = new ArrayDeque<>();
      right = new ArrayDeque<>();
      buffer = new ArrayDeque<>();
    }
    
    public void move(Deque<Integer> src, Deque<Integer> dest){
      if (!dest.isEmpty()) {
        return;
      }
      int halfSize = src.size() / 2;
      for (int i = 0; i < halfSize; i++){
        buffer.offerFirst(src.pollFirst());
      }
      while (!src.isEmpty()){
        dest.offerFirst(src.pollFirst());
      }
      while (!buffer.isEmpty()){
        src.offerFirst(buffer.pollFirst());
      }
    }
  
    public void offerFirst(int element) {
      left.offerFirst(element);
    }
    
    public void offerLast(int element) {
      right.offerFirst(element);
    }
    
    public Integer pollFirst() {
      move(right, left);
      return left.isEmpty() ? null : left.pollFirst();
    }
    
    public Integer pollLast() {
      move(left, right);
      return right.isEmpty() ? null : right.pollFirst();
    }
    
    public Integer peekFirst() {
      move(right, left);
      return left.isEmpty() ? null : left.peekFirst();
    }
    
    public Integer peekLast() {
      move(left, right);
      return right.isEmpty() ? null : right.peekFirst();
    }
    
    public int size() {
      return left.size() + right.size();
    }
    
    public boolean isEmpty() {
      return left.isEmpty() && right.isEmpty();
    }
  }
  ```

  

#### Stack by Queue(s)

```java
Implement a stack containing integers using queue(s). The stack should provide push(x), pop(), top() and isEmpty() operations.

In java: if the stack is empty, then top() and pop() will return null.
```

```java
class Solution {
    private Queue<Integer> q;
    /** Initialize your data structure here. */
    public Solution() {
       //q = new Queue<Integer>();
       //Queue is abstract; cannot be instantiated
       q = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public Integer pop() {
      if(q.isEmpty()){
        return null;
      }
      int size = q.size();
      while(--size != 0){
        q.offer(q.poll());
      }
      return q.poll();
    }

    /** Get the top element. */
    public Integer top() {
        if(q.isEmpty()){
          return null;
        }
        int tem = pop();
        q.offer(tem);
        return tem;
    }

    /** Returns whether the stack is empty. */
    public boolean isEmpty() {
      return q.isEmpty();       
    }
}

//Follow up with additional Assumption:
//Queu has only offer() and poll() operations;
// we cannot call isEmpty(), peek(), size(), etc.
class StackByTwoQueues {
  private Queue<Integer> q1;
  private Queue<Integer> q2;

  public StackByTwoQueues() {
    q1 = new ArrayDeque<>();
    q2 = new ArrayDeque<>();
  }

  public void push(int x) {
    q1.offer(x);
  }

  public Integer pop() {
    Integer prev = q1.poll();
    Integer cur = q1.poll();
    while (cur != null) {
      q2.offer(prev);
      prev = cur;
      cur = q1.poll();
    }
    Queue<Integer> tmp = q1;
    q1 = q2;
    q2 = tmp;
    return prev;
  }

  public Integer top() {
    Integer ret = pop();
    if (ret != null) {
      q1.offer(ret);
    }
    return ret;
  }

  public boolean isEmpty() {
    return top() == null;
  }
}
```

