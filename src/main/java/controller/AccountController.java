package controller;

import entities.Account;
import model.AccountModel;

import javax.faces.bean.*;

@ManagedBean(name = "accountController", eager = true)
@RequestScoped
public class AccountController {

    private AccountModel accountModel = new AccountModel();
    private Account account = new Account();
    private String message = "";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String login() {
        if (accountModel.login(this.account.getUsername(), this.account.getPassword()) != null) {

            return "welcome";
        } else {
            this.message = "Account's Invalid!";
            this.account = new Account();
            return "login";
        }
    }

    public String logout() {
        this.message = "";
        this.account = new Account();
        return "index";
    }

}
