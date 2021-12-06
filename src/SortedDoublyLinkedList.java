//______________________________________________________________________________________________________________________
// Programming Assignment 3
// Team Members
// 1. Manish Sehgal - 40165366
// 2. Pulkit Kakkar - 40160971
//
//______________________________________________________________________________________________________________________


class SortedDoublyLinkedList extends SIDCObjects{
    Node head;
    int count;

    public SortedDoublyLinkedList(){
        this.head = null;
    }

    private Node findNode(int key){
        Node temp = this.head;
        Node result = null;

        while(temp != null){
            if(temp.SIDC == key){
                result =  temp;
                break;
            }  else if(key < temp.SIDC){
                break;
            } else {
                temp = temp.next;
            }
        }

        return result;
    }

    public int rangeKey(int key1, int key2) {
        Node temp = this.head;

        Node k1 = findNode(key1);
        Node k2 = findNode(key2);
        
        if(k1==null || k2 == null) {
        	System.out.println("One of the Key doesn't exists in DB");
        	return 0;
        }
        
        int rangeCount = 0;

        while (temp != null) {
            if(temp.SIDC >= key1 && temp.SIDC <= key2){
                rangeCount++;
            }

            if(temp.SIDC > key2){
                break;
            }
            temp = temp.next;
        }

        return rangeCount;
    }

    public int[] getItemsInArr() {
        Node temp = this.head;
        int[] arr = new int[this.count];
        int i = 0;

        while (temp != null) {
            arr[i] = temp.SIDC;
            temp = temp.next;
            i++;
        }

        return arr;
    }

    @Override
    public int getSize() {
        return count;
    }

    public String getValues(int key){
        Node temp = findNode(key);

        if(temp == null){
            System.out.println("The Key: "+key+" is not present.");
            return "";
        }

        return temp.data;
    }

    public int nextKey(int key){
        Node temp = findNode(key);

        if(temp == null){
            System.out.println("The Key: "+key+" is not present.");
            return Integer.MIN_VALUE;
        } else {
            if(temp.next == null){
                System.out.println("The Key: "+key+" is last key in List.");
                return -1;
            } else {
                return temp.next.SIDC;
            }
        }
    }

    public int prevKey(int key){
        Node temp = findNode(key);

        if(temp == null){
            System.out.println("The Key: "+key+" is not present.");
            return Integer.MIN_VALUE;
        } else {
            if(temp.prev == null){
                System.out.println("The Key: "+key+" is first key in List.");
                return -1;
            } else {
                return temp.prev.SIDC;
            }
        }
    }

    public void remove(int key){
        if(this.head == null){
            System.out.println("The Key: "+key+" is not present.");
        } else {
            Node temp = this.head;
            while(temp != null){
                if(temp.SIDC == key){
                    this.count--;
                    if(temp.prev != null){
                        temp.prev.next = temp.next;
                    }

                    if(temp.next != null){
                        temp.next.prev = temp.prev;
                    }

                    if(temp == this.head){
                        this.head = temp.next;
                    }

    	        	System.out.println(key+" successfully deleted.");

                    break;
                } else if(temp.SIDC > key){
                    System.out.println("The Key: "+key+" is not present.");
                    break;
                } else {
                    temp = temp.next;
                }
            }
        }
    }

    private void recursiveAdd(Node n, int SIDC, String value){
        if(SIDC > n.SIDC){
            if(n.next != null){
                recursiveAdd(n.next, SIDC, value);
            } else {
                Node toAdd = new Node(SIDC, value);
                n.next = toAdd;
                toAdd.prev = n;
            }
        } else if(SIDC == n.SIDC) {
            this.count--;
            System.out.println("Redundant Key "+SIDC+"Detected");
        } else {
            Node toAdd = new Node(SIDC, value);
            toAdd.next = n;
            toAdd.prev = n.prev;
            n.prev = toAdd;

            if(n == this.head){
                this.head = toAdd;
            }
        }
    }

    public void add(int SIDC, String value){
        this.count++;

        if(this.head == null){
            this.head = new Node(SIDC, value);
        } else {
            recursiveAdd(this.head, SIDC, value);
        }
    }

    public int generate(){
        return generate(this.head, 0);
    }

    public int generate(Node head, int UID){
    	if(head == null) {
    		return 0;
    	}
        if(UID < head.SIDC){
            return UID;
        } else if(UID == head.SIDC){
            return generate(head, UID+1);
        }else {
            if(head.next == null){
                return UID;
            } else {
                return generate(head.next, UID);
            }
        }
    }

    public void allKeys(){
        Node temp = head;

        while(temp != null){
            temp.printNode();

            temp = temp.next;
        }
    }

    class Node {
        Node prev, next;
        int SIDC;
        String data;

        public Node(int SIDC, String data){
            this.SIDC = SIDC;
            this.data = data;
            this.prev = null;
            this.next = null;
        }

        public void printNode(){
            String padded = String.format("%08d" , this.SIDC);
            System.out.println("--- SIDC:"+padded+" ---");
            System.out.println(data+"\n");
        }
    }

}
