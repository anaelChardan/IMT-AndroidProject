# Android Project

### Architecture

This project tries to follow the Hexagonal architecture (introduced by Alistair Cockburn [here](http://alistair.cockburn.us/Hexagonal+architecture))
and Domain Driven Design Approach (introduced by Eric Evans in the ["blue book"](https://www.amazon.fr/Domain-Driven-Design-Tackling-Complexity-Software/dp/0321125215/ref=sr_1_1?ie=UTF8&qid=1511107275&sr=8-1&keywords=eric+evans)) even
if the project is a little bit simple for that it was interesting.

Here the Domain is the Library of Harry Pottier, to me if I understood well there is two bounded contexts
- The library and then domain (the book itself and how to retrieve them) / infrastructure (all the android stuff and the adapters like Retrofit)
- The supporting one which is totally Generic (I assume that this one is not really DDD as abstractions are prohibited)


Moreover the supportingboundedcontext is only a technical layer that provides everything necessary
to build an app with a list and a detailed view. This layer is totally abstracted from the Book domain
and is fully reusable. It could be an external library.

### Tackle the absence of DI

A choice has been made that as I didn't wanted to spend time on adding a dependency injection framework and as I am
allergic to static call I decided to make my Processors services parcelable in order to give it to fragments.

Make a service parcelable has no sense as it is not a data structure but a service, a function.