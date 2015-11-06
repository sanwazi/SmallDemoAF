
# Demonstartion of Material Design with Abercrombie&Fitch promotions

This is a simple demostration to show Abercrombie&Fitch promotions with Android's some new Material Design and some other cool new stuff which is (new) in Android Lollipop. ( Publish on Google Play Store soon )

* [Youtube](https://youtu.be/gN7NpyFjwdw)

# Demo

![PrintScreen APP exemplo](https://cloud.githubusercontent.com/assets/9556537/10978104/1f4feb0e-83c3-11e5-8fac-83e1be0fb5c2.png)
![PrintScreen APP exemplo](https://cloud.githubusercontent.com/assets/9556537/10978103/1d0b08e2-83c3-11e5-913b-7c9908e89b6a.png)


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
- Android SDK Build tools at least 23.0.1
- Android SDK 23 (at least Android 21 API)
- Android Support Respository
- Android Support Library 23 (at least 21.1.1)
- Google Respository
- Run with Android 5.0/higher system device

### Getting Started / How to test it

- Download this project Zip
- Open it with Android Studio
- setup your SDK location
- Run it using your vitural device/real device online (Android device better to be with 21 API)
- Turn off network, run it. then there is not data shown on screen
- Turn on Networkd, run it. then show some shoping-items
- Click item then go to detail page
- Click shop button then go to A&F website
- Or Presse back/home button(from action bar), then back to privous page
- Shut donw your network. run it you will find it still works (Alert network issue)

### Used Thried Library

- Android support v7, v13 suite
- com.Squareup.picasso 2.4.0
- com.Squareup.retrofit 1.9.0
- com.jakewharton:butterknife 6.0.0
- android-async-http 1.4.4
- crouton 1.8.5


#####Developed By
* Jumbo Chu - http://personal-env.elasticbeanstalk.com/single-page - <chu.jing.bo.stan@gmail.com>
* 
### Support

- Google+ Community: https://plus.google.com/communities/105153134372062985968

- Stack Overflow: http://stackoverflow.com/questions/tagged/android

If you've found an error in this sample, please file an issue:

https://github.com/sanwazi/SmallDemoAF/issues

Patches are encouraged, and may be submitted by forking this project and
submitting a pull request through GitHub.


#License

    Copyright 2015 Jumbo Chu


