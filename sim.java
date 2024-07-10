import java.io.File;
import java.util.Scanner;

class sim {
  public static void main(String[] args) {
    try {
      Branch_predictor predictor;
      File trace;

      if (args[0].equals("smith")) {
        predictor = new Smith_n_bit(Integer.parseInt(args[1]));
        trace = new File(args[2]);
      } else if (args[0].equals("bimodal")) {
        predictor = new Gshare_bimodal(Integer.parseInt(args[1]));
        trace = new File(args[2]);
      } else {
        predictor = new Gshare_m_n(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        trace = new File(args[3]);
      }

      Scanner scan = new Scanner(trace);
      while (scan.hasNextLine()) {

        String instruction = scan.nextLine();
        String[] tokens = instruction.split(" ");

        if (tokens[1].equals("t")) predictor.make_prediction(tokens[0], true);
        else predictor.make_prediction(tokens[0], false);
      }

      scan.close();

      double misprediction_rate = predictor.mispredictions / (double) predictor.accesses * 100;

      //print results
      System.out.println("output");
      System.out.println("no. of predictions:\t" + predictor.accesses);
      System.out.println("no. of mispredictions:\t" + predictor.mispredictions);
      System.out.println("misprediction rate :\t" + String.format("%.2f", misprediction_rate) + "%");
      predictor.print_final_content();
    } catch (Exception e) {
      System.out.println("an error occurred: ");
      e.printStackTrace();
    }
  }
}

