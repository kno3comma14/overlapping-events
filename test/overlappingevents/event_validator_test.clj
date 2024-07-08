(ns overlappingevents.event-validator-test
  (:require [clojure.test :refer [deftest is testing]]
            [overlappingevents.event-validator :refer [validate-event-fields
                                                       validate-event-range]]
            [overlappingevents.time-overlapping :refer [formatter
                                                        ->Event]])
  (:import (java.time LocalDateTime)))

(def dates [(LocalDateTime/parse "2023-01-01 18:36:34" formatter) 
            (LocalDateTime/parse "2023-02-01 18:36:34" formatter)])


(deftest validate-event-fields-test
  (testing "Throws IllegalArgumentException if event is invalid" 
    (is (thrown? IllegalArgumentException
                 (validate-event-fields (->Event nil (first dates) (second dates)))))
    (is (thrown? IllegalArgumentException
                 (validate-event-fields (->Event 1 nil (second dates)))))
    (is (thrown? IllegalArgumentException
                 (validate-event-fields (->Event 1 (first dates) nil))))
    (is (thrown? IllegalArgumentException
                 (validate-event-fields (->Event nil nil nil)))))
  (testing "Returns event if it is valid"
    (is (nil? (validate-event-fields (->Event 1 (first dates) (second dates)))))))

