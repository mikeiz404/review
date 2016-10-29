# Problem
Given a list of numbers and a target value determine if it is possible to reach that target value using a given set of operations.
Assume operation precedence is right to left.
Assume number order is fixed.

## Example:
```
operations: [+, -, *, /], numbers: [1, 1, 1], target: 4 => 1 ? 1 ? 1 ==> false
operations: [+, -, *, /], numbers: [0, 10, 2], target: 20 ==> 0 ? 10 ? 2 ==> true
```