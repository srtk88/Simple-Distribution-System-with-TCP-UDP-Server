create database banana default character set=utf8;
create user 'bananadbadm'@'localhost' identified by 'Banana@2018';
grant all on banana.* to 'bananadbadm'@'localhost';
flush privileges;
