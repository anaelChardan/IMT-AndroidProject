# Android Project

This project tries to follow the Hexagonal architecture but even
if the project is a little bit simple for that it was interesting.

Morevoer the supportingboundedcontext is only a technical layer that provides everything necessary
to build an app with a list and a detailed view. This layer is totally abstracted from the Book domain
and is fully reusable. It could be an external library.

A choice has been made that as I didn't wanted to add a dependency injection framework and as I am
allergic to static call I decided to make my Processors services parcelable in order to give it to fragments.