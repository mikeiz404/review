#Solution
One could drop an egg from floor `1` to `N`, reusing the egg, until `N` is found.
However since there are two eggs, another test is still possible, and this could be done more efficiently.

With two eggs we can do a linear search with the last egg, and do a different search with the first egg to establish a lower bound.
We can do a linear search over multiple floors to decrease the search space for the second egg.

If we use `10` as the multiple for the first egg, then we get these costs:
Breaks on floor 10: egg1(1) + egg2(9)
Breaks on floor 50: egg1(5) + egg2(9)
Breaks on floor 100: egg1(10) + egg2(9)
This generalizes to `c = f/w + (w - 1)` where `c` is the number of checks required, `f` is the floor to check, and `w` is the skip width.

We need to determine if `w=10` is the best choice. We can see that the worst case occurs at `f=100` since egg2 checks are fixed and egg1 checks are proportional to `f`.
Therefore if we maximize `100/w + (w - 1)` we can determine the best `w`.
`d/dw(100/w + (w - 1)) = 0 => -100/w^2 + 1 = 0 => -100/w^2 = -1 => w^2 = 100 => w = sqrt(100) => w = +/-10`.
So `w = 10` is optimal for this method.

Can we do better?
Currently egg1 requires a variable number of checks and egg2 requires a constant number.
What if we make the number of egg2 checks required equal to the number of egg1 checks required so they are balanced.

Example: Left side is egg1 checks required, right side is egg2 checks required.
```  
  10  
->9  
  8<-  
  7<-  
  6<-  
->5  
  4<-  
  3<-  
->2  
  1<-  
```

At floors `1 + 2^x`, `2x` checks are required using the two eggs.
So at floor 100, the worst case, 14 checks are required using this method.
(See book for an alternate explanation)