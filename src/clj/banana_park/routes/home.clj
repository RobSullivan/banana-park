(ns banana-park.routes.home
  (:require [banana-park.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as ok]
            [clojure.java.io :as io]
            [banana-park.db.core :as db]
            [bouncer.core :as b]
            [bouncer.validators :as v]
            [ring.util.http-response :refer [found]]))

(defn home-page [{:keys [flash]}]
  (layout/render
    "home.html"
    (merge {:questions (db/get-questions!)}
           (select-keys flash  [:question :answer :errors]))))

(defn about-page []
  (layout/render "about.html"))


(defn validate-question [params]
  (first
    (b/validate
      params
      :question v/required
      :answer v/required )))

(defn save-question! [{:keys [params]}]
  (if-let [errors (validate-question params)]
    (-> (ok/found "/")
        (assoc :flash (assoc params :errors errors)))
    (do
      (db/create-question!
        (assoc params :timestamp (java.util.Date.)))
      (ok/found "/"))))

(defroutes home-routes
  (GET "/" request (home-page request))
  (POST "/" request (save-question! request))
  (GET "/about" [] (about-page)))
