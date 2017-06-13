#Solution
It is not possible.
It is impossible to cover the border squares without causing the next inner border to contain an odd number of squares.

The initial setup of an `a` by `b` grid, where `a` and `b` are even, with two diagonal corners removed results in a 4 sided border where each side has an odd number of uncovered squares.
These squares can be covered by either placing a 1x2 rectangle perpendicular with the a border side (covers 1 border side square) or parallel to a border side (covers 2 border side squares).
Since the border sides have an odd number of uncovered squares and `odd - even = odd`, there must be an odd number of rectangles placed perpendicular to a border side.
And since the inner border side is even, an odd number of squares now cover the inner border side, and `even - odd = odd`, the inner border must now have an odd number of uncovered squares.
This logic can be applied recursively.
We can see that it is impossible to reach border sides with 1x2 uncovered squares since the uncovered squares in the border sides must always be odd.

Note: More elegant square coloring solution in book.
