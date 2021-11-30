-- median. works for both ODD and EVEN number of rows
select round((o1.lat_n+o2.lat_n)/2, 4) as median
from
(select
    t2.LAT_N as lat_n
from
(select count(LAT_N) as num from STATION) t1,
(select LAT_N, rank() over (order by LAT_N) as rnk from STATION) t2
where t2.rnk=round(t1.num/2)) o1,

(select
t2.LAT_N as lat_n
from
(select count(LAT_N) as num from STATION) t1,
(select LAT_N, rank() over (order by LAT_N) as rnk from STATION) t2
where t2.rnk=round((1+t1.num)/2)) o2;

---
SELECT Start_Date, min(End_Date)
FROM
    (SELECT Start_Date FROM Projects WHERE Start_Date NOT IN (SELECT End_Date FROM Projects)) a,
    (SELECT End_Date FROM Projects WHERE End_Date NOT IN (SELECT Start_Date FROM Projects)) b
WHERE Start_Date < End_Date
GROUP BY Start_Date
ORDER BY DATEDIFF(min(End_Date), Start_Date), Start_Date

--
select
s.Name
from Students s, Friends f, Packages p1, Packages p2
where s.ID=f.ID
and s.ID=p1.ID
and f.Friend_ID=p2.ID
and p2.Salary>p1.Salary
order by p2.Salary
----
SELECT f1.X, f1.Y FROM Functions f1
INNER JOIN Functions f2 ON f1.X=f2.Y AND f1.Y=f2.X
GROUP BY f1.X, f1.Y
HAVING COUNT(f1.X)>1 or f1.X<f1.Y
ORDER BY f1.X
----
select
c.contest_id, c.hacker_id, c.name,
sum(s.total_submissions), sum(s.total_accepted_submissions),
sum(v.total_views), sum(v.total_unique_views)
from
Contests c
join Colleges cl on c.contest_id = cl.contest_id
join Challenges ch on cl.college_id = ch.college_id
left join (
    select
    challenge_id,
    sum(total_submissions) as total_submissions,
    sum(total_accepted_submissions) as total_accepted_submissions
    from Submission_Stats
    group by challenge_id) s on ch.challenge_id=s.challenge_id
left join (
    select
    challenge_id,
    sum(total_views) as total_views,
    sum(total_unique_views) as total_unique_views
    from View_Stats
    group by challenge_id) v on ch.challenge_id =v.challenge_id
group by c.contest_id, c.hacker_id, c.name
having sum(s.total_submissions)>0
and sum(s.total_accepted_submissions)>0
and sum(v.total_views)>0
and sum(total_unique_views)>0
order by c.contest_id
----
select
c.contest_id, c.hacker_id, c.name,
sum(s.total_submissions), sum(s.total_accepted_submissions),
sum(v.total_views), sum(v.total_unique_views)
from
Contests c
join Colleges cl on c.contest_id = cl.contest_id
join Challenges ch on cl.college_id = ch.college_id
left join (
    select
    challenge_id,
    sum(total_submissions) as total_submissions,
    sum(total_accepted_submissions) as total_accepted_submissions
    from Submission_Stats
    group by challenge_id) s on ch.challenge_id=s.challenge_id
left join (
    select
    challenge_id,
    sum(total_views) as total_views,
    sum(total_unique_views) as total_unique_views
    from View_Stats
    group by challenge_id) v on ch.challenge_id =v.challenge_id
group by c.contest_id, c.hacker_id, c.name
having sum(s.total_submissions)>0
and sum(s.total_accepted_submissions)>0
and sum(v.total_views)>0
and sum(total_unique_views)>0
order by c.contest_id

----
DELIMITER $$

DROP PROCEDURE IF EXISTS P $$

CREATE PROCEDURE P(IN n INT)
BEGIN
    DECLARE i INT DEFAULT 0;
    SET i=n;

    WHILE i>0 DO
        SELECT REPEAT('* ', i);
        set i = i -1;
    END WHILE;
END $$

DELIMITER ;

CALL P(20);
---
DELIMITER $$

DROP PROCEDURE IF EXISTS P $$

CREATE PROCEDURE P(IN n INT)
BEGIN
  DECLARE i INT DEFAULT 1;

  WHILE i<=n DO
    SELECT REPEAT('* ', i);
    SET i=i+1;
  END WHILE;
END $$

DELIMITER ;

CALL P(20);
-----
select
h.hacker_id as hacker_id,
h.name as name,
count(c.challenge_id) as total
from Hackers h, Challenges c
where h.hacker_id=c.hacker_id
group by h.hacker_id, h.name
having total = (
    select count_ch from (
        select
            count(challenge_id) as count_ch
        from Challenges
        group by hacker_id
        order by count_ch desc
        limit 1
    ) t
)
or total in
(
    select count_ch
    from
    (
        select
            t1.count_ch as count_ch,
            count(t1.count_ch) as total_count
        from
        (
            select
            count(challenge_id) as count_ch
            from Challenges
            group by hacker_id
        ) t1
        group by t1.count_ch
        having total_count=1
    ) tt1
)
order by total desc, hacker_id

----
select
    tt1.submission_date, tt1.total_uniq_ids , h0.hacker_id, h0.name
from
(
    select
        submission_date, count(distinct hacker_id) as total_uniq_ids
    from (
            select
            dates.submission_date, hacker_id, count(hacker_id) as num
            from
             (select distinct submission_date from Submissions) dates
            left join
             (select h.hacker_id, submission_date
              from Hackers h join Submissions s on h.hacker_id=s.hacker_id) t
            on dates.submission_date=t.submission_date
            group by dates.submission_date, t.hacker_id
            having num>0
        ) t1
    group by submission_date
) tt1
join
(
    select
    t0.submission_date,
    min(hacker_id) as hacker_id
    from
    (
            select
            dates.submission_date, hacker_id, count(hacker_id) as num
            from
             (select distinct submission_date from Submissions) dates
            left join
             (select h.hacker_id, submission_date
              from Hackers h join Submissions s on h.hacker_id=s.hacker_id) t
            on dates.submission_date=t.submission_date
            group by dates.submission_date, t.hacker_id
            having num>0
        ) t0

    join
    (
        select
            t2.submission_date, max(num) as max_num
        from (
                select
                    dates.submission_date, hacker_id, count(hacker_id) as num
                    from
                     (select distinct submission_date from Submissions) dates
                    left join
                     (select h.hacker_id, submission_date
                      from Hackers h join Submissions s on h.hacker_id=s.hacker_id) t
                    on dates.submission_date=t.submission_date
                    group by dates.submission_date, t.hacker_id
                    having num>0
                ) t2
            group by t2.submission_date
        ) tt2 on t0.submission_date=tt2.submission_date and t0.num=tt2.max_num
        group by t0.submission_date
    ) ttt2
on tt1.submission_date=ttt2.submission_date
join Hackers h0
on ttt2.hacker_id=h0.hacker_id
----
DELIMITER $$

DROP PROCEDURE IF EXISTS P $$

CREATE PROCEDURE P(IN n INT)
BEGIN
    DECLARE prime VARCHAR(5000) DEFAULT '';
    DECLARE i INT DEFAULT 8;
    DECLARE j INT DEFAULT 0;
    DECLARE r INT DEFAULT 0;
    DECLARE flag INT DEFAULT 0;

    SET prime = '2&3&5&7';

    outer_while: WHILE i<n DO
        SET r = SQRT(i);
        SET j = 2;
        SET flag = 0;

        inner_while: WHILE j<=r DO
            IF MOD(i, j) = 0 THEN
                SET flag =1;
                LEAVE inner_while;
            END IF;

            SET j=j+1;
        END WHILE;

        IF flag=0 THEN
            SET prime = CONCAT(prime, '&', i);
        END IF;

        SET i=i+1;
    END WHILE;

    SELECT prime;

END $$

DELIMITER ;
CALL P(1000);