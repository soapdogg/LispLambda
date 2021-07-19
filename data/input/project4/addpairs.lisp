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
            (cons
                (cons
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

(ADDPAIRS (cons 3 NIL) (cons 34 NIL) ())
(ADDPAIRS (cons 45 NIL) (cons 56 NIL) ('()))
(ADDPAIRS
    (cons 45 (cons 89 (cons 34 NIL)))
    (cons ('(23 44 45)) (cons NIL (cons ('(56 34)) NIL)))
    (cons (cons 34 34) (cons (cons 67 12) NIL)))