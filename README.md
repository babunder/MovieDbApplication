Movie Database Application:

An application has to be developed to find movie information.

A list of movies should be displayed, so that when you click on them, a second screen with the information of each movie will open. In addition, the application should allow you to save your favorite movies so that you can watch them whenever you want.

Usage
For a working implementation, please have a look at the Sample Project

Include the library

   (A) Added following library

    (1) --for support multiple screen size. Refer following link: https://github.com/intuit/sdp
    compile 'com.intuit.sdp:sdp-android:1.0.3'
    
    (2) -- Glide libary for rending image efficiently
    compile 'com.github.bumptech.glide:glide:4.1.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.1.1'

    3) for view model design pattern
    implementation 'android.arch.lifecycle:extensions:1.1.1'
    implementation 'android.arch.lifecycle:viewmodel:1.1.1'
    implementation 'android.arch.lifecycle:livedata:1.1.1'
    annotationProcessor 'android.arch.lifecycle:compiler:1.1.1'
    
    (4) gosn library 
    compile 'com.google.code.gson:gson:2.6.2'

    (5) network calling library 
    compile 'com.squareup.retrofit2:retrofit:2.3.0'
    compile 'com.squareup.retrofit2:converter-gson:2.3.0'

    (6) recycler view
    implementation 'com.android.support:recyclerview-v7:26.1.0'
    
 (B) Add permissions to manifest
 
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    
 (C) Features
 
    User see the list of popular movies.
    Get the details of each movies.
    
 (D) Screen-shots
 
 ![alt text](E:\Project\company interview project/7d6435b2-7689-4e7a-b822-9fb298932469.jpg)
    




