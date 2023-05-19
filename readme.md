For the exercise I would like you to consider a simple Change Machine. It calculates the coins which should be
used to make a certain value.

For example if you have a set of coins as [1p, 2p, 5p, 5p, 5p, 5p] and you need to make up a change worth 20p,
then you can do that with 4 coins of 5p. If you need to make change which adds up to 16p, then it would be 3 coins of 5p
and 1 coin of 1p

Hope this makes sense. The change machine should take 2 parameters, the list of available coins denominations,
and the amount which the result must add up to.

---

Deviations made:

- Updated usage of `toList()`, as the tests provided were not using this Java collections function appropriately
- Swapped the Expected and Actual values of the test assertions to be correct

Oh, I also made a "bad" implementation to demonstrate a couple different ways of representing how one might go about
this problem.