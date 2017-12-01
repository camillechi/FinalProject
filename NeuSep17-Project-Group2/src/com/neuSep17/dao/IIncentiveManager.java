package com.neuSep17.dao;

import com.neuSep17.dto.Dealer;
import com.neuSep17.dto.Incentive;
import com.neuSep17.dto.Vehicle;

import java.util.ArrayList;

public interface IIncentiveManager {

    //get incentives for a dealer
    ArrayList<Incentive> getIncentivesForDealer(Dealer dealer);
    //get a specific incentive by passing a unique incentive id
    Incentive getAIncentive(String incentiveID);
    //add a specific incentive
    boolean addAIncentive(String dealerID, Incentive incentive);
    //update a specific incentive
    boolean updateAIncentive(String dealerID, Incentive incentive);
    //delete a specific incentive
    boolean deleteAIncentive(String dealerID, Incentive incentive);
}
