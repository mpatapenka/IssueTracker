create user "issue-tracker-admin" password 'secret';
create database issue_tracker_dev;
grant all privileges on database issue_tracker_dev to "issue-tracker-admin";