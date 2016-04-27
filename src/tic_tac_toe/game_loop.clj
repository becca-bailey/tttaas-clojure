(ns tic-tac-toe.game-loop
  (:gen-class)
  (:require [tic-tac-toe.board :as board]
            [tic-tac-toe.ui :as ui]
            [tic-tac-toe.game :as game]
            [tic-tac-toe.ai :as ai]))

; (defn move [game-state]
;   (if (= "X" (game/current-player game-state))
;     (ui/get-spot "X" (board/available-spots (:board game-state)))
;     (ai/best-computer-move game-state)))

(defmulti move (fn [game-state] (:player-type (game/current-player game-state))))

(defmethod move :default [game-state]
  (ui/get-spot (:marker (game/current-player game-state)) (board/available-spots (:board game-state))))

(defmethod move :computer [game-state]
  (ai/best-computer-move game-state))

(def initial-state (game/game-state [0 1 2 3 4 5 6 7 8] "X" 0))

(defn -main []
  (ui/clear-screen)
  (ui/display-welcome-message)
  (loop [game-state initial-state
          last-move nil]
    (do
      (if last-move
        (ui/confirm-move last-move (game/switch-player (game/current-player game-state))))
      (ui/display-board (:board game-state))
      (cond
        (board/winner game-state)
        (ui/display-winner (board/winner game-state))
        (board/tie? (:board game-state))
        (ui/display-tie)
        :no-winner-or-tie
          (let [next-move (move game-state)]
            (ui/clear-screen)
            (recur (game/progress-game-state next-move game-state) next-move))))))
