-- =====================================================================
-- CREATE DATABASE TABLES
-- =====================================================================

CREATE TABLE dictionary
(dictionary_id int PRIMARY KEY AUTO_INCREMENT,
 welsh_word VARCHAR(255) NOT NULL,
 english_word VARCHAR(255) NOT NULL,
 gender VARCHAR(255) NOT NULL);

CREATE TABLE users
(user_id INT PRIMARY KEY AUTO_INCREMENT,
 username VARCHAR(255) NOT NULL UNIQUE,
 passwords VARCHAR(255) NOT NULL,
 role VARCHAR(255) NOT NULL);

CREATE TABLE submissions
(submission_id INT PRIMARY KEY AUTO_INCREMENT,
 user_id INT NOT NULL,
 creation_date DATETIME NOT NULL,
 score INT NOT NULL,
 FOREIGN KEY (user_id) REFERENCES users(user_id) ON UPDATE CASCADE ON DELETE CASCADE);
 
CREATE TABLE results
(
    results_id INT PRIMARY KEY AUTO_INCREMENT,
    submission_id INT NOT NULL,
    dictionary_id INT NOT NULL,
    question_type INT NOT NULL,
    user_answer VARCHAR(255),
    correct_answer VARCHAR(255),
    FOREIGN KEY (submission_id) REFERENCES submissions(submission_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (dictionary_id) REFERENCES dictionary(dictionary_id) ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE sessions
(session_id VARCHAR(255) PRIMARY KEY,
 user_id INT NOT NULL,
 FOREIGN KEY (user_id) REFERENCES users(user_id) ON UPDATE CASCADE ON DELETE CASCADE);

