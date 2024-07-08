(ns overlappingevents.core
  (:require [overlappingevents.time-overlapping :refer [find-overlaps 
                                                        formatter 
                                                        ->Event]])
  (:import (java.time LocalDateTime)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))