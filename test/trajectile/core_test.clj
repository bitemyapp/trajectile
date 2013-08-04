(ns trajectile.core-test
  (:require [clojure.test :refer :all]
            [trajectile.core :refer :all]))

(defn sleep-and-return [time]
  (trace :begin)
  (Thread/sleep 100)
  (trace :end))

(deftest trace-sleep
  (testing "We see accurate numbers for sleeps"
    (is (= true
           (let [time-returned (sleep-and-return 100)]
             (and (> time-returned 99) (< time-returned 115)))))))
