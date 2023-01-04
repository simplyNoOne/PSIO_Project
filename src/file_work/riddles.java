package file_work;

import java.io.*;
import java.util.ArrayList;
import java.util.*;
import data.*;

public class riddles {
  public void read(ArrayList<Puzzle> obj) {
    try {
      FileInputStream fos = new FileInputStream("riddles.txt");
      ObjectInputStream out = new ObjectInputStream(fos);
      ArrayList<Puzzle> obj1 = ((ArrayList<Puzzle>) out.readObject());
      obj.addAll(obj1);
      out.close();
      fos.close();

    } catch (ClassNotFoundException | IOException e) {
      e.printStackTrace();
    }

  }

}
