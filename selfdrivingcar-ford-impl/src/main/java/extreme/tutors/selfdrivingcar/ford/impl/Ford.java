/*
 * The MIT License
 *
 * Copyright 2021 Omar Rashwan.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package extreme.tutors.selfdrivingcar.ford.impl;

import extreme.tutors.selfdrivingcar.api.Car;

/**
 *
 * @author Omar Rashwan
 * @date : 14-08-2021 12:00 AM
 */
public class Ford implements Car {

    private final int accelerationRate = 10;
    private final int speedLimit = 360;
    private int speed;
    private boolean started = false;

    @Override
    public void start() {
        started = true;
        speed = 0;
        System.out.println("Ford Car Started");
    }

    @Override
    public void accelerate() {
        if (speed == this.speedLimit) {
            System.out.println("Ford can not accelerate, "
                    + "speed now is at maximum speed limit " + this.speed);
            return;
        }
        this.speed += this.accelerationRate;
        System.out.println("Speed now is " + this.speed);
    }

    @Override
    public void brake() {
        for (int i = this.speed; i >= 0; i -= this.accelerationRate) {
            System.out.println("braking Ford car speed is : " + this.speed);
        }
    }

    @Override
    public void stop() {
        if (!started) {
            System.out.println("Ford already stopped");
        }
        if (this.speed != 0) {
            System.out.println("Ford can not stop while the car is moving,"
                    + " please brake first");
        } else {
            System.out.println("Ford now stopped");
            this.started = false;
        }
    }
}
