(ns reusser-studio.core
  (:require
   [reagent.core :as reagent]
   [reusser-studio.navigation :refer [go-home go-registry go-schedule go-wedding-party]]
   [cljs-time.core :refer [in-days interval date-time now]]))

(defn empty-page []
  [:div.row.text-center "THERE'S A PROBLEM!"])

(defonce app-state
  (reagent/atom {:to-display #(empty-page)}))

(defn days-until []
  (in-days (interval (now) (date-time 2020 10 10))))

(defn header-banner []
  [:div.header.text-center.h2.justify-content-center.flex-fill
   [:div.col-sm-12.text-gold "McFarland-Reusser Wedding | 10/10/2020"]
   [:div "Only " [:span.text-gold (days-until)] " days until the big day!"]])

(defn navigate-button [text callback]
  [:button.btn-lg.btn-block.text-gold.border-gold.background-orange {:on-click callback} text])


(defn header-buttons [application-state]
  (let [buttons [["Home" (go-home application-state)]
                 ["Registry"  (go-registry application-state)]
                 ["Schedule of Events" (go-schedule application-state)]
                 ["Wedding Party" (go-wedding-party application-state)]]]
    (map (fn [[text callback]] [:div.btn.col-sm-4
                                {:key text}
                                (navigate-button text callback)]) buttons)))

(defn page [ratom]
  (let [to-execute (:to-display @app-state)
        actual-content (to-execute)]
    [:div.container-fluid
     [:div.row.background-ivy (header-banner)]
     [:div.row.background-ivy (header-buttons app-state)]
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
