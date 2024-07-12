package com.example.quizzify

class DataQnA {
    private lateinit var datalist : ArrayList<QnaModel>
    fun getData():ArrayList<QnaModel>{
        datalist = ArrayList()
        datalist.add(QnaModel("What is the largest lake in the world?", "Caspian Sea", "Baikal", "Lake Superior", "Ontario", "Baikal"))
        datalist.add(QnaModel("Which planet in the solar system is known as the \"Red Planet\"?", "Venus", "Earth", "Mars", "Jupiter", "Mars"))
        datalist.add(QnaModel("What is the capital of Japan?", "Beijing", "Tokyo", "Seoul", "Bangkok", "Tokyo"))
        datalist.add(QnaModel("Which river is the longest in the world?", "Amazon", "Mississippi", "Nile", "Yangtze", "Nile"))
        datalist.add(QnaModel("Which is the largest coffee-producing state of India?", "Kerala", "Tamil Nadu", "Karnataka", " Arunachal Pradesh", "Karnataka"))
        datalist.add(QnaModel("What gas is used to extinguish fires?", "Oxygen", "Nitrogen", "Carbon dioxide", "Hydrogen", "Nitrogen"))
        datalist.add(QnaModel("What animal is the national symbol of Australia?", "Kangaroo", "Koala", "Emu", "Crocodile", "Kangaroo"))
        datalist.add(QnaModel("Which is the largest island?", "New Guinea", "Andaman Nicobar", "Greenland", "Hawaii", "Greenland"))
        datalist.add(QnaModel("Which one is the hottest continent?", "Africa", "South Asia", "North America", "Australia", "Africa"))
        datalist.add(QnaModel("Which of the following is the capital of Arunachal Pradesh?", "Itanagar", "Dispur", "Imphal", "Panaji", "Itanagar"))

        return datalist
    }
}