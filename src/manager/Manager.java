package manager;

import java.util.Scanner;

public abstract class Manager<t> {
	
    public Scanner input = new Scanner(System.in);
    public DatabaseConn db = new DatabaseConn();
}
