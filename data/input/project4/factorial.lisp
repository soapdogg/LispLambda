(DEFUN FACTORIAL (X)
    (COND
        ((< X 1) 1)
        (T (* X (FACTORIAL (- X 1))))
    )
)

(FACTORIAL 6)
(FACTORIAL 0)
(FACTORIAL 1)
(FACTORIAL (- 0 1))
(FACTORIAL 10)