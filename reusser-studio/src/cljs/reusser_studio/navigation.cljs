(ns reusser-studio.navigation
  (:require [reusser-studio.constants :refer [target-image amazon-image]]))

(defn content-home []
  [:div.col-sm-12.text-center
   [:br]   [:img.rounded-r.image-fluid {:src "images/hogwarts.jpg"}]])

(defn go-home [application-state]
  #(swap! application-state assoc :to-display content-home))

(defn content-registry []
  (let [registries
        [["Target"
          "https://www.target.com/gift-registry/giftgiver?registryId=4f2e663546804f79b47fdb7b55d152e0&type=GENERIC&occasionType=HOUSEWARMING" target-image]
         ["Amazon"
          "http://amazon.com/wedding/share/mcfarland-reusser-registry"
          amazon-image]]]

    (map (fn [[name link image]] [:a.col-sm-12.text-center
                                  {:key name
                                   :href link
                                   :target "_blank"}
                                  [:div [:div.text-gold.h3 name] [:img.rounded-r {:src image}]]]) registries)))

(defn go-registry [application-state]
  #(swap! application-state assoc :to-display content-registry))

(defn content-schedule-events []
  [:div.text-gold.h3.p-3 "All times in EST"
   [:ul
    [:li "4:00pm - Ceremony Begins"]
    [:li "5:00pm - Hors d'oeuvres and board games while the wedding party takes pictures"]
    [:li "6:00pm - Dinner laid out; buffet!"]
    [:li "7:30pm - Dancing!"]
    [:li "9:00pm - Bride and Groom head out; Grand Canyon here we come!"]]])

(defn go-schedule [application-state]
  #(swap! application-state assoc :to-display content-schedule-events))

(defn content-wedding-party [] [:div.h3.text-gold.p-3 [:div.d-flex.flex-row.justify-content-around.flex-fill
                                                       [:div "Bridal Party" [:ol
                                                                             [:li "Kendra " [:span "M" [:sup "c"] "Farland (Yes; she's taking the Reusser name 😏)"]]
                                                                             [:li "Aryiole Frost"]
                                                                             [:li "Justin LastNameHere"]
                                                                             [:li "Kelly Marcum"]
                                                                             [:li "TBD LastNameHere"]]]
                                                       [:div "Groomal* Party" [:ol
                                                                               [:li "Jake Reusser"]
                                                                               [:li "Ben Reusser"]
                                                                               [:li "Helen Reusser"]
                                                                               [:li "Dillon Last Name"]
                                                                               [:li "Cameron Calentine (need to confirm)"]]]] [:div "* - Don't believe \"groomal\" is a word? You can argue all you want, at *your* wedding 😁"]])

(defn go-wedding-party [application-state]
  #(swap! application-state assoc :to-display content-wedding-party))
