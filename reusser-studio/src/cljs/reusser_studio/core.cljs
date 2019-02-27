(ns reusser-studio.core
  (:require
   [reagent.core :as reagent]))

(defn header-banner []
  [:div.header.row.justify-content-sm-center.text-center
   [:div.col-sm-12 "McFarland-Reusser Wedding"]])

(defn navigate-button [text callback]
  [:input {:type "button" :value text :on-click (callback)}])

(defn go-rsvp []
  #(js/console.log "give us food!"))

(defn content-home []
  [:div.col-sm-12.text-center "Welcome to the McFarland Reusser Wedding Landing Page"])

(defonce app-state
  (reagent/atom {:to-display content-home}))

(defn go-home []
  (swap! app-state content-home))

(defn content-guest-list []
  [:div.col-sm-12.text-center "Please sign the Guest List :D"])

(defn go-guest-list []
  (swap! app-state content-guest-list))

(defn header-buttons []
  (let [buttons [["Home" go-home]
                 ["Sign the Guest List" go-guest-list]
                 ["RSVP"  go-rsvp]]]
    (map (fn [[text callback]] [:div.btn.col-sm-4
                                {:key text}
                                (navigate-button text callback)]) buttons)))

(defn page [ratom]
  [:div.container-fluid
   [:div.row (header-banner)]
   [:div.row (header-buttons)]
   [:div.row ((:to-display @app-state))]])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")))

(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (reload))
