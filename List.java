public class List {
    public Node first; // Reference to the first node in this list (CharData object)
    public int size; // Count of elements in this list

    /** Creates an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    /** Retrieves the number of elements in this list. */
    public int getSize() {
        return size;
    }

    /** Retrieves the first element in the list */
    public CharData getFirst() {
        return first.cd;
    }

    /**
     * Identifies the index of the first CharData object in this list
     * with the same chr value as the given char,
     * or -1 if there is no such object in this list.
     */
    public int indexOf(char chr) {
        Node current = first;
        int i = 0;
        while (current != null) {
            if (current.cd.equals(chr)) {
                return i;
            }
            current = current.next;
            i++;
        }
        return -1; // Not found
    }

    /** Inserts a CharData object with the given character at the beginning of the list. */
    public void addFirst(char chr) {
        Node newNode = new Node(new CharData(chr));
        newNode.next = first;
        first = newNode;
        size++;
    }

    /** Checks equality between two lists. */
    public boolean equals(List list) {
        if (size != list.size)
            return false;
        Node current = first;
        Node other = list.first;
        while (current != null) {
            if (!current.cd.equals(other.cd))
                return false;
            current = current.next;
            other = other.next;
        }
        return true;
    }

    /**
     * If the given character exists in one of the CharData objects in this list,
     * increments its counter. Otherwise, adds a new CharData object with the
     * given chr to the beginning of this list.
     */
    public void update(char chr) {
        int i = indexOf(chr);
        if (i >= 0) {
            this.get(i).count++;
        } else {
            addFirst(chr);
        }
    }

    /**
     * Removes the CharData object from the list if the given character exists in one
     * of the CharData objects. Otherwise, does nothing.
     */
    public boolean remove(char chr) {
        Node currNode = first;
        Node prevNode = null;
        while (currNode != null && !currNode.cd.equals(chr)) {
            prevNode = currNode;
            currNode = currNode.next;
        }
        if (currNode == null) {
            return false;
        }
        if (prevNode == null) {
            first = first.next;
        } else {
            prevNode.next = currNode.next;
        }
        size--;
        return true;
    }

    /**
     * Retrieves the CharData object at the specified index in this list.
     * Throws an IndexOutOfBoundsException if the index is negative or greater than
     * the size of this list.
     */
    public CharData get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node current = first;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current.cd;
    }

    /**
     * Generates an array of CharData objects, containing all CharData objects in
     * this list.
     */
    public CharData[] toArray() {
        CharData[] arr = new CharData[size];
        Node current = first;
        int i = 0;
        while (current != null) {
            arr[i++] = current.cd;
            current = current.next;
        }
        return arr;
    }

    /**
     * Creates an iterator over the elements in this list, starting at the given
     * index.
     */
    public ListIterator listIterator(int index) {
        if (size == 0){
            return null;
        }
        Node current = first;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return new ListIterator(current);
    }

    /** Generates a textual representation of this list. */
    public String toString() {
        String str = "(";
        Node current = first;
        while (current != null) {
            str += current.cd.toString();
            current = current.next;
            if (current != null)
                str += " ";
        }
        str += ")";
        return str;
    }
}
