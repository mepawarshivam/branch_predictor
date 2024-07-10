public abstract class Branch_predictor{
    int accesses;
    int mispredictions;
    int max_val;
    int taken;
    
    abstract void make_prediction(String PC, boolean a); //a = actual branch prediction outcome
    abstract void print_final_content();
}
