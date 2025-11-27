package libraryy;

public class main {
	public static void main(String[] args) {
        Admin admin = new Admin("admin", "1234");
        library library = new library();

        
        admin.login("admin", "1234");

        if (admin.isLoggedIn()) {
            
            library.addBook(new Book("Little woman", "Louisa May Alcott", "111"));
            library.addBook(new Book("The Fault in our stars", "John Green", "222"));
            library.addBook(new Book("Les Miserables", "Victor Hugo", "333"));
            
            library.searchBook("Little woman");
        }

        
        admin.logout();
}
}