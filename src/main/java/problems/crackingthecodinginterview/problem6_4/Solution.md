#Solution
Let `d` be the number of blue-eyed people a person can can see.
Let `c` be the actual number of blue-eyed people on the island.
From this persons perspective `c` must be `d` if they do not have blue eyes or `c + 1` if they do have blue eyes.

How can the person determine if they have blue eyes?

Base: `c=1`
If one person has blue eyes and `d = 0` then they must have blue eyes.
If one person has blue eyes and `d = 1` then they must not have blue eyes.
The blue eyed person will leave today.

Base: `c=2`
A blue-eyed person will not leave today since `d=1`.
They must wait one day to determine if `d=c`.
If a blue-eyed person leaves today (`c=1`), then they must not be blue eyed.

Recursive: `c > 2`
Must wait 1 + rec(c=`d`) days.
If `d + 1` days have passed and no one has left then they must blue eyes.

All people with blue eyes will leave in `c` days all at once.