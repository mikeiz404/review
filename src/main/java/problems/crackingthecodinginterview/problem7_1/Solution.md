# Solution
First we need to figure out the probabilities of winning each game.

## Game 1
`P(making 1 ball in 1 hoop) => p` by definition of `p`.

## Game 2
```
A = making the first ball into the hoop
B = making the second ball into the hoop
C = making the third ball into the hoop
A, B, C are independent events.

P(making two or more balls into the hoop) => P(making two of three balls into the hoop) | P(making all three balls into the hoop) => P(making two of three balls into the hoop) + P(making all three balls into the hoop)
P(making two of three balls into the hoop), P(making all three balls into the hoop) are mutually exclusive.

P(making all three balls into the hoop) => P(A & B & C) => p^3

P(making two of three balls into the hoop) => P(A & B & !C) | P(A & !B & C) | P(!A & B & C) => 3 * (p * p * (1 - p)) => 3p^2 * (1 - p) => 3p^2 - 3p^3

P(making two or more balls into the hoop) => (p^3) + (3p^2 - 3p^3) => 3p^2 - 2p^3
```

## Game 1 or Game 2
We will choose game 1 when the probability of winning is greater than game 2.

```
P(winGame1) > P(winGame2)
=> p > 3p^2 - 2p^3
=> 0 > 3p^2 - 2p^3 - p
=> 0 > p(3p - 2p^2 - 1)
=> 0 > -2p^2 + 3p - 1
=> 0 > -2(p^2 -3/2p + 1/2)
=> 0 > -2(p - 1)(p - 1/2)
=> 0 < (p - 1)(p - 1/2)
```

When `p > 1` and `p > 1/2` or when `p < 1` and `p < 1/2`.
However the latter cannot be true since a probability is non-negative.
Also `p > 1` is not possible for a probability.

Game 1 should be chosen over Game 2 when `p > 1/2` to optimize the probability of winning.

(See book for a more detailed description).