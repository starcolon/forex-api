# Forex API Playground

Forex REST interface with shorterm cache


### Build & Dependencies

The project requires the following external dependency:

- [Redis](https://redis.io/)

To compile the project, execute:

```bash
$ sbt compile
```

### Run

Start the REST interface server

```bash
$ sbt run
```

And terminate the server with `Ctrl+C`


### API Usage

Once having the server started, up and running, 
make a GET request to obtain the exchange rate as follows.

```
GET http://localhost:2017/forex/exchange?from={CURRENCY}&to={CURRENCY}&quantity={AMOUNT}
```

Where the `CURRENCY` denotes 3-alpha currency code
and `AMOUNT` is the amount in the source currency to exchange.


*Example:*

> http://localhost:2017/forex/exchange?from=JPY&to=EUR&quantity=1550


