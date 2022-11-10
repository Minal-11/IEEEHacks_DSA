----------------------------------------Monotonic stack working----------------------------------------------------------
  // A DETAILED AND COMPLETE EXPLAINATION
  A monotonic stack is a stack whose elements are monotonically increasing or descreasing.
Sometimes we store the index of the elements in the stack and make sure the elements corresponding to those indexes in the stack forms a mono-sequence.
-> Increasing or decreasing?
If we need to pop smaller elements from the stack before pushing a new element, the stack is decreasing from bottom to top.
Otherwise, it's increasing from bottom to top.
  
For example,
Mono-decreasing Stack
Before: [5,4,2,1]
To push 3, we need to pop smaller (or equal) elements first
After: [5,4,3]

Notes
For a mono-decreasing stack:
•	we need to pop smaller elements before pushing.
•	it keep tightening the result as lexigraphically greater as possible. (Because we keep popping smaller elements out and keep greater elements).
Sample problem   take Remove K Digits for example, since we are looking for lexigraphically smallest subsequence, we should use mono-increasing stack.
  
Approach 
•	Maintain a monotonic stack to keep track of peak digits starting from the left most digit of num
•	Peak digit is a digit which is greater than its next digit in num
•	If TOS (top of stack) is greater than current digit, then pop, since TOS is a peek.
•	Don't push 0 in the stack as the leading digit of a number can't be 0, since top of stack represents the most significant digit
•	If after traversing all the chars of num, k digits haven't been removed then it means num didn't have enough peaks and the numbers in the stack are in descending order, starting from TOS, so keep removing from the stack until k = 0.
•	If stack is empty then return 0, else return string representation of stack top to bottom.
Run the code on this example 
num = 1432219, k = 3
output: 1219
  explaination:
we first push 1 into the stack
           |         |
           |         |
           |         |   <-----Stack representation
           |   1     |
           |_________|

now we encounter 4 we check if 4 is lesser than 1 as it is not we push into the stack.

           |         |
           |         |
           |         |   <-----Stack representation
           |   4     |
           |___1_____|

then 3 comes as it is lesser than the top of the stack we pop 4 out and we push 3 in while simultaneously decrementing k . 

           |         |
           |         |
           |         |   <-----Stack representation  k=2
           |   3     |
           |___1_____|

2 beats 3 so 3 is popped out and 2 is pushed and k is decremented.

           |         |
           |         |
           |         |   <-----Stack representation  k=1
           |   2     |
           |___1_____|
2 doesnt beat 2 so it is pushed into the stack

           |         |
           |         |
           |   2     |   <-----Stack representation
           |   2     |
           |___1_____|
1 beats 2 so 2 is popped out and 1 is pushed in.

           |         |
           |         |
           |   1     |   <-----Stack representation   k=0
           |   2     |
           |___1_____|    
now we have the push whatever number is left as we have used up all our deletions

           |         |
           |   9     |
           |   1     |   <-----Stack representation
           |   2     |
           |___1_____|

           answer:-1219.

<code>

public String removeKdigits(String num, int k) {
	var stack = new ArrayDeque<Character>();

	for (var i = 0; i < num.length(); i++) {
		var digit = num.charAt(i);

		while (k > 0 && !stack.isEmpty() && stack.peek() > digit) {
			stack.pop();
			k--;
		}
		if (!stack.isEmpty() || digit != '0')
			stack.push(digit);
	}
	
	// use the remaining k
	while (k-- > 0 && !stack.isEmpty())
		stack.pop();
	return toString(stack);
}

private String toString(Deque<Character> stack) {
	if (stack.isEmpty())
		return "0";
		
	var reduced = new StringBuilder();
	stack.descendingIterator()
		 .forEachRemaining(reduced::append);
	return reduced.toString();
}

t.c-time complexity: O(n)/O(n), where n = size(num)
  
hope you liked it!
