(ns advent-of-code-2018.core
  (require [clojure.java.io :as io]
           [clojure.string :as str])
  (:gen-class))


(defn print-problem-1-solution
  "Computes the solution to the frequency sum problem"
  [input]
  (println (reduce + (map #(Integer. %) (str/split-lines input)))))

(defn -main
  "Computes solutions to AoC problems. Yippee."
  [& args]
  (println "Welcome to Advent of Code 2018! Solved by yours truly, Derek Schatzlein.")
  (let [problem-number (first args)]
    (case problem-number
          "1" (print-problem-1-solution (slurp (io/resource "1.in")))
          (println "No input given.")))
  (println "Done!"))
