create database if not exists hw2;

create table if not exists hw2.measurements
(
source String,
timestamp DateTime64(3),
value double
)
engine=AggregatingMergeTree
order by (source, timestamp);