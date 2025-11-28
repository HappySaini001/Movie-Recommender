# 🎬 Movie Recommender System (Java Spring Boot)

![Java-17+-orange](https://github.com/user-attachments/assets/5b648945-5b54-4a25-a40b-9840343f3d29)           ![Uploading Framework-Spring Boot-green.svg…](https://github.com/user-attachments/assets/db074d59-fb63-4be6-8ee4-2e3e84df94d5)             ![Uploading Build-Maven-blue.svg…](https://github.com/user-attachments/assets/50a0f1ad-715d-4d21-a5a5-1afe0c7e8a42)          ![Uploading Frontend-Thymeleaf_JS-yellow.svg…](https://github.com/user-attachments/assets/298fbd89-24a6-4c61-828e-f4e645768cf1)         ![API-TMDB-fcba03](https://github.com/user-attachments/assets/742f0c82-ba42-4b34-bc03-df3aec0c0656)


This application recommends movies similar to the one the user likes by analyzing metadata and fetching real-time details from the TMDB API.


# 🚀 Overview

The system uses Content-Based Filtering to recommend movies based on metadata such as cast, director, genres, keywords, and descriptions. It also supports movie search, auto-suggest, reviews scraping, and basic sentiment analysis.

# 🚀 Live Demo

🔗 View Live App on Render
https://movie-recommender-gqhn.onrender.com

# ✨ Key Features
# 🔧 Java Architecture

Completely rebuilt using Spring Boot 3 and Java 17

Structured MVC pattern with controllers, services, and utility classes

# 🎞️ Movie Recommendations

Fetches movie posters, ratings, cast details using TMDB API

Recommends movies using content-based similarity

# 🧠 Sentiment Analysis

Scrapes user reviews from IMDB using Jsoup

Classifies user sentiment as Positive or Negative

# 🔍 Search + Auto-Complete

Real-time suggestions using JavaScript & AJAX

# ☁️ Cloud Ready

Includes a Dockerfile for easy deployment to Render

Works on any cloud platform supporting Docker


# 🛠️ Technologies Used
# Backend

Java 17

Spring Boot (Web, Thymeleaf)

Apache Commons CSV

Jsoup (Web Scraping)

# Frontend

HTML, CSS, JavaScript

Thymeleaf Templates

# Dev Tools

1.Apache NetBeans / IntelliJ

2.Maven Build Tool

3.TMDB API (for Movie Details)


# 📦 Prerequisites

Make sure the following are installed:

1.Java JDK 17+

2.Maven

3.NetBeans / IntelliJ IDEA

4.TMDB API Key (v3)

# 🔑 How to Get TMDB API Key

1.Visit: https://www.themoviedb.org/

2.Sign up and go to Account Settings → API

3.Apply for an API key

4.Copy the API Key (v3 Auth)


# ▶️ How to Run the Project
# 1️⃣ Download or Clone the Repo
      git clone https://github.com/your-username/movie-recommender.git

# 2️⃣ Open in IDE

Open the folder in NetBeans or IntelliJ.

# 3️⃣ Add API Key

Locate this file:

src/main/resources/static/recommend.js


Replace 'YOUR_API_KEY' with your actual TMDB key (found on line 15 and 29).

# 4️⃣ Build the Project

Right-click → Clean and Build
This downloads all Maven dependencies.

# 5️⃣ Run the Application

Right-click → Run

Wait until console prints:

Started MovieRecommenderApplication

#  6️⃣ Open in Browser

Go to:

http://localhost:8081

# 📁 Project Structure
movie-recommender/
├── src/
│   ├── main/
│   │   ├── java/com/happy/movierecommender/
│   │   │   ├── MovieRecommenderApplication.java        # Main App Entry
│   │   │   ├── controller/MovieController.java         # Handles Web Routing
│   │   │   └── service/MovieService.java               # Logic + Scraping
│   │   └── resources/
│   │       ├── static/                                 # JS, CSS, Images
│   │       ├── templates/                              # Thymeleaf HTML
│   │       ├── main_data.csv                           # Movie Metadata
│   │       └── application.properties
└── pom.xml                                              # Maven Dependencies

# 📊 How Similarity Works

Uses metadata from main_data.csv

Processes fields like genres, cast, director, keywords

Computes similarity scores to recommend closest matches

# 📚 Datasets Used

IMDB 5000 Movie Dataset

The Movies Dataset (Kaggle)

List of American films (2018, 2019, 2020 – Wikipedia)

# 💡 Future Improvements

Add collaborative filtering

Improve sentiment analysis using ML models

Add user authentication

Add watchlist/favorite functionality






