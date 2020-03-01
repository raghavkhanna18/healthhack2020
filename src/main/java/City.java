import java.util.ArrayList;
import java.util.List;

public class City {
    private double infected;
    private double initial_population;
    private double growth_rate;
    private double dead = 0;
    private double percent_infected;
    private double transfer_factor = 1;
    private List<Double> infection_history = new ArrayList<>();
    private boolean infection_start;
    private boolean cured = false;
    private boolean fully_infected = false;

    City(int pop_in, double growth_in, double infected_in, boolean infection_start_in){
        this.initial_population = pop_in;
        this.growth_rate = growth_in;
        this.infected = infected_in;
        this.infection_start = infection_start_in;
    }

    public void turn(){
        status_check();
        if (!status_check()) {
            infection_growth();
        }
        if (!fully_infected)
            change_deaths();
    }

    public boolean status_check(){
        return cured || fully_infected;
    }
    private void change_deaths(){
        if (infection_history.size() > 14){
            double death_rate = 0.035;
            dead = dead + (infection_history.get(infection_history.size() -14) - infection_history.get(infection_history.size() -15)) * death_rate;
            infected = infected - (infection_history.get(infection_history.size() -14) - infection_history.get(infection_history.size() -15)) * death_rate;
        }
    }
    private void infection_growth() {
        infection_history.add(infected);
        infected =  (infected + (infected * growth_rate));
        if (infection_history.size() > 14 && infected > 0 && (infection_history.get(infection_history.size() -14) - infection_history.get(infection_history.size() -15) > 0 )) {
            infected = infected - (infection_history.get(infection_history.size() -14) - infection_history.get(infection_history.size() -15));
        }
        if (infected >= get_population()) {
            infected = get_population();
            fully_infected = true;
        }
        else if (infected <= 0) {
            infected = 0;
            cured = true;
        }
    }

    public void change_infected(double infected_in){
        this.infected = infected_in;
    }
    public void change_growth_rate(double new_rate_in){
        this.growth_rate = this.growth_rate * (1 + new_rate_in);
    }
    public void change_transfer_factor(double transfer_factor_in){
        this.transfer_factor = transfer_factor_in;
    }
    public void change_infection_start(boolean infection_start_in){
        infection_start = infection_start_in;
    }


    public boolean get_infection_start(){
        return infection_start;
    }
    public double get_percent_infected(){
        if (cured)
            return 0;
        else if (fully_infected)
            return 1;
        return (infected/get_population());
    }
    public double get_infected(){
        return Math.floor(infected);
    }
    public double get_population(){
        return initial_population - Math.floor(dead);
    }
    public double get_dead() {
        return Math.floor(dead);
    }
    public double get_transfer_factor(){
        return transfer_factor;
    }

    public void print_info(){
        System.out.println("Population = " + (int) Math.floor(get_population()));
        System.out.println("Infected = " + (int) Math.floor(infected) + " ----> " + String.format("%.4f", get_percent_infected()*100) + "%");
        System.out.println("Dead = " + (int) Math.floor(dead) + "\n");
    }
}