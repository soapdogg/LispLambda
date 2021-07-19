(DEFUN ADDPAIRS (X Y Z)
    (COND
        ((null Y)
            Z
        )
        ((ATOM X)
            Z
        )
        ((null(ATOM(car X)))
                    Z
        )
        ((ATOM Y)
            Z
        )
        ((null X)
            Z
        )
        (T
            (CONS
                (CONS
                    (car X)
                    (car Y)
                )
                (ADDPAIRS (cdr X) (cdr Y) Z)
            )
        )
    )
)

(DEFUN GETVAL (X Z)
    (COND
        ((EQ X (car (car Z)))
            (cdr (car Z))
        )
        (T
            (GETVAL X (cdr Z))
        )
    )
)

(ADDPAIRS (CONS 3 NIL) (CONS 34 NIL) ())
(ADDPAIRS (CONS 45 NIL) (CONS 56 NIL) ('()))
(ADDPAIRS
    (CONS 45 (CONS 89 (CONS 34 NIL)))
    (CONS ('(23 44 45)) (CONS NIL (CONS ('(56 34)) NIL)))
    (CONS (CONS 34 34) (CONS (CONS 67 12) NIL)))