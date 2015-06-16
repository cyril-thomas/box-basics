insert into address(address_id,first_line,second_line,city,state,zip_code) values
(1,'Main Street','600 S','Salt Lake City','UT','84111');

insert into organization(org_id,address_id,name,email,phone,web_domain) values
(1,1,'Brickwall','brickwall@gmail.com','8019209901','www.brickwall.com');


insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(1,'LeBron','James','theKing@gmail.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(2,'Stephen','Curry','curryisthebest@gmail.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(3,'Dwayne','Wade','WadeoMadeo@gmail.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(4,'Kobe','Bryant','kobebeef@gmail.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(5,'Derrick','Favors','favorswaivers@gmail.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(6,'Nick','Young','youngmoney@gmail.com','2313123123',1);

insert into wod(wod_id,name,description,notes) values
(1,'Cindy','Complete as many rounds in 20 minutes as you can of: <br/> 5 Pull-ups <br/> 10 Push-ups <br/> 15 Squats','Best wod for the quads!');
insert into wod(wod_id,name,description,notes) values
(2,'Mary','Complete as many rounds in 20 minutes as you can of: <br/> 5 Handstand Push-ups <br/> 10 One legged squats, alternating <br/> 15 Pull-ups','HSPU at its best!');
insert into wod(wod_id,name,description,notes) values
(3,'Kelly','Five rounds for time of: <br/> Run 400 meters <br/> 30 Box jump, 24 inch box <br/> 30 Wall ball shots, 20 pound ball','Run baby run!');
