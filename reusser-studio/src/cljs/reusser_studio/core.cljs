(ns reusser-studio.core
  (:require
   [reagent.core :as reagent]
   [reusser-studio.navigation :refer [go-home go-guest-list go-registry go-request-song go-schedule]]))

(defn empty-page []
  [:div.row.text-center "THERE'S A PROBLEM!"])

(defonce app-state
  (reagent/atom {:to-display #(empty-page)}))

(defn header-banner []
  [:div.header.row.justify-content-sm-center.text-center
   [:div.col-sm-12 "McFarland-Reusser Wedding"]])

(defn navigate-button [text callback]
  [:button.btn-primary.btn-lg.btn-block {:on-click callback} text])


(defn header-buttons [application-state]
  (let [buttons [["Home" (go-home application-state)]
                 ["Sign the Guest List" (go-guest-list application-state)]
                 ["Registry"  (go-registry application-state)]
                 ["Request a Song" (go-request-song application-state)]
                 ["Schedule of Events" (go-schedule application-state)]]]
    (map (fn [[text callback]] [:div.btn.col-sm-4
                                {:key text}
                                (navigate-button text callback)]) buttons)))

(defn page [ratom]
  (let [to-execute (:to-display @app-state)
        actual-content (to-execute)]
    [:div.container-fluid
     [:div.row (header-banner)]
     [:div.row (header-buttons app-state)]
     [:div.row actual-content]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn prod-setup [application-state]
  ((go-home application-state)))

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")))

(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload)
  (prod-setup app-state))
