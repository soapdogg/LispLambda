(DEFUN BOUND (X Y)
    (COND
        ((null X)
            NIL
        )
        ((null Y)
            NIL
        )
        ((atom Y)
            NIL
        )
        ((= X (car (car Y)))
            T
        )
        (T
            (BOUND X (cdr Y))
        )
    )
)

(BOUND () ())
(BOUND 3 ())
(BOUND 3 4)
(BOUND 3 ('((3 5))))
(BOUND 3 ('((23 34)(45 3)(56 23))))
(BOUND 3 ('((23 34)(45 3)(3 23))))
