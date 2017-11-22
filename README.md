# Forex API Playground

Forex REST interface with shorterm cache


### Prerequisites

The project requires the following:

- [Redis](https://redis.io/)
- [Forex API Key](https://1forge.com/forex-data-api/api-documentation) has to be stored as a system variable named `FOREX_API_KEY`

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


