Book Spring Start Here. Chapter 4.

curl http://localhost:8080/hello

curl -X POST http://localhost:8080/payment

#CH10: Test @RequestBody annotation
curl -X POST http://localhost:8080/makePayment -H "content-type: application/json" -d '{"amount": 9.9}' -v


