feedhabit
=========

A 'social' feed aggregator

Feed aggregation is difficult because of the sheer quantity of feed items. Applying similar principles as Twitter
can help to overcome the information overload and find relevance in the data.

Why Twitter?
------------

 1. The majority of tweets appear to be links to articles. So a Twitter-like application that sources items _only_ from feeds seems valid
 2. Twitter allows you to 'follow' anyone, without need for approval/reciprocal follow
 3. Sharing feeds doesn't have as many privacy concerns as sharing private data

Feed Streams
------------

The underlying concept of the Feed Stream is visible in two ways:

 1. The feeds you follow directly appear in your Feed Stream
 2. The Feed Streams you are following from other users also appear in your Feed Stream

Sharing
-------

Sharing items in your public Feed Stream is achieved in the following ways:

 1. Flag - mark an article as worthy of inclusion in your public Feed Stream
 2. Tag - categorise an article with a templated string (like a hashtag)
 3. Annotate - add a comment to an item

Following
---------

Following the Feed Stream of other users has the following effects to your Feed Stream:

 1. Items that have been Flagged, Tagged or Annotated by a user you are following will appear in your Feed Stream (even if you are not subscribed to the source feed)
 2. Items will aggregate and show any Flags, Tags and Annotations on items from those you are following

