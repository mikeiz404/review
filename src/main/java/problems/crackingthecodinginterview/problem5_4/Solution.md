# Solution
The statement returns true when `n` is a power of two or 0, and false otherwise.

## When n is a power of two
All `n = 2^x` will have a single bit set to `1` in binary.
Let `p` be the bit position of this bit (`p = log2(n)`).
All `n-1 = (2^x)-1` will have the bits below `p` set to `1` and the bit at `p` will be set to 0.
`n & (n-1)` must result in `0` when n is a power of two.

## When n is not a power of two
If `n` is *not* a power of two then `n` must have more than one bit set in binary.
Let `p` represent the least significant `1`'s bit position in `n`.
`n - 1` can only ever change the bit at `p` and all the bits before `p`, which must be `0`s.
Since `n` is *not* a power of two, there must be `1`s which come after `p`.
These upper bits will not be changed.
Therefore `n & (n-1) != 0` when n is *not* a power of two.
