# Cloud-Native-Application-Zodiac-Signs
Determine the zodiac sign that corresponds to a valid calendar date entered.

The assignment involves entering a calendar date (month/day/year) on the client side. Here, the entered date will be validated in accordance to the following criteria: 

- The format must be month/day/year (e.g. 01/01/1999 but also 1/1/1999)
- The message should only contain numbers and the "/" sign, correctly placed 
- The date has to be valid (pay attention to leap years)

If and only if the input is valid, it will be sent to the server. 

The server will get the zodiac intervals from a file and determine the right zodiac sign.
As a response, the zodiac sign’s name will be sent back to the client. 
If the input is not valid, no zodiac sign will be sent and the errors should be handled ACCORDINGLY.

The zodiac signs will be grouped based on their type (Chinese and European). Each type will be handled by a microservice.
