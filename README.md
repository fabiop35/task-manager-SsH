Book Spring Start Here. Chapter 4.

curl http://localhost:8080/hello

curl -X POST http://localhost:8080/payment

#CH10: Test @RequestBody annotation
curl -X POST http://localhost:8080/makePayment -H "content-type: application/json" -d '{"amount": 9.9}' -v

##CH11
curl -X POST http://localhost:8080/payment -H "content-type: application/json" -H "requestId: 1" -d '{"amount": 9.9}' -v

#OpenFeign request
curl -X POST -H 'content-type:application/json' -d '{"amount":1000}' http://localhost:8080/paymentOF
{"id":"30ac829c-21aa-4ba5-a4e3-de5d4ab19afe","amount":1000.0}


#Test JdbcTemplate with H2
curl -XPOST 'http://localhost:8080/purchase' -H 'Content-Type: application/json' -d '{"product" : "Spring Security in Action",  "price" : 25.2}'

#Install MariaDB
pkg install mariadb

#start MariaDB
cd '/data/data/com.termux/files/usr' ; /data/data/com.termux/files/usr/bin/mariadbd-safe --datadir='/data/data/com.termux/files/usr/var/lib/mysql'

#Test Transaction
curl http://localhost:8080/accounts

curl -XPOST -H "Content-Type: application/json" -d '{"senderAccountId":2, "receiverAccountId":1, "amount":900}' http://localhost:8080/transfer

#Test Spring Data JDBC
curl 'http://localhost:8080/accounts?name=Helen+Down'

#Run Tests
mvn test
mvn -Dtest=com.ssh.TransferServiceUnitTests test
mvn -Dtest=com.ssh.TransferServiceSpringIntegrationTests test
