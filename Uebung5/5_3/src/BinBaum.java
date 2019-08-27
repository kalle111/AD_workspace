import java.util.Arrays;

public class BinBaum {
    public int[] intArray;
    public Node rootNode = new Node(intArray[0]);


    public BinBaum (int... intArray) {
        this.intArray = intArray;
        initBinBaum();
    }

    public void initBinBaum() {
        for(int counter = 1; counter < intArray.length; counter++) {
            Node neu = new Node(intArray[counter]);
            Node temp = rootNode;

            if(intArray.length <= 1) {
                return;
            }

            while(true) {
                if(neu.knotenWert <= temp.knotenWert) {
                    if(!(temp.leftChild == null)) {
                        temp = temp.leftChild;
                        continue;
                    } else {
                        addLeft(temp, neu);
                        break;
                    }
                }
                if(neu.knotenWert > temp.knotenWert) {
                    if(!(temp.rightChilde == null)) {
                        temp = temp.rightChilde;
                    } else {
                        addRight(temp, neu);
                        break;
                    }
                }
            }
        }
    }
    public boolean rootEqualsNodeToDelete(Node nodeToDelete) {
        if(nodeToDelete.equals(this.rootNode)){
            // Es entsteht:
            // a.) zwei neue Bäume
            // b.) Baum wird mit bestehenden werten neu initialisiert
            this.intArray = Arrays.copyOfRange(intArray, 1, intArray.length-1);
            initBinBaum();
            return true;
        } else {
            return false;
        }
    }

    public void findDeleteNodeRecursive(Node nodeToDelete, Node nodeToCompare) {
            nodeToCompare = rootNode;

            if(rootEqualsNodeToDelete(nodeToDelete)){
                //root wurde exkludiert und baum neu aufgestellt
                return;
            }
            if(nodeToCompare.equals(nodeToDelete)){
                nodeToDelete.fatherNode.rightChilde = nodeToDelete.rightChilde;
                nodeToDelete.fatherNode.leftChild = nodeToDelete.leftChild;
                nodeToDelete.leftChild.fatherNode = nodeToDelete.fatherNode;
                nodeToDelete.rightChilde.fatherNode = nodeToDelete.fatherNode;
                return;
            } else if(nodeToDelete.knotenWert <= nodeToCompare.knotenWert){
                findDeleteNodeRecursive(nodeToDelete, nodeToCompare.leftChild);
            } else if(nodeToDelete.knotenWert > nodeToCompare.knotenWert) {
                findDeleteNodeRecursive(nodeToDelete, nodeToCompare.rightChilde);
            }
    }

    public void findNoteIterative(Node nodeToDelete) {
        boolean nodeFound = false;
        Node temp = rootNode;

        if(rootEqualsNodeToDelete(nodeToDelete)){
            //root wurde exkludiert und baum neu aufgestellt
            return;
        }
        while(temp.leftChild != null && temp.rightChilde != null) {
            // If both childs == null -> Abbruch Node not existent
            if(nodeToDelete.equals(temp)) {
                nodeToDelete.fatherNode.rightChilde = nodeToDelete.rightChilde;
                nodeToDelete.fatherNode.leftChild = nodeToDelete.leftChild;
                nodeToDelete.leftChild.fatherNode = nodeToDelete.fatherNode;
                nodeToDelete.rightChilde.fatherNode = nodeToDelete.fatherNode;
                break;
            } else {
                if(nodeToDelete.knotenWert <= temp.knotenWert) {
                    temp = temp.leftChild;
                }
                if (nodeToDelete.knotenWert > temp.knotenWert) {
                    temp = temp.rightChilde;
                }
            }
        }
    }

    public void addLeft(Node f, Node lc) {
        lc.fatherNode = f;
        f.leftChild = lc;
    }

    public void addRight(Node f, Node rc) {
        rc.fatherNode = f;
        f.rightChilde = rc;
    }
}
