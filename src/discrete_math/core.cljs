(ns discrete-math.core
  (:require
   [reagent.dom :as d]
   [discrete-math.math :refer [generate-sequence create-set compose inverse transitive]]
   [discrete-math.utils :refer [cell-value print-set]]))

;; -------------------------
;; Components
(defn matrix-component [matrix]
  [:table.table.table-sm.table-borderless
   [:tbody
    (for [x (generate-sequence)]
      [:tr (for [y (generate-sequence)]
             (cell-value x y matrix))])]])

(defn matrix-and-set-component
  ([matrix] (matrix-and-set-component "Matrix" matrix))
  ([name matrix] (list [:h6 name] (matrix-component matrix) [:h6 "Set:"] (print-set matrix))))

(defn sets-component [sets] (for [[name set] sets]
                              [:div.col-sm-2 (matrix-and-set-component name set)]))

;; -------------------------
;; Vars
(def rho (create-set #(>= 1 (js/Math.abs (* (- 3 %1) (- 3 %2))))))
(def tau (create-set #(> 5 (+ %1 %2))))

(def sets {"Rho" rho "Tau" tau})
(def sets-operations
  {"Composition of Rho and Tau" (compose rho tau)
   "Inverse relation of Rho" (inverse rho)
   "Inverse relation of Tau" (inverse tau)
   "Composition of both inverse Rho and Tau" (compose (inverse rho) (inverse tau))
   "Transitive closure of Rho" (transitive rho)
   "Transitive closure of Tau" (transitive tau)
   "Transitive composition of Rho and Tau" (transitive (compose rho tau))})

;; -------------------------
;; Views
(defn home-page []
  [:div.container-fluid
   [:div.row
    [:div.col [:h2 "Discrete math"]]]
   [:div.row (sets-component sets)]
   (for [row (partition-all 6 sets-operations)]
     [:div.row (sets-component row)])])

;; -------------------------
;; Initialize app
(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
