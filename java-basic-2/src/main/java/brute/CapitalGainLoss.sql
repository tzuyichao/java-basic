with sell_cte as (
    select 
        stock_name,
        sum(price) as price
    from stocks
    where operation = 'Sell'
    group by stock_name
),
buy_cte as (
    select 
        stock_name,
        sum(price) as price
    from stocks
    where operation = 'Buy'
    group by stock_name
)
select 
    distinct st.stock_name, 
    (ifnull(s.price, 0) - ifnull(b.price,0)) as capital_gain_loss
from Stocks as st
left join sell_cte as s on st.stock_name = s.stock_name
left join buy_cte as b on st.stock_name = b.stock_name
