package yarnshop.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInForm {

    @NotBlank(message = "Please enter username")
    @Size(min = 3, max = 40, message = "Wrong format, username must be from 3 to 40 characters")
    private String username;

    @NotBlank(message = "Please enter password")
    @Size(min = 5, max = 40, message = "Wrong format, password must be from 5 to 40 characters")
    private String password;

    public SignInForm() {
    }

    public SignInForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
