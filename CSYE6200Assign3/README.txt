## CSYE 6200 ASSIGNMENT 3
## Author by Jialin Chen 001466077
-----------------------------------------------------------------------------------------------
File Description:
Flower.java                (Flower class)
FlowerPlant.java           (FlowerPlant class extended from Plant class)
Plant.java                 (Plant class)
PlantTest.java             (Test class for assignment2b)
RegistryIO.java            (RegistryIO class to save and load data, test included)
Stem.java                  (Stem class)
Output_Assign3.txt         (Output file for Assignment3)

After Run:
plantdata.txt              (txt file for all plants)
flowerplantdata.txt        (txt file for all flowerplants)
------------------------------------------------------------------------------------------------
Instruction:
Please find all the java files under the pakcage(neu/edu/csye6200/csye6200/assign3) under src/.
Run the RegistryIO class to TEST all the fuctions.
Please refer the output txt file to check all the test results.

To the PlantTest class:
- I Added some changes to the PlantTest class, letting the childstem "actually" growing on the 
  fatherstems(Setting the X,Y of child stem based on father stems), so as to enable the calculation 
  of total height and width.
- Inside the PlantTest class, after removing some child stems, the total height and width changed.

To the FlowerPlant class:
- I created a new class--Flower, letting it having 'color' and 'petalnumber' member variables.
- Let 'flower' be a member variable of the flowerplant.

To the RegistryIO class:
- Let RegistryIO class having main method to directly test the save and load method.
- Seperately wrote save and load method for Plant and FlowerPlant classes.