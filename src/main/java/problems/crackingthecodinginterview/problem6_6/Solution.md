#Solution
Writing a program to compute this would be straight forward however since this is not allowed the task of manually performing these steps becomes quite time consuming.
Instead is there some property that we can exploit?

### Can we determine the final open or shut state of the ith locker?
A locker will be open and shut `j` times. If `j` is even, the locker will remain in the start state, closed.
If `j` is odd, the locker will be in the non start state, closed.

### When will `j` be even or odd?
`j` is dependent on the number of *unique* multiples of `i`.
Example:
```
1: 1, 1 => # unique: odd
2: 1, 2 => # unique: even
3: 1, 3 => # unique: even
4: 1, 2, 2, 4 => # unique: odd
5: 1, 5 => # unique: even
6: 1, 2, 3, 6 => # unique: even
7: 1, 7 => # unique: even
8: 1, 2, 4, 8 => # unique: even
9: 1, 3, 3, 9 => # unique: odd
```
This makes sense since each unique multiple will result in an `nth` pass.
A non-unique multiple, although it occurs twice, will result in only a single pass.

### When will non-unique multiples occur?
Non unique multiples will only occur at `a*a`, a perfect square, since a multiple can only consist of two numbers.

### How many lockers will become open, not in their start state, at the end?
If we can find all `i=a*a` from `i=1 to 100` we will know which lockers have non-unique multiples.
These lockers will become open.
```
for(a=1; a*a <= 100; a++) yield a*a;
=> [1, 4, 9, 16, 20, 36, 49, 64, 81, 100]
```
There are 10 lockers which will be open at the end.