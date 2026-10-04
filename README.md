This is implementation of an example feature for an interview.<br />
The implementation ended up relatively simple, yet it fulfills all requirements and remains extensible. It's a good base for a new service in situation, where there are many unknowns and decisions should be delayed into the future.

## Assumptions
### Backend
- A user can book more than just one bike.
- There might be more than just one bike with same manufacturer, color and other tracked features.
- Even if we do not close any bookings, I added `bookingEnded` timestamp as a field I know should exist in the future regardless how the bookings will be closed.

### Frontend
- Names of the users are public, everyone can see who booked a rented bike.

## Decisions
### Backend
- Current story is all about booking functionality and the user is merely a name attached to it. For this reason I went for `bookings` entity with its own lifecycle and left the user just as a field. Depending on further development of the system, user can be mapped in the future into its own entity. However, that was not necessary to fulfill this story.
- I have removed the `bike.rented` field from the database entity and instead the bike state is inferred from current booking's state. This allows me to have single source of truth for a current booking state. Right now, the booking's state is calculated on the fly, if it ever became a performance concern, it might be stored on `booking` entity. For current requirements though, that was not necessary.
- Because of relative low business logic in the backend, I wrote only integration test for the API, I didn't see much benefit from unit tests in this repository.

### Frontend
- I didn't like the idea of showing user's name directly in the table, so instead I have built a small booking summary modal. This way to acceptance criteria is fulfilled, but it's still one click away instead of showing directly in the bicycle table.