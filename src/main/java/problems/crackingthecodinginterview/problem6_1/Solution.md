#Solution
Only one weighing is allowed so at best you can determine which bottle is heavier of two without modifying the bottles.
However the scale provides a precise measurement so combining pills in various combinations will result in varying weights.
If we can modify the composition of what is being weighed such that the weight encodes which bottle contains the heavier pills, we can figure out which is the heavier bottle in one weighing.
By placing a unique number of pills from each bottle on the scale, we can subtract the expected weight if all pills were to weigh the same to get the extra weight.
This measured extra weight divided by the difference in the heavy bottle's weight will tell us how many of those pills contributed to the excess weight, thus indicating which bottle they came from.

1. Starting from `bottleId=1` to `|bottles|`, place `bottleId` number of pills onto the scale.
2. Measure.
3. `extraWeight = measure() - ((|bottles| * (|bottles| + 1) / 2) * 1.0)`
4. `heavyBottleId = extraWeight / (1.1 - 1.0)`