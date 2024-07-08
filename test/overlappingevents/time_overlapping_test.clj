(ns overlappingevents.time-overlapping-test
  (:require [clojure.test :refer [deftest is testing]]
            [overlappingevents.time-overlapping :refer [find-overlaps 
                                                        overlap?  
                                                        create_event]]))


(def events
  [(create_event 1 "2023-01-01 18:36:34" "2023-01-05 18:36:34")
   (create_event 2 "2023-01-03 18:36:34" "2023-01-07 18:36:34")
   (create_event 3 "2023-01-06 18:36:34" "2023-01-10 18:36:34")
   (create_event 4 "2023-01-11 18:36:34" "2023-01-15 18:36:34")])

(def event-use-cases
  {:not-overlapping [(create_event 1 "2023-01-01 18:36:34" "2023-02-01 18:36:34")
                     (create_event 2 "2023-02-03 18:36:34" "2023-03-12 18:36:34")]
   :overlapping [(create_event 1 "2023-01-01 18:36:34" "2023-02-01 18:36:34")
                 (create_event 2 "2022-12-03 18:36:34" "2023-01-02 18:36:34")]})

(deftest overlap?-test
  (testing "Returns false when events do not overlap"
    (let [events (:not-overlapping event-use-cases)
          expected-result false
          result (overlap? (first events) (second events))]
      (is (= expected-result result))))
  (testing "Returns true when events do overlap"
    (let [events (:overlapping event-use-cases)
          expected-result true
          result (overlap? (first events) (second events))]
      (is (= expected-result result)))))

(deftest find-overlaps-test
  (testing "Returns set of overlapping events"
    (let [expected-result #{#{1 2} #{3 2}}
          result (find-overlaps events)]
      (is (= expected-result result)))))