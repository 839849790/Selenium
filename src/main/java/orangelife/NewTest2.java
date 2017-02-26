package orangelife;

import org.testng.annotations.Test;

public class NewTest2 {

  @Test(groups="init")
  public void a() {
      System.out.println("This is test a" );
  }
  @Test(dependsOnGroups="init",groups="proccess")
  public void b() {
      System.out.println("This is test b " );
  }
  @Test(dependsOnGroups="init",groups="proccess")
  public void c() {
      System.out.println("This is test b" );
  }

}
