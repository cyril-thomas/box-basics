insert into address(address_id,first_line,second_line,city,state,zip_code) values
(-1,'Main Street','600 S','Salt Lake City','UT','84111');

insert into organization(org_id,address_id,name,email,phone,web_domain,hash_tag) values
(-1,-1,'Brickwall','cyril.satyam@gmail.com','801-920-9901','brickwall','brickwallcrossfit');

--Users
insert into "user"(user_id,first_name,last_name,user_email,user_phone,org_id) values
(-1,'LeBron','James','theKing@woddojo.com','2313123123',-1);
insert into "user"(user_id,first_name,last_name,user_email,user_phone,org_id) values
(-2,'Stephen','Curry','curryisthebest@woddojo.com','2313123123',-1);
insert into "user"(user_id,first_name,last_name,user_email,user_phone,org_id) values
(-3,'Dwayne','Wade','WadeoMadeo@woddojo.com','2313123123',-1);
insert into "user"(user_id,first_name,last_name,user_email,user_phone,org_id) values
(-4,'Kobe','Bryant','kobebeef@woddojo.com','2313123123',-1);
insert into "user"(user_id,first_name,last_name,user_email,user_phone,org_id) values
(-5,'Derrick','Favors','favorswaivers@woddojo.com','2313123123',-1);
insert into "user"(user_id,first_name,last_name,user_email,user_phone,org_id) values
(-6,'Nick','Young','youngmoney@woddojo.com','2313123123',-1);

--Coaches
insert into "user"(user_id,first_name,last_name,user_email,user_phone,org_id,password,role) values
(-7,'John','Wayne','johnwayne@woddojo.com','2313123123',-1,'$2a$10$GhF3KXpSQMvUenBHemWkXui7mqXWu9/YDrgeriz0KrmGugABOQswu','COACH');
insert into "user"(user_id,first_name,last_name,user_email,user_phone,org_id) values
(-8,'Rocky','Balboa','ironfist@woddojo.com','2313123123',-1);
insert into "user"(user_id,first_name,last_name,user_email,user_phone,org_id) values
(-9,'Conan','Destroyer','conanthedestroyer@woddojo.com','2313123123',-1);

insert into coach(coach_id,user_id,job_title,description,profile_pic) values
(-1,-7,'Head Coach','Wild wild west certified, bad-ass with gun slinging skills','/org/1/coach/1/original1.jpg');
insert into coach(coach_id,user_id,job_title,description,profile_pic) values
(-2,-8,'Asst Coach','Boxing camp has won over 5 world championships, has a lisp that is hard to not notice','/org/1/coach/2/original1.jpg');
insert into coach(coach_id,user_id,job_title,description,profile_pic) values
(-3,-9,'Cardio Coach','Rides horses and swings the sword','/org/1/coach/3/original1.jpg');

-- Wods
insert into wod(wod_id,name,description,notes) values
(-1,'Cindy','Complete as many rounds in 20 minutes as you can of: <br/> 5 Pull-ups <br/> 10 Push-ups <br/> 15 Squats','Best wod for the quads!');
insert into wod(wod_id,name,description,notes) values
(-2,'Mary','Complete as many rounds in 20 minutes as you can of: <br/> 5 Handstand Push-ups <br/> 10 One legged squats, alternating <br/> 15 Pull-ups','HSPU at its best!');
insert into wod(wod_id,name,description,notes) values
(-3,'Kelly','Five rounds for time of: <br/> Run 400 meters <br/> 30 Box jump, 24 inch box <br/> 30 Wall ball shots, 20 pound ball','Run baby run!');

-- Classes
insert into classes(class_id,org_id,coach_id,name,description) values
(-1,-1,-1,'6 am - 7 am', 'Early Morning class');
insert into classes(class_id,org_id,coach_id,name,description) values
(-2,-1,-1,'7 am - 8 am', 'Morning class');
insert into classes(class_id,org_id,coach_id,name,description) values
(-3,-1,-2,'9 am - 10 am', 'Ladies only class');
insert into classes(class_id,org_id,coach_id,name,description) values
(-4,-1,-2,'Noon - 1 pm', 'Nooner');
insert into classes(class_id,org_id,coach_id,name,description) values
(-5,-1,-3,'4 am - 5 am', 'Evening class');
insert into classes(class_id,org_id,coach_id,name,description) values
(-6,-1,-3,'5 am - 6 am', 'Oly lifts');

--Schedule
insert into schedule(schedule_id,wod_id,org_id,wod_date) values
(-1,-1,-1,current_date);

-- Portal
insert into home(home_id,title,intro_content,services_title,registration_title,registration_content,registration_banner,video_url, custom_css, org_id)
values(-1,'Welcome to Brickwall Crossfit!'
,'CrossFit may be the answer to your fitness shortcomings. At CrossFit Brickwall we are focused on teaching the theories and methodologies that are producing some of the fittest people in the world. Come learn how you can achieve a new level of fitness through an organic form of excercise.'
,'Services we provide'
,'Sign Up!', 'Call or email us to come in for a free first-time workout. If you choose to continue you will need to schedule your On-Ramp training which consists of a series of classes or personal sessions that provide an understanding of the basic movements we perform in CrossFit. These basic movements are the fundamentals to all the movements in the workouts and it is important that you are familiar with the movements and able to perform them correctly and safely.'
,'What are you waiting for? Lets do this!'
,'https://youtu.be/ykccBGhfkxM'
,'cyrilclass{ color : red; }'
,-1);

insert into about(about_id,about_title,about_content,facebook_url, twitter_url, google_plus_url, org_id)
values(-1,'About Brickwall!'
,'Brickwall offers individuals the opportunity to reach their fitness goals using the theories and methodologies of CrossFit. These workouts are used for increasing fitness levels of all types: children/elderly, male/female, weekend warriors/professional athletes. CrossFit is a wide variety of constantly varied, functional movements, performed at high intensity. The workouts use exercises from different areas, such as cardio (i.e. running or rowing), gymnastic or bodyweight exercises (push-ups, pull ups, bodyweight squats), and weight training (i.e. squats or shoulder press). Any and all combinations of exercises are used together to add continuous variety, which in turn raises the overall level of fitness for an individual. FOR EVERYONE - CrossFit Brickwall welcomes all fitness levels. The same workouts elite athletes are doing are the same workouts the everyday person should do. Trainers will work with you to scale workouts to your abilities and comfort levels, without sacrificing safety and proper form. The only difference would be the amount of weight, number of reps, and substitutions to promote the progression to the recommended workout. The workouts are meant to be intense for the individual but without sacrificing safety and proper form.'
,'https://www.facebook.com/BrickwallCrossFit'
,'https://twitter.com/brickwallcf'
,''
,-1);

insert into announcement(announcement_id,title,content,org_id,end_date)
values(-1,'Namaste!'
,'Brickwall is now offering yoga classes'
,-1, '9999-12-31');
insert into announcement(announcement_id,title,content,org_id,end_date)
values(-2,'Oly Lifts'
,'Oly lift classes are moved to Wednesday @ 7 pm instead of the Tuesdays. Please contact Coach Rocky for more info.'
,-1, '9999-12-31');



insert into service(service_id,title,content,org_id)
values(-1,'Foundations','This is designed for individuals new to CrossFit and exercise in general. We slowly integrate and explain the nine basic movements that CrossFit utilizes for its programming. We provide a high level of individual instruction, education, and physical adaptation. The environment this class offers allows for each individual to become comfortable with each movement as well as their own ability to perform each movement. This class also allows for the CrossFit Coaches to understand the physical and mental limitations of each member so that we are able to modify and adapt the individual client when joining a regular group setting. Each foundation class will last 60 minutes and will be offered two times a day, three days a week.'
,-1);
insert into service(service_id,title,content,org_id)
values(-2,'CrossFit','This class is designed for the average client of Brickwall Crossfit, LLC. The primary base of its programming is for General Physical Preparedness. Each class is designed for a limit of 20 individual members that have completed the proper foundations protocol. This class will be divided into four specific sections: Warm Up, Strength, Workout of the Day (WOD), and Mobility.'
,-1);
insert into service(service_id,title,content,org_id)
values(-3,'CrossFit Sport','This class is designed specifically for those wishing to compete in the Crossfit games or other regional competitions. Only after certain specific requirements are met will an individual or team be allowed access to this high intensity class. This class meets six times a week and is for the advanced Crossfitter.'
,-1);


insert into blog(blog_id,title,content,org_id,coach_id,post_date)
values(-1,'CrossFit Sport','This class is designed specifically for those wishing to compete in the Crossfit games or other regional competitions. Only after certain specific requirements are met will an individual or team be allowed access to this high intensity class. This class meets six times a week and is for the advanced Crossfitter.'
,-1, -1,current_date);
insert into blog(blog_id,title,content,org_id,coach_id,post_date)
values(-2,'CrossFit','This class is designed for the average client of Brickwall Crossfit, LLC. The primary base of its programming is for General Physical Preparedness. Each class is designed for a limit of 20 individual members that have completed the proper foundations protocol. This class will be divided into four specific sections: Warm Up, Strength, Workout of the Day (WOD), and Mobility.'
,-1, -1,current_date);

insert into comment(comment_id,content,blog_id,user_id,post_date)
values(-1,'Johns Advice is great',-1,-3,current_date);
insert into comment(comment_id,content,blog_id,user_id,post_date)
values(-2,'Johns Advice is great',-1,-4,current_date);
insert into comment(comment_id,content,blog_id,user_id,post_date)
values(-3,'Johns Advice is great',-1,-6,current_date);
insert into comment(comment_id,content,blog_id,user_id,post_date)
values(-4,'Johns Advice is great',-1,-7,current_date);



insert into custom_link(custom_link_id,link_name, link_url,org_id)
values(-1,'Our Blog','/blogs',-1);
insert into custom_link(custom_link_id,link_name, link_url,org_id)
values(-2,'Johns Advice','/post?id=-1',-1);