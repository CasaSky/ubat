# UBAT | Universal Blockchain Access Technology

## A core system that exposes blockchain as a rest api service

### test the rest api service:

#### deliver address info 

```shell script
$ curl -i -X GET  "http://localhost:8080/info/ethereum/0xbbe79f7d50e4ff6e8ca61082a8740c86e873b3b8"
```
```json
{
  "clientVersion":"Geth/v1.8.27-omnibus-c94f741c/linux-amd64/go1.11.1",
  "address":"0xbbe79f7d50e4ff6e8ca61082a8740c86e873b3b8",
  "blockNumber":"8428270",
  "balance":"0.1766469833609384 Ξ"
}
```
HTTP/1.1 200<br />
Content-Type: application/json;charset=UTF-8<br />
Transfer-Encoding: chunked<br />
Date: Mon, 26 Aug 2019 21:38:37 GMT

#### deliver transaction details

```shell script
$ curl -i -X GET  "http://localhost:8080/transactionDetail/ethereum/0xd014161ce38549f79ceee8cfbcede920726fe8b50ea748b72a1cff65330158c4"
```
```json
{
  "value":25368873531990629,
  "from":"0xea674fdde714fd979de3edf0f56aa9716b898ec8",
  "to":"0xbbe79f7d50e4ff6e8ca61082a8740c86e873b3b8",
  "block":4836332,
  "gasPrice":1000000000,
  "gas":50000
}
```
HTTP/1.1 200<br />
Content-Type: application/json;charset=UTF-8<br />
Transfer-Encoding: chunked<br />
Date: Mon, 26 Aug 2019 21:39:18 GMT
