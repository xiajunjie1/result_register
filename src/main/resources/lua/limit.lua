local key=KEYS[1] --外部传入，通过用户ip生成的唯一值
local limit=ARGV[1]--通过外部传入，限制访问的次数
local current=tonumber(redis.call('GET',key) or "0")

if current+1 > tonumber(limit) then
    return false --已达到次数限制
else
    redis.call('INCRBY',key,"1") --访问次数加1
    redis.call('EXPIRE',key,"3") --设置3s后过期，如果3s内同一客户端未访问过，那么就清除掉访问数据
end

return true

