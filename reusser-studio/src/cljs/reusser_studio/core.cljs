(ns reusser-studio.core
  (:require
   [reagent.core :as reagent]))

(defn header-banner []
  [:div.header.row.justify-content-sm-center.text-center
   [:div.col-sm-12 "McFarland-Reusser Wedding"]])

(defn navigate-button [text callback]
  [:button.btn-primary.btn-lg.btn-block {:on-click callback} text])

(defn content-registry []
  [:a {:href "https://www.google.com/" :target "_blank"} "Gooooooogle"]
  )

(defn go-registry []
  (swap! app-state assoc :to-display content-registry))

(defn content-home []
  [:div.col-sm-12.text-center
   [:div "Welcome to the McFarland Reusser Wedding Landing Page"]
   [:img.rounded.image-fluid {:src "images/hogwarts.jpg"}]])

(defonce app-state
  (reagent/atom {:to-display content-home}))

(defn go-home []
  (swap! app-state assoc :to-display content-home))

(defn content-guest-list []
  [:div.col-sm-12.text-center "Please sign the Guest List :D"])

(defn go-guest-list []
  (swap! app-state assoc :to-display content-guest-list))

(defn header-buttons []
  (let [buttons [["Home" go-home]
                 ["Sign the Guest List" go-guest-list]
                 ["Registry"  go-registry]]]
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
