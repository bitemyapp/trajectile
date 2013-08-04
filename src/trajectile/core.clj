(ns trajectile.core
  (:require [taoensso.timbre :as timbre]
            [clj-time.core :as time]))

;; Don't load the damn library if you don't want to see stuff.
(timbre/set-level! :trace)

(def time-keeper
  (atom {:time nil
         :kw nil
         :since nil}))

(defn loosey-goosey-interval [earlier later]
  " No fucks given. GIGO. "
  (if (and earlier later)
    (time/in-msecs (time/interval earlier later))
    nil))

(defn update-time [current new-kw]
  " Mixing time and atoms. SO BRAVE. "
  (let [last-time (:time current)
        last-kw (:kw last)
        new-time (time/now)
        since (loosey-goosey-interval last-time new-time)]
    (assoc current :time new-time :kw new-kw :since since)))

(defn trace [new-kw]
  " Trace-away! "
  (let [last-kw (:kw @time-keeper)
        updated (swap! time-keeper update-time new-kw)
        since (:since updated)]
    (timbre/trace (str "Last trace - " last-kw " Time since - " since " New trace - " new-kw))
    since))
