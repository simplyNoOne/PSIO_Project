package main;

abstract public class State {

    abstract public void update(double dt);
    abstract public void nextState();
}
