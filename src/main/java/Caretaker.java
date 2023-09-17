
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Ali Haider
 */
public class Caretaker {

    int counter = 1;
    int current_index = 0;
    List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }

    public int get_length() {
        return mementoList.size();
    }

    //This fucntion removes all future states are removed 
    public void remove_states() {
        for (int i = current_index + 1; i < mementoList.size(); i++) {
            mementoList.remove(i);
            counter = 0;
        }
    }

    //undo state    
    public Memento undo() {
        current_index = mementoList.size() - counter;//updating current variable
        //this method checks if the counter is less then the size of the list
        //if it does then it will rteurn the memento from calculating the index as listsize-1 (-1 to avoid outof boundindex) - counter that is updated after each return 
        //e.g most previous state is calulated as 
        //let size=3 and counter is intitalied to 1
        //then index = 3-1-1 =3-2=1 
        // so index =1 that is 0n index 2 lies current state and previos state lies at 1 
        //counter is updated by a single increment to keep track of the stages that we can go on every undo button trigger i-e counter=2

        return counter < mementoList.size() ? mementoList.get(mementoList.size() - 1 - counter++) : mementoList.get(mementoList.size() - counter);
    }

    public Memento redo() {
        //this method if the counter is greater then 1
        //then future index is calculatd as 
        //3+1-2=2 i-e was the future state according to the previous scenerio and 
        //then counter is decremented by 1  on each redo opeartion i-e counter is now =1
        current_index = mementoList.size() + 2 - counter;
        return counter > 1 ? mementoList.get(mementoList.size() + 1 - counter--) : mementoList.get(Math.abs(mementoList.size() - 1));
    }

}

/*
v1 v2 v3 v4 v5 v6

size=5  index=5-1=4 v5 Counter=2
size=5  index=5-2=3 v4 counter=3
size=5  index=5-3=2 v3 counter=4
size=5  index=5-4=1 v2 counter=5
size=5  index=5-5=0 v1 counter=6


5-6+2 =1 5
5-5+2 =2 4
5-4+2 =3 3
 */
