/**
Author: Quentin Flanders
Purpose: Make a program to simulate gaining glamour as a changeling on Cajun Nights.
*/

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Random;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ChangelingHarvesting
{
   public static void main(String[] args) throws IOException
   {
      //Declare an integer array to store constant integer values for calulation.
      int[] constants = new int[6];
      
      //List relevant constants.
      final int AVG_DREAMER_CONTEST = 4;
      final int SEELIE_REVERIIE_DC = 2;
      final int RAPTURE_DC = 3;
      final int SEELIE_RAVAGE_DC = 3;
      final int MAX_GLAMOUR_PER_HARVEST = 2;
      final int MAX_DROSS_PER_HARVEST = 6;
      
      //Store the values of constants for calculations.
      constants[0] = AVG_DREAMER_CONTEST;
      constants[1] = SEELIE_REVERIIE_DC;
      constants[2] = RAPTURE_DC;
      constants[3] = SEELIE_RAVAGE_DC;
      constants[4] = MAX_GLAMOUR_PER_HARVEST;
      constants[5] = MAX_DROSS_PER_HARVEST;
      
      //Declare an integer to decide how many test rolls should be made to determine a changeling's average result.
      int avgResultChecks = 100000;
      
      //Declare a boolean to decide if changelings should use arts to harvest glamour.
      boolean useArts = true;
      
      //Declare a list of relevant values for changeling glamour harvesting rolls.
      //Declare a changeling.
      Changeling Testling = new Changeling();
      
      //Declare and initalize a Random object to produce random numbers.
      Random randomNumber = new Random();
      
      //Declare and initialize a String object describing the csv file you want to pull data from. This can be changed later.
      String fileName = "changelingExamples.csv";
      
      //Create an ArrayList to store Changeling objects.
      ArrayList<Changeling> changelings = new ArrayList<Changeling>();
      
      //Fill the ArrayList with data from the declared file.
      readAndFill(fileName,changelings,avgResultChecks,useArts,randomNumber);
      
      //Print out the first Changeling.
      System.out.println(changelings.get(0));
      
      //Find the first Changeling's average wits+insight roll.
      //System.out.println(avgWitsInsight(changelings.get(0),avgResultChecks,randomNumber) + " successes on wits+insight avg.");
      
      //Print out the first Changeling's reverie harvest for a week.
      //System.out.println(reverieHarvest(changelings.get(0), avgResultChecks, randomNumber));
      
      //Find the average glamour the first Changeling harvests from reveries.
      //System.out.println("Avg Reverie: " + avgReverieHarvest(changelings.get(0), avgResultChecks, useArts, randomNumber));
      
      //
      //System.out.println("Avg Rapture: " + avgRaptureHarvest(changelings.get(0),avgResultChecks, useArts, randomNumber));
      
      //
      //System.out.println("Avg Ravage: " + avgRavageHarvest(changelings.get(0),avgResultChecks, useArts, randomNumber));
      
      //Single reverie roll.
      //System.out.println("Reverie: " + reverieHarvest(changelings.get(0),avgResultChecks, useArts, randomNumber));
      
      //Single rapture roll.
      //System.out.println("Rapture: " + raptureHarvest(changelings.get(0),avgResultChecks, useArts, randomNumber));
      
      //The first Changeling's roll for 4 weeks.
      //System.out.println("Average harvest for 4 weeks: " + weekHarvest(changelings.get(0), avgResultChecks, 4, constants, useArts, randomNumber));
      
      //Run user interface.
      byUserEntry(changelings, avgResultChecks, 4, constants, useArts);
      
   }//End of main.

   /**
   Read and store data from a csv file in an ArrayList when given the name of a csv file.
   @fileName         String                  -  The name of the file you want to import.
   @changelings      ArrayList<Changeling>   -  An ArrayList for Changeling objects where data will be stored.
   @avgResultChecks  int                     -  How many test rolls should be made to determine a changeling's average result.
   @useArts          boolean                 -  True if the changeling should use their arts to harvest glamour.
   @randomNumber     Random                  -  From the random utility. This is the name of the Random object created in the main program.
   */
   public static void readAndFill(String fileName,ArrayList<Changeling> changelings, int avgResultChecks, boolean useArts, Random randomNumber) throws IOException
   {
      //Check if the file name is valid. If so, create a scanner. If not, tell the user so and end the program.
      Scanner changelingScan = createScannerFromString(fileName);
      
      //Toss the first line.
      changelingScan.nextLine();
      
      //Create and initialize variables to temporarily store values as they are tokenized.
      String changelingName = "";
      String changelingKith = "";
      String changelingHouse = "";
      boolean isUnseelie = true;
      int cha = 0;
      int com = 0;
      int man = 0;
      int res = 0;
      int wit = 0;
      int insight = 0;
      int bestReveriePool = 0;
      int bestRapturePool = 0;
      int dreamerDots = 0;
      int sovereignDots = 0;
      int summerDots = 0;
      int taleCraftDots = 0;
      int banality = 0;
      int glamour = 4;
      int dross = 0;
      int expSpent =0;
      
      //Create four temporary objects to store each line as they are read.
      String tempLine = "";
      String tempUnseelie = "";
      StringTokenizer tempTokenizer;
      Changeling tempChangeling;
      
      //Run a loop to process information from the data file until it has been read in full.
      while (changelingScan.hasNext())
      {
         //Get the next line and store it as a String.
         tempLine = changelingScan.nextLine();
         
         //Create a StringTokenizer, pass in the line which was read, and use "," as the delimiter.
         tempTokenizer = new StringTokenizer(tempLine,",");
         
         //Read through tempLine, passing data into the appropriate variables as they are tokenized.
         changelingName = tempTokenizer.nextToken();
         changelingKith = tempTokenizer.nextToken();
         changelingHouse = tempTokenizer.nextToken();
         tempUnseelie = tempTokenizer.nextToken();
         cha = Integer.parseInt(tempTokenizer.nextToken());
         com = Integer.parseInt(tempTokenizer.nextToken());
         man = Integer.parseInt(tempTokenizer.nextToken());
         res = Integer.parseInt(tempTokenizer.nextToken());
         wit = Integer.parseInt(tempTokenizer.nextToken());
         insight = Integer.parseInt(tempTokenizer.nextToken());
         bestReveriePool = Integer.parseInt(tempTokenizer.nextToken());
         bestRapturePool = Integer.parseInt(tempTokenizer.nextToken());
         dreamerDots = Integer.parseInt(tempTokenizer.nextToken());
         sovereignDots = Integer.parseInt(tempTokenizer.nextToken());
         summerDots = Integer.parseInt(tempTokenizer.nextToken());
         taleCraftDots = Integer.parseInt(tempTokenizer.nextToken());
         banality = Integer.parseInt(tempTokenizer.nextToken());
         glamour = Integer.parseInt(tempTokenizer.nextToken());
         dross = Integer.parseInt(tempTokenizer.nextToken());
         expSpent = Integer.parseInt(tempTokenizer.nextToken());
         
         //Translate the Changeling's court to a boolean value.
         if(tempUnseelie.equals("Seelie"))
            isUnseelie = false;
         
         //Instantiate a Changeling object using the tokenized data.
         tempChangeling = new Changeling(changelingName, changelingKith, changelingHouse, isUnseelie, cha, com, man, res, wit, insight, bestReveriePool, bestRapturePool, dreamerDots, sovereignDots, summerDots, taleCraftDots, banality, glamour, dross, expSpent);
         
         //Set the new Changeling object's average wits + insight roll.
         tempChangeling.setAvgWitInsight(avgWitsInsight(tempChangeling,avgResultChecks,randomNumber));
         
         //Set the Changeling's average reverie harvest.
         tempChangeling.setAvgReverieHarvest(avgReverieHarvest(tempChangeling, avgResultChecks, useArts, randomNumber));
         
         //Set the Changeling's average rapture harvest.
         tempChangeling.setAvgRaptureHarvest(avgRaptureHarvest(tempChangeling, avgResultChecks, useArts, randomNumber));
         
         //Set the Changeling's average ravage harvest.
         tempChangeling.setAvgRavagingHarvest(avgRavageHarvest(tempChangeling, avgResultChecks, useArts, randomNumber));
         
         //Add the new Changeling object to the ArrayList.
         changelings.add(tempChangeling);
      }//End of while loop.
      
      //Close the scanner.
      changelingScan.close();
   }//End of readAndFill.
   
   /**
   Allow the user to interact with the data in an ArrayList for Changeling objects.
   @changelings      ArrayList<Changeling>   - A filled ArrayList for Changeling objects.
   @avgResultChecks  int                     -  How many test rolls should be made to determine a Changeling's average result.
   @weeksSimulated   int                     -  The number of weeks to simulate a changeling going through.
   @constants        int[]                   -  An array of integers containing constant values for calculations.
   @useArts          boolean                 -  True if the changeling should use their arts to harvest glamour.
   */
   public static void byUserEntry(ArrayList<Changeling> changelings, int avgResultChecks, int weeksSimulated, int[] constants, boolean useArts)
   {
      //Declare and initialize variables to keep track of user requests.
      int userChoice = 0;
      String userText = "";
      boolean invalidInput = true;
      
      //Create a Random object to generate random numbers.
      Random randomNumber = new Random();
      
      //Create a scanner to recieve user input from the keyboard.
      Scanner keyBoard = createKeyBoardScanner();
      
      //Run a do-while loop until the user decides to quit.
      do{
         //Ask the user how they want to interact with the data in the ArrayList, and present them with options.
         System.out.println("What information would you like on these Changelings?");
         System.out.printf("1: %s\n2: %s\n3: %s\n4: %s\n5: %s\n6: %s\n","Get List of Best Glamour Harvesters","Display the  Changelings","Show Me the Winner","Display a Specific Changeling", "Display Scores Above a Certain Score","Nothing (Exit the program.)");
      
         //Store the user's response, then run it through a switch.
         userChoice = keyBoard.nextInt();
      
         switch(userChoice)
         {
            case 1:  displayBestHarvester(changelings, avgResultChecks, weeksSimulated, constants, useArts, randomNumber);
                     break;
            case 2:  System.out.println("Case 2");
                     break;
            case 3:  System.out.println("Case 3");
                     break;
            case 4:  displayChangeling(changelings,keyBoard);
                     break;
            case 5:  System.out.println("Case 5");
                     break;
            default: //If the user did not enter 6, ask if they want to close the program.
                     if(!(userChoice == 6))
                     {
                        //Loop until the user provides valid input.
                        do{
                           System.out.println("You didn't enter a number on the list. Do you want to exit the program? (Please enter yes or no.)");
                           userText = keyBoard.next();
                           if(userText.equalsIgnoreCase("yes") || userText.equalsIgnoreCase("y") || userText.equalsIgnoreCase("ye") )
                           {
                              invalidInput = false;
                              userChoice = 6;
                           }//End of inner if.
                           else if(userText.equalsIgnoreCase("no") || userText.equalsIgnoreCase("n"))
                           {
                              invalidInput = false;
                           }//End of inner else-if.
                        }while(invalidInput);
                        //Reset the invalid input to true in case the user doesn't enter a number on the list again.
                        invalidInput = true;
                     }//End of if.
                     break;
         }//End of switch.
      }while(!(userChoice == 6));
      
      //Tell the user the program is closing.
      System.out.println("Closing the program. Thank you.");
      
      //Close the keyboard scanner.
      keyBoard.close();
      
   }//End of byUserEntry.
   
   //-----Request Data-----
   
   /**
   Determine which Changeling harvests the most glamour, then list them in order from most to least.
   @changelings      ArrayList<Changeling>   - A filled ArrayList for Changeling objects.
   @avgResultChecks  int                     -  How many test rolls should be made to determine a Changeling's average result.
   @weeksSimulated   int                     -  The number of weeks to simulate a changeling going through.
   @constants        int[]                   -  An array of integers containing constant values for calculations.
   @useArts          boolean                 -  True if the changeling should use their arts to harvest glamour.
   @randomNumber     Random                  -  From the random utility. This is the name of the Random object created in the main program.
   */
   public static void displayBestHarvester(ArrayList<Changeling> changelings, int avgResultChecks, int weeksSimulated, int[] constants, boolean useArts, Random randomNumber)
   {
      //Declare and initalize an integer to store the length of the ArrayList.
      int changelingsCount = changelings.size();
      
      //Declare a parallel arrays to keep track of how much glamour a Changeling harvests on average.
      String[] names = new String[changelingsCount+1];
      int[] avgHarvests = new int[changelingsCount+1];
      int[] expSpent = new int[changelingsCount+1];
      
      //Other values.
      Changeling tempChangling;
      String currentName = "";
      int currentHarvest = 0;
      int currentEXP = 0;
      int j = 0;
      
      //Print the head of the table.
      System.out.printf("%-15s | %-25s | %-9s\n","Changeling Name","Average Glamour Harvested","EXP Spent");
      System.out.println("-------------------------------------------------------");
      
      //Order the Changelings depending on which is the best at harvesting glamour, keeping any relevant data.
      for(int i = 0; i < changelingsCount; i++)
      {
         //Set j to 0.
         j = 0;
         
         //Store the Changeling object in a temporary value.
         tempChangling = changelings.get(i);
         
         //Get the Changeling's name.
         currentName = tempChangling.getName();
         
         //Store the current Changeling's average harvest.
         currentHarvest = weekHarvest(tempChangling, avgResultChecks, weeksSimulated, constants, useArts, randomNumber);
         
         //Get the Changeling's spent EXP.
         currentEXP = tempChangling.getEXP();
         
         //Find where the new Changeling goes in the order.
         while(avgHarvests[j] > currentHarvest)
         {
            //Increment j.
            j++;
         }//End of while.
         
         //Once the position has been found, move Changelings in and after it out of the way.
         for(int k = changelingsCount-1; k >= j; k--)
         {
            //Code for testing.
            //System.out.println("Insert " + avgHarvests[k] + " at position " + (k+1) + ".");
            
            //Move all relevant arrays out of the way.
            avgHarvests[k+1] = avgHarvests[k];
            names[k+1] = names[k];
            expSpent[k+1] = expSpent[k];
         }//End of for loop.
         
         //Once other elements have been shifted out of the way, add the correct position.
         avgHarvests[j] = currentHarvest;
         names[j] = currentName;
         expSpent[j] = currentEXP;
         
         //Code for testing.
         //System.out.println("Current best: " + avgHarvests[0]);
      }//End of for loop.
      
      for(int i = 0; i < changelingsCount; i++)
      {
         System.out.printf("%-15s | %-25s | %-9s\n",names[i],(avgHarvests[i] + " Glamour"),expSpent[i]);
      }//End of for.
      
      //Print an additional line for readability.
      System.out.println("");
   }//End of displayAllData.
   
   
   /**
   Display information on a specific Changeling.
   @changelings   ArrayList<Changeling>   -  A filled ArrayList for Changeling objects.
   @keyBoard      Scanner                 -  A scanner to accept input from a keyboard.       
   */
   public static void displayChangeling(ArrayList<Changeling> changelings, Scanner keyBoard)
   {
      //Create and initialize a String object to temporarily store user input.
      String userSearch = "";
      
      //Create a boolean to check if the user's value is found.
      boolean isNotFound = true;
      
      //Create and initialize an integer representing the size of the ArrayList to avoid calling the size() method multiple times.
      int changelingsSize = changelings.size();
      
      //Create and initialize a String object to temporarily the diver name of a given Diver object.
      String tempChangelingName = "";
      
      //Create a Dive object to temporarily store the address of Dive objects to speed up the program.
      Changeling tempChangeling;
      
      //Prompt the user for a diver's name.
      System.out.println("Enter the name of the Changeling you want to see information on.");
      
      //If the user's opportunity to respond was filled by a blank response already in the scanner, get the user's response again.
      userSearch = checkIfInputIsEmpty(keyBoard.nextLine(),keyBoard);
      
      //Traverse the length of the ArrayList.
      for(int changeling = 0; changeling < changelingsSize; changeling++)
      {
         //Get the address of the next Changeling object.
         tempChangeling = changelings.get(changeling);
         
         //Get the name of the current Changeling object.
         tempChangelingName = tempChangeling.getName();
         
         //If the Changeling's name is partially or fully found, print out information on them.
         if(tempChangelingName.toLowerCase().contains(userSearch.toLowerCase()))
         {
            isNotFound = false;
            System.out.println(tempChangeling);
         }//End of if.
         
      }//End of for loop.
      
      //If the user's entry has no matches, display so.
      if(isNotFound)
          System.out.println("Unfortunately, we couldn't find any Changelings with that name.");
      
      //Print an additional line for readability.
      System.out.println("");
   }//End of displayChangeling.
   
   //-----Simulate Rolls-----
   
   /**
   Simulate rolls to determine a changeling's average wits + insight roll.
   @dieRoller        Changeling  -  The Changeling object performing a Reverie.
   @avgResultChecks  int         -  How many test rolls should be made to determine a changeling's average result.
   @randomNumber     Random      -  From the random utility. This is the name of the Random object created in the main program.
   @return           avgRoll     -  The average wits + insight roll of a changeling.
   */
   public static int avgWitsInsight(Changeling dieRoller, int avgResultChecks, Random randomNumber)
   {
      //Declare integers to store the results of a Changeling's rolls.
      int totalRoll = 0;
      int avgRoll = 0;
      
      //Store the number of dice a changeling rolls for wits+insight rolls as an integer.
      int witsInsight = dieRoller.witsInsight();
      
      for(int i = 0; avgResultChecks > i; i++)
      {
         totalRoll += dieRoll(witsInsight,randomNumber);
      }//End of for loop.
      
      //Get the average result of the changeling's roll.
      avgRoll = totalRoll/avgResultChecks;
      
      //Return the result.
      return avgRoll;
   }//End of avgWitsInsight.
   
   /**
   Simulate rolls to determine a Changeling's average harvested glamour from reverie.
   @dieRoller        Changeling  -  The Changeling object performing a reverie.
   @avgResultChecks  int         -  How many test rolls should be made to determine a changeling's average result.
   @useArts          boolean     -  True if the changeling should use their arts to harvest glamour.
   @randomNumber     Random      -  From the random utility. This is the name of the Random object created in the main program.
   @return           avgRoll     -  The average glamour harvested by a Changeling in a single reverie.
   */
   public static int avgReverieHarvest(Changeling dieRoller, int avgResultChecks, boolean useArts, Random randomNumber)
   {
      //Declare integers to store the results of a Changeling's rolls.
      int totalRoll = 0;
      int avgRoll = 0;
      
      for(int i = 0; avgResultChecks > i; i++)
      {
         totalRoll += reverieHarvest(dieRoller, avgResultChecks, useArts, randomNumber);
      }//End of for loop.
      
      //Get the average result of the changeling's roll.
      avgRoll = totalRoll/avgResultChecks;
      
      //Return the result.
      return avgRoll;
   }//End of avgWitsInsight.
   
   /**
   Simulate rolls to determine a Changeling's average harvested glamour from rapture.
   @dieRoller        Changeling  -  The Changeling object performing a rapture.
   @avgResultChecks  int         -  How many test rolls should be made to determine a changeling's average result.
   @useArts          boolean     -  True if the changeling should use their arts to harvest glamour.
   @randomNumber     Random      -  From the random utility. This is the name of the Random object created in the main program.
   @return           avgRoll     -  The average glamour harvested by a Changeling in a single rapture.
   */
   public static int avgRaptureHarvest(Changeling dieRoller, int avgResultChecks, boolean useArts, Random randomNumber)
   {
      //Declare integers to store the results of a Changeling's rolls.
      int totalRoll = 0;
      int avgRoll = 0;
      
      for(int i = 0; avgResultChecks > i; i++)
      {
         totalRoll += raptureHarvest(dieRoller, avgResultChecks, useArts, randomNumber);
      }//End of for loop.
      
      //Get the average result of the changeling's roll.
      avgRoll = totalRoll/avgResultChecks;
      
      //Return the result.
      return avgRoll;
   }//End of avgRaptureHarvest.
   
   //-----Glamour Gathering Methods-----
   
   /**
   Simulate rolls to determine a Changeling's average harvested glamour from ravaging.
   @dieRoller        Changeling  -  The Changeling object ravaging a dreamer.
   @avgResultChecks  int         -  How many test rolls should be made to determine a changeling's average result.
   @useArts          boolean     -  True if the changeling should use their arts to harvest glamour.
   @randomNumber     Random      -  From the random utility. This is the name of the Random object created in the main program.
   @return           avgRoll     -  The average glamour harvested by a Changeling over 1 ravage.
   */
   public static int avgRavageHarvest(Changeling dieRoller, int avgResultChecks, boolean useArts, Random randomNumber)
   {
      //Declare integers to store the results of a Changeling's rolls.
      int totalRoll = 0;
      int avgRoll = 0;
      
      for(int i = 0; avgResultChecks > i; i++)
      {
         totalRoll += ravageHarvest(dieRoller, avgResultChecks, useArts, randomNumber);
      }//End of for loop.
      
      //Get the average result of the changeling's roll.
      avgRoll = totalRoll/avgResultChecks;
      
      //Return the result.
      return avgRoll;
   }//End of avgWitsInsight.
   
   /**
   Simulate a Changeling gathering glamour from Ravaging.
   @ravaging         Changeling        -  The Changeling object performing a Ravaging.
   @avgResultChecks  int               -  How many test rolls should be made to determine a changeling's average result.
   @useArts          boolean           -  True if the changeling should use their arts to harvest glamour.
   @randomNumber     Random            -  From the random utility. This is the name of the Random object created in the main program.
   @return           glamourHarvested  -  An integer representing the total glamour harvested while performing a Ravaging.
   */
   public static int ravageHarvest(Changeling ravaging, int avgResultChecks, boolean useArts, Random randomNumber)
   {
      //Store the number of dice a changeling rolls as integers.
      int witsInsight = ravaging.witsInsight();
      int ravageRoll = ravaging.ravageRoll();
      
      //Declare and initalize integers to track gained glamour and banality.
      int glamourHarvested = 0;
      int banalityGained = 0;
      
      //Declare and initalize an integer to store the number of successes a Changeling rolls to ravage a dreamer.
      int ravageSuccesses = 0;
      
      //Determine the Changeling's DC to ravage a dreamer.
      int ravageDC = 3;
      
      if(ravaging.getUnseelie())
      {
         //System.out.println("They're unseelie.");
         ravageDC = ravageDC - 1;
      }//End of if.
      
      //Declare and initalize a value to see successes on banality checks.
      int checkStatus = -1;
      
      //Roll wits+insight on the dreamer at difficulty 3.
      if(dieRoll(witsInsight, randomNumber) >= 3)
      {
         //If the wits+insight roll succeeds, the changeling can attempt to harvest.
         ravageSuccesses = dieRoll(ravageRoll, randomNumber);
         
         //If the ravaging succeeded, they gain the margin in glamour. Add 1 for ravaging threshold.
         if(ravageSuccesses >= ravageDC)
         {
            glamourHarvested = ravageSuccesses - ravageDC + 1;
         }//End of inner if.
         //If the changeling botches, they roll 2 banality checks and gain 1 banality.
         else if(ravageSuccesses == 0)
         {
            //Gain a banality.
            banalityGained++;
            
            //Make two banality checks.
            for(int i = 0; i < 2; i++)
            {
               //Roll one die.
               checkStatus = dieRoll(1, randomNumber);
               
               //Compare the die.
               if(checkStatus < 1)
               {
                  banalityGained++;
               }//End of inner if.
            }//End of for loop.
         }//End of inner else if.
         //If the changeling fails their harvest roll, they gain 1 banality.
         else
         {
            //Gain a banality
            banalityGained++;
         }//End of inner else.
      }//End of if.
      
      //Subtract the banality gained from the glamour harvested.
      glamourHarvested = glamourHarvested - banalityGained;
      
      //Return the amount of glamour the Changeling harvested.
      return glamourHarvested;
   }//End of ravageHarvest.
   
   /**
   Simulate a Changeling gathering glamour from Reverie.
   @ravaging         Changeling        -  The Changeling object performing a Reverie.
   @avgResultChecks  int               -  How many test rolls should be made to determine a Changeling's average result.
   @useArts          boolean           -  True if the changeling should use their arts to harvest glamour.
   @randomNumber     Random            -  From the random utility. This is the name of the Random object created in the main program.
   @return           glamourHarvested  -  An integer representing the total glamour harvested while performing a Reverie.
   */
   public static int reverieHarvest(Changeling reverie, int avgResultChecks, boolean useArts, Random randomNumber)
   {
      //Store the number of dice a changeling rolls as integers.
      int witsInsight = reverie.witsInsight();
      int harvestRoll = reverie.reverieRoll();
      
      //Create integers to track the amount of glamour the changeling has harvested.
      int glamourHarvested = 0;
      int banalityGained = 0;
      int rollResult = 0;
      
      //Determine the DC for this Changeling's reverie.
      int reverieDC = 2;
      if(reverie.getUnseelie())
      {
         reverieDC = 3;
      }//End of if.
      
      //Get the information needed to have the Changeling roll their Summer and Sovereign, and Tale Craft arts.
      int summerDots = reverie.getSummerDots();
      int sovereignDots = reverie.getSovereignDots();
      int taleCraftDots = reverie.getTaleCraftDots();
      int aphrodisiaBonus = 0;
      int grandeurBonus = 0;
      int workTheRoomBonus = 0;
      
      //Check if the Summer 3 art can be used.
      if(summerDots >= 3 && useArts)
      {
         //Get the information needed to have the Changeling roll their Summer art.
         int aphrodisiaRoll = summerDots + reverie.getCha();
         int dreamerContest = reverie.AVG_DREAMER_CONTEST;
         
         //Make a banality check.
         if(dieRoll(1, randomNumber) < 1)
         {
            banalityGained++;
         }//End of inner if.
         
         //If it can be rolled, have the mortal contest.
         if(dieRoll(aphrodisiaRoll, randomNumber) > dieRoll(dreamerContest, randomNumber))
         {
            //If the changeling succeeds, add their aphrodisia bonus.
            aphrodisiaBonus = summerDots;
         }//End of inner if.
      }//End of if.
      
      //Check if the Sovereign 1 art can be used.
      if(sovereignDots >= 3 && useArts)
      {
         //Get the information needed to have the Changeling roll their Sovereign art.
         int grandeurRoll = sovereignDots + reverie.getMan();
         int dreamerContest = reverie.AVG_DREAMER_CONTEST;
         
         //Make a banality check.
         if(dieRoll(1, randomNumber) < 1)
         {
            banalityGained++;
         }//End of inner if.
         
         //If it can be rolled, have the mortal contest.
         if(dieRoll(grandeurRoll, randomNumber) > dieRoll(dreamerContest, randomNumber))
         {
            //If the changeling succeeds, add their grandeur bonus.
            grandeurBonus = sovereignDots;
         }//End of inner if.
      }//End of if.
      
      //Check if the Tale Craft 2 art can be used.
      if(taleCraftDots >= 2 && useArts)
      {
         //Get the information needed to have the Changeling roll their Tale Craft art.
         int workTheRoomRoll = taleCraftDots + reverie.getMan();
         int dreamerContest = reverie.AVG_DREAMER_CONTEST;
         
         //Make a banality check.
         if(dieRoll(1, randomNumber) < 1)
         {
            banalityGained++;
         }//End of inner if.
         
         //If it can be rolled, have the mortal contest.
         if(dieRoll(workTheRoomRoll, randomNumber) > dieRoll(dreamerContest, randomNumber))
         {
            //If the changeling succeeds, add their Work the Room bonus.
            workTheRoomBonus = (taleCraftDots+1)/2;
         }//End of inner if.
      }//End of if.
      
      //Roll wits + insight, then store that value.
      rollResult = dieRoll(witsInsight,randomNumber);
      
      //If the current Dreamer can be harvested right away, do so.
      if(rollResult >= 4)
      {
         rollResult = dieRoll(harvestRoll+aphrodisiaBonus+grandeurBonus+workTheRoomBonus,randomNumber);
         
         //System.out.println("Result of Roll: " + rollResult);
         
         //Add an additional 1 for reverie threshold.
         glamourHarvested = rollResult + 2 - reverieDC;
      }//End of if.
      
      if(glamourHarvested < 0)
         glamourHarvested = 0;
      
      //Return the amount of glamour the Changeling harvested.
      return glamourHarvested;
   }//End of reverieHarvest.
   
   /**
   Simulate a Changeling gathering glamour from Rapture.
   @ravaging         Changeling        -  The Changeling object Rapturing.
   @avgResultChecks  int               -  How many test rolls should be made to determine a Changeling's average result.
   @useArts          boolean           -  True if the changeling should use their arts to harvest glamour.
   @randomNumber     Random            -  From the random utility. This is the name of the Random object created in the main program.
   @return           glamourHarvested  -  An integer representing the total glamour harvested while Rapturing.
   */
   public static int raptureHarvest(Changeling rapture, int avgResultChecks, boolean useArts, Random randomNumber)
   {
      //Store the number of dice a changeling rolls as integers.
      int witsInsight = rapture.witsInsight();
      int harvestRoll = rapture.raptureRoll();
      
      //Store the rapture DC.
      int raptureDC = rapture.RAPTURE_DC;
      
      //Store die rolls in an integer.
      int rollResult = -1;
      
      //Declare integer to track the amount of glamour and banality gained.
      int glamourHarvested = 0;
      int banalityGained = 0;
      
      //Roll wits + insight without the Dreamers background, then store that value.
      rollResult = dieRoll(witsInsight-rapture.getDreamerDots(),randomNumber);
      
      //If the Changeling can rapture right away, do so.
      if(rollResult >= 4)
      {
         //If the Changeling can rapture right away, they can attempt to harvest.
         rollResult = dieRoll(harvestRoll, randomNumber);
         
         //Error checking.
         //System.out.println("Result of Roll: " + rollResult);
         
         //If the ravaging succeeded, they gain the margin + 1 in glamour.
         if(rollResult >= raptureDC)
         {
            glamourHarvested = rollResult + 1 - raptureDC;
         }//End of inner if.
         //If the changeling botches, they roll 3 banality checks.
         else if(rollResult == 0)
         {
            //Make 3 banality checks.
            for(int i = 0; i < 3; i++)
            {
               //Roll 1 die.
               rollResult = dieRoll(1, randomNumber);
               
               //Compare the die.
               if(rollResult < 1)
               {
                  banalityGained++;
               }//End of inner if.
            }//End of for loop.
         }//End of inner else if.
      }//End of if.
      
      //Restore any suffered banality.
      glamourHarvested = glamourHarvested - banalityGained;
      
      //Return the harvested glamour value.
      return glamourHarvested;
   }//End of raptureHarvest.
   
   //-----Long-Term Gathering-----
   
   /**
   Simulate a Changeling gathering glamour over the course of several weeks.
   @changeling       Changeling        -  The Changeling object gathering glamour.
   @avgResultChecks  int               -  How many test rolls should be made to determine a Changeling's average result.
   @weeksSimulated   int               -  The number of weeks to simulate a changeling going through.
   @constants        int[]             -  An array of integers containing constant values for calculations.
   @useArts          boolean           -  True if the changeling should use their arts to harvest glamour.
   @randomNumber     Random            -  From the random utility. This is the name of the Random object created in the main program.
   @return           glamourHarvested  -  An integer representing the total glamour harvested.
   */
   public static int weekHarvest(Changeling changeling, int avgResultChecks, int weeksSimulated, int[] constants, boolean useArts, Random randomNumber)
   {
      //Declare and initalize a variable to keep track of how much glamour is gathered over the duration of avgResultChecks.
      int totalHarvest = 0;
      
      //Get the average harvests for the Changelings.
      int avgWitsInsight = changeling.getAvgWitInsight();
      int avgRavagingGain = changeling.getAvgRavagingHarvest();
      int avgReverieGain = changeling.getAvgReverieHarvest();
      int avgRaptureGain = changeling.getAvgRaptureHarvest();
      
      //Store relevant details about the Changeling to avoid multiple calls.
      boolean isUnseelie = changeling.getUnseelie();
      int charisma = changeling.getCha();
      int manipulation = changeling.getMan();
      int dreamerDots = changeling.getDreamerDots();
      int summerDots = changeling.getSummerDots();
      int sovereignDots = changeling.getSovereignDots();
      int taleCraftDots = changeling.getTaleCraftDots();
      
      //Declare and initalize integers for the Changeling's and Dreamer's rolls.
      int witsInsight = changeling.witsInsight();
      int ravageRoll = changeling.ravageRoll();
      int reverieRoll = changeling.reverieRoll();
      int raptureRoll = changeling.raptureRoll();
      int grandeurRoll = sovereignDots + manipulation;
      int aphrodisiaRoll = summerDots + charisma;
      int workTheRoomRoll = taleCraftDots + manipulation;
      int dreamerContest = constants[0];
      
      //Declare integers to store the bonuses Changelings get for using their arts.
      int aphrodisiaBonus = 0;
      int grandeurBonus = 0;
      int workTheRoomBonus = 0;
      
      //Declare and initalize an integer to store the number of successes a Changeling rolls.
      int successesRolled = 0;
      
      //Declare and intitalize integers to keep track of how many times the Changeling can gather glamour.
      int maxTimesGathered = charisma + 2;
      int timesGathered = 0;
      
      //Declare and initalize values to keep track of dreamers.
      int dreamersMused = 0;
      int[] dreamers = new int[charisma];
      int worstDreamer = 0;
      int worstDreamerSpot = charisma - 1;
      int avgDreamerWait = 0;
      boolean dreamerNotDropped = true;
      
      //Declare and initalize values to keep track of rapture.
      int raptureWait = 0;
      boolean raptureNotReady = true;
      
      //Declare and initalize values to track glamour gathering. 
      int changelingGlamour = 4;
      int changelingBanality = 0;
      int changelingDross = 0;
      int glamourGainedOnRoll = 0;
      int glamourHarvested = 0;
      int botchedDreamer = 0;
      boolean hasBotchedDreamer = false;
      
      //Declare and initalize values for the 4/1/2022 balance patch.
      int gainGlamourThreshold = 11 - constants[4];
      
      //Find the various DCs for Changeling harvesting rolls.
      int ravageDC = constants[3];
      int reverieDC = constants[1];
      int raptureDC = constants[2];
      
      //Change the DCs if the Changeling's court is unseelie.
      if(isUnseelie)
      {
         reverieDC++;
         ravageDC--;
      }//End of if.
      
      //Determine the average amount of half-weeks a Changeling must muse a dreamer for.
      if(avgWitsInsight >= 4)
      {
         avgDreamerWait = 1;
      }//End of if.
      else
      {
         switch(avgWitsInsight)
         {
            case 3:  avgDreamerWait = 2;
                     break;
            case 2:  avgDreamerWait = 4;
                     break;
            case 1:  avgDreamerWait = 8;
                     break;
            default: dreamers[dreamersMused] = 48;
                     break;
         }//End of switch.
      }//End of else.
      
      //Get the average by simulating as much as requested.
      for(int j = 0; avgResultChecks > j; j++)
      {
         //Loop for the decided number of weeks.
         for(int week = 1;week <= weeksSimulated;week++)
         {
            //Code for a weekHarvest* test print.
            /*
            System.out.println("!!! New Week! Begin rolling for week " + week + ". Starting Glamour: " + changelingGlamour + ". Starting Dross: " + changelingDross + ".");
            //*/
         
            //If it's optimal for the changeling to ravage, have them ravage until it's no longer optimal.
            while(avgRavagingGain > avgReverieGain && avgRavagingGain > avgRaptureGain && maxTimesGathered > timesGathered)
            {
               //Code for a weekHarvest* test print.
               /*
               System.out.println("Begin ravaging for week " + week + ".");
               //*/
               
               //Roll wits+insight on the dreamer at difficulty 3.
               if(dieRoll(witsInsight, randomNumber) >= 3)
               {
                  //Code for a weekHarvest* test print.
                  /*
                  System.out.println("Wits+insight to ravage successful. Begin ravaging for week " + week + ".");
                  //*/
               
                  //If the wits+insight roll succeeds, the changeling can attempt to harvest.
                  successesRolled = dieRoll(ravageRoll, randomNumber);
                  
                  //Code for a weekHarvest* test print.
                  /*
                  System.out.println("Successes rolled to ravage: " + successesRolled + ".");
                  //*/
                  
                  //If the ravaging succeeded, the Changeling gains the margin in glamour up to MAX_GLAMOUR_PER_HARVEST. The rest is gained as Dross up to MAX_DROSS_PER_HARVEST.
                  if(successesRolled >= ravageDC)
                  {
                     //Determine the potential glamour gained on this roll. Add 1 for Ravaging threshold. 
                     glamourGainedOnRoll = successesRolled - ravageDC + 1;
                     
                     //If the changeling can gain at least 2 glamour, and there is more than 2 which can be gained.
                     if(changelingGlamour < gainGlamourThreshold && glamourGainedOnRoll > constants[4])
                     {
                        //Add 2 Glamour to the Changeling's Glamour tracker.
                        changelingGlamour = changelingGlamour + 2;
                        
                        //Subtract the Glamour added.
                        glamourGainedOnRoll = glamourGainedOnRoll - 2;
                     }//End of inner if.
                     //If the Changeling can gain at least 2 glamour, but there is only 2 or less glamour to gain.
                     else if(changelingGlamour < gainGlamourThreshold)
                     {
                        //Add up to 2 Glamour to the Changeling's glamour total.
                        changelingGlamour = changelingGlamour + glamourGainedOnRoll;
                        
                        //Subtract the Glamour gained.
                        glamourGainedOnRoll = 0;
                     }//End of inner else if.
                     //If the Changeling already has 9 glamour.
                     else if(changelingGlamour == gainGlamourThreshold)
                     {
                        //Top off the Changeling's glamour.
                        changelingGlamour = 10;
                        
                        //Subtract the used glamour from the total glamour gained on this roll.
                        glamourGainedOnRoll = glamourGainedOnRoll - 1;
                     }//End of inner if else.
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Glamour remaining after added to glamour tracker: " + glamourGainedOnRoll + ".");
                     //*/
                     
                     //Add up to 4 Dross to the Changeling's tracker.
                     if(glamourGainedOnRoll > 0)
                     {
                        //The Changeling can only gain a maximum of 4 Dross.
                        if(glamourGainedOnRoll > constants[5])
                        {
                           changelingDross = changelingDross + constants[5];
                        }//End of if.
                        else
                        {
                           changelingDross = changelingDross + glamourGainedOnRoll;
                        }//End of else.
                     }//End of if.
                     
                     //Increment the Changeling's number of times gathered.
                     timesGathered++;
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Ravage success. Glamour at " + changelingGlamour + ". Dross at " + changelingDross + ". Gathered " + timesGathered + " times this week.");
                     //*/
                  }//End of inner if.
                  //If the Changeling rolls 0 successes when ravaging.
                  else if(successesRolled == 0)
                  {
                     //The Changeling gains 1 banality.
                     changelingBanality++;
                     
                     //The Changeling makes 2 banality checks.
                     for(int i = 0; i < 2; i++)
                     {
                        //The Changeling rolls 1 die.
                        successesRolled = dieRoll(1, randomNumber);
                        
                        //Add 1 banality if the Changeling failed.
                        if(successesRolled < 1)
                        {
                           changelingBanality++;
                        }//End of inner if.
                     }//End of for loop.
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Ravage botch. Banality at " + changelingBanality + ". Gathered " + timesGathered + " times this week.");
                     //*/
                  }//End of inner else if.
                  //If the Changeling fails their harvest roll.
                  else
                  {
                     //The Changeling gains 1 banality.
                     changelingBanality++;
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Ravage fail. Banality at " + changelingBanality + ". Gathered " + timesGathered + " times this week.");
                     //*/
                  }//End of inner else.
               }//End of if.
               
               //If the Changeling's glamour is over 10 after harvesting, set it to 10.
               if(changelingGlamour > 10)
               {
                  changelingGlamour = 10;
               }//End of if.
               
               //Clear the Changeling's banality tracker.
               if(changelingBanality > 0)
               {
                  //If the Changeling has enough dross to neutralize their banality, they do so.
                  if(changelingDross >= changelingBanality)
                  {
                     changelingDross = changelingDross - changelingBanality;
                  }//End of if.
                  //Otherwise, they subtract as much dross as possible, then the rest in glamour.
                  else
                  {
                     changelingGlamour = changelingGlamour + changelingDross - changelingBanality;
                     
                     changelingDross = 0;
                  }//End of else.
                  changelingBanality = 0;
               }//End of if.
               
               //Code for a weekHarvest* test print.
               /*
               System.out.println("Ravage done. Glamour at " + changelingGlamour + ". Dross at " + changelingDross + ".");
               //*/
            }//End of while.
            
            //If it's now optimal for the changeling to reverie, have them reverie until it's no longer possible.
            if(avgRaptureGain <= avgReverieGain && maxTimesGathered > timesGathered)
            {
               //Code for a weekHarvest* test print.
               /*
               System.out.println("Begin reverie for week " + week + ".");
               //*/
               
               //The Changeling rolls until they can't muse any more dreamers.
               while(dreamersMused < charisma)
               {
                  //Check if the Summer 3 art can be used.
                  if(summerDots >= 3 && useArts)
                  {  
                     //If it can, make a banality check.
                     successesRolled = dieRoll(1, randomNumber);
                     
                     //Check if the Changeling failed.
                     if(successesRolled < 1)
                     {
                        changelingBanality++;
                     }//End of inner if.
                     
                     //Have the Dreamer contest the Changeling.
                     if(dieRoll(aphrodisiaRoll, randomNumber) > dieRoll(dreamerContest, randomNumber))
                     {
                        //If the changeling succeeds, add their aphrodisia bonus.
                        aphrodisiaBonus = summerDots;
                     }//End of inner if.
                  }//End of if.
                  
                  //Check if the Sovereign 1 art can be used.
                  if(sovereignDots >= 1 && useArts)
                  {  
                     //If it can, make a banality check.
                     successesRolled = dieRoll(1, randomNumber);
                     
                     //Check if the Changeling failed.
                     if(successesRolled < 1)
                     {
                        changelingBanality++;
                     }//End of inner if.
                     
                     //Have the Dreamer contest the Changeling.
                     if(dieRoll(grandeurRoll, randomNumber) > dieRoll(dreamerContest, randomNumber))
                     {
                        //If the changeling succeeds, add their granduer bonus.
                        grandeurBonus = summerDots;
                     }//End of inner if.
                  }//End of if.
                  
                  //Roll wits+insight on the dreamer.
                  successesRolled = dieRoll(witsInsight, randomNumber);
                  
                  //Code for a weekHarvest* test print.
                  /*
                  System.out.println("Successes rolled on reverie wits + insight: " + successesRolled + ".");
                  //*/
                  
                  //If the result is greater than or equal to 5, and the Changeling can still harvest, the Changeling can harvest right away.
                  if(successesRolled >= 5 && maxTimesGathered > timesGathered)
                  {  
                     //The Changeling rolls to harvest the reverie.
                     successesRolled = dieRoll(reverieRoll,randomNumber);
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Successes rolled to reverie: " + successesRolled + ".");
                     //*/
                     
                     //If the Changeling succeeds.
                     if(successesRolled >= reverieDC)
                     {
                        //The Changeling gains the margin in glamour + 1. Add an additional 1 for reverie threshold.
                        glamourGainedOnRoll = successesRolled + 2 - reverieDC;
                        
                        //If the changeling can gain at least 2 glamour, and there is more than 2 which can be gained.
                        if(changelingGlamour < gainGlamourThreshold && glamourGainedOnRoll > constants[4])
                        {
                           //Add 2 Glamour to the Changeling's Glamour tracker.
                           changelingGlamour = changelingGlamour + 2;
                           
                           //Subtract the Glamour added.
                           glamourGainedOnRoll = glamourGainedOnRoll - 2;
                        }//End of inner if.
                        //If the Changeling can gain at least 2 glamour, but there is only 2 or less glamour to gain.
                        else if(changelingGlamour < gainGlamourThreshold)
                        {
                           //Add up to 2 Glamour to the Changeling's glamour total.
                           changelingGlamour = changelingGlamour + glamourGainedOnRoll;
                           
                           //Subtract the Glamour gained.
                           glamourGainedOnRoll = 0;
                        }//End of inner else if.
                        //If the Changeling already has 9 glamour.
                        else if(changelingGlamour == gainGlamourThreshold)
                        {
                           //Top off the Changeling's glamour.
                           changelingGlamour = 10;
                           
                           //Subtract the used glamour from the total glamour gained on this roll.
                           glamourGainedOnRoll = glamourGainedOnRoll - 1;
                        }//End of inner if else.
                        
                        //Code for a weekHarvest* test print.
                        /*
                        System.out.println("Glamour remaining after added to glamour tracker: " + glamourGainedOnRoll + ".");
                        //*/
                        
                        //Add up to 4 Dross to the Changeling's tracker.
                        if(glamourGainedOnRoll > 0)
                        {
                           //The Changeling can only gain a maximum of 4 Dross.
                           if(glamourGainedOnRoll > constants[5])
                           {
                              changelingDross = changelingDross + constants[5];
                           }//End of if.
                           else
                           {
                              changelingDross = changelingDross + glamourGainedOnRoll;
                           }//End of else.
                        }//End of if.
                        
                        //Increment the Changeling's number of times gathered.
                        timesGathered++;
                        
                        //Code for a weekHarvest* test print.
                        /*
                        System.out.println("Reverie success. Glamour at " + changelingGlamour + ". Dross at " + changelingDross + ". Gathered " + timesGathered + " times this week.");
                        //*/
                     }//End of inner if.
                     //If the Changeling does not roll a 0, but still fails.
                     else if(successesRolled != 0)
                     {
                        //Set the current Dreamer to take an additional 2 half-weeks.
                        dreamers[dreamersMused] = 2;
                        
                        //Increment the number of dreamers mused.
                        dreamersMused++;
                        
                        //Code for a weekHarvest* test print.
                        /*
                        System.out.println("Reverie failure for dreamer " + dreamersMused + ". Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                        //*/
                     }//End of inner else if. 
                     //If the Changeling rolls a 0.
                     else
                     {
                        //Set the current Dreamer to take an additional 69 half-weeks.
                        dreamers[dreamersMused] = 69;
                        
                        //Note the position of the botched dreamer.
                        botchedDreamer = dreamersMused;
                        
                        //Note that a dreamer was botched.
                        hasBotchedDreamer = true;
                        
                        //Increment the number of dreamers mused.
                        dreamersMused++;
                        
                        //Code for a weekHarvest* test print.
                        /*
                        System.out.println("Reverie botch. Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                        //*/
                     }//End of else.
                  }//End of if.
                  //If the Changeling has already harvested, but still rolled 5 successes, note that they can harvest a dreamer next week.
                  else if(successesRolled >= 5)
                  {
                     //Apply the amount of half-weeks before the changeling can harvest from the dreamer.
                     dreamers[dreamersMused] = 0;
                     
                     //Increment the number of dreamers mused.
                     dreamersMused++;
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Must wait " + dreamers[dreamersMused-1] + " half-weeks to harvest dreamer " + dreamersMused + ". Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                     //*/
                  }
                  //If the result was 4 or less, apply the amount of half-weeks before the changeling can harvest from them.
                  else
                  {
                     //Apply the amount of half-weeks before the changeling can harvest from the dreamer.
                     switch(successesRolled)
                     {
                        case 4:  dreamers[dreamersMused] = 1;
                                 break;
                        case 3:  dreamers[dreamersMused] = 2;
                                 break;
                        case 2:  dreamers[dreamersMused] = 4;
                                 break;
                        case 1:  dreamers[dreamersMused] = 8;
                                 break;
                        default: dreamers[dreamersMused] = 48;
                                 break;
                     }//End of switch.
                     
                     //Increment the number of dreamers mused.
                     dreamersMused++;
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Must wait " + dreamers[dreamersMused-1] + " half-weeks to harvest dreamer " + dreamersMused + ". Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                     //*/
                  }//End of else.
               }//End of inner while.
               
               //Find the Dreamer which will take the longest to harvest.
               for(int i = 0; dreamers.length > i; i++)
               {
                  //Code for a weekHarvest* test print.
                  /*
                  System.out.println("Find worst dreamer. Current worst takes " + worstDreamer + " half-weeks. Current dreamer takes " + dreamers[i] + " half-weeks.");
                  //*/
                  
                  //If the dreamer will take longer to harvest than the Changeling's previous worst dreamer, and they weren't a botched roll, note the dreamer's location.
                  if(worstDreamer < dreamers[i] && dreamers[i] != 69)
                  {  
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println(worstDreamer + " is less than " + dreamers[i] + ".");
                     //*/
                     
                     worstDreamer = dreamers[i];
                     worstDreamerSpot = i;
                  }//End of if.
               }//End of for loop.
               
               //Code for a weekHarvest* test print.
               /*
               System.out.println("The worst dreamer is dreamer " + (worstDreamerSpot+1) + ", who will take " + worstDreamer + " half-weeks.");
               //*/
               
               //Drop the Changeling's worst Dreamer if they're worse than the result of the Changeling's average wits+insight roll, then re-roll them.
               if(worstDreamer > avgDreamerWait && dreamersMused > 0 && dreamerNotDropped)
               {
                  //The Changeling rolls wits + insight.
                  successesRolled = dieRoll(witsInsight,randomNumber);
                  
                  //Code for a weekHarvest* test print.
                  /*
                  System.out.println("Successes rolled on dropped-dreamer reverie wits + insight: " + successesRolled + ".");
                  //*/
                  
                  //Code for a weekHarvest* test print.
                  /*
                  System.out.println("Drop dreamer " + (worstDreamerSpot+1) + ". Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                  //*/
                  
                  //Check how that applies to the dreamer.
                  if(successesRolled >= 4)
                  {
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Dreamer " + (worstDreamerSpot+1) + " can be harvested right away.");
                     //*/
                     
                     dreamers[worstDreamerSpot] = 1;
                  }//End of inner if.
                  else
                  {
                     switch(successesRolled)
                     {
                        case 3:  dreamers[worstDreamerSpot] = 2;
                                 break;
                        case 2:  dreamers[worstDreamerSpot] = 4;
                                 break;
                        case 1:  dreamers[worstDreamerSpot] = 8;
                                 break;
                        default: dreamers[worstDreamerSpot] = 48;
                                 break;
                     }//End of switch.
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Must wait " + dreamers[worstDreamerSpot] + " half-weeks to harvest dreamer " + (worstDreamerSpot+1) + ". Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                     //*/
                  }//End of inner else.
                  
                  //Note that a dreamer has been dropped this week.
                  dreamerNotDropped = false;
               }//End of if.
               
               //If the Changeling already has Dreamers to harvest, do so.
               if(maxTimesGathered > timesGathered)
               {
                  //Check which dreamers can be harvested.
                  for(int i = 0; dreamers.length > i; i++)
                  {
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Dreamer " + (i+1) + " will take " + dreamers[i] + " half-weeks to harvest.");
                     //*/
                     
                     //If the dreamer can be harvested, do so.
                     if(dreamers[i] <= 0 && maxTimesGathered > timesGathered)
                     {
                        //The Changeling rolls to harvest the reverie.
                        successesRolled = dieRoll(reverieRoll,randomNumber);
                        
                        //Code for a weekHarvest* test print.
                        /*
                        System.out.println("Successes rolled to help dreamer " + (i+1) + " reverie during the first-half of week " + week + ": " + successesRolled + ".");
                        //*/
                        
                        //If the Changeling succeeds.
                        if(successesRolled >= reverieDC)
                        {
                           ///The Changeling gains the margin in glamour + 1. Add an additional 1 for reverie threshold.
                           glamourGainedOnRoll = successesRolled + 2 - reverieDC;
                           
                           //If the changeling can gain at least 2 glamour, and there is more than 2 which can be gained.
                           if(changelingGlamour < gainGlamourThreshold && glamourGainedOnRoll > constants[4])
                           {
                              //Add 2 Glamour to the Changeling's Glamour tracker.
                              changelingGlamour = changelingGlamour + 2;
                              
                              //Subtract the Glamour added.
                              glamourGainedOnRoll = glamourGainedOnRoll - 2;
                           }//End of inner if.
                           //If the Changeling can gain at least 2 glamour, but there is only 2 or less glamour to gain.
                           else if(changelingGlamour < gainGlamourThreshold)
                           {
                              //Add up to 2 Glamour to the Changeling's glamour total.
                              changelingGlamour = changelingGlamour + glamourGainedOnRoll;
                              
                              //Subtract the Glamour gained.
                              glamourGainedOnRoll = 0;
                           }//End of inner else if.
                           //If the Changeling already has 9 glamour.
                           else if(changelingGlamour == gainGlamourThreshold)
                           {
                              //Top off the Changeling's glamour.
                              changelingGlamour = 10;
                              
                              //Subtract the used glamour from the total glamour gained on this roll.
                              glamourGainedOnRoll = glamourGainedOnRoll - 1;
                           }//End of inner if else.
                           
                           //Code for a weekHarvest* test print.
                           /*
                           System.out.println("Glamour remaining after reverie added to glamour tracker: " + glamourGainedOnRoll + ".");
                           //*/
                           
                           //Add up to 4 Dross to the Changeling's tracker.
                           if(glamourGainedOnRoll > 0)
                           {
                              //The Changeling can only gain a maximum of 4 Dross.
                              if(glamourGainedOnRoll > constants[5])
                              {
                                 changelingDross = changelingDross + constants[5];
                              }//End of if.
                              else
                              {
                                 changelingDross = changelingDross + glamourGainedOnRoll;
                              }//End of else.
                           }//End of if.
                           
                           //Increment the Changeling's number of times gathered.
                           timesGathered++;
                           
                           //Code for a weekHarvest* test print.
                           /*
                           System.out.println("Reverie success. Glamour at " + changelingGlamour + ". Gathered " + timesGathered + " times this week.");
                           //*/
                           
                           //Roll wits+insight for this dreamer slot again.
                           successesRolled = dieRoll(witsInsight,randomNumber);
                           
                           //Code for a weekHarvest* test print.
                           /*
                           System.out.println("Wits+insight rolled on new dreamer " + (i+1) + ": " + successesRolled + ".");
                           //*/
                           
                           //Check how that applies to the dreamer.
                           if(successesRolled >= 5)
                           {
                              //Code for a weekHarvest* test print.
                              /*
                              System.out.println("Dreamer " + (i+1) + " can be harvested right away.");
                              //*/
                              
                              //Set the current dreamer to take no time.
                              dreamers[i] = 0;
                           }//End of inner if.
                           else
                           {
                              switch(successesRolled)
                              {
                                 case 4:  dreamers[i] = 1;
                                          break;
                                 case 3:  dreamers[i] = 2;
                                          break;
                                 case 2:  dreamers[i] = 4;
                                          break;
                                 case 1:  dreamers[i] = 8;
                                          break;
                                 default: dreamers[i] = 48;
                                          break;
                              }//End of switch.
                              
                              //Code for a weekHarvest* test print.
                              /*
                              System.out.println("Must wait " + dreamers[i] + " half-weeks to harvest dreamer " + (i+1) + ". Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                              //*/
                           }//End of inner else.
                           
                           //Decrement the tracker for dreamers so that this dreamer slot is evaluated again.
                           i--;
                        }//End of inner if.
                        //If the Changeling does not roll a 0, but still fails.
                        else if(successesRolled != 0)
                        {
                           //Set the current Dreamer to take an additional 1 half-week.
                           dreamers[i] = 1;
                           
                           //Code for a weekHarvest* test print.
                           /*
                           System.out.println("Reverie failure for dreamer " + (i+1) + ". Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                           //*/
                        }//End of inner else if. 
                        //If the Changeling rolls a 0.
                        else
                        {
                           //Set the current Dreamer to take an additional 49 half-weeks.
                           dreamers[i] = 69;
                           
                           //Note the position of the botched dreamer.
                           botchedDreamer = dreamersMused;
                           
                           //Note that a dreamer was botched.
                           hasBotchedDreamer = true;
                           
                           //Code for a weekHarvest* test print.
                           /*
                           System.out.println("Reverie botch. Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                           //*/
                        }//End of else.
                     }//End of inner if.
                  }//End of for.
               }//End of if.
               
               //If no dreamer has been dropped yet this week, consider if one should be again.
               if(dreamerNotDropped)
               {
                  //Find the Dreamer which will take the longest to harvest after harvesting for the first half of the week.
                  for(int i = 0; dreamers.length > i; i++)
                  {
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Find worst dreamer. Current worst takes " + worstDreamer + " half-weeks. Current dreamer takes " + dreamers[i] + " half-weeks.");
                     //*/
                     
                     //If the dreamer will take longer to harvest than the Changeling's previous worst dreamer, and they weren't a botched roll, note the dreamer's location.
                     if(worstDreamer < dreamers[i] && dreamers[i] != 69)
                     {  
                        //Code for a weekHarvest* test print.
                        /*
                        System.out.println(worstDreamer + " is less than " + dreamers[i] + ".");
                        //*/
                        
                        worstDreamer = dreamers[i];
                        worstDreamerSpot = i;
                     }//End of if.
                  }//End of for loop.
                  
                  //Code for a weekHarvest* test print.
                  /*
                  System.out.println("The worst dreamer is dreamer " + (worstDreamerSpot+1) + ", who will take " + worstDreamer + " half-weeks.");
                  //*/
                  
                  //Drop the Changeling's worst Dreamer if they're worse than the result of the Changeling's average wits+insight roll, then re-roll them.
                  if(worstDreamer > avgDreamerWait && dreamersMused > 0)
                  {
                     //The Changeling rolls wits + insight.
                     successesRolled = dieRoll(witsInsight,randomNumber);
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Successes rolled on dropped-dreamer reverie wits + insight: " + successesRolled + ".");
                     //*/
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Drop dreamer " + (worstDreamerSpot+1) + ". Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                     //*/
                     
                     //Check how that applies to the dreamer.
                     if(successesRolled >= 4)
                     {
                        //Code for a weekHarvest* test print.
                        /*
                        System.out.println("Dreamer " + (worstDreamerSpot+1) + " can be harvested right away.");
                        //*/
                        
                        dreamers[worstDreamerSpot] = 1;
                     }//End of inner if.
                     else
                     {
                        switch(successesRolled)
                        {
                           case 3:  dreamers[worstDreamerSpot] = 2;
                                    break;
                           case 2:  dreamers[worstDreamerSpot] = 4;
                                    break;
                           case 1:  dreamers[worstDreamerSpot] = 8;
                                    break;
                           default: dreamers[worstDreamerSpot] = 48;
                                    break;
                        }//End of switch.
                        
                        //Code for a weekHarvest* test print.
                        /*
                        System.out.println("Must wait " + dreamers[worstDreamerSpot] + " half-weeks to harvest dreamer " + (worstDreamerSpot+1) + ". Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                        //*/
                     }//End of inner else.
                     
                     //Note that a dreamer has been dropped this week.
                     dreamerNotDropped = false;
                  }//End of if.
               }//End of if.
               
               //Reduce the time each dreamer takes to harvest by 1 half-week.
               for(int i = 0; dreamers.length > i; i++)
               {
                  //Only do so if the current dreamer is being mused.
                  if(dreamers[i] > 0)
                  {
                     dreamers[i]--;
                  }//End of if.
               }//End of for.
               
               //Move on to the second half of the week if neccessary.
               if(maxTimesGathered > timesGathered)
               {
                  //Check which dreamers can be harvested.
                  for(int i = 0; dreamers.length > i; i++)
                  {
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Dreamer " + (i+1) + " will take " + dreamers[i] + " half-weeks to harvest.");
                     //*/
                     
                     //If the dreamer can now be harvested, do so.
                     if(dreamers[i] <= 0 && maxTimesGathered > timesGathered)
                     {
                        //The Changeling rolls to harvest the reverie.
                        successesRolled = dieRoll(reverieRoll,randomNumber);
                        
                        //Code for a weekHarvest* test print.
                        /*
                        System.out.println("Successes rolled to help dreamer " + (i+1) + " reverie during the second-half of week " + week + ": " + successesRolled + ".");
                        //*/
                        
                        //If the Changeling succeeds.
                        if(successesRolled >= reverieDC)
                        {
                           //The Changeling gains the margin in glamour + 1. Add an additional 1 for reverie threshold.
                           glamourGainedOnRoll = successesRolled + 2 - reverieDC;
                           
                           //If the changeling can gain at least 2 glamour, and there is more than 2 which can be gained.
                           if(changelingGlamour < gainGlamourThreshold && glamourGainedOnRoll > constants[4])
                           {
                              //Add 2 Glamour to the Changeling's Glamour tracker.
                              changelingGlamour = changelingGlamour + 2;
                              
                              //Subtract the Glamour added.
                              glamourGainedOnRoll = glamourGainedOnRoll - 2;
                           }//End of inner if.
                           //If the Changeling can gain at least 2 glamour, but there is only 2 or less glamour to gain.
                           else if(changelingGlamour < gainGlamourThreshold)
                           {
                              //Add up to 2 Glamour to the Changeling's glamour total.
                              changelingGlamour = changelingGlamour + glamourGainedOnRoll;
                              
                              //Subtract the Glamour gained.
                              glamourGainedOnRoll = 0;
                           }//End of inner else if.
                           //If the Changeling already has 9 glamour.
                           else if(changelingGlamour == gainGlamourThreshold)
                           {
                              //Top off the Changeling's glamour.
                              changelingGlamour = 10;
                              
                              //Subtract the used glamour from the total glamour gained on this roll.
                              glamourGainedOnRoll = glamourGainedOnRoll - 1;
                           }//End of inner if else.
                           
                           //Code for a weekHarvest* test print.
                           /*
                           System.out.println("Glamour remaining after reverie added to glamour tracker: " + glamourGainedOnRoll + ".");
                           //*/
                           
                           //Add up to 4 Dross to the Changeling's tracker.
                           if(glamourGainedOnRoll > 0)
                           {
                              //The Changeling can only gain a maximum of 4 Dross.
                              if(glamourGainedOnRoll > constants[5])
                              {
                                 changelingDross = changelingDross + constants[5];
                              }//End of if.
                              else
                              {
                                 changelingDross = changelingDross + glamourGainedOnRoll;
                              }//End of else.
                           }//End of if.
                           
                           //Increment the Changeling's number of times gathered.
                           timesGathered++;
                           
                           //Code for a weekHarvest* test print.
                           /*
                           System.out.println("Reverie success. Glamour at " + changelingGlamour + ". Gathered " + timesGathered + " times this week.");
                           //*/
                           
                           //Roll wits+insight for this dreamer slot again.
                           successesRolled = dieRoll(witsInsight,randomNumber);
                           
                           //Code for a weekHarvest* test print.
                           /*
                           System.out.println("Wits+insight rolled on new dreamer " + (i+1) + ": " + successesRolled + ".");
                           //*/
                           
                           //Check how that applies to the dreamer.
                           if(successesRolled >= 5)
                           {
                              //Code for a weekHarvest* test print.
                              /*
                              System.out.println("Dreamer " + (i+1) + " can be harvested right away.");
                              //*/
                              
                              //Set the current dreamer to take no time.
                              dreamers[i] = 0;
                           }//End of inner if.
                           else
                           {
                              switch(successesRolled)
                              {
                                 case 4:  dreamers[i] = 1;
                                          break;
                                 case 3:  dreamers[i] = 1;
                                          break;
                                 case 2:  dreamers[i] = 3;
                                          break;
                                 case 1:  dreamers[i] = 7;
                                          break;
                                 default: dreamers[i] = 48;
                                          break;
                              }//End of switch.
                              
                              //Code for a weekHarvest* test print.
                              /*
                              System.out.println("Must wait " + dreamers[i] + " half-weeks to harvest dreamer " + i + ". Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                              //*/
                           }//End of inner else.
                           
                           //Decrement the tracker for dreamers so that this dreamer slot is evaluated again.
                           i--;
                        }//End of inner if.
                        //If the Changeling does not roll a 0, but still fails.
                        else if(successesRolled != 0)
                        {
                           //Set the current Dreamer to take an additional 1 half-week.
                           dreamers[i] = 1;
                           
                           //Code for a weekHarvest* test print.
                           /*
                           System.out.println("Reverie failure for dreamer " + i + ". Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                           //*/
                        }//End of inner else if. 
                        //If the Changeling rolls a 0.
                        else
                        {
                           //Set the current Dreamer to take an additional 49 half-weeks.
                           dreamers[i] = 69;
                           
                           //Note the position of the botched dreamer.
                           botchedDreamer = dreamersMused;
                           
                           //Note that a dreamer was botched.
                           hasBotchedDreamer = true;
                           
                           //Code for a weekHarvest* test print.
                           /*
                           System.out.println("Reverie botch. Dreamers mused at: " + dreamersMused + ". Gathered " + timesGathered + " times this week.");
                           //*/
                        }//End of else.
                     }//End of inner if.
                  }//End of for.
               }//End of if.
               
               //Reduce the time each dreamer takes to harvest by 1 additional half-week.
               for(int i = 0; dreamers.length > i; i++)
               {
                  //Only do so if the current dreamer is being mused.
                  if(dreamers[i] > 0)
                  {
                     dreamers[i]--;
                  }//End of if.
               }//End of for.
               
               //If the Changeling's glamour is over 10 after harvesting, convert the excess to Dross.
               if(changelingGlamour > 10)
               {
                  changelingDross += changelingGlamour - 10;
                  
                  changelingGlamour = 10;
               }//End of if.
               
               //Clear the Changeling's banality tracker.
               if(changelingBanality > 0)
               {
                  //If the Changeling has enough dross to neutralize their banality, they do so.
                  if(changelingDross >= changelingBanality)
                  {
                     changelingDross = changelingDross - changelingBanality;
                  }//End of if.
                  //Otherwise, they subtract as much dross as possible, then the rest in glamour.
                  else
                  {
                     changelingGlamour = changelingGlamour + changelingDross - changelingBanality;
                  }//End of else.
                  changelingBanality = 0;
               }//End of if.
               
               //Code for a weekHarvest* test print.
               /*
               System.out.println("Reverie done for week " + week + ". Glamour at " + changelingGlamour + ", Dross at " + changelingDross + ", and Banality at " + changelingBanality + ".");
               //*/
            }//End of if.
            
            //If it is now possible to rapture, the Changeling does so.
            while(maxTimesGathered > timesGathered && raptureWait <= 0)
            {  
               //Code for a weekHarvest* test print.
               /*
               System.out.println("Begin rapture for week " + week + ".");
               //*/
               
               //Roll wits + insight without the Dreamers background, then store that value.
               if(raptureNotReady)
               {
                  //Roll the wits+insight check.
                  successesRolled = dieRoll(witsInsight-dreamerDots,randomNumber);
                  
                  //Code for a weekHarvest* test print.
                  /*
                  System.out.println("Wits+insight for rapture: " + successesRolled);
                  //*/
               }//End of if.
               //Code for a weekHarvest* test print.
               /*
               else
               {
                  System.out.println("Wits+insight not rolled.");
               }
               //*/
               
               //If the Changeling can rapture right away, do so.
               if(successesRolled >= 5 || !raptureNotReady)
               {  
                  //If the Changeling can rapture right away, they can attempt to harvest.
                  successesRolled = dieRoll(raptureRoll, randomNumber);
                  
                  //If the rapture succeeded, they gain the margin + 1 in glamour.
                  if(successesRolled >= raptureDC)
                  {
                     //Determine the potential glamour gained on this roll. 
                     glamourGainedOnRoll = successesRolled - raptureDC + 1;
                     
                     //If the changeling can gain at least 2 glamour, and there is more than 2 which can be gained.
                     if(changelingGlamour < gainGlamourThreshold && glamourGainedOnRoll > constants[4])
                     {
                        //Add 2 Glamour to the Changeling's Glamour tracker.
                        changelingGlamour = changelingGlamour + 2;
                        
                        //Subtract the Glamour added.
                        glamourGainedOnRoll = glamourGainedOnRoll - 2;
                     }//End of inner if.
                     //If the Changeling can gain at least 2 glamour, but there is only 2 or less glamour to gain.
                     else if(changelingGlamour < gainGlamourThreshold)
                     {
                        //Add up to 2 Glamour to the Changeling's glamour total.
                        changelingGlamour = changelingGlamour + glamourGainedOnRoll;
                        
                        //Subtract the Glamour gained.
                        glamourGainedOnRoll = 0;
                     }//End of inner else if.
                     //If the Changeling already has 9 glamour.
                     else if(changelingGlamour == gainGlamourThreshold)
                     {
                        //Top off the Changeling's glamour.
                        changelingGlamour = 10;
                        
                        //Subtract the used glamour from the total glamour gained on this roll.
                        glamourGainedOnRoll = glamourGainedOnRoll - 1;
                     }//End of inner if else.
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Glamour remaining after rapture added to glamour tracker: " + glamourGainedOnRoll + ".");
                     //*/
                     
                     //Add up to 4 Dross to the Changeling's tracker.
                     if(glamourGainedOnRoll > 0)
                     {
                        //The Changeling can only gain a maximum of 4 Dross.
                        if(glamourGainedOnRoll > constants[5])
                        {
                           changelingDross = changelingDross + constants[5];
                        }//End of if.
                        else
                        {
                           changelingDross = changelingDross + glamourGainedOnRoll;
                        }//End of else.
                     }//End of if.
                     
                     //Increment the number of times gathered.
                     timesGathered++;
                     
                     //Require the Changeling to roll wits + insight again.
                     raptureNotReady = true;
                     
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Successes rolled to rapture: " + successesRolled + ". Glamour at: " + changelingGlamour + ". Gathered " + timesGathered + " times this week.");
                     //*/
                  }//End of inner if.
                  //If the changeling botches, they roll 3 banality checks.
                  else if(successesRolled == 0)
                  {
                     //Make 3 banality checks.
                     for(int i = 0; i < 3; i++)
                     {
                        //Roll 1 die.
                        successesRolled = dieRoll(1, randomNumber);
                        
                        //Compare the die.
                        if(successesRolled < 1)
                        {
                           changelingBanality++;
                        }//End of inner if.
                     }//End of for loop.
                  }//End of inner else if.
                  //Otherwise, the Changeling must take another week before they can rapture.
                  else
                  {
                     //Code for a weekHarvest* test print.
                     /*
                     System.out.println("Rapture failed. Successes rolled to rapture: " + successesRolled + ".");
                     //*/
                  
                     raptureWait = 1;
                     
                     raptureNotReady = false;
                  }//End of inner else.
               }//End of if.
               //If the changeling can not rapture right away, note how long it will take before they can.
               else
               {
                  switch(successesRolled)
                  {
                     case 4:  raptureWait = 1;
                              raptureNotReady = false;
                              break;
                     case 3:  raptureWait = 1;
                              raptureNotReady = false;
                              break;
                     case 2:  raptureWait = 2;
                              raptureNotReady = false;
                              break;
                     case 1:  raptureWait = 4;
                              raptureNotReady = false;
                              break;
                  }//End of switch.
               }//End of else.
               
               //Clear the Changeling's banality tracker.
               if(changelingBanality > 0)
               {
                  //If the Changeling has enough dross to neutralize their banality, they do so.
                  if(changelingDross >= changelingBanality)
                  {
                     changelingDross = changelingDross - changelingBanality;
                  }//End of if.
                  //Otherwise, they subtract as much dross as possible, then the rest in glamour.
                  else
                  {
                     changelingGlamour = changelingGlamour + changelingDross - changelingBanality;
                     
                     changelingDross = 0;
                  }//End of else.
                  changelingBanality = 0;
               }//End of if.
               
               //Code for a weekHarvest* test print.
               /*
               System.out.println("Rapture done for week " + week + ". Glamour at " + changelingGlamour + ", Dross at " + changelingDross + ", and Banality at " + changelingBanality + ".");
               //*/
            }//End of while.
            
            //Once all harvesting rolls have been done for the week, reset timesGathered, worstDreamer, and dreamerNotDropped. 
            timesGathered = 0;
            worstDreamer = 1;
            dreamerNotDropped = true;
            
            //Lower raptureWait by 1.
            if(raptureWait > 0)
            {
               raptureWait--;
               
               if(raptureWait <= 0)
               {
                  raptureNotReady = false;
               }//End of inner if.
            }//End of if.
         }//End of for loop.
         
         //Calculate how much glamour was gathered total.
         glamourHarvested = (changelingGlamour-4) + changelingDross;
         
         //Divide the glamour gathered by the number of weeks.
         glamourHarvested = glamourHarvested / weeksSimulated;
         
         //Reset changeling glamour and dross.
         changelingGlamour = 4;
         changelingDross = 0;
         
         //Add the average glamour harvested to the total.
         totalHarvest += glamourHarvested;
      }//End of for loop.
      
      //Divide the total harvest by
      glamourHarvested = totalHarvest / avgResultChecks;
      
      //Return the average glamour gathered each week.
      return glamourHarvested;
   }//End of weekHarvest.
   
   /**
   Simulate a changeling gathering glamour from a Reverie over the course of a week.
   @reverie          Changeling              -  The Changeling object performing a Reverie.
   @avgResultChecks  int                     -  How many test rolls should be made to determine a changeling's average result.
   @randomNumber     Random                  -  From the random utility. This is the name of the Random object created in the main program.
   @return           totalGlamourHarvested   -  The total glamour harvested while performing a Reverie.
   */
   public static int reverieHarvestWeek(Changeling reverie, int avgResultChecks, Random randomNumber)
   {
      //Store the number of dice a changeling rolls as integers.
      int witsInsight = reverie.witsInsight();
      
      //Get the average wits + insight roll for a changeling.
      int avgWitsInsight = reverie.getAvgWitInsight();
      
      //Create arrays to store data for dreamers and wits + insight rolls. 
      int[] dreamerTime = new int[reverie.getCha()];
      int[] insightRoll = new int[witsInsight];
      
      //Create integers to track the amount of glamour the changeling has harvested.
      int maxHarvests = reverie.getCha() + 2;
      int harvestRoll = reverie.reverieRoll();
      int harvestCount = 0;
      int totalGlamourHarvested = 0;
      int glamourHarvested = 0;
      
      //Declare integers to keep track of the highest changeling's worst dreamer.
      int worstDreamer = 0;
      int worstDreamerSpot = 0;
      
      //Determine the DC for this Changeling's reverie.
      int reverieDC = 2;
      if(reverie.getUnseelie())
      {
         reverieDC = 3;
      }//End of if.
      
      //Roll wits + insight for the Changeling's suite of dreamers at the start of the week, then apply twice the amount of weeks before the changeling can harvest from them.
      for(int i = 0; dreamerTime.length > i; i++)
      {  
         //Roll wits + insight, then store that value.
         insightRoll[i] = dieRoll(witsInsight,randomNumber);
         
         //Check how that applies to the dreamer.
         if(insightRoll[i] >= 5)
            dreamerTime[i] = 0;
         else
         {
            switch(insightRoll[i])
            {
               case 4:  dreamerTime[i] = 1;
                        break;
               case 3:  dreamerTime[i] = 2;
                        break;
               case 2:  dreamerTime[i] = 4;
                        break;
               case 1:  dreamerTime[i] = 8;
                        break;
               default: dreamerTime[i] = 20;
                        break;
            }//End of switch.
         }//End of else.
         
         //Code for a test print.
         /**
         System.out.print(insightRoll[i] + " Successes: ");
         if(dreamerTime[i] > 2)
            System.out.println((dreamerTime[i]/2) + " Weeks. Dreamer " + (i+1) + ".");
         else if(dreamerTime[i] == 2)
            System.out.println((dreamerTime[i]/2) + " Week. Dreamer " + (i+1) + ".");
         else if(dreamerTime[i] == 1)
            System.out.println("Half a week. Dreamer " + (i+1) + ".");
         else
            System.out.println("Right away.");
         //*/
         
         //If the current Dreamer can be harvested right away, do so.
         if(dreamerTime[i] == 0 && harvestCount < maxHarvests)
         {
            //Incriment the amount of times the Changeling has harvested.
            harvestCount++;
            
            glamourHarvested = dieRoll(harvestRoll,randomNumber) + 1 - reverieDC;
            
            if(glamourHarvested > 0)
               totalGlamourHarvested += glamourHarvested;
            i = i - 1;
            
            //Code for a test print.
            /*
            System.out.println(glamourHarvested + " glamour gained. " + totalGlamourHarvested + " total. " + harvestCount + " total harvests.");
            */
         }//End of if.
      }//End of for loop.
      
      //Find the Dreamer which will take the longest to harvest.
      for(int i = 0; dreamerTime.length > i; i++)
      {
         //If the value is higher than the changeling's worst dreamer, note their location.
         if(worstDreamer > insightRoll[i])
         {  
            worstDreamer = insightRoll[i];
            worstDreamerSpot = i;
         }//End of if.
      }//End of for loop.
      
      //Drop the Changeling's worst Dreamer if they're worse than the Changeling's average wits+insight roll, then re-roll them.
      if(worstDreamer < avgWitsInsight)
      {
         //Code for a test print.
         /*
         System.out.println("Reroll dreamer " + (worstDreamerSpot+1) + ".");
         //*/
         
         //Roll wits + insight, then store that value.
         insightRoll[worstDreamerSpot] = dieRoll(witsInsight,randomNumber);
         
         //Check how that applies to the dreamer.
         if(insightRoll[worstDreamerSpot] >= 5)
            dreamerTime[worstDreamerSpot] = 0;
         else
         {
            switch(insightRoll[worstDreamerSpot])
            {
               case 4:  dreamerTime[worstDreamerSpot] = 1;
                        break;
               case 3:  dreamerTime[worstDreamerSpot] = 2;
                        break;
               case 2:  dreamerTime[worstDreamerSpot] = 4;
                        break;
               case 1:  dreamerTime[worstDreamerSpot] = 8;
                        break;
               default: dreamerTime[worstDreamerSpot] = 20;
                        break;
            }//End of switch.
         }//End of else.
         //Code for a test print.
         /**
         System.out.print(insightRoll[worstDreamerSpot] + " Successes: ");
         if(dreamerTime[worstDreamerSpot] > 2)
            System.out.println((dreamerTime[worstDreamerSpot]/2) + " Weeks.");
         else if(dreamerTime[worstDreamerSpot] == 2)
            System.out.println((dreamerTime[worstDreamerSpot]/2) + " Week.");
         else if(dreamerTime[worstDreamerSpot] == 1)
            System.out.println("Half a week.");
         else
            System.out.println("Right away.");
         //*/
      }//End of if.

      //Roll for the Changeling's suite of dreamers at the end of the week.
      for(int i = 0; dreamerTime.length > i; i++)
      {
         //Lower the current dreamer's timer by 1.
         insightRoll[i] = insightRoll[i] - 1;
         
         //If the dreamer can now be harvested, do so.
         if(dreamerTime[i] <= 0 && harvestCount < maxHarvests)
         {
            //Incriment the amount of times the Changeling has harvested.
            harvestCount++;
            
            //Roll the Changeling's harvest roll, then subtract the DC from their roll.
            glamourHarvested = dieRoll(harvestRoll,randomNumber) - reverieDC;
            
            //If the harvest roll was successful, add it to their total harvested glamour.
            if(glamourHarvested > 0)
               totalGlamourHarvested += glamourHarvested;
            
            //Code for a test print.
            /*
            System.out.println(glamourHarvested + " glamour gained. " + totalGlamourHarvested + " total. " + harvestCount + " total harvests.");
            //*/
            
            //Roll wits + insight, then store that value.
            insightRoll[i] = dieRoll(witsInsight,randomNumber);
            
            //Check how that applies to the dreamer.
            if(insightRoll[i] >= 5)
               dreamerTime[i] = 0;
            else
            {
               switch(insightRoll[i])
               {
                  case 4:  dreamerTime[i] = 1;
                           break;
                  case 3:  dreamerTime[i] = 2;
                           break;
                  case 2:  dreamerTime[i] = 4;
                           break;
                  case 1:  dreamerTime[i] = 8;
                           break;
                  default: dreamerTime[i] = 20;
                           break;
               }//End of switch.
            }//End of else.
         
            //Code for a test print.
            /**
            System.out.print(insightRoll[i] + " Successes: ");
            if(dreamerTime[i] > 2)
               System.out.println((dreamerTime[i]/2) + " Weeks.");
            else if(dreamerTime[i] == 2)
               System.out.println((dreamerTime[i]/2) + " Week.");
            else if(dreamerTime[i] == 1)
               System.out.println("Half a week.");
            else
               System.out.println("Right away.");
            //*/
            
            //Lower the incriment value. 
            i = i - 1;
         }//End of if.
      }//End of for loop.
      
      //Return the glamour gained.
      return totalGlamourHarvested;
   }//End of reverieHarvestWeek.
   
   //-----Misc. Utility Methods-----
   
   /**
   Simulate a single die roll then return the number of successes.
   @dieCount      int            -  The number of dice to be rolled.
   @randomNumber  Random         -  From the random utility. This is the name of the Random object created in the main program.
   @returns       successCount   -  The number of successes rolled.
   */
   public static int dieRoll(int dieCount, Random randomNumber)
   {
      //Create an int array to store dice rolls.
      int[] diceRolled = new int[dieCount];
      
      //Declare integers to keep track of the number of successes rolled.
      int tenCount = 0;
      int successCount = 0;
      
      //Simulate die rolls for the Changeling.
      for(int j = 0; dieCount > j; j++)
      {
         //Roll a die.
         diceRolled[j] = 1+randomNumber.nextInt(10);
         
         //Code for a test print.
         //System.out.print(diceRolled[j] + ", ");
         
         //Keep track of the number of successes and tens rolled.
         if(diceRolled[j] == 10)
         {
            tenCount++;
            successCount++;
         }//End of if.
         else if(diceRolled[j] >= 6)
         {
            successCount++;
         }//End of else if.
      }//End of for loop to simulate die rolls.
      //Add the crit bonus after all rolls have been simulated.
      if(tenCount >= 2)
      {
         successCount += Math.floor(tenCount / 2) * 2;
      }//End of if.
         
      //Return the number of successes.
      return successCount;
   }//End of dieRoll.
   
   /**
   Check if a file exists after being given a string containing the file name, then create a scanner for it if it does exist. Exit the program if it does not.
   @fileName   String   - The name of the file you want to create a scanner for.
   @return     fileScan - This is the scanner for the file name you entered.
   */
   public static Scanner createScannerFromString(String fileName) throws IOException
   {
      //Create a new file object using the string passed into this method.
      File inFile = new File(fileName);
      
      //Check if the file exists.
      if (!inFile.exists())
      {
         System.out.println("File does not exist.");
         System.exit(0);
      }//End of if.
      
      //Create the scanner.
      Scanner fileScan = new Scanner(inFile);
      
      //Return the scannner for external use.
      return fileScan;
   }//End of createScannerFromString.
   
   /**
   Create a simple scanner for user input via keyboard. 
   @return  scan  - From the Scanner util. A scanner to accept user input via their keyboard. 
   */
   public static Scanner createKeyBoardScanner()
   {
      //Create a scanner.
      Scanner scan = new Scanner(System.in);
      
      //Return the scanner.
      return scan;
   }//end createKeyBoardScanner.
   
   /**
   Use this after getting input from a user via System.in. This checks if a String is empty,
   flushes the line if it is, then prompts the user for entry and returns a String.
   @firstRead  String      - A String to check the status of.
   @keyBoard   Scanner     - A Scanner meant to recieve input from a keyboard.
   @return     secondRead  - The new String to return.
   */
   public static String checkIfInputIsEmpty(String firstRead, Scanner keyBoard)
   {
      //Declare and initialize a String.
      String secondRead = "";
      
      //If firstRead is blank, get the user's response again.
      if(firstRead.length() < 1)
         secondRead = keyBoard.nextLine();
      else
         secondRead = firstRead;
         
      //Return the corrected String.
      return secondRead;
   }//End of checkIfInputIsEmpty.
}//End of ChangelingHarvesting class.