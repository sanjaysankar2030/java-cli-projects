class Book{
    String title;
    String author;
    boolean isCheckout;
    public Book(String title, String author){
        this.title=title;
        this.author=author;
        this.isCheckout=false;
    }
    public void chechout(){
        if (isCheckout){
            System.out.println("Checked out "+ title );
        }
        else{
            System.out.println("Not Checking out");
            isCheckout=true;
            System.out.println("you can check it out ");
        }
    }
    public void returnBook(){
        if (isCheckout){
            System.out.println("Return the book "+ title );
            isCheckout=false;
        }
        else{
            System.out.println("Aldready Returned");
        }
    }
    public void display(){
        System.out.println("Title:"+ title + "Author"+ author );
    }
}
