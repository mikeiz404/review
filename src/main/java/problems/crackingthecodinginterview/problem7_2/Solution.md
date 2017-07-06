# Solution
It is easier to reason about this if we think about what the probability of not colliding is.

# !Collision
```
p(!collision) = p(all ants moving left) | p(all ants moving right)
p(all ants moving left), p(all ants moving right) are mutually exclusive

p(all ants moving left) = p(ant 1 direction = left) & p(ant 2 direction = left) & ... & p(ant n direction = left) = p^n
p(all ants moving right) = p(ant 1 direction = right) & p(ant 2 direction = right) & ... & p(ant n direction = right) = p^n

p(!collision) = p^n + p^n = 2p^n 
```

# Collision
```
p(collision) = 1 - p(!collision) = 1 - 2p^n = 1 - 2*(1/2)^n = 1 - 1/(2^n-1)
```