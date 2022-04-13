# 184. Department Highest Salary
# https://leetcode.com/problems/department-highest-salary/
#
# Runtime: 686 ms, faster than 34.83% of MySQL online submissions for Department Highest Salary.
# Memory Usage: 0B, less than 100.00% of MySQL online submissions for Department Highest Salary.
#

with max_sal_dep as (
    select max(salary) as max_sal, departmentId
from employee
group by departmentId)
select d.name as Department, e.name as Employee, e.Salary
from Employee as e
inner join max_sal_dep as m on e.salary = m.max_sal and e.departmentId = m.departmentId
inner join Department as d on d.id = e.departmentId
