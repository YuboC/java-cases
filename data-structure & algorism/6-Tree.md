#   6-Binary Tree & Binary Search Tree

[TOC]



## Basic Principle

## General Tree

**each node can have an arbitrary number of children**

```java
class TreeNode {
    int key;
    List<TreeNode> children;
    // TreeNode parent;
    public TreeNode(int key) {
        this.key = key;
        children = new ArrayList<TreeNode>();
    }
}
```



### Binary Tree

Definition: at most two children nodes (最多两个)

```java
LinkedList is a special kind of Tree (if there is only one child for each of the node in the binary tree, it can be reduced to a linked list)
class ListNode {	// for ListNode1 my own address 0XFFF001
	int value;		// = 1;
    string name;	// = "Node1"
    ListNode next;	// = 0XFFF999
}

binary tree:
class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;	// 0xFFF001
    //TreeNode parent
    // We can store parent reference as well, this will be useful for solving some problems
    public TreeNode(int key){
        this.key = key;
    }
} 
```

​					**10**	<=	root

​				/		 \

​			**5				15**

​		/	  \			/	   \

​	**2			7	  12		20**	<=	all leaf node's level = 3

/	  \

null   null

### Tree traversal

不要把leaf node作为base case，而是把leaf node 下的 null作为base case

- **Pre-order** (自 - 左 - 右)： 10 5 2 null null 7 null null 15 12 null null 20 n n

  ```java
  //every node in recursion tree: a recursion function call
  
  void preOrder(TreeNode root){
  	if (root == null){
          return;	//base case
      }
      System.out.println(root.value);
      preOrder(root.left);
      preOrder(root.right);
  }
  ```

  ```java
  /**
   * public class TreeNode {
   *   public int key;
   *   public TreeNode left;
   *   public TreeNode right;
   *   public TreeNode(int key) {
   *     this.key = key;
   *   }
   * }
   */
  public class Solution {
    public List<Integer> preOrder(TreeNode root) {
      List<Integer> res = new ArrayList();
      helper(root, res);
      return res;
    }
    public void helper(TreeNode root, List<Integer> res) {
      if (root == null) {
        return;
      }
      res.add(root.key);
      helper(root.left, res);
      helper(root.right, res);
    }
  }
  ```

- **in - order** (左 - 自 - 右) ：null 2 null 5 null 7 null 10 null 12 null 15 null 20 null

  ```java
  void inOrder(TreeNode root){
      if (root == null){
          return;
      }
      inOrder(root.left);
      System.out.println(root.value);
      inOrder(root.right);
  }
  ```

- **post - order** ( 左 - 右 - 自)： null null 2 null null 7 5 null null 12 null null 20 15 10

  ```java
  void inOrder(TreeNode root){
      if (root == null){
          return;
      }
      inOrder(root.left);
      inOrder(root.right);
      System.out.println(root.value);
  }
  ```

  

### Balanced Binary Tree

is commonly defined as a binary tree in which the **height** of the left and right subtrees of **every node** differ by 1 or less

```markdown
		10 == root					root
	   /   \						/	\
	  5		15					  node	node
	/ \		/	\				  /		  \
   2	n	n	null			node	  node
  / \							
 n	null					
```

**Conclusion1:**

- **If a  tree has n  number of nodes, and it is balanced, then the height(level) of the tree = O(log_2(n))** 

- if a tree has n number of nodes, but we don't know if it is balanced, then the height of the tree = O(n)





### Complete Binary Tree

is a binary tree in which every level, **except** possibly **the last**, is **completely filled**, and all nodes **are as far** **left** as possible

**conclusion2:**

- if a tree is a complete tree, then it must be a balanced tree
- 存储方便，一个array就可以存: [10   5   15   2   12   20]
  - 任意第 **i** 个node，其left index是 **2i+1**, right index 是 **2i+2**

```markdown
		10 == root					
	   /   \						
	  5		15				
	/ \		/	\			
   2   12  20	null			
  / \							
 n	null			
```



### Binary Search Tree

for **every single node** in the tree, the values in its **left** subtree are **all smaller** than its value, and the values in its **right** subtree are **all larger** than its value.

```markdown
		10 == root					
	   /   \						
	  5		15				
	/ \		/	\			
   2   7  12	20	
```

```markdown
BST 查找的时间复杂度不一定是O(log n), 因为不一定是balanced
			3
		   /
		  2
		 /
		1
	   /
	  0
	 /
	-1
time complexity = O(n)

或者统一说成：O(Height)
```

- if we print the value of the nodes in BST in in-order sequence, then it must form an ascending order

  if there are duplicate values? : 10, 10, 5, 5, 5, 15, 2, 7, 12, 20

  ```java
  	   (10,2) == root					
  	   /   \						
  	(5,3)  (15,1)				
  	/ \		/	\			
     2   7  12	20	
  
  class TreeNode {
      int key;
      int counter;
      TreeNode left;
      TreeNode right;
      public TreeNode(int key) {
          this.key = key;
      }
      //use recursion
      public TreeNode searchR(TreeNode root, int target) {
          if (root == null || root.key == target) {
              return root;
          }
          if (root.key < target) {
              return searchR(root.right, target);//break point
          } else {
              return searchR(root.left, target);
          }
      } 
      //use iteration
      public TreeNode search(TreeNode root, int target) {
          while (root != null && root.key != target) {
  			if (root.key > target) {
                  root = root.left;
              } else {
                  root = root.right;
              }
          }
          return root;
      }
  }
  
  Tail Recursion:
  The recursion call is always the last execution statement
  We can easily transfer the tail recursive to iterative solution.
      
  Not a tail recursion: //last call is "and &&"
  1. return isBest(root.left, ..) && isBst(root.right, ..)
  2. return a && isBst(root.right, ..)
  
  in fib(int n, int a, int b) {
      if (n == 0) {
          return a;
      }
      if (n == 1) {
          return b;
      }
      return fibo(n-1, b, a + b);
  }
  
  int fib (int n) {
      int a = 0, b = 1, c, i;
      if (n == 0) {
          return a;
      }
      for (i = 2; i <= n; i++) {
          c = a + b;
          a = b;
          b = c;
      }
      return b;
  }
  ```



### Discussion(High Level) 

- Binary Tree 往往是最常见，和recursion 结合最紧密的面试题目类型
- Reason：
  - 每层的node 具备的性质，传递的值和下一层的性质往往一致。比较容易定义recursion rule
  - **base case** (generally): null pointer under the leaf node
  - Example1: `int getHeight (node root)`
  - Example2: 统计tree里边有多少个node



## Questions

### getHeight()



<img src="C:\Users\n56vv\AppData\Roaming\Typora\typora-user-images\image-20200722134006178.png" alt="image-20200722134006178" style="zoom: 50%;" />

```java
int getHeight (TreeNode) {
    if (root == NULL) { //base case
        return 0;
    }
    /*
    //recursion rule
    //Step 1: ask children for info
   	 	int leftHeight = getHeight(root.left);
    	int rightHeight= getHeight(root.right);
    //Step 2: compute the info of the current node
    	int selfHeight = max(leftHeight, rightHeight) + 1;
    //Step 3: return the info to its parent
    	return selfHeight;
    */
    return max(getHeight(root.left), getHeight(root.right)) + 1;
}

Step1 and Step3是一致的：recursion就是用同样方法解决同样的的问题

一个recursion function的时间复杂度是所有node时间复杂度之和 n*O(1) = O(n)
    
time complexity = O(n) where n is the height
space complexity= O(n) [worst case] / O(log n) [if balanced]
```

### isBalanced()

```java
//判断是否为balanced
public boolean isBalanced(TreeNode root){
    if (root == null){
        return true; //base case, 空子树一定balanced
    }
    int leftHeight = getHeight(root.left);//Step2
    int rightHeight= getHeight(root.rigth);//Step2
    if(Math.abs(left.height - rightHeight) > 1){
        return false;
    }
    return isBalance(root.left) && isBalance(root.right));//step1 & step3
}

Time complexity(nodes之和): 
(worst case:balanced)： = [getHeight(root.left)] + [getHeight(root.right)]+ O(1) [if] 
    				    = n/2 + n/2 + n/4 + n/4 + n/4 + n/4 +... 
    					= 每层为n，总共logn层 =O(n·logn)
(best case: 单边一串)： O(n)
```

```java
public class Solution {
  public boolean isBalanced(TreeNode root) {
    if (root == null) {
      return true;
    }
    int left = findHeight(root.left);
    int right= findHeight(root.right);
    if(Math.abs(left - right) > 1) {
      return false;
    }
    return isBalanced(root.left) && isBalanced(root.right);
    //check at every subtree node
  }
  private int findHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return max(findHeight(root.left), findHeight(root.right)) + 1;
  }
  private int max(int a, int b) {
    if (a >= b) {
      return a;
    } else {
      return b;
    }
  }
}

//Method 2: better O(n) algrism
public boolean isBanlanced2(TreeNode root) {
    if (root == null) {
        return null;
    }
    // use -1 to denote the tree is not balanced
    // >= 0 value means the tree is balanced and it is height of the tree
    return height(root) != -1;
}
private int height(TreeNode root) {
    if (root == null) {
        return 0;
    }
    int leftHeight = height(root.left);
    // if left subtree is already not balanced, we do not need to continue
    // and we can return -1 directly
    if (leftHeight == -1) {
        return -1;
    }
    int rightHeight = height(root.right);
    if (rightHeight == -1) {
        return -1;
    }
    //if not balanced, return -1
    if (Math.abs(leftHeight - rightHeight) > 1) {
        return -1;
    }
    return Math.max(leftHeight, rightHeight) + 1;
}
```



### isSymmetric()

```markdown
input tree:
				10
		   5a	|	 5b
	  1a	 3a | 3b     1b
    2a 4a  6a 8a|8b 6b  4b	2b
    
Recursion Tree:
				isSym(5a,5b)
	isSym(1a,1b)     |     isSym(3a,3b)
(2a,2b)		(4a,4b)	 |	(6a,6b)	  (8a,8b)
(nullnull)............          <= base case


```

```java
boolean isSym(TreeNode left, TreeNode, right){
    if (left == null && right == null) {
        return true;	//base case
    } else if (left == null || right == null) {
        return false;	//base case
    } else if (left.value != right.value) {
        return false;	//base case
    }
    return isSym(left.left, right.rigth) && isSym(left.right, right.left);
}

Time = O(n/2) = O(n) //the recursion tree (n/2 nodes) is different from the input tree(n nodes)
space= O(height)
```

### isStrucId()

Let's assume if we **tweak** the **left child** with **right child** of an arbitrary node in a binary tree, then the **"structure" of the tree** are **not changed**. Then how can we determine whether two binary trees' **structures are identical**.

```markdown
case1:							case2:
    8a		and		8b		OR		8a		and		8b
   /  \			   /  \			   /  \			   /  \
  4a  5a		  4b   5b		  4a   5a		  4b   5b
  |				  |				  |				  	   |
  7				  7				  7				  	   7
        
recursion tree:
		    isIdentical(8a,8b)
       /		/	   \	  	  \
 (4a,5b) && (5a,4b)	|| (4a,4b) && (5a,5b) 
   	[tweak后]	    	[未tweak的情况]
```

```java
boolean isStrucId(TreeNode one, TreeNode two) {
    if (one == null && two == null) {
        return true;		// base case 1
    }else if (one == null || two == null) {
        return false;		// base case 2 + 3
    }else if (one.value != two.value) {
        return false;		// base case 
    }
    return isStrucId(one.left, two.left) && isStrucId(one.right, two.right) //case1
     		|| isStrucId(one.left, two.right) && isStrucId(one.right,two.left);//case2
}

Time complexity:
worst-case: O(4n) = O(n)
    1a 		1b				isStrucId(1a,1b)
    |		|				/	/		\		\
    2a		2b		(2a,2b)	(null,null)	(2a,n)	(n,2b)
    |		|		| | | |		|			|		|
    3a		3b		  4n		true		false	false
    |		|
    4a		4b
    |		|
    5a		5b
    
balanced tree case: 
input tree level= log(n)
recursion tree level = log(n) + 1
time complexity = 1 + 4 + 16 + 64 + ... + 4*log(n) 	
    			= O((4^(logn + 1) - 1))/(4-1))
    			= O(4^(logn + 1))
    			= O(4^logn)
    			= O(2^2^logn)
    			= O(2^logn)^2
    			= O(n^2)
```



### isBst()?

```markdown
			10 == root
		   /		\
		  5			15
		 / \		/ \
		2   7	   12 20
```

- **Solution 1a**: (primitive way but very bad in terms of space consumption)
  - step1: **inorder** traverse the tree and store all numbers in an arraylist
  - step2: iterate over the array to determine, whether A[ i ] < A[i+1]

- **Solution 1b**: compare with prev at each recursion 

  ```java
  /*
  because java doesn't have global variable, so we use class solution to call isBst()
  or pass the value into isBst()
  			10 == root
  		   /		\
  		isBst(5)  isBst(15)
  		 / \		/ \
  	   (2) (7)	 (12) (20)
  */
  伪代码：
  TreeNode prev = null;
  boolean isBst = true;
  void InOrder(TreeNode p, TreeNode[] prev, boolean[] isBst){
      if (p == null) {
          return;
      }
      InOrder(p.left);
      if (prev != null && prev.value >= p.value) {
          isBst = false;
          return;
      }
      prev = p;
      InOrder(p.right);
  }
  Time complexity = O(n)
  space complexity = O(n), heap上没有new，stack上
  ```

- **Solution 2：** 

  ```markdown
  	10(min = -inf, max = +inf) == root
  		   /					\
         5[min=-inf,max=10]		15[]
  		 / 				\		 / \
  	2[min=-inf,max=5]     7	   	12 20
  
  every node 与左子树最大值比较，与右子树最小值比较
  ```

- **Solution 3: 从下往上**

  ```java
  //root 的期望位置在左子树中最大和右子树中最小的开区间内
  boolean isBstHelper(TreeNode root = 10, int min, int max) {
      if (root = null ) {
          return true;
      }
      if (root.val <= min || root.val >= max) {
          return false;
      }
      return isBstHelper(root.left, min, root.val) &&
          	isBstHelper(root.right, root.val, max);
  }
  time = O(n);
  space= O(height);
  ```

  

### BST print in range

given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree. Print all the keys of tree in range k1 to k2. i.e. print all x such that k1 <= x <= k2 and x is a key of given BST. Print all the keys in an increasing order.

- Step1: determine whether we need to go left. `only if (root.value > lower_bound) do we need to go left`
- Step2: determine whether we need to go right.`only if (root.value < upper_bound) do we need to go right`

```java
void rangeInOrderTra(TreeNode root, int lower, int upper) {
    if (root == null) {
        return;
    }
    
    rangeInOrderTra(root.left, lower, upper); //go left till end
    
    if (root.value >= lower && root.value <= upper) {
        print(root.value);
    }

    rangeInOrderTra(root.right, lower, upper);//go right
}
Time = O(n) worst case
Space= O(height)
    
void rangeInOrderTra(TreeNode root, int lower, int upper) {
    if (root == null) {
        return;
    }
    if (root.value > lower) { //剪枝 pruning
        rangeInOrderTra(root.left, lower, upper);
    }
    if (root.value >= lower && root.value <= upper) {
        print(root.value);
    }
    if (root.value < upper) { //剪枝 pruning
        rangeInOrderTra(root.right, lower, upper);
    }
}
Time = O(height + # of nodes in the range of [k1, k2])
```

### find key node

```java
public class SearchBst {
    public TreeNode search(TreeNode root, int key) {
        if (root == null || root.key == key) {
			return root;
        }
        return search(root.key > key ? root.left : root.right, key);
    }


// method with iterator
    public TreeNode seachII(TreeNode root, int key) {
        TreeNode cur = root;
        while (cur != null && cur.key != key) {
            cur = cur.key > key ? cur.left : cur.right;
        }
        return cur;
    }
}
```

### Insert in Binary Search Tree

https://www.youtube.com/watch?v=RIDBLO-S7OA

```java
//Recursion I：
public class InsertBst {
    public TreeNode InsertBst(TreeNode root, int val) {
        if (root == null || root.key == val) {
            //break point, return回parent
            return new TreeNode(val);
        }
        if (root.key < val) {
            //有dereference, heap上的写操作
            //才算把新node挂在了tree上
            root.right = InsertBst(root.right, val);
        }
        if (root.key > val) {
            root.left = InsertBst(root.left, val);
    	}
        return root;
    }
}

//Recursion II (Remove redundant operation):
//also a tail recursion
public static TreeNode insert(TreeNode root, int target) {
    if (root == null) {
        return new TreeNode(target);
    }
    helper(root, target);
    return root;
}
public static void helper(TreeNode root, int target) {
    if (target == root.key) {
        return;
    } else if (target < root.key) {
        if (root.left == null) {
            root.left = new TreeNode(target);
        } else {
            helper(root.left, target);
        }
    } else {
        if (root.right == null) {
            root.right = new TreeNode(target);
        } else {
            helper(root.right, target);
        }
    }
}

//Iteration way:
	//往下看
	public TreeNode IsertBst(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode cur = root;//最终返回root，不能改
        while (root.key != val) {
            if (root.key <= val) {
                if (root.right != null) {
                    cur = cur.right;
                }
                cur.right = new TreeNode(val);
            } else {
                if (root.left != null) {
                    cur = cur.left;
                }
                cur.left = new TreeNode(val);
            }
        }
        return root;
    }
	
	//往上看
	public TreeNode isert(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        TreeNode prev = null;
        TreeNode curr = root;
        while (curr != null) {
            prev = curr;
            if (curr.key == target) {
                return root;
            } else if (curr.key < taget) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        
        TreeNode newNode = new TreeNode(target);
        if (prev.key < target) {
            prev.right = newNode;
        } else {
            prev.left = newNode;
        }
        return root;
    }
```

### Delete in BST

`Case 1`  - the node to be delete has no children

​				3							3

​			/		\		→		/	

​			2		**8**				2

`Case 2` - the node to be deleted has no left child

​				3							3

​			/		\					/		\

​			2		**8**		→	2			**10**

​						\								\

​						**10**							12

​							\

​							12

`case 3` - the node to be deleted has no right child

​				3							3

​			/		\					/		\

​			2		**8**		→	2			**6**

​					/								/

​				**6**								5

​			/

​		5

`Case 4` - the node to be delete has both left and right child. we need to move some nodes from left/right subtree to replace it

- which node to replace the target?
  - either from left sub, **largest of the left subtree**
  - or from right sub, **smallest of the right subtree**

- case4.1: `node.right` does not have left child, meaning itself is the smallest node. in this case, we just move `node.right` up.

  ​				3							3

  ​			/		\					/		\

  ​			2		**8**		→	2			**10**

  ​					/	\							/	\

  ​				6		**10**					6		 12

  ​			  /				\				/

  ​			5		    		12	    5

  - root → 8
  - root.right → 10
  - root.right.left → null
  - root.right.left = root.left

- Case4.2: `node.right` has left child, we need to find the smallest node, and move it up

  ​				3							3

  ​			/		\					/		\

  ​			2		**8**		→	2			**9**

  ​					/	\							/	\

  ​				6		12					6		 12

  ​			  			/	\							/	\

  ​					  11(p)   14	    			11	14	

  ​					/									/

  ​				**9**									10

  ​					\								/	\

  ​					10

  9 → left → left → left

  9.left == null

```java
public class DeleteBST {
    //返回值不能是Boolean，因为root可能会变
    public TreeNode delete(TreeNode root, int target) {
		if (root == null) {
            return null;
        }
        if (target == root.key) {
		*	if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else if (root.right.left == null) {
                root.right.left = root.left;
                return root.right;
            } else {
                TreeNode newRoot = deleteSmallest(root.right);
                newRoot.left = root.left;
                newRoot.right= root.right;
                return newRoot;
            }
        }
        if (target < root.key) {
            root.left = delete(root.left, target);
        } else if (key > root.key) {
            root.right = delete(root.right, target);
        }
        return root;
    }
    private TreeNode deleteSmallest(TreeNode root) {
        while (root.left.left != null) {
            root = root.left;
        }
        TreeNode smallest = root.left;
        root.left = root.left.right;
        return smallest;
    }
}

public TreeNode delete(TreeNode root, int target) {
    if (root == null) {
        return null;
    }
    //find the target node
    if (root.Key > target) {
        root.left = delete(root.left, target);
        return root;
    } else if (root.key < target) {
        root.right = delete(root.right, target);
        return root;
    } //到这里意味着root.key == target
      //guarantee root != null && root.key == target
    
    //case 1:  left&right=null, return 给上边delete()中的一个
    //case 2: reurn root.right/left 给delete()中的一个
    if (root.left == null) {
        return root.right; 
    } else if (root.right == null) {//case 3
        return root.left; 
    }
    
    //guarantee root.left != null && root.right != null
    //4.1
    if (root.right.left == null) {
        root.right.left = root.left;
        return root.right;
    }
    //4.2
    //1. find and delete smallest node in root.right.
    TreeNode smallest = deleteSmallest(root.right);
    //2. connect the smallest node with root.left and root.left
    smallest.left = root.left;
    smallest.right = root.right;
    //3. return the smallest node
    return smallest;
}
	public TreeNode deleteSmallest(TreeNode cur) {
        TreeNode prev = cur;
        cur = cur.left;
        while (cur.left != null) {
            prev = cur;
            cur = cur.left;
        }
        // cur is the smallest one, and prev is its parent
        // Invariance: curr (prev.left) does not have left child
        prev.left = prev.left.right;//cur.right;
        return cur;
    }
```



### Pre-Order Traversal of BT (iterative)

```java
public class preOrderBt {
    public List<Integer> preOrderBt(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> result = new ArrayList();
        Deque<Integer> stack = new LinkedList();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
          TreeNode cur = stack.pollFirst(); 
          if (cur.right != null) {
              stack.offerFirst(cur.right);
          }
          if (cur.left != null) {
              stack.offerFirst(cur.left);
          }
          result.add(cur.key);
        }
        return result;
    }
}
```

```markdown
		5
	  /  \
	 3	  8
	/ \	   \
   1   4	11
pre-order traversal: [5,3,1,4,8,11]

#1 
result = [] 	`//new Arraylist()
stack  = [  	`//new LinkedList()
stack  = [*5* 	`//stack.offerFirst(root)
cur = *5*		`//stack.pollFirst()
stack  = [
cur.right = *8* `//!= null
stack  = [*8*	`//stack.offerFirst(cur.right)
cur.left = *3*	`//!= null
stack  = [*8*,*3*`//stack.offerFirst(cur.left)
result = [5]	`//result.add(cur.key)

#2
stack  = [*8*,*3* `//!isEmpty()
cur = *3*		`//stack.pollFirst()
stack  = [*8*	`//after poll
cur.right = *4*
stack  = [*8*,*4*
cur.left  = *1*
stack  = [*8*,*4*,*1*
restult= [5, 3]

#3
stack  = [*8*,*4*,*1* `//!isEmpty()
cur = *1*		`//stack.pollFirst()
stack  = [*8*,*4*	`//after poll
cur.right = null
stack  = [*8*,*4*
cur.left  = null
stack  = [*8*,*4*
restult= [5, 3, 1]

#4
stack  = [*8*,*4*`//!isEmpty()
cur = *4*		`//stack.pollFirst()
stack  = [*8*	`//after poll
cur.right = null
stack  = [*8*
cur.left  = null
stack  = [*8*
restult= [5, 3, 1, 4]
...
```

### In-Order Traversal BT

```java
public class InOrder {
    public list<Integer> inOrder(TreeNode root) {
		List<Integer> inorder = new ArrayList<Integer>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty) {
            //always try to go to the left side to see if there is any node
            //should be traversed before the cur node, cur node is stored 
            //on stack since it has not been traversed yet
            if (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            } else {
                //if can not go left, meaning all the node on the left side of
                //the top node on stack have been traversed, the next traversing 
                //node should be the top node on stack
                cur = stack.pollFirst();
                inorder.add(cur.key);
                //the next subtree we want to traverse is cur.right.
                cur = cur.right;
            }
        }
        return inorder;
    }
}
```



### Post-Order Traversal BT

```java
public class PostOrder {
    // method1: post-order is the reverse order of pre-order with traversing
    // right subtree before traversing left subtree
  public List<Integer> postOrderI(TreeNode root) {
    List<Integer> result = new ArrayList<Integer>();
    if (root == null) {
        return result;
    }
    Deque<TreeNode> preOrder = new LinkedList<TreeNode>();
    preOrder.offerFirst(root);
    while (!preOrder.isEmpty()) {
        TreeNode current = preOrder.pollFirst();
        //conduct the result for the special pre-order traversal
        result.add(current.key);
        // in pre-order the right subtree will be traversed before the 
        // left subtree so pushing left child first
        if (current.left != null) {
            preOrder.offerFirst(current.left);
        }
    }
    //reverse the pre-order traversal sequence to get the post-order result
    Collections.reverse(result);
    return result;
  }
```

```java
// Method 2: check the relation between the current node and the previous node
// to determine which direction should go next
  public List<Integer> postOrderII(TreeNode root) {
	  List<Integer> result = new ArrayList<Integer>();
      if (root == null) {
          return result;
      }
      Deque<TreeNode> stack = new LinkedList<TreeNode>();
      stack.offerFirst(root);
      //to record the previous node on the way of DFS so that
      //we can determine the direction
      TreeNode prev = null;
      while (!stack.isEmpty) {
          TreeNode cur = stack.peekFirst();
          //if we are going down, either left/right direction
          if (prev == null || cur == prev.left || cur == prev.right) {
              //if we can still go down, try go left first
              if (cur.left != null) {
                  stack.offerFirst(cur.left);
              } else if (cur.right != null) {
                  stack.offerFirst(cur.left);
              } else {
                  // if we can not go either way, meaning cur is a leaf node
                  stack.pollFirst();
                  result.add(cur.key);
              }
          } else if (prev == cur.right || prev == cur.left && cur.right == null) {
              stack.pollFirst();
              result.add(cur.key);
          }
          prev = cur;
      }
      return result;
  } 
}
```



