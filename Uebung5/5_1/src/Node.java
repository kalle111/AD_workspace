public class Node {
    public int knotenWert;
    public Node fatherNode = null;
    public Node leftChild = null;
    public Node rightChilde = null;
    public int balanceFaktor;

    public Node (int i) {
        this.knotenWert = i;
    }

    public boolean hasKids(){
        if(this.leftChild == null && this.rightChilde == null) {
            return false;
        }
        else return true;
    }

    public void evaluateBalanceFactor() {
        int temp = 0;
        if(this.thisIsALeaveNode()) {
            this.balanceFaktor = 0;
        } else {
            this.balanceFaktor = recursiveChildChecker(this, temp);
        }

    }


    public boolean thisIsALeaveNode() {
        if(this.leftChild == null && this.rightChilde == null) {
            return true;
        }
        return false;
    }

    public int recursiveChildChecker(Node nodeToCheck, int balanceCounter) {
        if (this.leftChild.thisIsALeaveNode()&& this.rightChilde.thisIsALeaveNode()) {
            return balanceCounter;
        }
        if (this.leftChild.thisIsALeaveNode() && this.rightChilde.thisIsALeaveNode() == false) {
             return recursiveChildChecker(this.rightChilde, balanceCounter+1);
        }
        if (this.leftChild.thisIsALeaveNode() == false && this.rightChilde.thisIsALeaveNode()) {
            return recursiveChildChecker(this.leftChild, balanceCounter-1);
        }
        else {
             return recursiveChildChecker(this.leftChild, balanceCounter) + recursiveChildChecker(this.rightChilde, balanceCounter);
        }
    }
}
