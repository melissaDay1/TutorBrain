Steps to deploy plug-in

1.	Set up server
Server code: https://github.com/mpenumal/ServerForBrain/tree/master/server

•	Create AWS instance on Ubuntu
•	Update server code to point to the info for your AWS instance:
o	Go to: src/main/resources/application.properties
o	Update URL:
	“spring.datasource.url” -> Change highlighted part to the IP address for your AWS instance
o	Update username:
	Change “spring.datasource.username” to the string for the username for your AWS instance
o	Update password:
	Change “spring.datasource.password” to the password for your AWS instance
•	Install MySQL on Ubuntu/AWS Server
•	Install Git
•	Clone/pull server code there
•	Run server code on remote server:
o	mvn clean install
o	nohup java -jar java -jar target/server-0.0.1-SNAPSHOT.jar &
o	-> This will leave it running even when you close the terminal
•	(Eventually, when you need to stop the running API on the server)
o	ps -ef
o	kill <PID>
•	Note: Whenever you stop the server and want to restart it again, you will need to manually drop tables in the database on the server, as these are created when the server starts. You will need to drop the tables called Input & Assignments (same names as in server.model)
o	E.g., drop table tutordata.Input

2.	Server calls – test in Postman first
•	Test in Postman
•	Always test a get first
o	http://18.224.214.12:8080/server/assignments
•	Upload an assignment
o	Put: http://18.224.214.12:8080/server/assignments/1/30
o	The numbers at the end are the dates of the month during which the assignment is visible
o	Click “Select File” in the body of payload
o	Upload the assignment file. It must be named “Assignment.java”
 
•	Other commands:
o	Get: Get assignment: http://18.224.214.12:8080/server/assignments
o	Del: Delete assignment: http://18.224.214.12:8080/server/assignments/Assignment.java
o	Post: Save Input: http://18.224.214.12:8080/server/inputs
	This allows you to save something to the database via Postman in the Body tab
 

•	Put: Save MessageAndFeedback: localhost:8080/server/inputs/8
o	This allows you to save the feedback code from the student, the message from the brain, and the message code from the brain
 

•	Get: Get all inputs: http://18.224.214.12:8080/server/inputs
o	This does a dump of all data in the DB. This is used to get the data after the student uses the plug-in
•	Get: Get input by StudentId: localhost:8080/server/inputs/0
o	Gives you data for the student with the ID as the last option of the URL

3.	Generate plug-in
Plug-in Code: https://github.com/mpenumal/BrainPlugin

•	Clone the project locally
•	Open the project in Eclipse 2018-9
•	Select File  Export  Plugin Development  Deployable plugins and fragments  Select all available plug-ins and fragments (Destination = Archive File)  Finish
•	Extract the zip file to find the plug-in

4.	If you want to make updates to the Brain code:
Brain code: https://github.com/melissaDay1/TutorBrain
•	Update the code in the brain
•	Generate a .jar file from the brain code
•	Copy/paste the .jar file from the brain into the plug-in code
o	Put it in: src\ExternalJARs
o	Make sure it is named “brain”
•	Repeat step 3

5.	If you want to change the trained neural network model used for the brain:
•	Replace the model stored on the server (can be any server, not the same one used for AWS instance above)
•	Change the path in the Brain to point to the correct location
o	This is in src\main\java\thesis_neuroph\thesis_neuroph\StudentMessageCalculator.java
•	Repeat Steps 3 & 4
 

6.	Test installation of plug-in on a non-development machine
•	Take the .jar generated from step 3 and follow the directions for student installation

7.	After this, check to make sure that data is in the DB by following step 2.
