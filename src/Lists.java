/**
 * A collection of utility functions for C style primitive list handling.
 *
 * @author(s) Mona Khoshoi, Elias Svensson
 * @version 2016-04-21
 * Email: khoshoimona@gmail.com, elias.svensson.1992@gmail.com
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
    /**
     * Returns a list as a string
     * @param l list
     * @return s string
     */
    public static String toString(ListNode l) {
        // check exception 
    	Exception(l, "toString");
    	String s = "";
    	ListNode nextListNode = l.next;
    	// Add elements to string
    	while(nextListNode != null){
    		s += nextListNode.element;
    		nextListNode = nextListNode.next;
    	}
    	return s;     
    }
    
    // Testmetod: JunitListTest.testContains()
    /**
     * Checks if a list contains a char
     * @param head
     * @param c
     * @return true or false
     */
    public static boolean contains(ListNode head,char c) {
        // check exception
    	Exception(head, "contains");
    	boolean found = false;
    	ListNode nextHead = head.next;
    	// check if char found
    	while(nextHead != null && !found){
    		if(nextHead.element == c)
    			found = true;
    		nextHead = nextHead.next;
    	}
    	return found;
    }
    
    // Testmetod: JunitListTest.testCopyUpperCase()
    /**
     * Returns a copy of a list with upper case characters
     * @param head
     * @return
     */
    public static ListNode copyUpperCase(ListNode head) {
        // check exception
    	Exception(head, "copyUpperCase");
    	ListNode headNew = new ListNode();
    	ListNode pointerNew = headNew;
    	ListNode pointerOld = head.next;
    	while(pointerOld != null){
    		// check if element in old list is upper case
    		if(Character.isUpperCase(pointerOld.element)){
    			// add new node for this character
    			ListNode listNode = new ListNode();
    			listNode.element = pointerOld.element;
    			// update new pointer 
    			pointerNew.next = listNode;
    			pointerNew = pointerNew.next;
    		}
    		// update old pointer
    		pointerOld = pointerOld.next;
    	}
    	return headNew;
    }
    
    // Testmetod: JunitListTest.testAddFirst()
    /**
     * Adds a character to list
     * @param l
     * @param c
     * @return
     */
    public static ListNode addFirst(ListNode l,char c) {  
    	// check exception
    	Exception(l, "addFirst");
        ListNode head = new ListNode();
        // update element of l to c
        l.element = c;
        // update next head to l
        head.next = l;
        return head;
    }
         
    // This is a private utility method.
    /**
     * Returns the last node in a list
     * @param head
     * @return
     */
    private static ListNode getLastNode(ListNode head) {
    	ListNode listNode = head;
    	while(listNode.next != null){
    		listNode = listNode.next;
    	}
    	return listNode;
    }
   
    // Testmetod: JunitListTest.testAddLast()
    /**
     * Returns a list with a character added last in the list
     * @param l
     * @param c
     * @return
     */
    public static ListNode addLast(ListNode l,char c) {  
        // check exception
    	Exception(l, "addLast");
    	ListNode listNode = l;
        while(true){
        	// check if listNode is null
        	if(listNode.next == null){
        		// update listNode element to c
        		listNode.next = new ListNode();
        		listNode.next.element = c;
        		break;
        	}
        	else
        		// update listNode
        		listNode = listNode.next;
        }
        return l;
    }
    
    // Testmetod: JunitListTest.testConcat()
    /**
     * Returns a list that is a concatenation of two lists
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode concat(ListNode l1,ListNode l2) {  
        // check exceptions
    	Exception(l1, "concat");
        Exception(l2, "concat");
        // concatenate lists
        ListNode listNode = getLastNode(l1);
        listNode.next = l2.next;
        l2.next = null;
        return l1;
    }
    
    // Testmetod: JunitListTest.testAddAll()
    /**
     * Returns a list with all nodes of another list added
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addAll(ListNode l1,ListNode l2) { 
        // check exceptions
    	Exception(l1, "addAll");
    	Exception(l2, "addAll");
    	// add elements to node
    	getLastNode(l1).next = copy(l2).next;
    	return l1;
    }
      
	// Testmetod: JunitListTest.testReverse()
    /**
     * Returnes a reversed list
     * @param head
     * @return
     */
	public static ListNode reverse(ListNode head) {
		// check exception
		Exception(head, "reverse");
		ListNode oldListNode = null;
		ListNode listNode = head.next;
		// reverse list
		while(listNode != null){
			ListNode newListNode = new ListNode();
			newListNode.element = listNode.element;
			newListNode.next = oldListNode;
			oldListNode = newListNode;
			listNode = listNode.next;
		}
		ListNode headNew = new ListNode();
		headNew.next = oldListNode;
		return headNew;
	}
    /**
     * Throws an exception if ListNode is null
     * The exception contains a specified string
     * @param l
     * @param s
     */
    private static void Exception(ListNode l, String s){
    	if(l == null)
    		throw new ListsException("Lists: null passed to" + s);
    }
}
