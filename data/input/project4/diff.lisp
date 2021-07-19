(DEFUN DIFF (X Y)
    (COND ((= X Y) NIL) (T T)))

(DIFF 5 6)
(DIFF (+ 1 1) 4)
(DIFF T NIL)
(DIFF T T)
(DIFF (* (+ 1 3) 2) (- 12 4))