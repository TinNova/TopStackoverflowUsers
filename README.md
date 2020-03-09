# Android Coding Task

## App Description

The app displays the top 100 StackOverflow users

- When the app is launched, it lists the top 100 StackOverflow users
- App locally simulates follow/block functionality without any calls to an API.
- When a user is blocked, block button turns to unblock, their status is saved locally
- When a user is followed, follow button turns to unfollow, their status is saved locally

## The Challenge
- I was given an unfinished project with no Dependency Injection and mostly written in Java, my task was to refactor it, fix bugs and deliver a functioning app
- The only existing classes that I kept are: SoResponse.java, User.java and UsersAdapter.java
- The timeframe was 4 to 5 hours

## Technical Details

#### Retrieving Data From API And Saving Data To Room
- The Api doesn't return whether a user is Followed or Blocked, this only comes from the Room database
- When Block or Follow is clicked it saves this to Room
- When the user opens their app, the api returns 100 StackOverFlow users, their userIds are referenced against the userIds within the Room database, if there is a match the Block/Follow status of that user is applied

#### LiveData
- The Activity is managed using a ViewState in the form of the data class ViewStateModel. The data class contains whether the Activity is in an Error, Loading, or Presenting state.
- These states make it very easy to test and read the ViewModel

#### RxJava vs Coroutines
- Due to the time limitations of this coding challenge RxJava was chosen
- A base "DisposingViewModel" was created to handle disposing of disposables in a lifecycle aware fashion

#### Testing
- Only the UserListViewModel could be extensively tested within the time limitations of the coding challenge
- Due to good architecture practises and dependency injection it was easy to test as I could mock the data returned from the UserRepository.

#### Challenges with Testing
- The UserRepository is challenging to test because it requires both a mock of an Api and of a Room database. The challenge here is that I have little experience with Room and to test it would require time to read up on it. Therefore it couldn't be done within the time limitations.
- UI Testing, again due to the lack of mocks and time, robust UI testing couldn't be achieved, although a simple test does exist.

#### RecyclerView NotifyItemChanged
- A small detail, however worth mentioning. When you block a user, the button text should change from "block" to "unblock", this would normally be done using notifyItemChanged, however this was causing the entire viewholder to refresh creating an unwanted UI experience. A work around was to globally store the view (button) that needed to be updated and only update that one view (button)

#### If I Had More Time I Would...
- Delete a user from the database if there Block and Follow state were both False
- When referencing the userId from the Api against the userId from Room, I would like to first compare the list sizes, and begin the comparison always with the smallest list (currently it is assumed the room database is always the smallest which could be false one day)
- Create mocks for the Room database and Api to allow the UserRepository to be tested
- Test the UsersHelp.kt class

## Libraries
- Networking: gson, okHttp, Retrofit, RxJava
- Dependency Injection: Dagger2
- Architecture: MVVM, LiveData
- Local Storage: Room
- Testing: Mockito, Espresso
