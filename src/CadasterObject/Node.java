package CadasterObject;

class Node {
    protected REOwner owner;
    protected Node link;

    public Node() {
        link = null;
        owner = null;
    }

    public Node(REOwner e, Node n) {
        owner = e;
        link = n;
    }

    public void setLink(Node n) {
        link = n;
    }

    public void setOwner(REOwner e) {
        owner = e;
    }

    public Node getLink() {
        return link;
    }

    /*public REOwner getData() {

    }*/
}
