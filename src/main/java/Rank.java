public enum Rank {
    NORMAL(1),
    IN_PARENTHESES(2);


    private int id;
    private Rank(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }


}
