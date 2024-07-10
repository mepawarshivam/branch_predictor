public class Gshare_m_n extends Branch_predictor {
  int m;
  int n;
  int global_history; //n bit global branch history register
  int[] b;

  public Gshare_m_n(int m, int n) {
    this.m = m;
    this.n = n;
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
    //m bits of program counter...last two bits removed
    int m_bits = (int)(dec / 4) % b.length;
    //n least significant bits of program counter
    int n_bits = (int)(dec / 4) % ((int) Math.pow(2, n));

    // m-n most significant bits
    int m_n_bits = (int)(m_bits / Math.pow(2, n));

    int XOR = n_bits ^ global_history; // the current n-bit global branch history register is XORed with 
    //the lowermost n bits of the index (PC) bits.

    // concatenate xor and m-n mosst significant bits of PC
    int index = m_n_bits << n;
    index = index + XOR;

    // make a prediction
    boolean prediction = false;
    if (b[index] >= taken) prediction = true;

    // update the value of counter b based on actual outcome
    if (a == true && b[index] < max_val) b[index]++;
    else if (a == false && b[index] > 0) b[index]--;

    //update the number of mispredictions
    if (prediction != a) this.mispredictions++;

    //update global history register
    global_history = global_history >> 1;
    // if the actual outcome was true, insert 1 as MSB
    if (a) global_history = global_history + (int) Math.pow(2, n - 1);
    // otherwise inserting zero is effectively adding 0;
  }
  // print final contents
  void print_final_content() {
    System.out.println("FINAL GSHARE CONTENTS");
    for (int i = 0; i < b.length; i++) {
      System.out.println(i + "\t" + b[i]);
    }
  }

}