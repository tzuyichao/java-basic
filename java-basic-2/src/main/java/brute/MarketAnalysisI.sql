# 1158. Market Analysis I
# https://leetcode.com/problems/market-analysis-i/
#
# Runtime: 1349 ms, faster than 29.28% of MySQL online submissions for Market Analysis I.
# Memory Usage: 0B, less than 100.00% of MySQL online submissions for Market Analysis I.
with cte as (
    select buyer_id, count(1) as orders_in_2019
    from orders
    where year(order_date) = 2019
    group by buyer_id
)
select
    user_id as buyer_id,
    join_date,
    ifnull(orders_in_2019, 0) as orders_in_2019
from
    Users as u left join cte on u.user_id = cte.buyer_id