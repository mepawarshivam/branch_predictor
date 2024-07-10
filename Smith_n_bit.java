public class Smith_n_bit extends Branch_predictor {
  int no_of_counter_bits;
  int b;
  public Smith_n_bit(int no_of_counter_bits) {
    this.no_of_counter_bits = no_of_counter_bits;
    this.b = this.taken = (int) Math.pow(2, no_of_counter_bits - 1);
    this.max_val = (int) Math.pow(2, no_of_counter_bits) - 1;
    this.accesses = 0;
    this.mispredictions = 0;
  }

  void make_prediction(String PC, boolean a) {
    this.accesses++; //update the number of predictions
    boolean prediction = false;
    //make prediction
    if (b >= taken) prediction = true;
    //update the counter based on actual outcome
    if (a == true && b < max_val) b++;
    else if (a == false && b > 0) b--;

    if (prediction != a) this.mispredictions++;
  }
  //print final contents
  void print_final_content() {
    System.out.print("FINAL COUNTER CONTENT:\t" + b);
  }

}