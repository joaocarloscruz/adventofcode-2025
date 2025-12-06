## Problem
--- Part Two ---
The Elves start bringing their spoiled inventory to the trash chute at the back of the kitchen.

So that they can stop bugging you when they get new inventory, the Elves would like to know all of the IDs that the fresh ingredient ID ranges consider to be fresh. An ingredient ID is still considered fresh if it is in any range.

Now, the second section of the database (the available ingredient IDs) is irrelevant. Here are the fresh ingredient ID ranges from the above example:

3-5
10-14
16-20
12-18
The ingredient IDs that these ranges consider to be fresh are 3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, and 20. So, in this example, the fresh ingredient ID ranges consider a total of 14 ingredient IDs to be fresh.

Process the database file again. How many ingredient IDs are considered to be fresh according to the fresh ingredient ID ranges?

## Solution     
To solve this problem we just need to take care of the overlapping ranges and then do (End - Start + 1) for every range and we get the total number of possible ids. To solve the overlapping issues we can update the array for the previous exercise to be a list of arrays (List<Long[]>) and then sort everything based on the starting value of the range. After that we just check if the next value (i+1) in the array starts before the current ends (i).