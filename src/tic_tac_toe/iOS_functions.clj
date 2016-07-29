(ns tic-tac-toe.iOS-functions
  (:require [tic-tac-toe.ai :as ai]
            [tic-tac-toe.game :as game]))

(defn minimax-move [board]
  (ai/best-computer-move (game/game-state board)))

(defn spot-with-index [board]
  (map-indexed vector board))

(defn return-move-or-index [move-and-index]
  (let [[index move] move-and-index]
    (if (= move "") index move)))

(defn convert-board [board]
  (let [indexed-board (spot-with-index board)]
    (map return-move-or-index indexed-board)))

(defn minimax-move [board-from-java]
  (let [game-state (game/game-state (convert-board board-from-java))]
    (ai/best-computer-move game-state)))
