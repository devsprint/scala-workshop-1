###1 Multi service integration - transactions over multiple external services.

Saga pattern: 
https://dzone.com/articles/saga-pattern-how-to-implement-business-transaction

https://github.com/dobrynya/saga/blob/master/src/main/scala/ru/dimitrius/saga/saga.scala


###2 Lock on a method
- This necessity should be avoided through design. In scala, the goal is to have the implementation async and non-blocking.
- There are alternative solutions using immutable data structures, using akka or other building blocks. 

###3 Design

- Seems a design issue. But you end up with a lot of different value objects but duplication is not a good sign, in general 
smells as design issue. 


###4 Pagination

- simple pagination implemented in Registration service Tag 0.0.8
- Slick streams are not for pagination. THose are used to stream the data from a db or to a db. Those are implementing 
Reactive Streams on top of akka streams
- Explain reactive streams in the session.  