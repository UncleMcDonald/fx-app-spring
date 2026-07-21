| Use case | Verb | Path | Success | Failure modes |
|---|---|---|---|---|
| List all latest rates | GET | /api/rates | 200 OK | none for the hardcoded stub |
| Get one pair's latest rate | GET | /api/rates/{base}/{quote} | 200 OK | 404 Not Found for unknown pair |
| Create a conversion | POST | /api/conversions | 201 Created | 400 Bad Request for invalid payload, 404 Not Found for unknown pair |