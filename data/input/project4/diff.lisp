(DEFUN DIFF (X Y)
    (COND ((EQ X Y) NIL) (T T)))

(DIFF 5 6)
(DIFF (PLUS 1 1) 4)
(DIFF T NIL)
(DIFF T T)
(DIFF (TIMES (PLUS 1 3) 2) (MINUS 12 4))