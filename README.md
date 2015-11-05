
# Small demonstartion of Material Design with Abercrombie&Fitch promotions

This is a simple demostration to show Abercrombie&Fitch promotions with Android's some new Material Design and some other cool new stuff which is (new) in Android Lollipop.

#Developed By

* Jumbo Chu - http://personal-env.elasticbeanstalk.com/single-page - <chu.jing.bo.stan@gmail.com>

This includes features like:
- Toolbar
- RecyclerView
- CardView
- Floating Action Button (FAB)
- Animations (Including animations for the RecyclerView)
- Android Compat Theme

Functionalities implemented:
	Online---
- Request data from A&F webite and parse it into the shoping-item promotion
- Show image and title of shoping-item 
- Drawer is shown with no real meanings
- Clicking the shoping-item leads to the item detail pages with some animation
- Detailed information shown the detail page
- Shop (FAB) button leads to the A&F website with animation
- Store item caching image and item information in local database(SQLite)

	Offline---
- Alert " Network unavailable"
- Load local data
- Show image and title of viewed shoping-item 
- Clicking the shoping-item leads to the viewed item detail pages with some animation

### Setup/run requirements:
- Lastest Android SDK tools 
- Lastest Android platform tools
- Android SDK Build tools at least 21.1.1
- Android SDK 23 (at least Android 21 API)
- Android Support Respository
- Android Support Library 23 (at least 21.1.1)
- Google Respository
- Run with Android 5.0/higher system device

### Getting Started

1.Download this project Zip 
2.Open it with Android Studio
3.Run it using your vitural device/real device online
4.Turn off network, run it. then there is not data shown on screen
5.Turn on Networkd, run it. then show some shoping-items
7.Click item then go to detail page
8.Click shop button then go to A&F website
9.Or Presse back/home button(from action bar), then back to privous page
10.Shut donw your network. run it you will find it still works (Alert network issue)

### Used Thried Library

- Android support v7, v13 suite
- com.Squareup.picasso 2.4.0
- com.Squareup.retrofit 1.9.0
- com.jakewharton:butterknife 6.0.0
- android-async-http 1.4.4
- crouton 1.8.5

### Support

- Google+ Community: https://plus.google.com/communities/105153134372062985968

- Stack Overflow: http://stackoverflow.com/questions/tagged/android

If you've found an error in this sample, please file an issue:

https://github.com/googlesamples/android-topeka/issues

Patches are encouraged, and may be submitted by forking this project and
submitting a pull request through GitHub.


#License

    Copyright 2015 Jumbo Chu

