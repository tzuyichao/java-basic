# 180. Consecutive Numbers
# https://leetcode.com/problems/consecutive-numbers/
#
# Runtime: 462 ms, faster than 67.04% of MySQL online submissions for Consecutive Numbers.
# Memory Usage: 0B, less than 100.00% of MySQL online submissions for Consecutive Numbers.
#

# Write your MySQL query statement below
select distinct(a.num) as ConsecutiveNums
from logs as a
inner join logs as b on a.id+1 = b.id
inner join logs as c on b.id+1 = c.id
where a.num = b.num and b.num = c.num
