"# cs46-Proj4" 
Port-Number: For the moment is 10014.

Operation: To run the project, first tunnel into port 10014.
           Cd into the project directory and run mvn spring-boot:run
           On browser run localhost:10014
           NOTE: We have strict primary key/foreign key references. If you attempt to
           add or update a table with a foreign key that doesn't currently exist in the parent table,
           the project will throw an error. To find a range of valid primary keys that currently exist in the db,
           reference the list below
           The range of numbers is the range of valid pk for that table, i.e. for Patient, we're referencing PID:

           Patient: 	1001 - 1042
           Doctor: 	4001 - 4009
           Pharmacist: 6001 - 6004
           Nurse: 		7001 - 7010
           Staff: 		8001 - 8004 8001, 8002 cashier; 8003, 8004 receptionist
           Xact_ID:	5001 - 5019
           Hospital: 	 173 - 300
           Department:	   1 - 9
           Appt:		9001 - 9019

Work Distribution:
    Gary built the front/back-end for adding, updating, and removing from all of the tables required.
    I.e. Patient, Doctor, etc.
    Alex created data and seeded it into our db. He also wrote up the front/backend for queries 1 - 4.

    Overall, there was equal distribution of work amongst the two members.

NOTE: Rather than commenting every single setDate method and setLong in the Model package, I'll add it here.

Numeric Values in Forms:

To handle numeric input from the user, every Bean has its field set as a long to handle very long input.
Furthermore, there are some values that can be left empty, such as room number in an UPDATE query.
In this case, the form method we're using in HTML sets this value to "" which causes a NumberFormatException.

To combat this, if a field can ever be left as blank, we set its th:field to be a String value. This way, if it is left
empty, we can detect the value being "" and set a flag variable to true. We have a flag-var for each
numeric value that can be left empty. We use it later on when we're doing validation/building the queries the form
is gathering information for.

The String values we're using this method on are prefixed with "s_", where the suffix is the name of the
long field corresponding to it.

Date Values:

To handle dates, I have similar method to above. We send in the String representation of the Date from the form
and convert it to a Date from there. When STORING the date into our db, we convert it to its EPOCH value.
This removes a lot of headache from storing dates in our DB without any performance issues.


