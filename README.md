"# cs46-Proj4" 
Port-Number: For the moment is 10014.


Query 4: For an appointment list of a given EID, list all of the medications/who prescribed them for all patients
found in this appointment list.

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


