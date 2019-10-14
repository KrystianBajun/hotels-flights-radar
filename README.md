# flights-hotels-app

<center>
  <img width="460" height="300" src="https://i.imgur.com/C9IvdBD.png">
<center>
<h2 style="text-align: center" align="middle"> Web Flight System </h2>
<h4 style="text-align: center" align="middle"> Low-cost Hotels and Flights search engine</h4>

## Overview

A web application is dedicated for users traveling in the cheapest possible way.
Containing inter alia :
- the ability to search and choose the cheapest flights and hotels
- saving information about hotels and air connections to the database
- mailing - newsletter, contact, booking evaluation
- booking management
- roles on the site (administrator, moderator, user)
- social media elements: friends, adding posts, sending messages, profile page, notifications
- commenting and rating travel, hotel ranking - dynamic update
- blog
- real time api exchange rate 

## Stack

* Java
* Spring
* Hibernate
* Thymeleaf
* Bootstrap
* Maven
* H2 Database

### To do

- [ ] Dockerizing 


## Main Page

 - Real time notifications. 
 - Top three hotels based on ranking. 
 - Blog section - three articles.
 
 
<center>
  <img src="https://i.imgur.com/4wvdhoN.jpg"  alt="Github">
</center>

## Search engine 

Five APIs are connected to the application :

- the first one checks air connections
- the second is responsible for providing information about hotels
- the third one searches additional information in google for example overall rating and hotel photos
- the fourth searches for information on historical flight prices
- the app constantly updates the exchange rate so provide the real price of the trip


<center>
  <img src="https://i.imgur.com/5i5Fbrc.jpg"  alt="search-bar">
</center>

## List of the cheapest flights.


<center>
  <img src="https://i.imgur.com/TYmcSoa.png"  alt="flight-list">
</center>

## List of the cheapest hotels.


<center>
  <img src="https://i.imgur.com/MDrSWcn.png"  alt="hotel-list">
</center>

## The ability to manage travels


<center>
  <img src="https://i.imgur.com/23SLuAC.png" alt="manage">
</center>

## Trip summary

- details about reservation 


<center>
  <img src="https://i.imgur.com/sHytzS8.png" alt="summary">
</center>

## Detailed price information

- the app calculates detailed price information
- based on information provided by historical Api data
- graph shows inter alia: minimal, highest, median, and average price from last two years.

<center>
  <img src="https://i.imgur.com/xiZUbSN.png" alt="graphs">
</center>

## Payment simulation
- users can pay for reservation 

<center>
  <img src="https://i.imgur.com/1zHhTuf.png" alt="payment">
</center>

## Confirmation

- reservation confirmation in form of pdf document
- each have unique bar code


<center>
  <img src="https://i.imgur.com/SirqUQu.png" alt="conf-1">
</center>
<center>
  <img src="https://i.imgur.com/Ixae6dq.png" alt="conf-2">
</center>



## Hotel ranking

- based on votes. 
- only users who have chosen and paid for the accommodation can rate. 


<center>
  <img src="https://i.imgur.com/11ZLk49.png"  alt="hotel-ranking">
</center>

- on the last day of stay, on the user email adress is sent an email 
- contains a request for rate
- based on the ratings, the total rating of the hotel is calculated

<center>
  <img src="https://i.imgur.com/qHutqAo.png"  alt="rank-email">
</center>



## Hotel info

- detailed information about the selected hotel.
- below are comments posted by logged users.


<center> 
  <img src="https://i.imgur.com/a5G7yGm.png"  alt="hotel-info">
</center>

## Friends system

- invite to friends.


<center> 
  <img src="https://i.imgur.com/uJua0u5.png" alt="invite">
</center>
<center> 
  <img src="https://i.imgur.com/IBWCf6j.png" alt="conf">
</center>

- notifications system


<center> 
  <img src="https://i.imgur.com/92El6Fq.png"  alt="notifications">
</center>

- invitations list 


<center> 
  <img src="https://i.imgur.com/2rhyWu5.png"  alt="invitations-list">
</center>

## User profile

- log-in / create new user


<center> 
  <img src="https://i.imgur.com/2JY6067.png"  alt="profile">
</center>
<center> 
  <img src="https://i.imgur.com/EVamDoP.png"  alt="profile">
</center>


each user can:
- add posts
- edit profile information
- change photos


<center> 
  <img src="https://i.imgur.com/myYwRbh.png"  alt="profile">
</center>

- edit


<center> 
  <img src="https://i.imgur.com/vyWt8AO.png"  alt="profile-edit">
</center>

- friends list


<center> 
  <img src="https://i.imgur.com/oRIoNHc.png"  alt="profile-edit">
</center>

## Contact form


<center>
  <img src="https://i.imgur.com/H0R9v9w.png" alt="contact-form">
  
</center>

## Chat 

- sending / receiving messages
- only possible with users who are friends


<center> 
  <img src="https://i.imgur.com/HnkuUjR.png"  alt="profile-edit">
</center>

## Admin

- sending e-mail newsletter messages to users from database


<center> 
  <img src="https://i.imgur.com/3NRi3GZ.png"  alt="profile-edit">
</center>

- user management, deleting, adding new ones


<center> 
  <img src="https://i.imgur.com/bMb1UB5.png"  alt="profile-edit">
</center>

## Blog 

- blog articles


<center> 
  <img src="https://i.imgur.com/UlVYxSB.png"  alt="blog-article">
</center>

## Moderator

- article management
- three articles on the main page
- adding post, deleting, editing, adding photos


<center> 
  <img src="https://i.imgur.com/QxpBusK.png"  alt="moderator">
</center>

- add new article


<center> 
  <img src="https://i.imgur.com/5Pns1Av.png"  alt="add-article">
</center>

- edition of the current article


<center> 
  <img src="https://i.imgur.com/JbKAb4U.png"  alt="edit-article">
</center>

- upload photos

<center> 
  <img src="https://i.imgur.com/lV3wYJV.png"  alt="upload-photo">
</center>

## Email System

- The application is connected to a Google email account.
- Messages sent by users come to this email address
- newsletter and messages are sent via this address


<center> 
  <img src="https://i.imgur.com/CryIdCW.png"  alt="email-sys">
</center>





