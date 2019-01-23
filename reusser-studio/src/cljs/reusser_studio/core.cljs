(ns reusser-studio.core
  (:require
   [reagent.core :as reagent]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars

(defonce app-state
  (reagent/atom {}))

(defn navigate-button [text location]
  [:input {:type "button" :value text :on-click #(js/console.log location)}])

(defn header-banner []
  [:h1 {:class "header"} "Reusser Studios"])

(defn header-buttons []
  (let [home {:text "Home" :location "home"}]
    (navigate-button (home :text) (home :location))))

(defn header []
  [:div (header-banner) (header-buttons)])

(defn page [ratom]


  [:div (header-banner)
   (header-buttons)])


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
