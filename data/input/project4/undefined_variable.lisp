(DEFUN FACTORIAL (X)
    (cond
        ((< X 1) 1)
        (T (* E (FACTORIAL (- X 1))))
    )
)

(FACTORIAL 6)
(FACTORIAL 0)
(FACTORIAL 1)
(FACTORIAL (MINUS 0 1))
(FACTORIAL 10)