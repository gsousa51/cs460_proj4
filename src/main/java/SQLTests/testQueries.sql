SELECT COUNT(*) AS occupied
  FROM aswindle.appointment
 WHERE room = ?
   AND admission <= ?
   AND exp_discharge >= ?
    OR IS NULL exp_discharge;