package dict;

public class EntryChained extends Entry {
    private EntryChained next;

    EntryChained(){
        super();
        next = null;
    }

    public EntryChained getNext() {
        return next;
    }

    public void setNext(EntryChained next) {
        this.next = next;
    }
}
