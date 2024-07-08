(ns overlappingevents.time-overlapping
  (:require [clojure.string :as string]
            [overlappingevents.event-validator :as ev])
  (:import (java.time LocalDateTime)
           (java.time.format DateTimeFormatter)))


(def formatter (DateTimeFormatter/ofPattern "yyyy-MM-dd HH:mm:ss"))

(defrecord Event [id start-date end-date])

(defn create_event
  "Returns an Event record"
  [id start-date end-date]
  (let [evt (->Event id
                     (LocalDateTime/parse start-date formatter)
                     (LocalDateTime/parse end-date formatter))]
    (ev/validate-event-fields evt)
    (ev/validate-event-range evt)
    evt))

(defn parse-event
  "Returns an Event record given a string"
  [event-string]
  (let [items (string/split event-string #",")]
    (create_event (Integer. (first items)) (second items) (nth items 2))))

(defn overlap?
  "Returns true if events overlap"
  [event1 event2]
  (and (not (.isAfter (:start-date event1) (:end-date event2)))
       (not (.isAfter (:start-date event2) (:end-date event1)))))

(defn find-overlaps
  "Returns set of overlapping events"
  [events]
  (distinct
   (for [event1 events
         event2 events
         :when (and (not= (:id event1) (:id event2))
                    (overlap? event1 event2))]
     #{(:id event1) (:id event2)})))

