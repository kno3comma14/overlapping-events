(ns overlappingevents.event-io 
  (:require [clojure.java.io :as io]))


(defn read-resource-file-by-line
  "Reads a file from the resources folder line by line and applies a function to each line."
  [file-name line-fn]
  (with-open [rdr (-> file-name io/resource io/reader)]
    (map line-fn (vec (line-seq rdr)))))