The approach I used to solve this problem is through object oriented method. 
The first thing I did is I read the file line by line and scanned  for the key word " Driver" Once I find the word driver I add it as a driver to the system and check if the driver is already stored . 
Next thing I did is once the driver is stored in the system , I added the recent trip for each of the driver . 
While adding the trip for the driver, I keep in mind to keep track the total miles and the toal time for the entire trip therefore i created a variable in driver history to keep track of them. 
i used HashMap data structure to store the driver as key and its trip detail as the value so everytime I see a new trip I put it in hashMap with it's trip detail, if the driver is already present in the hashmap , I update the trip detail of the current driver. This can be done in O(1).
