package com.bins.designpattern.objectsstates.votemanager;

public class Client {

    public static void main(String[] args) {
        
        VoteManager vm = new VoteManager();
        for(int i=0;i<9;i++){
            vm.vote("u1","A");
        }
    }

}