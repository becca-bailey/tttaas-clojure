(ns tic-tac-toe.iOS-functions
  (:require [tic-tac-toe.ai :as ai]
            [tic-tac-toe.game :as game]
            [tic-tac-toe.player :as player]))

(defn switch-marker [marker]
  (if (= marker "X") "O" "X"))

(defn get-game-state [board computer-marker]
  (let [computer-player (player/computer computer-marker)
        human-player (player/human (switch-marker computer-marker))]
    (game/game-state board [human-player computer-player])))

(defn spot-with-index [board]
  (map-indexed vector board))

(defn return-move-or-index [move-and-index]
  (let [[index move] move-and-index]
    (if (= move "") index move)))

(defn convert-board [board]
  (let [indexed-board (spot-with-index board)]
    (into [] (map return-move-or-index indexed-board))))

(defn minimax-move
  ([board-from-java]
    (let [game-state (game/game-state (convert-board board-from-java))]
      (ai/best-computer-move game-state)))
  ([board-from-java computer-marker]
    (let [game-state (get-game-state (convert-board board-from-java) computer-marker)]
      (ai/best-computer-move game-state))))
