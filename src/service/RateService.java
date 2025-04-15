package service;

import models.Driver;
import models.Passenger;
import models.Rate;
import models.Ride;
import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RateService {

    ArrayList<Rate> rates = new ArrayList<>();
    private static final Logger logger = LoggerUtil.getLogger(RateService.class);

    public void rateDriver(Ride ride, Passenger passenger, Driver driver, int rate) {
        if (checkRate(ride)) {
            logger.warning("Rate is already done.");
            return;
        }

        if (rate < 1 || rate > 5) {
            logger.warning("Rate must be between 1 and 5.");
            return;
        }

        Rate newRate = new Rate(ride, passenger, driver, rate);
        rates.add(newRate);
        logger.info("Rate is added successfully");
    }

    public boolean checkRate(Ride ride) {
        boolean exist = false;
        for (Rate rate : rates) {
            if (rate.getRide().equals(ride)) {
                exist = true;
                return exist;
            }
        }
        return exist;
    }

    public ArrayList<Rate> viewAllRates() {
        return rates;
    }

    public double getRatingsAvg(Driver driver) {
        int total = 0;
        int count = 0;
        double ratesAvg;

        for (Rate rate : rates) {
            if (rate.getDriver().equals(driver)) {
                total += rate.getRate();
                count++;
            }
        }

        if (total == 0) {
            logger.warning("This driver has not been rated yet.");
            return 0;
        }
        return (double) total / count;
    }

}
