(ns banana-park.config
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[banana-park started successfully]=-"))
   :middleware identity})
