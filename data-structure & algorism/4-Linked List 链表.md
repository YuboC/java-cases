# Linked List 链表

[TOC]



## Reverse linked list in 2 ways

> ```markdown
> No.1 interview question on linked list: How to reverse a liked list
> 
> 		Node1 -> Node2 -> Node3 -> Node4 ... -> NodeN -> NULL
> prev	head 	next
> 
> 	NUll <- Node1 <- Node2 <- Node3 <- Node4 ... <- NodeN
> .													head
> ```



#### Solution 1: iterator

```markd
	Basically, I will use the function names as reverse to reverse the linked list. this function takes one argument that is head pointer to head reference of the linked list, and return type is listNode which is the new head after this reverse completed. 
	Now we look at the functional code: if the head reference is null, we don't need to do anything. Else we need three references to implement this function: the current pointer as cur, the previous pointer as pre and the next node. 
	Lets look at the while loop: For each while loop, we reverse one node. The first step in the loop is to store the reference of next node. The reason is that after reverse we need to define the new sub head of remaining part of linked list. In the second line in the while loop, we reverse the next pointer in current node to previous node. And after that, we only need to do two things, one is moving priouse reference once behind and then moving the current reference to the next reference.
```

```java
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode reverse(ListNode head) {
    if(head == null){
      return null;
    }
    ListNode pre = null;
    ListNode curr= head;
    while(curr != null){
      ListNode next = curr.next;
      curr.next = pre;
      pre = curr;
      curr = next;
    }
    return pre;
  }
}
```



#### Solution 2: Recursively reverse

```java
00 public ListNode reverse(ListNode head){
01     if(head == null || head.next == null){
02         return null;
03     }
04     ListNode newHead = reverse(head.next); // breaking point
05     head.next.next = head;	//node2.next = node1
06     head.next = null;	//node1.next = null
07     return newHead;
08 }
```

`[1]  1st call reverse (Node 1):`  

​		**Node1 → Node2 → Node3 → null**	// bp = line04

​		head

`[2]  2nd call reverse (Node 2):` 

​		**Node1 → Node2 → Node3 → null**	// bp = line04

​							head

`[3]  3rd call reverse (Node 3):` 

​		**Node1 → Node2 → Node3 → null**	// newHead = n3

​											 head

`[4]  back to Call Stack level 2 at the second call:`

​		**Node1 → Node2 → Node3 → null**	// newHead = n3; 

​							head

​		line05: head.next.next = head =>  n2.next.next = n2 =>  n3.next = n2  => **n3 → n2**

​		line06: n2.next = null  =>  **n2 →null**

​		**Node1 → Node2 ← Node3 **	//newHead = n3

​							head

`[5]  back to Call Stack level 1 at the firt call: `

​		**Node1 → Node2 ← Node3 **  //newHead = n3

​		head

​		line05: n1.next.next = n1  =>  n2.next = n1  => **n2 → n1**

​		line06: n1.next = null  =>  **n1 → null**

​		**null ← Node1 ← Node2 ← Node3**  // **return newHead = n3 **

​					  head

 

#### 思考题 (pair reverse)

input:	(N1 → N2) → (N3 → N4) → (N5 → N6) → Null

output: N2  →  N1  → N4  → N3  → N6  → N5 → Null 

`step 1: 判断子问题：` 每个pair后的元素为子问题

`step 2:` N2 → N1

`step 3:` N1 → return of next pair

```java

```



## Find the middle of a LL

```markdown
Q: How to find the middle node of a linked list?

	Node1 → Node2 → Node3 → Node4 → Node5 → Node6 → null
```

#### Solution 1: Fast & Slow pointer

*Time = O(fast) + O(slow) = O(n + 1.5n)*

- when the number of nodes is **even**. which stop is better for slow pointer, node3 or node4?

```markdown
	Node1 → Node2 → Node3 →	|  Node4 → Node5 → Node6 → null
.					slow	
.									   fast
In some future cases, we need to process two half parts seperatly. It will be much convenient when we left the slow pointer in the first half.

	head != null;
	ListNode slow = head;
	ListNode fast = head;
	//when #nodes is even: fast.next.next != null
	while (fast.next != null && fast.next.next != null){ 
	}
```

```java
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode middleNode(ListNode head) {
    if(head == null || head.next == null){
      return head;
    }
    ListNode slow = head;
    ListNode fast = head;
      //when #nodes is even: fast.next.next != null
    while(fast.next != null && fast.next.next != null){
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
}
```

#### Solution 2: One pointer, Two rounds

*Time = O(1 round) + O(half round) = O(1.5n)*

```markdown
	head != null;
	ListNode pointer = head;
	while(pointer.next != null){
		count++;
	}
```

#### online algorism v.s offline algorism
	online: infinite streaming. computing at any stop point. [method 1]
	offline: process untill all data comes in				 [method 2]


## Check if there is a circle in LL

- **using fast & slow pointer**

​	 **Node1 → Node2 → <u>Node3 → Node4 → Node5</u> → Node6 → null**

​	case 1: fast can reach null, then no cycle

​	case 2: else, fast reaches slow

```java
public class Solution{
    public boolean hasCycle(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null || fast.next != null){
            slow = slow.next;
            fast = fast.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}
```



## Insert a node in a sorted LL

​	**Node1 → Node2 → Node3 → Node4 → null**

​		1	   →	3		→	**6**		→	**9**		→  null	|	target = 7

​										 curr			next

```java
//solution 1:
	public ListNode insert(ListNode head, int target){
		ListNode tNode = new ListNode(target)
        //determine if the inserted node is before head
        if(head == null || target <= head.value){
            tNode.next = head;
            return tNode;
        }
        ListNode cur = head;
        while(cur != null){
            if(cur.value < target && (cur.next == null || cur.next.value >= target)){
                target.next = cur.next;
                cur.next = target;
                return head;
        }
         cur = cur.next;
     }
//case 1: iftarget == 0, target.next = head; return target // as new head;
//case 2: if targer == tail ==> target.next = null

// optimalize the code
     public ListNode insert(ListNode head, int value){
         ListNode newNode = new ListNode(value);
         if(head == null || head.value <= value){
             newNode.next = head;
             return newNode;
         }
         ListNode cur = head;
         //head.value <= value 保证了cur.value < target 已被筛出，所以多余
         //cur.next = null停下：没有比value更大的数
         //cur.next.value < value停下：发现大于或等于value的数
         while(cur.next != null && cur.next.value < value){
             cur = cur.next;
         }
         newNode.next = cur.next;
         cur.next = newNode;
         return head;
     }

//solution 2: use a dummy head (-infinity)
```



### Insert in a circular LL

- Insert a node in a circular linked list (given the head of the linkedlist)

  **0 --> 1 -->2 --> 5 --> 10**

  head

  **<------------------------------**

  Case1: insert before the head

  Case2: else

​		

## merge two sorted LL into one

[1]	**Node1 → Node2 → Node5 → null**

​			cur1

[2]	**Node1.5 → Node3 → Node6 → null**

​			cur2

use two pointers and do 谁小移谁

[result] **N1 → N1.5 →  N2 → ...**

```markdown
ListNode `dummyHead` = new ListNode(0);
dummyHead.next = N1;
return dummyHead.next;
```

`What is DummyHead? :`

`When to use it? :`  

When we want to build a LinkedList from scratch(initially zerol nodes). In order to avoid NPE(null pointer dereference), we need to use a dummy head.   

`when should we maintain a tail pointer, besides the header?`

​	when we want to append a new Node to the end of the LinkedList

​	tail.next = n3;

​	tail = tail.next;

```java
//My code:
public class Solution{
    public ListNode merge(ListNode one, ListNode two){
        if(one == null && two == null){
            return null;
        }
        ListNode dummyH = ListNode(0);
        ListNode cur = dummyH;
        while(one != null && two != null){
            if(one.value < two.value){
                cur.next = one;
                one = one.next;
            }else{
                cur.next = two;
                two = two.next;
            }
            cur = cur.next;
        }
        if(one != null){
            cur.next = one;
            //one = one.next; 多此一举
            //cur = cur.next; 因为是链表
        }else if(two != null){
            cur.next = two;
            //two = two.next; 链接头部
            //cur = cur.next; 就连上剩余
        }
        return dummyH.next;
    }
}
```





## Middle+Reverse+ merge

[input]		**N1 → N2 → N3 → N4 → N5 → N6 → ... → Nn → null**

[output]	**(N1 → Nn) → (N2 → Nn-1) → ...**

[Solution:]

Step1: find the middle node.

Step2: reverse the 2nd half

Step3: merge 

```java
public class Solution {
  public ListNode reorder(ListNode head) {
    if(head == null || head.next == null){
      return head;
    }
    //1. find the middle node
    ListNode mid = middleNode(head);
    ListNode one = head;
    //2. reverse the second half from the list
    ListNode two = reverse(mid.next);
    //de-link the scond half from the list
    mid.next = null;
    //3. merge the two halves
    return merge(one, two);
  }

  public ListNode middleNode(ListNode head){
    ListNode slow = head;
    ListNode fast = head;
    while(fast.next != null && fast.next.next != null){
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }
  public ListNode reverse(ListNode head){
    if(head == null || head.next == null){
      return head;
    }
    ListNode pre = null;
    ListNode cur = head;
    while(cur != null){
      ListNode nex = cur.next;
      cur.next = pre;
      pre = cur;
      cur = nex;
    }
    return pre;
  }

  public ListNode merge(ListNode one, ListNode two){
    ListNode dummyH = new ListNode(0);
    ListNode cur = dummyH;
    while(one != null && two != null){
        cur.next = one;
        one = one.next;
        cur.next.next = two;
        two = two.next;
        cur = cur.next.next;
        
    }
    if(one != null){
      cur.next = one;
    }else{
      cur.next = two;
    }
  
    return dummyH.next;
  }
}
```





## Partition List

`Given a linked list and a target value x, partition it such that all nodes less than x are listed before the nodes larger than or equal to target value x. (keep the original relative order of the nodes in each of the two partitions)`

For example:

[input] 	1 -> 5 -> 3 -> 2a -> 5 -> 2b  | and target x = 4 |  (2a means the first 2)

[output]	1 -> 3 -> 2a -> 2b -> 6 -> 5.

 

**Solution:**  

`Step 1:`**we allocate  2 Linkedlist, one for smaller values and the other for larger values than the target**

[input] 	1 -> 5 -> 3 -> 2a -> 5 -> 2b 

​				cur→

​	**SmallLinkedList → 1 → 3 → 2a → 2b**

​	dummySHead									Tsmall

​	**LargeLinkedList → 6 → 5**

​	dummyLHead				Tlarge

`Step2:` **Concatenate the 2 linkedlist, by calling **

​				Tsmall.next  = dummyLHead

`Step2b:` Tlarge.next = null;

`Step3:` **return dummySHead**

```java
public class Solution {
  public ListNode partition(ListNode head, int target) {
  if(head == null || head.next == null){
    return head;
  }
  ListNode large = new ListNode(0);
  ListNode small = new ListNode(0);
  ListNode nodeL = large;
  ListNode nodeS = small;
  ListNode cur = head;
  while(cur != null){
    if(cur.value < target){
      nodeS.next = cur;
      nodeS = nodeS.next;
    }else{
      nodeL.next = cur;
      nodeL = nodeL.next;
    }
    cur = cur.next;
  }
  //merge
  nodeL.next = null; //the tail of merged sort
  nodeS.next = large.next; //the connection
  return small.next; //the head of merged sort
  }
}
```

```java
//Initially we allocate four pointers: fakeSmallHead, fakeLargeHead, smallTail and largeTail. smallTail and largeTail are the tail reference of the small list and large list. The while loop will iterate over the input list, and and for each current node there are two cases: if the current node's value is smaller than the target, we append it to the tail after the small tiail, otherwise we append the node to the larger linked list.
```



## 举一反三

### 1. MergeSort Linked List 

- Example
  - input:	4  →  2 →  6 → -3 → 5
  - output: -3 → 2 → 4 → 5 → 6
- Step1: Split the list into two halves (Q1)
  - left:	4 → 2 → 6 → null
  - right:  -3 → 5 → null
- Step2: Sort each half (recursion)
  - left: 2 → 4 → 6 → null
  - right: -3 → 5 → null
- Step3: Combine two halves
  - dummy → -3→ 2 → 4 → 5→ 6 → null
- Total time = O(n logn + n logn) = O(n logn)
- Total Space = O(logn)

```java
public class Solution {
  public ListNode mergeSort(ListNode head) {
    if(head == null || head.next == null){
      return head;
    }
    
    ListNode mid = findMiddle(head);
    ListNode midNext = mid.next;
    mid.next = null;
    ListNode left = mergeSort(head);
    ListNode right = mergeSort(midNext);
    return merge(left, right);

  }
  private ListNode findMiddle(ListNode head){
    ListNode slow = head;
    ListNode fast = head;
    while(fast.next != null && fast.next.next != null){
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  private ListNode merge(ListNode one, ListNode two){
    ListNode dummyH = new ListNode(0);
    ListNode cur = dummyH;
    while(one != null && two != null){
      if(one.value < two.value){
        cur.next = one;
        one = one.next;
      }else{
        cur.next = two;
        two = two.next;
      }
      cur = cur.next;
    }
    if(one != null){
      cur.next = one;
    }else{
      cur.next = two;
    }
    return dummyH.next;

  }
}
```



### 2. Add Two Numbers

`You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their of the nodes contain a single digit. Add the two numbers and return it as a linked list.`

- Example: **342 + 465 = 807**

  - input:  (2 → 4 →  3) + (5 →  6 →  4)
  - output: 7 → 0 →  8

- solution:

  1. reverse linkedlist

     5 →  6 →  4

     2 →  4 →  3

     7	   0	  7 (carry = 1)

     7	   0	  8

- Follow up: Add Two Number II: **The digits are stored in normal order**

  - input: (1 →  4 →  3) + (5 →  6 →  4)	|	143 + 564 = 707
  - output: 7 →  0 →  7

```java
public class Solution{
    public ListNode assTwoNumbers(ListNode l1, ListNode l2){
        ListNode dummyH = ListNode(0); // save the new number
        ListNode cur = dummyH;
        int val = 0;
        while(l1 != null || l2 != null || val != 0){
            if(l1 != null){
                val += l1.value;
                l1 = l1.next;
            }
            if(l2 != null){
                val += l2.value;
                l2 = l2.next;
            }
            cur.next = new ListNode(val % 10);
            cur = cur.next;
            val = val / 10;
        }
        return dummy.next;
    }
}
```



### 3.check if a linked list is palindrome 回文

- Space complexity must be O(1)
- Example:
  - input:	1 →  2 →  3 →  2 →  1 →  null
  - output:  true
  - input:	1 →  2 →  3 →  null
  - output:  false
- solution:
  1. find the mid
  2. reverse the 2nd half and compare with the 1st half

```java
public class Solution{
 	public boolean isPalindrome(ListNode head){
        if(head == null || head.next == null){
            return true; //clarify with the interviwer
        }
        ListNode mid = findMiddle(head);
        ListNode poHalf = reverse(mid.next);
        mid.next = null;
        while(poHalf != null){
            if(head.value == poHalf.value){
                poHalf = poHalf.next;
                head = head.next;
            }else{
                return false;
            }
        } 
        return true;
    }
    public ListNode findMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    public ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }
}
```



### 4. Remove all nodes with target value

- Example:
  - list  =  2 →  3 →  6 →  4  →  6  →  null  |  target = 6
  - result:  2  →   3 →  4  →  null
- the second use of dummyHead:
  - head must be changed

```java
public clas Solution{
    public ListNode removeElements(ListNode head, int target){
        ListNode dummyH = new ListNode(0);
        dummyH.next = head;
        ListNode pre = dummyH;
        while(head != null){
            if(head.value == target){
                pre.next = head.next;
            }else{
                pre = pre.next;
            }
            head = head.next;
        }
        return dummyH.next;
    }
}
```



### Discussion

- When to use Iterative Way v.s Recursive Way
  - a. In real work environment, consider using iterative way first to avoid call stack overflow
  - b. In interview environment
    - for tree related problem, usually use recursion way is more preferred.
    - for other problem, you can evaluate the real problem:
      1. if you can use both method, when their time complexity the same, you can use either way
      2. if their time complexity are different. E.g. Fibonacci,  recursion v.s DP (iterative way). Of course, you should pick the one with smaller time complexity 



## 在IDE里测试

```java
package test;

import java.util.Arrays;
import java.util.Objects;


public class Solution1{

    public ListNode reorder(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = middleNode(head);
        ListNode one = head;
        ListNode two = mid.next;
        mid.next = null;
        ListNode reverseTwo = reverse(two);
        return merge(one, reverseTwo);

    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }

    public ListNode merge(ListNode one, ListNode two) {
        ListNode dummyH = new ListNode(0);
        ListNode cur = dummyH;
        while (one != null && two != null) {
            cur.next = one;
            cur.next.next = two;
            cur = two;
            one = one.next;
            two = two.next;
        }
        if (one != null) {
            cur.next = one;
        } else {
            cur.next = two;
        }
        return dummyH.next;
    }



    static class ListNode {
        public int      value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
            next = null;
        }

        public static ListNode of(int[] arr) {
            Objects.requireNonNull(arr, "arr cannot be null");
            if (arr.length == 1) {
                return new ListNode(arr[0]);
            }
            ListNode node = new ListNode(arr[0]);
            node.next = of(Arrays.copyOfRange(arr, 1, arr.length));
            return node;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(value);

            ListNode node = this;
            while (node.next != null) {
                sb.append("-");
                node = node.next;
                sb.append(node.value);
            }
            return sb.toString();
        }
    }

    public static void main(String... args) {
        int[] arr = new int[]{293,16,83,960,430,215,369,813,343,927,480,263,797,885,408,146,895,677,994,673,116,1015,330,714,954,910,339,822,903};

        ListNode origin = ListNode.of(arr);
        System.out.println(origin);

        ListNode node = new Solution1().reorder(origin);
        System.out.println(node);
    }

}
```



