(ns discrete-math.utils)

(defn cell-value [x y set] [:td (if (some #{[x y]} set) "1" "0")])

(defn print-set [set] [:p.small (str set)])