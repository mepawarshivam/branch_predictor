import java.lang.Math;
public class Gshare_bimodal extends Branch_predictor {
  int m;
  int[] b;

  public Gshare_bimodal(int m) {
    this.m = m;
    this.accesses = 0;
    this.mispredictions = 0;
    this.b = new int[(int) Math.pow(2, m)];
    this.max_val = 7;
    this.taken = 4;

    for (int i = 0; i < b.length; i++) {
      b[i] = 4;
    }
  }

  void make_prediction(String PC, boolean a) {
    this.accesses++; //update the number of predictions

    Long dec = Long.decode("0x" + PC);
    int index = (int)(dec / 4) % b.length;

    // make a prediction
    boolean prediction = false;
    if (b[index] >= taken) prediction = true;

    // update the counter based on actual outcome
    if (a == true && b[index] < max_val) b[index]++;
    else if (a == false && b[index] > 0) b[index]--;

    //update the number of mispredictions
    if (prediction != a) this.mispredictions++;
  }
  void print_final_content() {
    System.out.println("FINAL BIMODAL CONTENTS");
    for (int i = 0; i < b.length; i++) {
      System.out.println(i + "\t" + b[i]);
    }
  }

}