(ns exceptions_test
  (:require [clojure.test :refer :all]
            [exceptions :refer :all]
  )
)


(deftest bad-payment-method-test
  (is (thrown? Exception
    (check_field "INVALID_METHOD" "invalid PAYMENT.method"))
  )
)

(deftest bad-payment-method-test-no-one
  (is (thrown? Exception
    (check_field "NO_ONE" "invalid PAYMENT.method"))
  )
)


(deftest unknown-id-test
   (is (thrown? Exception
     (check_unknown_id "[]"))
   )
 )

 (deftest unknown-id2-test
    (is (thrown? Exception
      (check_unknown_id "{}"))
    )
  )

  (deftest unknown-id3-test
     (is (thrown? Exception
       (check_unknown_id nil))
     )
   )

   (deftest unknown-id4-test
      (is (thrown? Exception
        (check_unknown_id ""))
      )
    )


    (deftest duplicated-codes-test
       (is (thrown? Exception
         (check_duplicate_codes [{"code" "75.10"}{"code" "75.10"}]))
       )
     )
;;    (deftest check-cycle-test
;;          (is (empty? (check_cycle [{"code" "X" "rules" "Y"}{"code" "Y" "rules" "X"}])))
;;      )
