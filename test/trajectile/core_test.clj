(ns trajectile.core-test
  (:require [clojure.test :refer :all]
            [trajectile.core :refer :all]))

(defn sleep-and-return [time]
  (trace :begin)
  (Thread/sleep time)
  (trace :end))

(defn sleep-twice-and-return [time]
  (trace :begin)
  (Thread/sleep time)
  (Thread/sleep time)
  (trace :end))


(deftest trace-sleep
  (testing "We see accurate numbers for sleeps"
    (is (= true
           (let [time-returned (sleep-and-return 100)]
             (and (> time-returned 99) (< time-returned 115)))))
    (is
        (= true
           (let [time-returned (sleep-twice-and-return 100)]
             (and (> time-returned 199) (< time-returned 230)))))))
