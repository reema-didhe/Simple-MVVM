Application Features:
1.	LiveData
2.	ViewModel
3.	Data Binding
4.	Room
5.	Retrofit
6.	Recycler View
7.	Navigation Fragment


Implementation Steps:
1.	Add Internet and access network state permissions in Manifest
2.	Add dependencies: Room, Lifecycle Dependencies, retrofit, navigation, Coroutines in Gradle
3.	Model class to manage API response in the form of java objects.
4.	Create Retrofit client and API interface for network calling and fetching API response
5.	Use recycler view and implements its adapter
6.	Create repository for the presentation layer
7.	Setup Room for local storage (Entity, Dao and AppDatabase)
8.	Create View Model its fragment to connect to repository
9.	Connect View to View Model to display the list of data

Libraries Used:
Retrofit, Lifecycle, Room, Coroutine, Navigation, Recycler View
