(ns overlappingevents.time-overlapping-test
  (:require [clojure.test :refer [deftest is testing]]
            [overlappingevents.time-overlapping :refer [find-overlaps 
                                                        overlap? 
                                                        formatter 
                                                        ->Event]])
  (:import (java.time LocalDateTime)))

(def events
  [(->Event 1 (LocalDateTime/parse "2023-01-01 18:36:34" formatter) (LocalDateTime/parse "2023-01-05 18:36:34" formatter))
   (->Event 2 (LocalDateTime/parse "2023-01-03 18:36:34" formatter) (LocalDateTime/parse "2023-01-07 18:36:34" formatter))
   (->Event 3 (LocalDateTime/parse "2023-01-06 18:36:34" formatter) (LocalDateTime/parse "2023-01-10 18:36:34" formatter))
   (->Event 4 (LocalDateTime/parse "2023-01-11 18:36:34" formatter) (LocalDateTime/parse "2023-01-15 18:36:34" formatter))])

(def event-use-cases
  {:not-overlapping [(->Event 1 (LocalDateTime/parse "2023-01-01 18:36:34" formatter) (LocalDateTime/parse "2023-02-01 18:36:34" formatter))
                     (->Event 2 (LocalDateTime/parse "2023-02-03 18:36:34" formatter) (LocalDateTime/parse "2023-03-12 18:36:34" formatter))]
   :overlapping [(->Event 1 (LocalDateTime/parse "2023-01-01 18:36:34" formatter) (LocalDateTime/parse "2023-02-01 18:36:34" formatter))
                 (->Event 2 (LocalDateTime/parse "2022-12-03 18:36:34" formatter) (LocalDateTime/parse "2023-01-02 18:36:34" formatter))]})



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