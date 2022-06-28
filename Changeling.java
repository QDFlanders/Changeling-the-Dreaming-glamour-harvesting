/**
Create a changeling object to represent changeling characters for calculating glamour harvesting.
*/

public class Changeling
{
   //Declare public attributes.
   public final int AVG_DREAMER_CONTEST = 4;
   public final int SEELIE_REVERIIE_DC = 2;
   public final int RAPTURE_DC = 3;
   public final int SEELIE_RAVAGE_DC = 3;
   
   
   //Declare private attributes.
   
   //Declare a String object for the Changeling's name.
   private String name;
   
   //Declare two String objects for the Changeling's Kith and House.
   private String kith;
   private String house;
   
   //Declare integers for Changeling stats.
   private int cha;
   private int com;
   private int man;
   private int res;
   private int wit;
   
   //Declare integers for changeling skills.
   private int insight;
   private int bestReverieSkill;
   private int bestRaptureSkill;
   
   //Declare values for other changeling details.
   private int banality;
   private int glamour;
   private int dross;
   private boolean isUnseelie;
   
   //Changeling backgrounds.
   private int dreamerDots;
   private int dreamerCount;
   
   //Changeling arts.
   private int summerDots;
   private int sovereignDots;
   private int taleCraftDots;
   
   //EXP spent by Changeling.
   private int expSpent;
   
   //Average rolls for changelings.
   private int avgWitInsight;
   private int avgReverieHarvest;
   private int avgRaptureHarvest;
   private int avgRavagingHarvest;
   
   /**
   Used to instantiate a Changeling object when no information is provided.
   */
   public Changeling()
   {
      //Use the secondary constructor to initalize the default constructor.
      //Set numeric values to -1 for future comparison.
      this("(No name.)","(No kith.)","(No house.)",false,1,1,1,1,1,0,0,0,0,0,0,0,0,4,0,0);
   }//End of default constructor.
   
   /**
   @name             String   -  The Changeling's name.
   @kith             String   -  The Changeling's kith.
   @house            String   -  The name of the Changeling's house.
   @isUnseelie       boolean  -  True if the Changeling is an unseelie fae.
   @cha              int      -  The charisma score of the Changeling.
   @com              int      -  The composure score of the Changeling.
   @man              int      -  The manipulation score of the Changeling.
   @res              int      -  The resolve score of the Changeling.
   @wit              int      -  The wits score of the Changeling.
   @insight          int      -  The insight score of the Changeling.
   @bestReverieSkill int      -  The largest pool the Changeling can roll to inspire a dreamer, not including the dreamer background.
   @bestRaptureSkill int      -  The largest pool the Changeling can roll to inspire themselves.
   @dreamerDots      int      -  The Changeling's dots in the dreamer background.
   @sovereignDots    int      -  The Changeling's dots in the sovereign art.
   @summerDots       int      -  The Changeling's dots in the summer art.
   @banality         int      -  The Changeling's current banality.
   @glamour          int      -  The Changeling's current glamour.
   @dross            int      -  The Changeling's current dross.
   @expSpent         int      -  The EXP the Changeling has spent.
   */
   public Changeling(String name, String kith, String house, boolean isUnseelie, int cha, int com, int man, int res, int wit, int insight, int bestReverieSkill, int bestRaptureSkill, int dreamerDots, int sovereignDots, int summerDots, int taleCraftDots, int banality, int glamour, int dross, int expSpent)
   {
      //Set the Strings and boolean to accept the entered values.
      this.name = name;
      this.kith = kith;
      this.house = house;
      this.isUnseelie = isUnseelie;

      //Ensure the integers are valid. Set thier values to -1 if they are not valid.      
      //Charisma.
      if(cha >= 1)
         this.cha = cha;
      else
         this.cha = -1;
      
      //Composure.
      if(com >= 1)
         this.com = com;
      else
         this.com = -1;
      
      //Manipulation.   
      if(man >= 1)
         this.man = man;
      else
         this.man = -1;
      
      //Resolve.   
      if(res >= 1)
         this.res = res;
      else
         this.res = -1;
      
      //Wits.
      if(wit >= 1)
         this.wit = wit;
      else
         this.wit = -1;
      
      //Insight.
      if(insight >= 0)
         this.insight = insight;
      else
         this.insight = -1;
      
      //Best pool for Reverie.
      if(bestReverieSkill >= 0)
         this.bestReverieSkill = bestReverieSkill;
      else
         this.bestReverieSkill = -1;
      
      //Best pool for Rapture.
      if(bestRaptureSkill >= 0)
         this.bestRaptureSkill = bestRaptureSkill;
      else
         this.bestRaptureSkill = -1;
      
      //Dots in the Dreamers background.
      if(dreamerDots >= 0)
         this.dreamerDots = dreamerDots;
      else
         this.dreamerDots = -1;
      
      //Dots in the Sovreign art.
      if(sovereignDots >= 0)
         this.sovereignDots = sovereignDots;
      else
         this.sovereignDots = -1;
      
      //Dots in the Summer art.
      if(summerDots >= 0)
         this.summerDots = summerDots;
      else
         this.summerDots = -1;
      
      //Dots in the Tale Craft art.
      if(taleCraftDots >= 0)
         this.taleCraftDots = taleCraftDots;
      else
         this.taleCraftDots = -1;
      
      //Banality, Dross, and Glamour.
      if(banality >= 0)
         this.banality = banality;
      else
         this.banality = -1;
         
      if(dross >= 0)
         this.dross = dross;
      else
         this.dross = -1;
         
      if(isUnseelie)
         this.isUnseelie = isUnseelie;
      else
         this.isUnseelie = false;
      
      //Dreamers count.
      if(dreamerDots >= 0)
         switch(dreamerDots)
         {
            case 1:  this.dreamerCount = 1;
                     break;
            case 2:  this.dreamerCount = 2;
                     break;
            case 3:  this.dreamerCount = 4;
                     break;
            case 4:  this.dreamerCount = 6;
                     break;
            case 5:  this.dreamerCount = 8;
                     break;
            default: this.dreamerCount = 0;
                     break;
         }//End of switch.
      else
         this.dreamerCount = -1;
         
      //Experience points spent.
      if(expSpent >= 0)
         this.expSpent = expSpent;
      else
         this.expSpent = -1;
   }//End of secondary constructor.
   
   /**
   Creates a deep copy of a Changeling object.
   @originalChangeling  Changeling  -  The Changeling object to make a copy of.
   */
   public Changeling(Changeling originalChangeling)
   {
      //Pass data from originalChangeling through the secondary constructor.
      this(originalChangeling.name, originalChangeling.kith, originalChangeling.house, originalChangeling.isUnseelie, originalChangeling.cha, originalChangeling.com, originalChangeling.man, originalChangeling.res, originalChangeling.wit, originalChangeling.insight, originalChangeling.bestReverieSkill, originalChangeling.bestRaptureSkill, originalChangeling.dreamerDots, originalChangeling.sovereignDots, originalChangeling.summerDots, originalChangeling.taleCraftDots, originalChangeling.banality, originalChangeling.glamour, originalChangeling.dross, originalChangeling.expSpent);
   }//End of copy constructor.
   
   //-----Getters-----
   
   /**
   Returns the name of the Changeling object.
   */
   public String getName()
   {
      return this.name;
   }//End of getName.
   
   
   /**
   Returns the isUnseelie status of the Changeling object.
   */
   public boolean getUnseelie()
   {
      return this.isUnseelie;
   }//End of getCha.
   
   /**
   Returns the charisma score of the Changeling object.
   */
   public int getCha()
   {
      return this.cha;
   }//End of getCha.
   
   /**
   Returns the manipulation score of the Changeling object.
   */
   public int getMan()
   {
      return this.man;
   }//End of getMan.
   
   /**
   Returns the number of dots a Changeling has in the dreamers background.
   */
   public int getDreamerDots()
   {
      return this.dreamerDots;
   }//End of getDreamerDots.
   
   /**
   Returns the number of Summer Art dots the Changeling object has.
   */
   public int getSummerDots()
   {
      return this.summerDots;
   }//End of getSummerDots.
   
   /**
   Returns the number of Sovereign Art dots the Changeling object has.
   */
   public int getSovereignDots()
   {
      return this.sovereignDots;
   }//End of getSovereignDots.
   
   /**
   Returns the number of Tale Craft Art dots the Changeling object has.
   */
   public int getTaleCraftDots()
   {
      return this.taleCraftDots;
   }//End of getTaleCraftDots.
   
   /**
   Returns the average wits + insight roll for the Changeling object.
   */
   public int getAvgWitInsight()
   {
      return this.avgWitInsight;
   }//End of getAvgWitInsight.
   
   /**
   Returns the average amount of glamour a Changeling recieves from reverie.
   */
   public int getAvgReverieHarvest()
   {
      return this.avgReverieHarvest;
   }//End of getAvgReverieHarvest.
   
   /**
   Returns the average amount of glamour a Changeling recieves from rapture.
   */
   public int getAvgRaptureHarvest()
   {
      return this.avgRaptureHarvest;
   }//End of getAvgRaptureHarvest.
   
   /**
   Returns the average amount of glamour a Changeling recieves from ravaging.
   */
   public int getAvgRavagingHarvest()
   {
      return this.avgRavagingHarvest;
   }//End of getAvgRavagingHarvest.
   
   /**
   Returns the EXP of the Changeling object.
   */
   public int getEXP()
   {
      return this.expSpent;
   }//End of getEXP.
   
   //-----Setters-----
   
   /**
   Set the average wits + insight roll for the Changeling object.
   */
   public void setAvgWitInsight(int avgWitInsight)
   {
      this.avgWitInsight = avgWitInsight;
   }//End of setAvgWitInsight.
   
   /**
   Set the average amount of glamour a Changeling recieves from reverie.
   */
   public void setAvgReverieHarvest(int avgReverieHarvest)
   {
      this.avgReverieHarvest = avgReverieHarvest;
   }//End of setAvgReverieHarvest.
   
   /**
   Set the average amount of glamour a Changeling recieves from rapture.
   */
   public void setAvgRaptureHarvest(int avgRaptureHarvest)
   {
      this.avgRaptureHarvest = avgRaptureHarvest;
   }//End of setAvgRaptureHarvest.
   
   /**
   Set the average amount of glamour a Changeling recieves from ravaging.
   */
   public void setAvgRavagingHarvest(int avgRavagingHarvest)
   {
      this.avgRavagingHarvest = avgRavagingHarvest;
   }//End of setAvgRavagingHarvest.
   
   //-----Changeling Roll Pools-----
   
   /**
   Finds and returns the Changeling's wits + insight pool for understanding what inspires the Dreamer.
   @returns witsInsight - The changeling's wits + insight pool for understanding what inspires the Dreamer.
   */
   public int witsInsight()
   {
      int witsInsight = this.wit + this.insight + this.dreamerDots;
      return witsInsight;
   }//End of WitsInsight.
   
   /**
   Finds and returns the Changeling's charisma or manipulation + their best skill pool for inspiring Dreamers.
   @returns inspireRoll - The changeling's roll to inspire a Dreamer.
   */
   public int reverieRoll()
   {
      int inspireRoll = this.bestReverieSkill + this.dreamerDots;
      
      if(this.cha >= this.man)
         inspireRoll = inspireRoll + this.cha;
      else
         inspireRoll = inspireRoll + this.man;
         
      return inspireRoll;
   }//End of reverieRoll.
   
   /**
   Finds and returns the Changeling's charisma or manipulation + their best skill pool for inspiring themselves.
   @returns inspireRoll - The changeling's roll to enter a rapture.
   */
   public int raptureRoll() 
   {
      int inspireRoll = this.bestRaptureSkill;
      
      if(this.cha >= this.man)
         inspireRoll = inspireRoll + this.cha;
      else
         inspireRoll = inspireRoll + this.man;
         
      return inspireRoll;
   }//End of raptureRoll.
   
   /**
   Finds and returns the Changeling's resolve + composure pool for ravaging Dreamers.
   @returns inspireRoll - The changeling's roll to ravage a Dreamer.
   */
   public int ravageRoll() 
   {
      int inspireRoll = this.res + this.com + ((1+this.dreamerDots)/2);
      return inspireRoll;
   }//End of ravageRoll.
   
   //-----toString-----
   
   /**
   Outputs information on the Changeling object.
   @returns changelingInfo - A String object containing information on the Changeling object. 
   */
   public String toString()
   {
      String changelingInfo = "";
      
      changelingInfo += name + " rolls " + String.format("%d",witsInsight()) + " dice to determine what inspires a dreamer, with an average roll of " + avgWitInsight + ".\n";
      changelingInfo += name + " rolls " + String.format("%d",reverieRoll()) + " dice to inspire a dreamer, gaining " + avgReverieHarvest + " glamour on average.\n";
      changelingInfo += name + " rolls " + String.format("%d",raptureRoll()) + " dice to inspire themselves, gaining " + avgRaptureHarvest + " glamour on average.\n";
      changelingInfo += name + " rolls " + String.format("%d",ravageRoll()) + " dice to ravage a dreamer, gaining " + avgRavagingHarvest + " glamour on average.";
      
      return changelingInfo;
   }//End of toString.
   
}//End of Changeling class.