### Test the endpoint

#### Step 1 - Install the dependencies and run the application

- Open the backend project in Intellij
- Install the dependencies using gradle
- Run the BackendApplication.java file

#### Step 2 - Test the endpoint

```
curl --location --request POST 'http://localhost:8080/rewards/calculate-monthly-rewards' \
--form 'file=@"[PATH-TO-ROOT]/rewards/src/main/resources/demo-transactions.csv"'
```

Expected output:

```
[
    {
        "month":"JUNE",
        "totalSpent":15816.88,
        "totalTransactions":91,
        "totalRewards":25297
    },{
        "month":"APRIL",
        "totalSpent":129243.02,
        "totalTransactions":122,
        "totalRewards":250519
    },{
        "month":"MAY",
        "totalSpent":19638.93,
        "totalTransactions":69,
        "totalRewards":34946
    }
]
```