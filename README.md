# UBAT | Universal Blockchain Access Technology

## A core system that exposes blockchain as a rest api service

### test the rest api service:

#### deliver address info 

curl -i -X GET  "http://localhost:8080/info/ethereum"

#### deliver transaction details

curl -i -X GET  "http://localhost:8080/transactionDetail/ethereum"