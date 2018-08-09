# Taco Golf

Taco Golf (hopefully one day <http://taco.golf>) is a prospective web service, making the world a better place for taco-golfers everywhere by providing an intuitive, easy to use, innovated, webscale, organic, GMO free, and absolutely not overengineered cloud-based platform to visualize and track their scores as they progress through the high-stakes competetive world of taco-golf.


# But I've Never Heard of Taco Golf

Taco Golf is an up in coming game in the world of minor league alcoholics and tex-mex enthusiasts everywhere. Let me explain the rules:

1. Have your taco-golf organizer draft up a list of Mexican resturaunts in your area, make sure they have liquor licenses or you're defeating the point.
2. Get a group of friends, start early, and travel to each of these places. In order to meet par you must:
 (1) Eat at least one taco
 (2) Drink at least one beer
3. If you drink more ore eat more tacos, then congratulations! You're under par!
4. Some rules allow for large mixed drinks such as jumbo margaritas to count as 2 drinks, so feel free to play with that rule as well.
5. Anyone who shows up late or leaves early must take a 2-point penalty for every place they missed.
6. The winner is the golfer with the lowest score at the end of the day

And it's that easy!

You can also:
  - Drink tequila shots in place of beers
  - Substitute tacos with appropriately similar foods
  - Do whatever I guess, I'm not your mom

# Setting Up Your Personal Taco Cloud

Hopefully one day this won't be necessary, but with a project so stupid I wouldn't bet on this ever going into production, so maybe we've turned a stupid idea into a cloud service and your paranoid we're mining your taco-data so venders know who they need to put less effort into cooking for, or maybe we just never published this, who cares! Run taco golf for yourself on your own server!

Currently taco.golf is setup as a docker-compose service meant to run on whatever machine you so choose but it has grander visions of being developed into a kubernetes cluster because... you know... reasons. To start a taco golf service all you reall need to do is clone this repo, cd into the director and run:

```
docker-compose build
docker-compose up -d
```

And your taco-golf frontend will be hosted at <http://localhost:9000/>
Easy, right?

## Component Breakdown

Taco Golf is currently broken into 3 separate components and probably eventually 4. Right now there is:

1. A node-based webpack build react frontend using material-ui
2. A scala-play API backend
3. A redis database to store your groups and counts

# TODO

Literally everything. This is just boiler plate code right now that says taco golf.

License
----

MIT


**Free Software, Hell Yeah!**
