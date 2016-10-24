# Problem
Implement a data structure with the stack interface, composed of a set of stacks with a maximum stack size.
A push on a full stack will overflow onto a new stack. Conversely a pop will remove an empty stack.

The data structure has the additional method `popAt(index)` to pop off an arbitrary stack.
All stacks before the current stack must maintain their maximum stack size.