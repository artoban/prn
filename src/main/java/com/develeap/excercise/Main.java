package com.develeap.excercise;

import com.develeap.excercise.action.*;
import java.io.File;


public class Main {

  public static void main(String[] args) {
    try {
      new Main().go(args[0]);
    }
    catch(Exception ex){
      System.out.println("The name of the rpn file is not supplied");
    }
  }

  private void go(String fname) throws Exception {

    File f = new File(fname);
    System.out.println(f + (f.exists()? " is found " : " is missing "));

    if(!f.exists()) {
      return;
    }

    Parser.ReadFileLineByLine(fname);

    Analizer.MakeReport();


//    System.out.println("The most productive authors are:");
//    System.out.println(" - John Doe (? titles)");
//    System.out.println(" - Jane Doe (? titles)");
//    System.out.println(" - Jim Doe (? titles)");
  }

}
