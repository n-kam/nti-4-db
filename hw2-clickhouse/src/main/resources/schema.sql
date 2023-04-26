create database if not exists hw2;

drop table if exists hw2.measurements;
create table if not exists hw2.measurements
(
    source    String,
    timestamp DateTime64(3, 'Europe/Moscow'),
    value     double
)
    engine = AggregatingMergeTree
        order by (source, timestamp);