package Spring.AOP.api;

public interface UserApi {
    public String findUser(String username);  
    public void addUser(String username);  
    public void findAll();  
}
