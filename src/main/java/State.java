import java.util.Random;

public class State {
    private int money = 100000000;
    private int turn_counter = 0;
    private City c1 = new City(400000,0.3,10,true);
    private City c2 = new City(21500000, 0, 0, false);
    private City c3 = new City(3500000, 0, 0,false);
    private City c4 = new City(280000, 0, 0,false);

    State(){
    }

    void change_money(int money_spent){
        money = money - money_spent;
    }
    void change_global_growth(double growth_in){
        c1.change_growth_rate(growth_in);
        c2.change_growth_rate(growth_in);
        c3.change_growth_rate(growth_in);
        c4.change_growth_rate(growth_in);
    }
    void change_city_growth(double growth_in, City city){
        city.change_growth_rate(growth_in);
    }
    void change_transfer_factor(double factor_in, City city){
        city.change_transfer_factor(factor_in);
    }

    int get_turn_count(){
        return turn_counter;
    }

    City get_city1(){
        return c1;
    }
    City get_city2(){
        return c2;
    }
    City get_city3(){
        return c3;
    }
    City get_city4(){
        return c4;
    }

    void turn() {
        turn_counter++;
        Random number = new Random();
        int spreadNewCity2 = number.nextInt(1000);
        int spreadNewCity3 = number.nextInt(1000);
        int spreadNewCity4 = number.nextInt(1000);

        if (((c1.get_percent_infected()*c1.get_transfer_factor() + c2.get_percent_infected()*c2.get_transfer_factor() + c3.get_percent_infected()*c3.get_transfer_factor() + c4.get_percent_infected()*c4.get_transfer_factor())*1000) > spreadNewCity2 && !c2.get_infection_start()) {
            c2.change_infection_start(true);
            System.out.println("City 2 has been infected! ------> " + spreadNewCity2);
            c2.change_growth_rate(0.25);
            c2.change_infected(10);
        }

        if (((c1.get_percent_infected()*c1.get_transfer_factor() + c2.get_percent_infected()*c2.get_transfer_factor() + c3.get_percent_infected()*c3.get_transfer_factor() + c4.get_percent_infected()*c4.get_transfer_factor())*1000) > spreadNewCity3 && !c3.get_infection_start()) {
            c3.change_infection_start(true);
            System.out.println("City 3 has been infected! ------> " + spreadNewCity3);
            c3.change_growth_rate(0.25);
            c3.change_infected(10);
        }

        if (((c1.get_percent_infected()*c1.get_transfer_factor() + c2.get_percent_infected()*c2.get_transfer_factor() + c3.get_percent_infected()*c3.get_transfer_factor() + c4.get_percent_infected()*c4.get_transfer_factor())*1000) > spreadNewCity4 && !c4.get_infection_start()) {
            c4.change_infection_start(true);
            System.out.println("City 4 has been infected! ------> " + spreadNewCity4);
            c4.change_growth_rate(0.25);
            c4.change_infected(10);
        }


        System.out.println("\n");

        System.out.println("Day " + turn_counter);

        if(c1.get_infection_start()) {
            c1.turn();
            System.out.println("City 1:");
            c1.print_info();
        }

        if (c2.get_infection_start()) {
            c2.turn();
            System.out.println("City 2:");
            c2.print_info();
        }

        if (c3.get_infection_start()) {
            c3.turn();
            System.out.println("City 3:");
            c3.print_info();
        }

        if (c4.get_infection_start()) {
            c4.turn();
            System.out.println("City 4:");
            c4.print_info();
        }
    }
    boolean end_status(){
        return !c1.status_check() || !c2.status_check() || !c3.status_check() || !c4.status_check();
    }
    void print_map(){
        System.out.println("--------------------------------\n");
        System.out.println("     " + (int)(c1.get_percent_infected()*100) + "------------------"  + (int)(c1.get_percent_infected()*100) + "     ");
        for(int i = 0;i < 2;i++){
            System.out.println("      |                    |      ");
        }
        System.out.println("     " + (int)(c3.get_percent_infected()*100) + "------------------"  + (int)(c4.get_percent_infected()*100) + "     \n");
        System.out.println("--------------------------------\n");
    }
}
