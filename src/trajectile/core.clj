(ns trajectile.core
  (:require [taoensso.timbre :as timbre]
            [clj-time.core :as time]))

;; Don't load the damn library if you don't want to see stuff.
(timbre/set-level! :trace)

(def time-keeper
  (atom {:time nil
         :kw nil
         :since nil
         :last-kw nil}))

(defn maybe-interval [earlier later]
  " It's Clojure guys. GIGO. "
  (if (and earlier later)
    (time/in-msecs (time/interval earlier later))
    nil))

(defn are-you-fucking-serious []
  (time/in-msecs (time/interval (time/epoch) (time/now))))

(defn update-time [current new-kw]
  " Mixing time and atoms. SO BRAVE. "
  (let [last-time (:time current)
        last-kw (:kw current)
        new-time (time/now)
        since (maybe-interval last-time new-time)]
    (assoc current
      :time new-time :kw new-kw
      :since since :last-kw last-kw)))

(defn trace [new-kw]
  " Trace-away! "
  (let [updated (swap! time-keeper update-time new-kw)
        since (:since updated)
        last-kw (:last-kw updated)]
    (timbre/trace (str "Last trace - " last-kw
                       " Millis since - " since
                       " New trace - " new-kw))
    since))
