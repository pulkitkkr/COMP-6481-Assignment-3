//______________________________________________________________________________________________________________________
// Programming Assignment 3
// Team Members
// 1. Manish Sehgal - 40165366
// 2. Pulkit Kakkar - 40160971
//
//______________________________________________________________________________________________________________________

public class AVLTree extends SIDCObjects{
    Node root = null;

    String inOrderTraversal = "";
    int count;
    int idCounter = 0;

    class Node {
        int SIDC, height;
        String data;
        Node left, right;

        Node(int SIDC, String data) {
            this.SIDC = SIDC;
            this.data = data;
            height = 1;
        }

        public void printNode(){
            String padded = String.format("%08d" , this.SIDC);
            System.out.println("--- SIDC:"+padded+" ---");
            System.out.println(data+"\n");
        }
    }

    private int getHeight(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }

    private Node insert(Node node, int key, String value) {
        if (node == null){
            this.count++;
            return (new Node(key, value));
        }

        if (key < node.SIDC){
            node.left = insert(node.left, key, value);
        } else if (key > node.SIDC){
            node.right = insert(node.right, key, value);
        }else
            return node;

        node.height = 1 + Math.max(getHeight(node.left),
                getHeight(node.right));


        int balance = getBalanceFactor(node);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && key < node.left.SIDC)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.SIDC)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.SIDC) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.SIDC) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int getBalanceFactor(Node N) {
        if (N == null)
            return 0;

        return getHeight(N.left) - getHeight(N.right);
    }

    private Node getMinValueElement(Node node)
    {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    Node deleteNode(Node root, int key)
    {
        if (root == null){
            return root;
        }

        if (key < root.SIDC){
            root.left = deleteNode(root.left, key);
        } else if (key > root.SIDC) {
            root.right = deleteNode(root.right, key);
        } else {
        	
            if ((root.left == null) || (root.right == null))
            {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else
                    root = temp;
                
                this.count--;
            	System.out.println("successfully deleted !!");
            }
            else
            {

                Node temp = getMinValueElement(root.right);

                root.SIDC = temp.SIDC;
                root.data = temp.data;

                root.right = deleteNode(root.right, temp.SIDC);
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;

        int balance = getBalanceFactor(root);

        if (balance > 1 && getBalanceFactor(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalanceFactor(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalanceFactor(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalanceFactor(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            node.printNode();
            printInOrder(node.right);
        }
    }

    private Node findNode(Node node, int sidc) {
        if(node == null){
            return null;
        }

        if(node.SIDC == sidc){
            return node;
        } else {
            if(sidc < node.SIDC){
                return findNode(node.left, sidc);
            } else {
                return findNode(node.right, sidc);
            }
        }
    }

    public int generate() {
    	if(this.root == null) {
    		 this.idCounter++;
    		return 0;
    	}

        Node p = findNode(this.root, this.idCounter);

        
        while(p != null){
            this.idCounter++;
            p = findNode(this.root, this.idCounter);
        }

        return this.idCounter;

    }

    public void allKeys() {
        printInOrder(this.root);
    }

    public void add(int sidc, String value) {
        this.root = insert(this.root, sidc, value);
    }

    public void remove(int sidc) {
        deleteNode(this.root, sidc);
    }

    public String getValues(int sidc) {
        Node temp = findNode(this.root, sidc);

        if(temp == null){
            System.out.println("The Key: "+sidc+" is not present.");
            return "";
        }

        return temp.data;
    }

    void saveInOrder(Node node) {
        if (node != null) {

            saveInOrder(node.left);
            this.inOrderTraversal += node.SIDC+",";
            saveInOrder(node.right);

        }
    }

    public int nextKey(int sidc) {
        inOrderTraversal = "";
        saveInOrder(this.root);

        int[] arr = getInOrderArr();
        int index = findIndexInSortedArr(arr, sidc);

        if(index == arr.length-1){
            System.out.println("The Key: "+sidc+" is last key in List.");
            return -1;
        } else if(index == -1){
            System.out.println("The Key: "+sidc+" is not present.");
            return Integer.MIN_VALUE;
        } else {
            return (arr[index+1]);
        }
    }

    public int[] getItemsInArr() {
      return getInOrderArr();
    }

    @Override
    public int getSize() {
        return this.count;
    }

    public int[] getInOrderArr() {
        this.inOrderTraversal = "";
        saveInOrder(this.root);



        String[] arr = this.inOrderTraversal.split(",");
        int[] items = new int[arr.length];

        for(int i = 0; i < arr.length; i++){
            items[i] = Integer.parseInt(arr[i]);
        }

        return items;
    }

    int findIndexInSortedArr(int[] arr, int key){
        int left = 0, right = arr.length - 1;

        while (left <= right)
        {
            int mid = (left + right) / 2;
            if (key == arr[mid]) {
                return mid;
            }

            else if (key < arr[mid]) {
                right = mid - 1;
            }

            else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int prevKey(int sidc) {
        int[] arr = getInOrderArr();
        int index = findIndexInSortedArr(arr, sidc);

        if(index == 0){
            System.out.println("The Key: "+sidc+" is first key in List.");
            return -1;
        } else if(index == -1){
            System.out.println("The Key: "+sidc+" is not present.");
            return Integer.MIN_VALUE;
        } else {
            return (arr[index-1]);
        }
    }

    public int rangeKey(int key1, int key2) {
        int[] arr = getInOrderArr();

        int index1 = findIndexInSortedArr(arr, key1);
        int index2 = findIndexInSortedArr(arr, key2);
        
        if(index1== -1|| index2 == -1) {
        	System.out.println("One of the Key doesn't exists in DB");
        	return 0;
        }

        return index2 - index1 + 1;
    }
}
