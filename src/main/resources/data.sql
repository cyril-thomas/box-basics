insert into address(address_id,first_line,second_line,city,state,zip_code) values
(1,'Main Street','600 S','Salt Lake City','UT','84111');

insert into organization(org_id,address_id,name,email,phone,web_domain) values
(1,1,'Brickwall','brickwall@woddojo.com','8019209901','brickwall');

--Users
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(1,'LeBron','James','theKing@woddojo.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(2,'Stephen','Curry','curryisthebest@woddojo.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(3,'Dwayne','Wade','WadeoMadeo@woddojo.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(4,'Kobe','Bryant','kobebeef@woddojo.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(5,'Derrick','Favors','favorswaivers@woddojo.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(6,'Nick','Young','youngmoney@woddojo.com','2313123123',1);

--Coaches
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(7,'John','Wayne','johnwayne@woddojo.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(8,'Rocky','Balboa','ironfist@woddojo.com','2313123123',1);
insert into member(user_id,first_name,last_name,user_email,user_phone,org_id) values
(9,'Conan','Destroyer','conanthedestroyer@woddojo.com','2313123123',1);

insert into coach(coach_id,user_id,job_title,description) values
(1,7,'Head Coach','Wild wild west certified, bad-ass with gun slinging skills');
insert into coach(coach_id,user_id,job_title,description) values
(2,8,'Asst Coach','Boxing camp has won over 5 world championships, has a lisp that is hard to not notice');
insert into coach(coach_id,user_id,job_title,description) values
(3,9,'Cardio Coach','Rides horses and swings the sword');

-- Wods
insert into wod(wod_id,name,description,notes) values
(1,'Cindy','Complete as many rounds in 20 minutes as you can of: <br/> 5 Pull-ups <br/> 10 Push-ups <br/> 15 Squats','Best wod for the quads!');
insert into wod(wod_id,name,description,notes) values
(2,'Mary','Complete as many rounds in 20 minutes as you can of: <br/> 5 Handstand Push-ups <br/> 10 One legged squats, alternating <br/> 15 Pull-ups','HSPU at its best!');
insert into wod(wod_id,name,description,notes) values
(3,'Kelly','Five rounds for time of: <br/> Run 400 meters <br/> 30 Box jump, 24 inch box <br/> 30 Wall ball shots, 20 pound ball','Run baby run!');

-- Portal
insert into home(home_id,title,intro_title,intro_content,services_title,services_content,registration_title,registration_content,registration_banner,org_id)
values(1,'Welcome to Brickwall Crossfit!'
,'Who are we?','CrossFit may be the answer to your fitness shortcomings. At CrossFit Brickwall we’re focused on teaching the theories and methodologies that are producing some of the fittest people in the world. Come learn how you can achieve a new level of fitness through an organic form of excercise.'
,'How we do it!','Brickwall CrossFit welcomes all fitness levels. The same workouts elite athletes are doing are the same workouts the everyday person should do. Trainers will work with you to scale workouts to your abilities and comfort levels, without sacrificing safety and proper form. The only difference would be the amount of weight, number of reps, and substitutions to promote the progression to the recommended workout. The workouts are meant to be intense for the individual but without sacrificing safety and proper form.'
,'Sign Up!', 'Call or email us to come in for a free first-time workout. If you choose to continue you’ll need to schedule your On-Ramp training which consists of a series of classes or personal sessions that provide an understanding of the basic movements we perform in CrossFit. These basic movements are the fundamentals to all the movements in the workouts and it is important that you are familiar with the movements and able to perform them correctly and safely.'
,'What are you waiting for lets do this!',1);

insert into about(about_id,about_title,about_content,org_id)
values(1,'About Brickwall!'
,'Brickwall offers individuals the opportunity to reach their fitness goals using the theories and methodologies of CrossFit. These workouts are used for increasing fitness levels of all types: children/elderly, male/female, weekend warriors/professional athletes. CrossFit is a wide variety of constantly varied, functional movements, performed at high intensity. The workouts use exercises from different areas, such as cardio (i.e. running or rowing), gymnastic or bodyweight exercises (push-ups, pull ups, bodyweight squats), and weight training (i.e. squats or shoulder press). Any and all combinations of exercises are used together to add continuous variety, which in turn raises the overall level of fitness for an individual. FOR EVERYONE - CrossFit Brickwall welcomes all fitness levels. The same workouts elite athletes are doing are the same workouts the everyday person should do. Trainers will work with you to scale workouts to your abilities and comfort levels, without sacrificing safety and proper form. The only difference would be the amount of weight, number of reps, and substitutions to promote the progression to the recommended workout. The workouts are meant to be intense for the individual but without sacrificing safety and proper form.'
,1);

insert into announcement(announcement_id,title,content,org_id,end_date)
values(1,'Namaste!'
,'Brickwall is now offering yoga classes'
,1, '9999-12-31');
insert into announcement(announcement_id,title,content,org_id,end_date)
values(2,'Oly Lifts'
,'Oly lift classes are moved to Wednesday @ 7 pm instead of the Tuesday’s. Please contact Coach Rocky for more info.'
,1, '9999-12-31');