(ns overlappingevents.event-validator)

(defn validate-event-fields
  "Throws IllegalArgumentException if event is invalid"
  [event]
  (when (or (nil? (:id event))
            (nil? (:start-date event))
            (nil? (:end-date event)))
    (throw (IllegalArgumentException. "All the fields are required"))))

(defn validate-event-range
  "Throws IllegalArgumentException if event range is invalid"
  [event]
  (when (.isAfter (:start-date event) (:end-date event))
    (throw (IllegalArgumentException. "Invalid event date range"))))