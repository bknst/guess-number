package by.bknst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class GameImpl implements Game{
    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    private NumberGenerator numberGenerator;
    private int guessCount;
    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int largest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    @PostConstruct
    @Override
    public void reset() {
        smallest = 0;
        guess = 0;
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.info("the number is {}", number);
    }

    @PreDestroy
    public void preDestroy(){
        log.info("in Game preDestroy()");
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public void check() {
        checkValidNumberRange();
        if(validNumberRange){
            if(guess > number){
                biggest = guess - 1;
            }
            if(guess < number){
                smallest = guess + 1;
            }

            remainingGuesses--;
        }
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}