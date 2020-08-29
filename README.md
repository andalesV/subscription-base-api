# subscription-base-api


URL POST: http://localhost:8085/create/subscription

## Subscription Type: WEEKLY
**Request Body:** 
{
    "amount": 852.75,
    "subscriptionType": "WEEKLY",
    "scope": "MONDAY",
    "duration": {
        "startDate": "08/2020",
        "endDate": "8/2020"
    }
}

**Reponse:**
{
    "amount": 852.75,
    "subscriptionType": "WEEKLY",
    "invoiceDates": [
        "03/08/2020",
        "10/08/2020",
        "17/08/2020",
        "24/08/2020",
        "31/08/2020"
    ]
}

## Subscription Type: MONTHLY
**Request Body:**
{
    "amount": 852.75,
    "subscriptionType": "MONTHLY",
    "scope": 25,
    "duration": {
        "startDate": "08/2020",
        "endDate": "11/2020"
    }
}

**Reponse:**
{
    "amount": 852.75,
    "subscriptionType": "MONTHLY",
    "invoiceDates": [
        "25/09/2020",
        "25/10/2020",
        "25/11/2020"
    ]
}

