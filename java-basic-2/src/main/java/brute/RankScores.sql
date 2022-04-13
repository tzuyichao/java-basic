# 178. Rank Scores
# https://leetcode.com/problems/rank-scores/
#
# Runtime: 276 ms, faster than 63.39% of MySQL online submissions for Rank Scores.
# Memory Usage: 0B, less than 100.00% of MySQL online submissions for Rank Scores.

# Write your MySQL query statement below
select
  score,
  DENSE_RANK() OVER (order by score desc) `rank`
from Scores