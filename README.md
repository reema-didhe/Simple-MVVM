# Simple-MVVM
This android application fetches the data from a remote server using Retrofit, stores in a local device using the ROOM database and displays the data in the form of recycler view. The application follows the MVVM design pattern that contains Android Jetpack components such as LiveData, ViewModel and Data Binding.

Application Features:

LiveData
ViewModel
Data Binding
Room
Retrofit
Recycler View
Navigation Fragment
Implementation Steps:

Add Internet and access network state permissions in Manifest
Add dependencies: Room, Lifecycle Dependencies, retrofit, navigation, Coroutines in Gradle
Model class to manage API response in the form of java objects.
Create Retrofit client and API interface for network calling and fetching API response
Use recycler view and implements its adapter
Create repository for the presentation layer
Setup Room for local storage (Entity, Dao and AppDatabase)
Create View Model its fragment to connect to repository
Connect View to View Model to display the list of data
Libraries Used: Retrofit, Lifecycle, Room, Coroutine, Navigation, Recycler View
