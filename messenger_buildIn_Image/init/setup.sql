CREATE DATABASE IF NOT EXISTS messengerdb;
USE messengerdb;
CREATE TABLE IF NOT EXISTS messages(messageId int AUTO_INCREMENT PRIMARY KEY, messageInfo varchar(255), author varchar(255),date DATETIME);