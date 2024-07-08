(ns overlappingevents.time-overlapping
  (:import
   (java.time.format DateTimeFormatter)))

(defrecord Event [id start-date end-date])

(def formatter (DateTimeFormatter/ofPattern "yyyy-MM-dd HH:mm:ss"))

(defn overlap? 
  "Returns true if events overlap"
  [event1 event2] 
  (and (not (.isAfter (:start-date event1) (:end-date event2)))
       (not (.isAfter (:start-date event2) (:end-date event1)))))

(defn find-overlaps 
  "Returns set of overlapping events"
  [events]
  (set
   (for [event1 events
         event2 events
         :when (and (not= (:id event1) (:id event2))
                    (overlap? event1 event2))]
     #{(:id event1) (:id event2)})))

