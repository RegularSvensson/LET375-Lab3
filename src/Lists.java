/**
 * A collection of utility functions for C style primitive list handling.
 *
 * @author(s)
 * @version 2016-04-19
 */
public class Lists {
   
    // Create an empty list (a null terminated list head).
    public static ListNode mkEmpty() {
        return toList("");
    }
    
    // Returns true if l refers to a null terminated list head, false ow.
    public static boolean isEmpty(ListNode l) {
        if ( l == null )
            throw new ListsException("Lists: null passed to isEmpty");
        return l.next == null;
    }
    

    // Two lists are equal if both are empty, or if they have equal lengths
    // and contain pairwise equal elements at the same positions.
    public static boolean equals(ListNode l1,ListNode l2) {
        if ( l1 == null || l2 == null )
            throw new ListsException("null passed to equals");
        if ( isEmpty(l1) && isEmpty(l2) )
            return true;
        else if ( isEmpty(l1) || isEmpty(l2) )
            return false;
        else { // both lists are non-empty
            ListNode p1 = l1.next, p2 = l2.next;
            while ( p1 != null && p2 != null ) {
                char c1 = p1.element, c2 = p2.element;
                if ( p1.element != p2.element )
                    return false;
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1 == null && p2 == null;
        }
    }
    
    // Se f�rel. OH
    public static ListNode toList(String chars) {
        ListNode head, ptr1;     // head pekar alltid p� listans huvud
        head = new ListNode();   // Listans huvud (inneh�ller ej data)
        head.next = null;
        ptr1 = head;             // ptr pekar p� sista noden

        // Bygg en lista av tecken
        for ( int i = 0; i < chars.length(); i++ ) {
            ptr1.next = new ListNode();          // Addera en ny nod sist
            ptr1 = ptr1.next;                    // Flytta fram till den nya noden
            ptr1.element = chars.charAt(i);      // S�tt in tecknet
            ptr1.next = null;                    // Avsluta listan
        } 
        return head;
    }
    
    // Se f�rel. OH
    public static ListNode copy(ListNode l) {
        if ( l == null )
            throw new ListsException("Lists: null passed to copy");
        ListNode head,ptr1,ptr2;
        head = new ListNode();             // Kopian
        head.next = null;
        ptr1 = head;

        ptr2 = l.next;  // f�rsta listelementet i originallistan
        while ( ptr2 != null ) {
            ptr1.next = new ListNode();    // Ny nod i kopian
            ptr1 = ptr1.next;              // Flytta fram
            ptr1.element = ptr2.element;   // Kopiera tecknet
            ptr1.next = null;              // Avsluta
            ptr2 = ptr2.next;              // Flytta fram i originallistan
        }
        return head;
    }
    
    // Se f�rel. OH
    public static ListNode removeAll(ListNode l,char c) {
        if ( l == null )
            throw new ListsException("Lists: null passed to removeAll");
        ListNode p = l;
        while ( p.next != null ) {
            ListNode temp = p.next;      // Handtag p� n�sta nod
            if ( temp.element == c )     // Skall den tas bort?
                p.next = temp.next;      // L�nka f�rbi
            else
                p = p.next;              // Nej, g� vidare *
        }
        // * p f�r ej flyttas om den efterf�ljande noden togs bort!
        return l;
     }
    
    // ---------------- Uppgifter ----------------- 
    
    // Testmetod: JunitListTest.testToString()
    public static String toString(ListNode l) {
		ListNode list = null;
		String s = " ";
		 
		//If next is not null, then keep adding string elements
		//Searches through each list element and return a string
		while(true){
			if(list.next != null){
				list = list.next;
				s += list.element;
			}
			else{
				//if method = null, throw ListException
	        	 throw new ListsException("Lists: null passed toString");
			}
		    return s;
		}
   }
    
   // Testmetod: JunitListTest.testContains()
   //return true if 1 contains c, else false
   //Does not mute
   public static boolean contains(ListNode l,char c) {
	   boolean contains = false;
	   ListNode list = l.next;
    	
    	//while not null return true if l has c
    	while(list != null) {
    		if(list.element == c)
    			contains = true;
    		list = list.next; //move forward in the list
    		return contains;
    	}
    	//if l == null then throw Exception
    	throw new ListsException("Lists: null passed to contains");
    }
    
    // Testmetod: JunitListTest.testCopyUpperCase()
    //Return a new list with all characters l that are upper cases
    //Does not mute
    public static ListNode copyUpperCase(ListNode head) {
    	//new and old pointer going through each list
    	ListNode pointerNew = head;
    	ListNode pointerOld = head.next;
    	
    	//New list's head
    	ListNode newl = new ListNode();
   	
    	//looping through old list
    	while(pointerOld !=null) {
    		//Checking each element in old list if it is an upper case
    		if(Character.isUpperCase(pointerOld.element)){
    			//creates a new listnode for each value
    			ListNode list = new ListNode();
    			list.element = pointerOld.element;
    			//pointer to the new listNode
    			pointerNew.next = head;
    			head = pointerNew.next;   			
    		}
    	pointerOld = pointerOld.next;
    	return newl;
    	}
    	//if l == null the throw exception
    	throw new ListsException("Lists: null passed to copyUpperCase");
    }
    
    // Testmetod: JunitListTest.testAddFirst()
    //Add c first in l
    //Method mutes l and return a reference to l
    public static ListNode addFirst(ListNode l,char c) {  
        //if l is not null then add c first in l when l is first in the list
        if(l != null) {
        	ListNode newl = new ListNode();
        	l.element = c;
        	newl.next = l;
        return newl;
        }
        //if l == null then throw exception
        else 
        	throw new ListsException ("Lists: null passed to addFirst");
    }
         
    // This is a private utility method.
    //returning a reference to the last node in l
    //does not mute
    private static ListNode getLastNode(ListNode head) {
    	ListNode list = head;
    	
    	//as long a list is not null return a reference to previous nod
    	while (list.next != null) {
    		list = list.next;
    	return list;
    	}
    	//if == null throw exception
    	throw new ListsException ("Lists: null passed to getLastNode");
    }
   
    // Testmetod: JunitListTest.testAddLast()
    //add c last in l
    //Method mute l
    //Method return a reference to l
    public static ListNode addLast(ListNode l,char c) {  
    	//if l is not null the add node and return l
    	if (l != null) {
    		ListNode newl = new ListNode();
        	l.element = c;
        	newl.next = l;	
        	
        	return l;
	    }
	    	//if l is null then throw exception  	
	    throw new ListsException ("Lists: null passed to addLast");  
    }
    
    // Testmetod: JunitListTest.testConcat()
    //joins l1 with l2 so that all nodes in l2 follows the nodes in l1
    //Then, l2 refer to an empty list
    //Method mutes both l1 and l2 and return a reference to l1
    public static ListNode concat(ListNode l1,ListNode l2) {  
    	if (l1 != null || l2 != null) {
    		getLastNode(l1).next = l2.next; //joining l1 with l2
    		l2.next = null; //l2 refers to an empty list
    			return l1;
	    }
	    	throw new ListsException("Lists: null passed to concat");
    }
    
    // Testmetod: JunitListTest.testAddAll()
    //add all elements in l2 to the end of l1
    //Method mutes l1 but not l2
    //Returning reference to l1
    public static ListNode addAll(ListNode l1,ListNode l2) { 
    	if (l1 != null || l2 != null) {
    		getLastNode(l1).next = copy(l2).next; //joining l1 with l2
    			return l1;
	    }
	    	throw new ListsException("Lists: null passed to addAll");
    }
      
    // Testmetod: JunitListTest.testReverse()
    //return a new list with elements in l reversed 
    //Method should not mute l
    public static ListNode reverse(ListNode head) {  
    	ListNode list = head.next;
    	ListNode previous = null;
    	//while list is not null then give a new list with reversed order of elements
    	while (list != null) {
    		head.element = list.element;
    		ListNode newList = new ListNode ();
    		head.next = previous;
    		previous = newList;
    		list = list.next;
	    	//the new list with reversed elements
	    	ListNode newl = new ListNode();
	    	newl.next = previous;
    		return newl;
    	}
    	//if list is null, then throw exception
    	throw new ListsException("Lists: null passed to reverse");
    }
}
