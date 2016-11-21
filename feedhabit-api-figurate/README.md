# FeedHabit API

The purpose of the API is to act as a glorified feed cache (i.e. aggregator) that provides the following functions:

## Scheduled update

Check for feed updates and synchronise on a regular basis

## Feed retrieval

* /feed/123         # returns metadata for feed 123 (title, description, url, item count, etc.)
* /feed/123/items   # returns items for feed 123
* 

## Search

* /search?q=blah    # returns items matching the query blah

## Common query parameters

* offset=10         # return results starting with position 10
* limit=50          # return a maximum of 50 results
* sort=date_asc     # sort results by date (oldest results first)
