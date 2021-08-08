/**
 * class to hold employee information
 */

package Employee_Info;

import Encryption.Encrypt;

import java.io.Serializable;

public class EInfo implements Serializable {
    private String id;
    private String password;

    /**
     * sets employee id
     * @param id employee id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * takes the password entered by users and encrypts it before assigning it
     * @param password the encrypted password
     */
    public void setPassword(String password) {
        this.password = Encrypt.encrypted(password);
    }

    /**
     * returns the id
     * @return employee id
     */
    public String getId() {
        return id;
    }

    /**
     * returns the encrypted password
     * @return the encrypted password
     */
    public String getPassword() {
        return password;
    }

}
