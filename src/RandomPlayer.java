import java.util.Random;

public class RandomPlayer extends Player{
    RandomGenerator randomGenerator;


    public RandomPlayer(String name, char mark) {
        super(name, mark);
        this.randomGenerator = new RandomGenerator();
    }

    @Override
    public void makeMove() {
        //super.makeMove();
        super.getBoard().addMark(this.randomGenerator.discrete(0, 3), this.randomGenerator.discrete(0, 3), super.getMark());
        super.getBoard().display();
    }
}
