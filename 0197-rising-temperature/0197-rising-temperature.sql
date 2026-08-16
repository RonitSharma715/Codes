# Write your MySQL query statement below
SELECT today.id
FROM Weather AS today
JOIN Weather AS yesterday
ON DATE_SUB(today.recordDate, INTERVAL 1 DAY) = yesterday.recordDate
WHERE today.temperature > yesterday.temperature;
