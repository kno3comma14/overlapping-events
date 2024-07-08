(ns overlappingevents.core
  (:require [overlappingevents.event-io :as ev-io]
            [overlappingevents.time-overlapping :refer [find-overlaps
                                                        parse-event]]))


(defn -main
  "Entry point for the program"
  [& args]
  (let [file-name (first args)
        events (ev-io/read-resource-file-by-line file-name parse-event)
        overlaps (find-overlaps events)]
    (println overlaps)))