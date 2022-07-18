# match-sample

A simmple app using `Compose`, `Retrofit`, `OkHttp`, `Hilt`, and other modern libraries.

Architecture is a simple MVVM pattern.

Uses Nasa's api, calling two different endpoints.
  - APOD which gets the planetary picture of the day, so should be new everyday,
  - Mars rover endpoint which uses pagination to display photos taken by a mars rover on a specific day. Once the photos for that day are exhausted it will then query a new day and repeat.
