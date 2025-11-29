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

 ![image alt](https://github.com/HappySaini001/Movie-Recommender/blob/9dd40b95f3bb22eed6a4be1f71f2d6529a2f6b28/Screenshot%202025-11-28%20020223.png)
 
 ![image alt](https://github.com/HappySaini001/Movie-Recommender/blob/9dd40b95f3bb22eed6a4be1f71f2d6529a2f6b28/Screenshot%202025-11-28%20020320.png)

 ![image alt](https://github.com/HappySaini001/Movie-Recommender/blob/9dd40b95f3bb22eed6a4be1f71f2d6529a2f6b28/Screenshot%202025-11-28%20020251.png)

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
  ![image alt](https://github.com/HappySaini001/Movie-Recommender/blob/987a6dcd028a7e2c7a7fcdbfe3768d8262dddf5f/Screenshot%202025-11-28%20020154.png)

4.Copy the API Key (v3 Auth)


# ▶️ How to Run the Project
# 1️⃣ Download or Clone the Repo
      git clone https://github.com/HappySaini001/Movie-Recommender.git
      ![image alt](https://github.com/HappySaini001/Movie-Recommender/blob/9dd40b95f3bb22eed6a4be1f71f2d6529a2f6b28/Screenshot%202025-11-28%20232848.png)

# 2️⃣ Open in IDE

Open the folder in NetBeans or IntelliJ.
 ![image alt](https://github.com/HappySaini001/Movie-Recommender/blob/0d407542257649b0f0fc22ccfb4b0e79ba40a1db/Screenshot%202025-11-28%20233507.png)

# 3️⃣ Add API Key

Locate this file:

src/main/resources/static/recommend.js


 'dcfde840ed4e3454377a875fc0f51207' 
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
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── happy/
│   │   │           └── movie/
│   │   │               └── recommender/
│   │   │                   ├── MovieRecommenderApplication.java  # Main entry point
│   │   │                   ├── controller/
│   │   │                   │   └── MovieController.java          # Web routes
│   │   │                   ├── model/                            # Database Entity
│   │   │                   │   └── Movie.java                    # (New)
│   │   │                   ├── repository/                       # Database Access
│   │   │                   │   └── MovieRepository.java          # (New)
│   │   │                   └── service/
│   │   │                       └── MovieService.java             # Logic & CSV Loader
│   │   └── resources/
│   │       ├── static/                                           # CSS/JS/Images
│   │       │   ├── style.css
│   │       │   ├── recommend.js
│   │       │   ├── autocomplete.js
│   │       │   └── loader.gif
│   │       ├── templates/                                        # HTML Views
│   │       │   ├── home.html
│   │       │   └── recommend.html
│   │       ├── application.properties                            # DB Config
│   │       └── main_data.csv                                     # Original Dataset
├── Dockerfile                                                    # For Render Deployment
├── pom.xml                                                       # Dependencies
└── README.md                                                     # Documentation


# 🧩 System Architecture
      
 ![image alt](https://github.com/HappySaini001/Movie-Recommender/blob/68a446425093606e390f0a98052b675fcc55f86a/Architecture.jpg)
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


# ⚙️ How to Run Locally
# 📌 Prerequisites

JDK 17+

NetBeans / IntelliJ / Eclipse

A TMDB API Key

Step 1: Clone the Repository
git clone https://github.com/HappySaini001/Movie-Recommender.git

Step 2: Configure the TMDB API Key

Open

src/main/resources/static/recommend.js


Find 'dcfde840ed4e3454377a875fc0f51207' (appears twice)

 Actual API key:

var my_api_key = 'dcfde840ed4e3454377a875fc0f51207';

Step 3: Build & Run the Application
# ▶️ Using NetBeans / IntelliJ

Open as Maven project

Right-click → Clean and Build

Right-click → Run

# ▶️ Using Terminal
mvn clean package
java -jar target/movie-recommender-0.0.1-SNAPSHOT.jar


Access App → http://localhost:8081

# ☁️ Deploying to Render (Docker)
Steps:

Fork/Clone the repository

Go to Render.com → Create Web Service

Connect your GitHub repo

Set:

Setting	Value
Runtime	Docker
Region	Singapore (recommended)
Plan	Free

Click Create Web Service

Render will auto-detect your Dockerfile and deploy.

# 🧠 Similarity Logic (How Recommendations Work)

This system uses Content-Based Filtering, operating on main_data.csv (5000+ movies).

For each movie, the algorithm compares:

# 🎬 Director

# ⭐ Top 3 cast members

# 📝 Genres & Keywords



