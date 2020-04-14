public class BinarySearchTree<D> {

    private class BinarySearchTreeNode{

        private class Data{
            int key;
            D value;

            private Data(int key, D value) {
                this.key = key;
                this.value = value;
            }

            private int getKey() {
                return key;
            }

            private D getValue() {
                return value;
            }
        }
        private Data data;
        private BinarySearchTreeNode left = null;
        private BinarySearchTreeNode right = null;
        private BinarySearchTreeNode parent = null;

        public BinarySearchTreeNode() {}

        public BinarySearchTreeNode left() {
            return left;
        }

        public void setLeft(BinarySearchTreeNode left) {
            this.left = left;
            this.left.setParent(this);
        }

        public BinarySearchTreeNode right() {
            return right;
        }

        public void setRight(BinarySearchTreeNode right) {
            this.right = right;
            this.right.setParent(this);
        }

        public BinarySearchTreeNode parent() {
            return parent;
        }

        private void setParent(BinarySearchTreeNode parent) {
            this.parent = parent;
        }

        public int key(){
            return this.data.getKey();
        }

        public D value(){
            return this.data.getValue();
        }

        public void setData(int key, D value) {
            this.data = new Data(key, value);
        }
    }

    private final BinarySearchTreeNode top = new BinarySearchTreeNode();

    public BinarySearchTree(int key, D value){
        this.top.setData(key, value);
    }

    public void insertNode(int key, D value){
        BinarySearchTreeNode node = findParent(key, this.top);
        if (node.key() != key) node = null;
        if (node != null) node.setData(key, value);
        else {
            BinarySearchTreeNode parent = findParent(key, this.top);
            BinarySearchTreeNode newNode = new BinarySearchTreeNode();
            if (parent.key() > key) {
                parent.setLeft(newNode);
            } else {
                parent.setRight(newNode);
            }
            newNode.setParent(parent);
        }
    }

    public void removeNode(int key){

        BinarySearchTreeNode node = findParent(key, this.top);
        if (node.key() != key) node = null;

        if (node == null) return;
        else {
            if (node.right() == null && node.left() == null){
                if (node.parent().left() == node) node.parent().setLeft(null);
                if (node.parent().right() == node) node.parent().setRight(null);
                node = null;
            }
            else if (node.right() == null || node.left() == null){
                BinarySearchTreeNode son = node.right() == null ? node.left() : node.right();
                node.setRight(son.right());
                node.setLeft(son.left());
                node.setData(son.key(), son.value());
                son = null;
            }
            else {
                if (node.right().left() == null) node.setRight(node.right().right());
                else{
                    BinarySearchTreeNode maxLeft = node.right().left();
                    while (maxLeft.left() != null) maxLeft = maxLeft.left();
                    removeNode(maxLeft.key());
                }
            }
        }
    }

    public D findValue(int key){
        BinarySearchTreeNode node = findParent(key, this.top);
        if (node.key() == key) return node.value();
        return null;
    }

    private BinarySearchTreeNode findParent(int key, BinarySearchTreeNode node){
        if (node.key() == key) return node;
        if (node.key() > key)
        {
            if (node.left() != null)
            findParent(key, node.left());
        }
        if (node.key() < key)
        {
            if (node.right() != null)
            findParent(key, node.right());
        }
        return node;
    }
}