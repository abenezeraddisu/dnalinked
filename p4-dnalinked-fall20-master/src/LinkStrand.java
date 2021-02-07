public class LinkStrand implements IDnaStrand {

    private class Node {
        String info;
        Node next;
        public Node(String s) {
            info = s;
            next = null;
        }
    }
    private Node myFirst,myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private int LocalIndex;
    private Node myCurrent;

    public LinkStrand(String dna){
        initialize(dna);
    }

    public LinkStrand(){

        this("");
    }

    @Override
    public String toString() {
        StringBuilder allDNA = new StringBuilder();
        Node list = this.myFirst;
        while(list != null){
            allDNA.append(list.info);
            list = list.next;
        }
        return allDNA.toString();
    }

    @Override
    public long size() {
        return mySize;
    }

    @Override
    public void initialize(String source) {
        Node holup = new Node(source);
        myFirst = holup;
        myLast = myFirst;
        LocalIndex = 0;
        myIndex = 0;
        mySize = source.length();
        myAppends = 0;
        myCurrent = myFirst;
    }

    @Override
    public IDnaStrand getInstance(String source) {

        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        Node added = new Node(dna);
        myLast.next = added;
        myLast = myLast.next;
        mySize += dna.length();
        myAppends++;
        return this;
    }

    @Override
    public IDnaStrand reverse() {
            StringBuilder lastInfo = new StringBuilder(myLast.info);
            LinkStrand output = new LinkStrand(lastInfo.reverse().toString());
            Node prev = null;
            Node current = myFirst;

            while(current.next != null){
                StringBuilder tempInfo = new StringBuilder(current.info);
                Node temp = new Node(tempInfo.reverse().toString());
                temp.next= prev;
                prev = temp;
                current = current.next;
            }

            while(prev != null){
                output.append(prev.info);
                prev = prev.next;
            }

            return output;

    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        if(index<0) throw new IndexOutOfBoundsException();
        if(index>=mySize) throw new IndexOutOfBoundsException();
        if(index<0) throw new IndexOutOfBoundsException();
        if(myIndex > index) {
            myIndex = 0;
            LocalIndex = 0;
            myCurrent = myFirst;
        }
        while (myIndex != index) {
            myIndex++;
            LocalIndex++;
            if (LocalIndex >= myCurrent.info.length()) {
                LocalIndex = 0;
                myCurrent = myCurrent.next;
            }
        }
        return myCurrent.info.charAt(LocalIndex);
    }
}
