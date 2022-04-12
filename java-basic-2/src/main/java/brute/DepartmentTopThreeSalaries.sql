# 185. Department Top Three Salaries
# https://leetcode.com/problems/department-top-three-salaries/
#
# Runtime: 775 ms, faster than 62.79% of MySQL online submissions for Department Top Three Salaries.
# Memory Usage: 0B, less than 100.00% of MySQL online submissions for Department Top Three Salaries.
# 

# Write your MySQL query statement below
with sal_rank as (select
  d.name as Department,
  e.name as Employee,
  e.salary as Salary,
  dense_rank() over (partition by departmentId order by salary desc) r
from Employee as e
inner join Department as d on e.departmentId = d.id)
select Department, Employee, Salary
from sal_rank
where sal_rank.r <= 3;
