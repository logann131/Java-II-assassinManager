/* Nam Phu Nguyen (Logan)
   IT220-JAVA II 
   Programing Assignment
 */ 
import java.util.*;
public class  AssassinManager {
    // YOUR CODE GOES HERE


    /***************** DO NOT MODIFY AssassinNode   **************************/
    /**
     * Each AssassinNode object represents a single node in a linked list
     * for a game of Assassin.
     */
    private static class AssassinNode
    {
        public final String name;  // this person's name
        public String killer;      // name of who killed this person (null if alive)
        public AssassinNode next;  // next node in the list (null if none)

        /**
         * Constructs a new node to store the given name and no next node.
         */
        public AssassinNode(String name)
        {
            this(name, null);
        }

        /**
         * Constructs a new node to store the given name and a reference
         * to the given next node.
         */
        public AssassinNode(String name, AssassinNode next)
        {
            this.name = name;
            this.killer = null;
            this.next = next;
        }
    }//end of AssasinNode

    private AssassinNode frontKillRing; //the kill ring
    private AssassinNode frontGraveyard; //front of Graveyard

    //paramative constructor
    public AssassinManager(List<String> names ){
        //if names == null || names.size() == 0 (empty)
        if (names == null || names.size() == 0) {
            throw new IllegalArgumentException();
        }
        //TODO: get the value name from the List<String> names
        for (int i=0; i<names.size(); i++) {
            String name = names.get(i);
            //AssassinNode
            AssassinNode assassin = new AssassinNode(name);

            //add assassin into frontKillRing
            if (frontKillRing == null) {
                frontKillRing = assassin;
            } else {
                AssassinNode current = frontKillRing;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = assassin;
            }
        }

    }//end of AssassinManager here

    //method printKillRing()
    public void printKillRing(){

        AssassinNode current = frontKillRing;
        while(current != null){
            if (current.next == null){
                System.out.println("    "+current.name+ " is stalking " +frontKillRing.name);
            } else {
                System.out.println("    "+current.name+ " is stalking " +current.next.name);
            }
            current = current.next;
        }

    } // end of print Kill Ring

    //void method printGraveYeard
    public void printGraveyard(){
        if(!isGameOver()){
            System.out.println("Current graveyard: ");
        } else {
            System.out.println("Final graveyard is as follow: ");
        }

        AssassinNode current = frontGraveyard;
        while(current != null){
            if(current.next == null) {
                System.out.println("    " +current.name+ " was killed by " +current.killer);
            } else {
                System.out.println("    " +current.name+ " was killed by " +current.killer);
            }
            current = current.next;
        }
    }

    //boolean method killRingContatins
    public boolean killRingContains(String name){

        //iterate through frontKillRing to find if name was found
        AssassinNode current = frontKillRing;
        while(current != null){
            if(current.name.equalsIgnoreCase(name)) { //Ignore Case
                return true;
            }
            current = current.next;
        }
        return false;
    }

    //boolean method graveyardContains
    public boolean graveyardContains(String name){
        //iterate the frontGraveYard
        AssassinNode current = frontGraveyard;
        while(current != null) {
            if(current.name.equalsIgnoreCase(name)){ //ignore case
                return true;
            }
            current = current.next; //update current
        }
        return false;
    }

    //boolean method isGameOver()
    public boolean isGameOver(){
        return frontKillRing.next == null;
    }

    //method winner
    public String winner(){
        if(isGameOver()){
            return frontKillRing.name;
        }
        return null;
    }

    //method Kill
    public void kill(String name){
        //throw IllegalStateException if the game is over
        if (isGameOver()) {
            throw new IllegalStateException();
        } else if (!killRingContains(name)){
            throw new IllegalArgumentException();
        } else {
            AssassinNode theDeadAssa = null;
            if (frontKillRing.name.equalsIgnoreCase(name)){ //if victim is at the
                                                            // front of the kill ring
                String assassin = null;
                AssassinNode current = frontKillRing;
                while(current != null){
                    if (current.next == null){
                        assassin = current.name;
                    }
                    current = current.next;
                }
                theDeadAssa = frontKillRing;
                theDeadAssa.killer = assassin;
                frontKillRing = frontKillRing.next;
            } else {                                //victim is NOT at the front of the kill ring
                AssassinNode current = frontKillRing;
                while (current.next != null){
                    if(current.next.name.equalsIgnoreCase(name)){
                        theDeadAssa = current.next;
                        theDeadAssa.killer = current.name;
                        if (current.next.next != null) {   //if there is someone after victim
                            current.next = current.next.next;
                            break;
                        } else {                            //if there is no one
                            current.next = null;
                            break;
                        }
                    }
                    current = current.next;
                }

            }
            //TODO update frontGraveyard
            if(frontGraveyard != null){
                theDeadAssa.next = frontGraveyard;
            } else {
                theDeadAssa.next = null;
            }
            frontGraveyard = theDeadAssa;
        }

    }
}










