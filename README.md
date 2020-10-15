In this branch I add another 2 solutions. Although the performance of these 2 solutions are not much better than the original solution in every test case. the thinking behind these 2 solutions may be able to lead to improved solutions.

# Solution 1 (original solution)
There are two requirements in this exercise. The first is each attendee can only join one meeting.
This is simple. Just disjoint each meeting attendee with all assigned meeting attendees.

The second requirement is maximizing the number of attendees for all meetings.
The basic idea is, starting from the biggest meeting, since it can contribute most attendees, and keep adding other meetings with fewer and disjoint attendees.
However, the more attendees in one meeting, the more possibility that this meeting will block other smaller meetings.
Therefore, besides starting from the biggest meeting, I try to remove each "big" meeting and find the best meeting from remains with the same sorted order.
The definition of "big" is the number of attendees in one meeting greater than the average number of attendees in every meeting.

# Solution 2
Based on the idea of solution 1, besides the size of attendees in each meeting, we also need to consider the size of *lost* attendees when a meeting is selected. For example, for this input `[[0,1,2],[2,3],[1,4]]`, when `[0,1,2]` is selected, both `[2,3]` and `[1,4]` will be blocked. Therefore the *lost* attendees are `3` and `4`. We can find lost attendees of each meeting and sort meetings by `number of attendees - number of lost attendees` in descending order. In this way if a big meeting which may block a lot of other meetings, this meeting will be considered in lower priority.

# Solution 3
This solution starts from attendee instead of meeting. For each attendee it can be part of one or more meetings. The more meeting this attendee needs to join, the more possibility that this attendee will be a bottleneck. First of all we build a histogram of attendees with meetings each attendee needs to join. Then we start from the attendee who has most meetings, sort the meetings by size in descending order, and add meetings to result with disjoint checking. 

# Result

|  input | Solution 1 | Solution 2 | Solution 3 |
|  - | - | - | - |
| input 1 | 2  | 2 | 2 |
| input 2 | 3  | 3 | 3 |
| input 3 | **13**  | **13** | 10 |
| input 4 | **14**  | 12 | 11 |
| input 5 | **20**  | 15 | 17 |
| input 6 | **61**  | **61** | 60 |
| input 7 | 63  | **65** | 61 |

Overall solution 1 performs better than the other two solutions. However Solution 2 performs best for input 7, which is the most complex input. I think solution 2 potentially can be a start point to develop an iterative optimization algorithm.
