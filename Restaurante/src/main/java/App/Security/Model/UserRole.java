package App.Security.Model;

public enum UserRole {
    ADMIN("admin"),
    ATENDIMENTO("atendimento"),
    COZINHA("cozinha");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
