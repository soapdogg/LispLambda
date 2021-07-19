
(DEFUN GETVAL (X Z)
    (COND
        ((= X (car (car Z)))
            (cdr (car Z))
        )
        (T
            (GETVAL X (cdr Z))
        )
    )
)

(GETVAL 3 (cons (cons 3 5) NIL))
(GETVAL 45 (cons (cons 3 34) (cons (cons 45 ('(3 4 5))) (cons (cons 3 23) NIL))))
(GETVAL 1 (cons (cons 1 23) (cons (cons 1 48) NIL)))
(GETVAL 564 (cons (cons 34 4) (cons (cons 23 4) (cons (cons 564 34) NIL))))

