(ns advent-of-code-2018.core
  (require [clojure.java.io :as io]
           [clojure.string :as str])
  (:gen-class))

(defn problem-2-solution
  "Computes the solution to the frequency duplication problem"
  [input]
  (let [base-frequency-list (map #(Integer. %) (str/split-lines input))
        find-dupe-reducing-fn (fn [acc v]
                                  (if (= acc v)
                                    (reduced (str v))
                                    v))
        first-iteration (reductions + 0 base-frequency-list)
        dupe-in-first-iteration? (reduce find-dupe-reducing-fn first-iteration)]
    (if (string? dupe-in-first-iteration?)
      dupe-in-first-iteration?
      (loop [previous-freqs-set (into #{} first-iteration)
             previous-freqs-sum (reduce + base-frequency-list)]
        (let [current-iteration (reductions + previous-freqs-sum base-frequency-list)
              possible-result (reduce (fn [acc v]
                                          (if (acc v)
                                            (reduced (str v))
                                            (conj acc v))) 
                                      previous-freqs-set 
                                      current-iteration)]
          (if (string? possible-result)
            possible-result
            (recur possible-result (reduce + previous-freqs-sum base-frequency-list))))))))
    

(defn problem-1-solution
  "Computes the solution to the frequency sum problem"
  [input]
  (reduce + (map #(Integer. %) (str/split-lines input))))

(defn -main
  "Computes solutions to AoC problems. Yippee."
  [& args]
  (println "Welcome to Advent of Code 2018! Solved by yours truly, Derek Schatzlein.")
  (let [problem-number (first args)]
    (case problem-number
          "1" (println (problem-1-solution (slurp (io/resource "1.in"))))
          "2" (println (problem-2-solution (slurp (io/resource "1.in"))))
          (println "No input given.")))
  (println "Done!"))
