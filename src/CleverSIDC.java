//______________________________________________________________________________________________________________________
// Programming Assignment 3
// Team Members
// 1. Manish Sehgal - 40165366
// 2. Pulkit Kakkar - 40160971
//
//______________________________________________________________________________________________________________________


class CleverSIDC {
    SIDCObjects sidcHandler;
    int size;
    int INSERT_THRESHOLD = 5;

    public CleverSIDC(int size){
        this.size = this.INSERT_THRESHOLD = size;
        this.sidcHandler = new SortedDoublyLinkedList();
    }

    private void handleThresholdChange(){
        System.out.println("Size = "+sidcHandler.getSize());
        // Convert AVL to DoublyLinkedList
        if(sidcHandler.getSize()==INSERT_THRESHOLD-1 && this.sidcHandler instanceof AVLTree){
            System.out.println("Value Less Than Threshold: "+INSERT_THRESHOLD+". Using Sorted Doubly Linked List Now");
            int[] items = sidcHandler.getItemsInArr();

            SIDCObjects newHandler = new SortedDoublyLinkedList();
            for(int i = 0; i< items.length; i++){
                System.out.println(items[i]);
                newHandler.add(items[i], "Data for "+items[i]);
            }

            this.sidcHandler = newHandler;
        }

        if(sidcHandler.getSize()==INSERT_THRESHOLD){
            if(this.sidcHandler instanceof SortedDoublyLinkedList){
                System.out.println("Value Greater Than Threshold: "+INSERT_THRESHOLD+". Using AVL Trees Now");
                int[] items = sidcHandler.getItemsInArr();
                SIDCObjects newHandler = new AVLTree();

                for(int i = 0; i< items.length; i++){
                    newHandler.add(items[i], "Data for "+items[i]);
                }

                this.sidcHandler = newHandler;
            }
        }
    }
    public void SetSIDCThreshold(int size) {
        this.size = this.INSERT_THRESHOLD = size;
        handleThresholdChange();
    }

    public int generate(){
        return sidcHandler.generate();
    }

    public void allKeys(CleverSIDC sidc) {
        sidc.sidcHandler.allKeys();
    }

    public void add(CleverSIDC sidc, int SIDC, String value){
        sidc.sidcHandler.add(SIDC, value);
        handleThresholdChange();
    }

    public void remove(CleverSIDC sidc, int SIDC){
        sidc.sidcHandler.remove(SIDC);
        handleThresholdChange();
    }

    public String getValues(CleverSIDC sidc, int SIDC){
        return sidc.sidcHandler.getValues(SIDC);
    }

    public int nextKey(CleverSIDC sidc, int SIDC){
        return sidc.sidcHandler.nextKey(SIDC);
    }

    public int prevKey(CleverSIDC sidc, int SIDC){
        return sidc.sidcHandler.prevKey(SIDC);
    }

    public int rangeKey(CleverSIDC sidc, int key1, int key2){
        return sidc.sidcHandler.rangeKey(key1, key2);
    }
}