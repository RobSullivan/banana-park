(ns banana-park.config
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [banana-park.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[banana-park started successfully using the development profile]=-"))
   :middleware wrap-dev})
